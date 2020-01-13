//https://kipalog.com/posts/Huong-dan-lap-trinh-Spring-Security
//
package com.codegym.security.services;
import com.codegym.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserPrinciple implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String username;

    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrinciple(Long id, String name,
                         String username, String email, String password,
                         Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrinciple build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        return new UserPrinciple(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    // trả về username đã dùng trong qúa trình xác thực
    @Override
    public String getUsername() {
        return username;
    }

    //trả về password đã dùng trong qúa trình xác thực
    @Override
    public String getPassword() {
        return password;
    }

    //trả về danh sách các quyền của người dùng
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    // trả về true nếu tài khoản của người dùng chưa hết hạn
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //trả về true nếu người dùng chưa bị khóa
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // trả về true nếu chứng thực (mật khẩu) của người dùng chưa hết hạn
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //trả về true nếu người dùng đã được kích hoạt
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(id, user.id);
    }
}