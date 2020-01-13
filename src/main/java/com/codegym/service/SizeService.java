package com.codegym.service;

import com.codegym.model.Size;

import java.util.Optional;

public interface SizeService {
    Optional<Size> findById(Long id);
    Iterable<Size> findAll();
    Size save(Size size);
    void delete(Long id);
}
