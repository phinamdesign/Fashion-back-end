package com.codegym.service.impl;

import com.codegym.model.Size;
import com.codegym.repository.SizeRepository;
import com.codegym.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SizeServiceImpl implements SizeService {
   @Autowired
   private SizeRepository sizeRepository;

    @Override
    public Optional<Size> findById(Long id) {
        return sizeRepository.findById(id);
    }

    @Override
    public Iterable<Size> findAll() {
        return sizeRepository.findAll();
    }

    @Override
    public Size save(Size size) {
        return sizeRepository.save(size);
    }

    @Override
    public void delete(Long id) {
        sizeRepository.deleteById(id);
    }
}
