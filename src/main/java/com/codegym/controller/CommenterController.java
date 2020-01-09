package com.codegym.controller;

import com.codegym.model.Commenter;
import com.codegym.model.Oder;
import com.codegym.service.CommenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class CommenterController {
    @Autowired
    private CommenterService commenterService;

    @GetMapping("/commenter")
    public ResponseEntity<?> listCommenter(){
        List<Commenter> commenters = (List<Commenter>) commenterService.findAll();
        if (commenters.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(commenters, HttpStatus.OK);
    }

    @GetMapping("/commenter/{id}")
    public ResponseEntity<?> getCommenter(@PathVariable Long id){
        Optional<Commenter> commenter = commenterService.findById(id);
        if (!commenter.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(commenter, HttpStatus.OK);
    }

    @PostMapping("/commenter")
    public ResponseEntity<?> createCommenter(@Valid @RequestBody Commenter commenter){
        commenterService.save(commenter);
        return new ResponseEntity<>(commenter, HttpStatus.CREATED);
    }

    @PutMapping("commenter/{id}")
    public ResponseEntity<?> updateCommenter(@Valid @RequestBody Commenter commenter, @PathVariable Long id){
        Optional<Commenter> commenter1 = commenterService.findById(id);
        if(!commenter1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        commenter1.get().setTitle(commenter.getTitle());
        commenter1.get().setContent(commenter.getContent());
        commenter1.get().setTime(commenter.getTime());

        commenterService.save(commenter1.get());
        return new ResponseEntity<>(commenter1, HttpStatus.OK);
    }
//
//    @DeleteMapping("/oder/{id}")
//    public ResponseEntity<?> deleteOder(@PathVariable Long id){
//        Optional<Oder> oder = oderService.findById(id);
//        if (!oder.isPresent()){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        oderService.delete(id);
//        return new ResponseEntity<>(oder, HttpStatus.OK);
//    }
}
