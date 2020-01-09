package com.codegym.controller;

import com.codegym.model.Oder;
import com.codegym.service.OderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
