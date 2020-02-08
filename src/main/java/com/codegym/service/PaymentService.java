package com.codegym.service;

import com.codegym.model.Color;
import com.codegym.model.Payment;

import java.util.Optional;

public interface PaymentService {
    Optional<Payment> findById(Long id);
    Iterable<Payment> findAll();
    Payment save(Payment payment);
    void delete(Long id);
}
