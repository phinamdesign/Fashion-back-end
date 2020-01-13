package com.codegym.controller;

import com.codegym.model.Supplier;
import com.codegym.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/api/admin")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @GetMapping("/supplier")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Supplier>> getAllSupplier() {
        List<Supplier> supplierList = supplierService.findAllSupplier();
        if (supplierList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(supplierList, HttpStatus.OK);
    }

    @PostMapping("/supplier")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier, UriComponentsBuilder ucBuilder) {
        supplierService.saveSupplier(supplier);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/admin/supplier/{id}").buildAndExpand(supplier.getSupplierId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping("/supplier/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> getSupplier(@PathVariable("id") Long id) {
        Optional<Supplier> supplier = supplierService.findSupplierById(id);
        if (!supplier.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(supplier, HttpStatus.OK);
    }

    @PutMapping("/supplier/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> updateSupplier(@PathVariable("id") Long id, @RequestBody Supplier supplier) {
        Optional<Supplier> currentSupplier = supplierService.findSupplierById(id);
        if (!currentSupplier.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentSupplier.get().setSupplierName(supplier.getSupplierName());
        currentSupplier.get().setSupplierPhone(supplier.getSupplierPhone());
        currentSupplier.get().setSupplierAddress(supplier.getSupplierAddress());
        ;
        supplierService.saveSupplier(currentSupplier.get());
        return new ResponseEntity<>(currentSupplier, HttpStatus.OK);
    }

    @DeleteMapping("/supplier/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> removeSupplier(@PathVariable("id") Long id) {
        Optional<Supplier> supplier = supplierService.findSupplierById(id);
        if (!supplier.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        supplierService.removeSupplier(id);
        return new ResponseEntity<Supplier>(HttpStatus.NO_CONTENT);
    }

}