package com.codegym.service;

import com.codegym.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    List<Payment> getAllPayment();
    Payment updatePayment(Payment payment);
    void deletePayment(Long id);
    Optional<Payment> findById(Long id);

}