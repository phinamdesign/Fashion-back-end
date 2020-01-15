package com.codegym.controller;


import com.codegym.model.Size;
import com.codegym.service.SizeService;
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
public class SizeController {
    @Autowired
    private SizeService sizeService;

    @GetMapping("/size")
    public ResponseEntity<?> listSize(){
        List<Size> sizes = (List<Size>) sizeService.findAll();
        if (sizes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(sizes,HttpStatus.OK);
    }

    @GetMapping("/size/{id}")
    public ResponseEntity<?> getSize(@PathVariable Long id){
        Optional<Size> size = sizeService.findById(id);
        if (!size.isPresent()){
            return new ResponseEntity<>(size, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(size, HttpStatus.OK);
    }
    @PostMapping("/size")
    public ResponseEntity<?> createSize(@Valid @RequestBody Size size){
        sizeService.save(size);
        return new ResponseEntity<>(size,HttpStatus.CREATED);
    }

    @PutMapping("/size/{id}")
    public ResponseEntity<?> updateSize(@Valid @RequestBody Size size, @PathVariable Long id){
        Optional<Size> size1 = sizeService.findById(id);
        if (!size1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        size1.get().setName(size.getName());
        sizeService.save(size1.get());
        return new ResponseEntity<>(size1,HttpStatus.OK);
    }

    @DeleteMapping("/size/{id}")
    public ResponseEntity<?> deleteSize(@PathVariable Long id){
        Optional<Size> size = sizeService.findById(id);
        if (!size.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        sizeService.delete(id);
        return new ResponseEntity<>(size, HttpStatus.NO_CONTENT);
    }
}
