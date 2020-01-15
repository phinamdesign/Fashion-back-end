package com.codegym.service;

import com.codegym.model.Color;

import java.util.Optional;

public interface ColorService {
    Optional<Color> findById(Long id);
    Iterable<Color> findAll();
    Color save(Color color);
    void delete(Long id);
}
