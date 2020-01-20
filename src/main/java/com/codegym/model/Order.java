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
    private Long total;
    @NotEmpty
    private String deliveryAddress;
    @OneToMany
    private List<ProductDetail>productDetails;
    @ManyToOne
    private User user;

    public Order() {
    }

    public Order(Long id, Date deliveryTime, Long total,
                 @NotEmpty String deliveryAddress, List<ProductDetail> productDetails, User user) {
        this.id = id;
        this.deliveryTime = deliveryTime;
        this.total = total;
        this.deliveryAddress = deliveryAddress;
        this.productDetails = productDetails;
        this.user = user;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
