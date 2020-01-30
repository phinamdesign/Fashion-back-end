package com.codegym.service.impl;

import com.codegym.model.Order;
import com.codegym.model.Status;
import com.codegym.repository.OderRepository;
import com.codegym.service.OderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OderServiceImpl implements OderService {
    @Autowired
    private OderRepository oderRepository;

    @Override
    public Optional<Order> findById(Long id) {
        return oderRepository.findById(id);
    }

    @Override
    public Iterable<Order> findAll() {
        return oderRepository.findAll();
    }

    @Override
    public Order save(Order order) {
        return oderRepository.save(order);
    }

    @Override
    public void delete(Long id) {
        oderRepository.deleteById(id);
    }

    @Override
    public Order findByStatusAndUser_Id(Status status, Long User_Id) {
        return oderRepository.findByStatusAndUser_Id(status, User_Id);
    }
}
