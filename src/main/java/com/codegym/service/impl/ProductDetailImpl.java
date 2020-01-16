package com.codegym.service.impl;

import com.codegym.model.ProductDetail;
import com.codegym.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ProductDetailImpl implements ProductDetailService {
    @Autowired
    private ProductDetailService productDetailService;
    @Override
    public List<ProductDetail> findAllProductDetail() {
        return productDetailService.findAllProductDetail();
    }

    @Override
    public Optional<ProductDetail> findDetailById(Long id) {
        return productDetailService.findDetailById(id);
    }

    @Override
    public ProductDetail saveProductDetail(ProductDetail productDetail) {
        return productDetailService.saveProductDetail(productDetail);
    }

    @Override
    public void removeProductDetail(Long id) {
        productDetailService.removeProductDetail(id);
    }
}