package com.codegym.controller;

import com.codegym.model.Color;
import com.codegym.model.Payment;
import com.codegym.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @GetMapping("/payment")
    public ResponseEntity<?> ListPayment(){
        List<Payment> payments=(List<Payment>) paymentService.findAll();
        if (payments.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }
    @GetMapping("/payment/{id}")
    public ResponseEntity<?> getPayment(@PathVariable Long id){
        Optional<Payment> payment = paymentService.findById(id);
        if (!payment.isPresent()){
            return new ResponseEntity<>(payment, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }
    @PostMapping("/payment")
    public ResponseEntity<?> createPayment(@Valid @RequestBody Payment payment){
        paymentService.save(payment);
        return new ResponseEntity<>(payment,HttpStatus.CREATED);
    }
    @PutMapping("/payment/{id}")
    public ResponseEntity<?> updatePayment(@Valid @RequestBody Payment payment, @PathVariable Long id){
        Optional<Payment> payment1 = paymentService.findById(id);
        if (!payment1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        payment1.get().setName(payment.getName());
        paymentService.save(payment1.get());
        return new ResponseEntity<>(payment1, HttpStatus.OK);
    }

    @DeleteMapping("/payment/{id}")
    public ResponseEntity<?> deletePayment(@PathVariable Long id){
        Optional<Payment> payment = paymentService.findById(id);
        if (!payment.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        paymentService.delete(id);
        return new ResponseEntity<>(payment, HttpStatus.NO_CONTENT);
    }
}
