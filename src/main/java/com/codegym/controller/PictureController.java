package com.codegym.controller;

import com.codegym.model.Picture;
import com.codegym.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/auth")
public class PictureController {
    @Autowired
    private PictureService pictureService;

    @GetMapping("/picture")
    public ResponseEntity<List<Picture>> findAllPicture() {
        List<Picture> pictures = pictureService.findAllPicture();
        if (pictures.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pictures, HttpStatus.OK);
    }

//    @PostMapping("/picture")
//    public ResponseEntity<Picture> createPicture(@RequestBody Picture picture, UriComponentsBuilder ucBuilder) {
//        pictureService.savePicture(picture);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(ucBuilder.path("api/admin/picture/{id}").buildAndExpand(picture.getId()).toUri());
//        return new ResponseEntity<>(headers, HttpStatus.CREATED);
//    }

    @PostMapping("/picture")
    public ResponseEntity<?> addNewBookPicture(@RequestBody Picture picture) {
        System.out.println("Creating Book picture ");
        pictureService.savePicture(picture);
        return new ResponseEntity<Long>(picture.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/picture/{id}")
    public ResponseEntity<?> getAPicture(@PathVariable("id") Long id) {
        Optional<Picture> picture = pictureService.findByPictureId(id);
        if (!picture.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(picture, HttpStatus.OK);
    }

    @PutMapping("/picture/{id}")
    public ResponseEntity<?> savePicture(@PathVariable("id") Long id, @RequestBody Picture picture) {
        Optional<Picture> currentPicture = pictureService.findByPictureId(id);
        if (!currentPicture.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentPicture.get().setSrc(picture.getSrc());
        pictureService.savePicture(currentPicture.get());
        return new ResponseEntity<>(currentPicture, HttpStatus.OK);
    }

    @DeleteMapping("/picture/{id}")
    public ResponseEntity<Void> removePicture(@PathVariable("id") Long id) {
        Optional<Picture> picture = pictureService.findByPictureId(id);
        if (!picture.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        pictureService.deletePicture(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
