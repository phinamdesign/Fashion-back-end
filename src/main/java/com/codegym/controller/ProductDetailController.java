package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.model.ProductDetail;
import com.codegym.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
public class ProductDetailController {
    @Autowired
    private ProductDetailService productDetailService;

    @GetMapping("/productDetail")
    public ResponseEntity<List<ProductDetail>> getAllProductDetail() {
        List<ProductDetail> productDetailList = productDetailService.findAllProductDetail();
        if (productDetailList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productDetailList, HttpStatus.OK);
    }

//    @PostMapping("/productDetail")
//    public ResponseEntity<?> createProductDetail(
//            @RequestBody ProductDetail productDetail, UriComponentsBuilder ucBuilder) {
//        productDetailService.saveProductDetail(productDetail);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(ucBuilder.path("/api/admin/productDetail/{id}").buildAndExpand(productDetail.getId()).toUri());
//        return new ResponseEntity<>(headers, HttpStatus.CREATED);
//    }

    @PostMapping("/productDetail")
    public ResponseEntity<?> addNewOrderItem(@RequestBody ProductDetail productDetail){
        try {
            productDetailService.saveProductDetail(productDetail);
            return new ResponseEntity<ProductDetail>(productDetail, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/productDetail/{id}")
    public ResponseEntity<?> getAProductDetail(
            @PathVariable ("id") Long id){
        Optional<ProductDetail> thisProductDetail = productDetailService.findDetailById(id);
        if(!thisProductDetail.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(thisProductDetail, HttpStatus.OK);
    }

    @PutMapping("/productDetail/{id}")
    public ResponseEntity<?> updateProductDetail(@PathVariable ("id") Long id, @RequestBody ProductDetail productDetail){
        Optional<ProductDetail> currentProductDetail = productDetailService.findDetailById(id);
        if(!currentProductDetail.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentProductDetail.get().setColor(productDetail.getColor());
        currentProductDetail.get().setSize(productDetail.getSize());
        currentProductDetail.get().setPayment(productDetail.getPayment());
        currentProductDetail.get().setProduct(productDetail.getProduct());
        currentProductDetail.get().setQuantity(productDetail.getQuantity());
        productDetailService.saveProductDetail(currentProductDetail.get());
        return new ResponseEntity<>(currentProductDetail,HttpStatus.OK);
    }

    @DeleteMapping("/productDetail/{id}")
    public ResponseEntity<ProductDetail> removeProductDetail(@PathVariable ("id") Long id){
        Optional<ProductDetail> thisProductDetail = productDetailService.findDetailById(id);
        if(!thisProductDetail.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productDetailService.removeProductDetail(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/productDetail/cart/{id}")
    public ResponseEntity<List<ProductDetail>> findByOrderId(@PathVariable Long id) {
        List<ProductDetail> productDetails = productDetailService.findByOrder_Id(id);
        return new ResponseEntity<List<ProductDetail>>(productDetails, HttpStatus.OK);
    }

    @GetMapping("/productDetail/cart/{idProduct}/{idOrder}")
    public  ResponseEntity<ProductDetail> findByProduct_IdAndOrder_Id(@PathVariable Long idProduct,@PathVariable Long idOrder) {
        ProductDetail productDetail = productDetailService.findByProduct_IdAndOrder_Id(idProduct,idOrder);
        if (productDetail==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ProductDetail>(productDetail, HttpStatus.OK);
    }
}