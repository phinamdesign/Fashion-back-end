package com.codegym.repository;

import com.codegym.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findById(Long id);
}
