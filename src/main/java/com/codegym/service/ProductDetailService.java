package com.codegym.service;

import com.codegym.model.ProductDetail;

import java.util.List;
import java.util.Optional;

public interface ProductDetailService {
    List<ProductDetail> findAllProductDetail();
    Optional<ProductDetail> findDetailById(Long id);
    ProductDetail saveProductDetail(ProductDetail productDetail);
    void removeProductDetail(Long id);
}
