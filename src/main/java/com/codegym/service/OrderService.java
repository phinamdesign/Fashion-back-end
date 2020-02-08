package com.codegym.service;

import com.codegym.model.Order;
import com.codegym.model.Status;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<Order> findById(Long id);

    Iterable<Order> findAll();

    Order save(Order order);

    void delete(Long id);

    Order findByStatusAndUser_Id(Status status, Long User_Id);

    List<Order> findAllByUser_Id(Long User_Id);

    void deleteOrder(Order order);
}
