package com.codegym.service;

import com.codegym.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> findById(Long id);

    Iterable<Product> findAll();

    Product save(Product product);

    void delete(Long id);

    Iterable<Product> findByName(String name);
    List<Product> findAllByCategory_CategoryId(Long Category_CategoryId);
    List<Product> findAllBySupplier_SupplierId(Long Supplier_SupplierId);

    Iterable<Product> findProductsByNameContaining(String name);
}
