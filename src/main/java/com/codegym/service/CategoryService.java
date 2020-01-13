package com.codegym.service;

import com.codegym.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAllCategory();
    Optional<Category> findByCategoryId(Long id);
    Category saveCategory( Category category);
    void removeCategory(Long id);
    Iterable<Category> findAllByCategoryName(String categoryName);
}
