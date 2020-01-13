package com.codegym.service;

import com.codegym.model.Commenter;

import java.util.Optional;

public interface CommenterService {
    Optional<Commenter> findById(Long id);

    Iterable<Commenter> findAll();

    Commenter save(Commenter commenter);

    void delete(Long id);

    Iterable<Commenter> findByTitle(String title);
}
