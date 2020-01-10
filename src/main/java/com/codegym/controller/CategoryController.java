package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/api/admin")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categoryList = (List<Category>) categoryService.findAllCategory();
        if (categoryList.isEmpty()) {
            return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Category>>(categoryList, HttpStatus.OK);
    }

    @PostMapping("/category")
    ResponseEntity<Category> createCategory(@RequestBody Category category, UriComponentsBuilder ucBuilder) {
        categoryService.saveCategory(category);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/admin/category/{id}").buildAndExpand(category.getCategoryId()).toUri());
        return new ResponseEntity<Category>(headers, HttpStatus.CREATED);
    }

    @GetMapping("/category/{id}")
    ResponseEntity<?> getCategory(@PathVariable ("id") Long id){
        Optional<Category> category = categoryService.findByCategoryId(id);
        if (!category.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
    @PutMapping("/category/{id}")
    ResponseEntity<?> editCategory(@PathVariable ("id") Long id, @RequestBody Category category ){
        Optional<Category> currentCategory = categoryService.findByCategoryId(id);
        if(!currentCategory.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentCategory.get().setCategoryName(category.getCategoryName());
        categoryService.saveCategory(currentCategory.get());
        return new ResponseEntity<>(currentCategory,HttpStatus.OK);
    }

    @DeleteMapping("/category/{id}")
    ResponseEntity<Void> deleteCategory(@PathVariable ("id") Long id, @RequestBody Category category){
        Optional<Category> thisCategory = categoryService.findByCategoryId(id);
        if(!thisCategory.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.removeCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
