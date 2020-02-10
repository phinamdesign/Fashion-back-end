package com.codegym.controller;

import com.codegym.model.Payment;
import com.codegym.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payment")
    public ResponseEntity<List<Payment>> getAllPayment(){
        List<Payment> payments = paymentService.getAllPayment();
        if(payments.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    @PostMapping("/payment")
    public ResponseEntity<?> createPayment(@RequestBody Payment payment){
        paymentService.updatePayment(payment);
        return new ResponseEntity<>(payment.getId(), HttpStatus.CREATED);
    }

    @PutMapping("/payment/{id}")
    public ResponseEntity<?> savePayment(@PathVariable ("id") Long id, @RequestBody Payment payment){
        Optional<Payment> currentPayment = paymentService.findById(id);
        if(!currentPayment.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        currentPayment.get().setId(payment.getId());
        currentPayment.get().setName(payment.getName());
        paymentService.updatePayment(currentPayment.get());
        return new ResponseEntity<>(currentPayment, HttpStatus.OK);
    }
    @DeleteMapping("/payment/{id}")
    public ResponseEntity<?> deletePayment(@PathVariable ("id") Long id){
        Optional payment = paymentService.findById(id);
        if(!payment.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        paymentService.deletePayment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/payment/{id}")
    public ResponseEntity<?> getPayment(@PathVariable("id") Long id){
        Optional<Payment> payment1 = paymentService.findById(id);
        if(!payment1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(payment1, HttpStatus.OK);
    }

}