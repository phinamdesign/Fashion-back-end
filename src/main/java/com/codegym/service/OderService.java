package com.codegym.service;

import com.codegym.model.Oder;

import java.util.Optional;

public interface OderService {
    Optional<Oder> findById(Long id);

    Iterable<Oder> findAll();

    Oder save(Oder oder);

    void delete(Long id);
}
