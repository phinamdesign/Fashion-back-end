package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.search.SearchByName;
import com.codegym.search.SearchProductByName;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/auth/product")
    public ResponseEntity<?> listProduct(Pageable pageable){
        List<Product> products = (List<Product>) productService.findAll();
        if (products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/auth/product/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id){
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/admin/product")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product){
        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

//    @PostMapping("/product")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<Optional<Product>> createBook(@RequestBody Product product) {
//        System.out.println("Creating Book " + product.getName());
//        Category category = product.getCategory();
//        Supplier supplier = product.getSupplier();
//        long millis = System.currentTimeMillis();
//        java.sql.Date date = new java.sql.Date(millis);
//        Product currentProduct = new Product(product.getName(), product.getPrice(), product.getDescription(), product.getQuantity(), product.getPictures(), product.getSupplier(), category, supplier, date);
//        productService.save(currentProduct);
//        return new ResponseEntity<Optional<Product>>(HttpStatus.CREATED);
//    }

    @PutMapping("/admin/product/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody Product product, @PathVariable Long id){
        Optional<Product> product1 = productService.findById(id);
        if (!product1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        product1.get().setName(product.getName());
        product1.get().setPrice(product.getPrice());
        product1.get().setDescription(product.getDescription());
        product1.get().setQuantity(product.getQuantity());
        productService.save(product1.get());

        return new ResponseEntity<>(product1, HttpStatus.OK);
    }

    @DeleteMapping("/admin/product/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable ("id") Long id){
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.delete(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/auth/product/list/category/{id}")
    public ResponseEntity<?> getProductByCategoryId(@PathVariable Long id){
        List<Product> product = productService.findAllByCategory_CategoryId(id);
        if (product.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/auth/product/list/supplier/{id}")
    public ResponseEntity<?> getProductBySupplierId(@PathVariable Long id){
        List<Product> product = productService.findAllBySupplier_SupplierId(id);
        if (product.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/auth/product/search-by-name")
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



//    @PostMapping("/auth/product/search-by-productId-and-name")
//    public ResponseEntity<?> searchAlbumByTagIdAndTitle(@RequestBody SearchAlbumByTagIdAndTitle formSearch) {
//        if (formSearch.getTitle() == null && formSearch.getTagId() == null) {
//            List<Album> albums = (List<Album>) albumService.findAll();
//            if(albums.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(albums,HttpStatus.OK);
//        }
//
//        if (formSearch.getTitle() == null && formSearch.getTagId() != null) {
//            List<Album> albums = (List<Album>) albumService.findAlbumsByTagId(formSearch.getTagId());
//            if(albums.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(albums,HttpStatus.OK);
//        }
//
//        if (formSearch.getTitle() != null && formSearch.getTagId() == null) {
//            List<Album> diaries = (List<Album>) albumService.findAlbumsByTitleContaining(formSearch.getTitle());
//            if(diaries.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(diaries,HttpStatus.OK);
//        }
//
//        if (formSearch.getTagId() != null && formSearch.getTitle() != null) {
//            List<Album> albums = (List<Album>) albumService.findAlbumsByTagIdAndTitleContaining(formSearch.getTagId(),formSearch.getTitle());
//            if(albums.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(albums,HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PostMapping("/auth/product/search-product-by-name")
    public ResponseEntity<?> findProductByName(@RequestBody SearchProductByName searchProductByName) {
        if(searchProductByName.getNameProduct() == "" || searchProductByName.getNameProduct() == null) {
            List<Product> products = (List<Product>) productService.findAll();
            if(products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(products,HttpStatus.OK);
        } else {
            List<Product> products = (List<Product>) productService.findProductsByNameContaining(searchProductByName.getNameProduct());
            if(products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(products,HttpStatus.OK);
        }
    }
}
