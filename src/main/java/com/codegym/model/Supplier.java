package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long supplierId;
    @NotEmpty
    private String supplierName;
    @NotEmpty
    private Integer supplierPhone;
    @NotEmpty
    private String supplierAddress;
//    @OneToMany(targetEntity = Product.class)
//    private List<Product> productList;

    public Supplier() {
    }

//    public Supplier(String supplierName, Integer supplierPhone, String supplierAddress, List<Product> productList){
//        this.supplierName = supplierName;
//        this.supplierPhone = supplierPhone;
//        this.supplierAddress = supplierAddress;
//        this.productList = productList;
//    }


    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Integer getSupplierPhone() {
        return supplierPhone;
    }

    public void setSupplierPhone(Integer supplierPhone) {
        this.supplierPhone = supplierPhone;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

//    public List<Product> getProductList() {
//        return productList;
//    }
//
//    public void setProductList(List<Product> productList) {
//        this.productList = productList;
//    }
}