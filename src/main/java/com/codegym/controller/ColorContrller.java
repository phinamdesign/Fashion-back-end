package com.codegym.controller;


import com.codegym.model.Color;
import com.codegym.service.ColorService;
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
public class ColorContrller {
    @Autowired
    private ColorService colorService;

    @GetMapping("/color")
    public ResponseEntity<?> ListColor(){
        List<Color> colors=(List<Color>) colorService.findAll();
        if (colors.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(colors, HttpStatus.OK);
    }
    @GetMapping("/color/{id}")
    public ResponseEntity<?> getColor(@PathVariable Long id){
        Optional<Color> color = colorService.findById(id);
        if (!color.isPresent()){
            return new ResponseEntity<>(color, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(color, HttpStatus.OK);
    }
    @PostMapping("/color")
    public ResponseEntity<?> createColor(@Valid @RequestBody Color color){
        colorService.save(color);
        return new ResponseEntity<>(color,HttpStatus.CREATED);
    }
    @PutMapping("/color/{id}")
    public ResponseEntity<?> updateColor(@Valid @RequestBody Color color, @PathVariable Long id){
        Optional<Color> color1 = colorService.findById(id);
        if (!color1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        color1.get().setName(color.getName());
        colorService.save(color1.get());
        return new ResponseEntity<>(color1, HttpStatus.OK);
    }

    @DeleteMapping("/color/{id}")
    public ResponseEntity<?> deleteColor(@PathVariable Long id){
        Optional<Color> color = colorService.findById(id);
        if (!color.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        colorService.delete(id);
        return new ResponseEntity<>(color, HttpStatus.NO_CONTENT);
    }
}
