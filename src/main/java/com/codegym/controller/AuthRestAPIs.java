package com.codegym.controller;

import com.codegym.message.request.LoginForm;
import com.codegym.message.request.SignUpForm;
import com.codegym.message.response.JwtResponse;
import com.codegym.message.response.ResponseMessage;
import com.codegym.model.Role;
import com.codegym.model.RoleName;
import com.codegym.model.User;
import com.codegym.security.jwt.JwtProvider;
import com.codegym.security.services.UserPrinciple;
import com.codegym.service.RoleService;
import com.codegym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(),
                userDetails.getId() , userDetails.getName(), userDetails.getEmail(), userDetails.getAvatar() ,
                userDetails.getAuthorities()
        ));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleService.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(adminRole);

                    break;
                case "pm":
                    Role pmRole = roleService.findByName(RoleName.ROLE_PM)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(pmRole);

                    break;
                default:
                    Role userRole = roleService.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(userRole);
            }
        });

        user.setRoles(roles);
        userService.save(user);

        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
    }

//    @PutMapping("/update-profile/{id}")
//    public ResponseEntity<?> updateUser(@Valid @RequestBody UserForm userForm, @PathVariable Long id) {
//        Optional<User> user = userService.findById(id);
//
//        if(user == null) {
//            return new ResponseEntity<>("Can't Find User By Id" + id, HttpStatus.BAD_REQUEST);
//        }
//
//        try {
//            user.get().setName(userForm.getName());
//
//            userService.save(user.get());
//
//            return new ResponseEntity<>(new ResponseMessage("Update successful"), HttpStatus.OK);
//        } catch (Exception e ) {
//            throw new RuntimeException("Fail!");
//        }
//    }
//
//
//    @PutMapping("/update-password/{id}")
//    public ResponseEntity<?>updatePassword(@Valid @RequestBody PasswordForm passForm, @PathVariable Long id) {
//        Optional<User> user = userService.findById(id);
//
//        if (user == null ){
//            return new ResponseEntity<>(new ResponseMessage("Not found user"),HttpStatus.NOT_FOUND);
//        }
//
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(passForm.getUsername(), passForm.getCurrentPassword()));
//
//            user.get().setPassword(passwordEncoder.encode(passForm.getNewPassword()));
//
//            userService.save(user.get());
//
//            return new ResponseEntity<>(new ResponseMessage("Change password successful"),HttpStatus.OK);
//        } catch (Exception e) {
//            throw new RuntimeException("Fail!");
//        }
//    }
}
