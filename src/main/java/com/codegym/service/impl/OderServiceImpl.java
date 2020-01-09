package com.codegym.service.impl;

import com.codegym.model.Oder;
import com.codegym.repository.OderRepository;
import com.codegym.service.OderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class OderServiceImpl implements OderService {
    @Autowired
    private OderRepository oderRepository;

    @Override
    public Optional<Oder> findById(Long id) {
        return oderRepository.findById(id);
    }

    @Override
    public Iterable<Oder> findAll() {
        return oderRepository.findAll();
    }

    @Override
    public Oder save(Oder oder) {
        return oderRepository.save(oder);
    }

    @Override
    public void delete(Long id) {
        oderRepository.deleteById(id);
    }
}
