package com.codegym.controller;

import com.codegym.model.Supplier;
import com.codegym.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/admin")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @GetMapping("/supplier")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Supplier>> getAllSupplier(){
        List<Supplier> supplierList = supplierService.findAllSupplier();
        if(supplierList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(supplierList, HttpStatus.OK);
    }

}