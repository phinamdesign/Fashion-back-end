package com.codegym.controller;

import com.codegym.model.Oder;
import com.codegym.service.OderService;
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
public class OderController {
    @Autowired
    private OderService oderService;

    @GetMapping("/oder")
    public ResponseEntity<?> listOder(){
        List<Oder> oders = (List<Oder>) oderService.findAll();
        if (oders.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(oders, HttpStatus.OK);
    }

    @GetMapping("/oder/{id}")
    public ResponseEntity<?> getOder(@PathVariable Long id){
        Optional<Oder> oder = oderService.findById(id);
        if (!oder.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(oder, HttpStatus.OK);
    }

    @PostMapping("/oder")
    public ResponseEntity<?> createOder(@Valid @RequestBody Oder oder){
        oderService.save(oder);
        return new ResponseEntity<>(oder, HttpStatus.CREATED);
    }
}
