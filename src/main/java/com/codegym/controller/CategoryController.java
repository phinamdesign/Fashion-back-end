package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(maxAge = 3600)
//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
@RequestMapping("/api/admin")

public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categoryList = (List<Category>) categoryService.findAllCategory();
        if (categoryList.isEmpty()) {
            return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Category>>(categoryList, HttpStatus.OK);
    }

    @PostMapping("/category")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<Category> createCategory(@RequestBody Category category, UriComponentsBuilder ucBuilder) {
        categoryService.saveCategory(category);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/admin/category/{id}").buildAndExpand(category.getCategoryId()).toUri());
        return new ResponseEntity<Category>(headers, HttpStatus.CREATED);
    }

    @GetMapping("/category/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> getCategory(@PathVariable ("id") Long id){
        Optional<Category> category = categoryService.findByCategoryId(id);
        if (!category.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
    @PutMapping("/category/{id}")
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> deleteCategory(@PathVariable ("id") Long id){
        Optional<Category> thisCategory = categoryService.findByCategoryId(id);
        if(!thisCategory.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.removeCategory(id);
        return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search/category")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    ResponseEntity<?> findCategory(@RequestParam("name") Optional<String> categoryName) {
        Iterable<Category> categories;
        if(categoryName.isPresent()){
            categories = categoryService.findAllByCategoryName(categoryName.get());
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
