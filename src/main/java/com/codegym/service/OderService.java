package com.codegym.service;

import com.codegym.model.Order;

import java.util.Optional;

public interface OderService {
    Optional<Order> findById(Long id);

    Iterable<Order> findAll();

    Order save(Order order);

    void delete(Long id);
}
