package com.codegym.controller;


import com.codegym.model.OderDetail;
import com.codegym.service.OderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class OderDetailController {
    @Autowired
    private OderDetailService oderDetailService;

    @GetMapping("/oderdetail")
    public ResponseEntity<?> listOderDetail() {
        List<OderDetail> oderDetails = (List<OderDetail>) oderDetailService.findAll();
        if (oderDetails.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(oderDetails, HttpStatus.OK);
    }

    @GetMapping("/oderdetail/{id}")
    public ResponseEntity<?> getOderDetail(@PathVariable Long id){
        Optional<OderDetail> oderDetail = oderDetailService.findById(id);
        if (!oderDetail.isPresent()){
            return new ResponseEntity<>(oderDetail, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(oderDetail, HttpStatus.OK);
    }

    @PostMapping("/oderdetail")
    public ResponseEntity<?> createOderDetail(@Valid @RequestBody OderDetail oderDetail) {
        oderDetailService.save(oderDetail);
        return new ResponseEntity<>(oderDetail, HttpStatus.CREATED);
    }

    @PutMapping("/oderdetail/{id}")
    public ResponseEntity<?> updateOderDetail(@Valid @RequestBody OderDetail oderDetail, @PathVariable Long id){
        Optional<OderDetail> oderDetail1 = oderDetailService.findById(id);
        if (!oderDetail1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        oderDetail1.get().setPrice(oderDetail.getPrice());
        oderDetail1.get().setQuantity(oderDetail.getQuantity());
        oderDetailService.save(oderDetail1.get());
        return new ResponseEntity<>(oderDetail1, HttpStatus.OK);
    }

    @DeleteMapping("/oderdetail/{id}")
    public ResponseEntity<?> deleteOderDetail(@PathVariable Long id){
        Optional<OderDetail> oderDetail=oderDetailService.findById(id);
        if (!oderDetail.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        oderDetailService.delete(id);
        return new ResponseEntity<>(oderDetail, HttpStatus.NO_CONTENT);
    }
}
