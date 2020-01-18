package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date deliveryTime;
    @NotEmpty
    private String deliveryAddress;
    @OneToMany
    private List<ProductDetail>productDetails;

    public Order() {
    }

    public Order(Long id, Date deliveryTime, @NotEmpty String deliveryAddress, List<ProductDetail> productDetails) {
        this.id = id;
        this.deliveryTime = deliveryTime;
        this.deliveryAddress = deliveryAddress;
        this.productDetails = productDetails;
    }

    public List<ProductDetail> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<ProductDetail> productDetails) {
        this.productDetails = productDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
