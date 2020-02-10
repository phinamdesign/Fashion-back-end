package com.codegym.repository;

import com.codegym.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository <Payment, Long> {
}
