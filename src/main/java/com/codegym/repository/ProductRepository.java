package com.codegym.repository;

import com.codegym.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Iterable<Product> findByName(String name);
    Page<Product> findAllByName(String name, Pageable pageable);
    Page<Product> findAllById(Long id, Pageable pageable);
    List<Product> findAllByCategory_CategoryId(Long Category_CategoryId);
    List<Product> findAllBySupplier_SupplierId(Long Supplier_SupplierId);
}

