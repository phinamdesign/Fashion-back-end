package com.codegym.service.impl;

import com.codegym.model.Product;
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

    @Override
    public ProductDetail findByProduct_IdAndOrder_Id(Long Product_Id, Long Order_Id) {
        return productDetailRepository.findByProduct_IdAndOrder_Id(Product_Id, Order_Id);
    }

    @Override
    public List<ProductDetail> findByOrder_Id(Long Order_Id) {
        return productDetailRepository.findByOrder_Id(Order_Id);
    }

    @Override
    public void deleteProductDetail(ProductDetail productDetail) {
        productDetailRepository.delete(productDetail);
    }
}