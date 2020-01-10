package com.codegym.service;

import com.codegym.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface ProductService {
    Optional<Product> findById(Long id);

//    Iterable<Product> findAll();

    Product save(Product product);

    void delete(Long id);

    Iterable<Product> findByName(String name);

    Page<Product> findAll(Pageable pageable);

    Page<Product> findAllByName(String name, Pageable pageable);

    Page<Product> findAllById(Long id, Pageable pageable);
}
