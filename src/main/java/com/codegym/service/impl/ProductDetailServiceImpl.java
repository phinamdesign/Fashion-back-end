package com.codegym.service.impl;

import com.codegym.model.ProductDetail;
import com.codegym.repository.ProductDetailRepository;
import com.codegym.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductDetailServiceImpl implements ProductDetailService {
    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Override
    public List<ProductDetail> findAllProductDetail() {
        return productDetailRepository.findAll();
    }

    @Override
    public Optional<ProductDetail> findDetailById(Long id) {
        return productDetailRepository.findById(id);
    }

    @Override
    public ProductDetail saveProductDetail(ProductDetail productDetail) {
        return productDetailRepository.save(productDetail);
    }

    @Override
    public void removeProductDetail(Long id) {
        productDetailRepository.deleteById(id);
    }
}