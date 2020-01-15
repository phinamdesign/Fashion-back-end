package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.search.SearchByName;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public ResponseEntity<?> listProduct(Pageable pageable){
        List<Product> products = (List<Product>) productService.findAll();
        if (products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id){
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/product")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createEmployee(@Valid @RequestBody Product product){
        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/product/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody Product product, @PathVariable Long id){
        Optional<Product> product1 = productService.findById(id);
        if (!product1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        product1.get().setName(product.getName());
        product1.get().setImage(product.getImage());
        product1.get().setPrice(product.getPrice());
        product1.get().setDescription(product.getDescription());
        product1.get().setQuantity(product.getQuantity());
        productService.save(product1.get());

        return new ResponseEntity<>(product1, HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.delete(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

//    @GetMapping("product/search")
//    public ResponseEntity<?> searchByName(@RequestParam("name") Optional<String> name, Pageable pageable){
//        Page<Product> products;
//        if (name.isPresent()){
//            products = productService.findAllByName(name.get(), pageable);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }

//    public ResponseEntity<?> searchById(@RequestBody Optional<Long> id, Pageable pageable){
//        Page<Product> products;
//        if (id.isPresent()){
//            products = productService.findAllById(id.get(), pageable);
//        } else {
////            products = productService.findAll(pageable);
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }

    @PostMapping("product/search-by-name")
    public ResponseEntity<?> searchByName(@RequestBody SearchByName productForm){
        if (productForm.getName() == "" || productForm.getName() == null){
            List<Product> tags = (List<Product>) productService.findAll();
            if (tags.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        List<Product> products = (List<Product>) productService.findByName(productForm.getName());
        if (products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
    }
}
