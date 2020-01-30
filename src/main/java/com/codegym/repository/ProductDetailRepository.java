package com.codegym.repository;

import com.codegym.model.Product;
import com.codegym.model.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
    ProductDetail findByProduct_IdAndOrder_Id(Long Product_Id, Long Order_Id);
    List<ProductDetail> findByOrder_Id(Long Order_Id);
}
