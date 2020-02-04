package com.codegym.service.impl;

import com.codegym.model.Order;
import com.codegym.model.Status;
import com.codegym.repository.OrderRepository;
import com.codegym.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order findByStatusAndUser_Id(Status status, Long User_Id) {
        return orderRepository.findByStatusAndUser_Id(status, User_Id);
    }

    @Override
    public List<Order> findAllByUser_Id(Long User_Id) {
        return orderRepository.findAllByUser_Id(User_Id);
    }

    @Override
    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }
}
