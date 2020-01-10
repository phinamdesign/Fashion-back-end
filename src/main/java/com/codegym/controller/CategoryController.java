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

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/api/admin")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/category")
    ResponseEntity<List<Category>> getAllCategory(){
     List<Category> categoryList = (List<Category>) categoryService.findAllCategory();
     if(categoryList.isEmpty()){
         return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
     }
     return new ResponseEntity<List<Category>>(categoryList, HttpStatus.OK);
    }

    @PostMapping("/category")
    ResponseEntity<Category> createCategory(@RequestBody Category category, UriComponentsBuilder ucBuilder){
        categoryService.saveCategory(category);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/admin/category/{id}").buildAndExpand(category.getCategoryId()).toUri());
        return new ResponseEntity<Category>(headers, HttpStatus.CREATED);
    }

}