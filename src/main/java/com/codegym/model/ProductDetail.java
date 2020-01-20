package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private long quantity;
    private Long salePrice;
    @ManyToOne
    private Size size;
    @ManyToOne
    private Color color;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Order order;

    public ProductDetail(Long id) {
    }

    public ProductDetail(Long id, @NotNull long quantity, Long salePrice, Size size, Color color, Product product, Order order) {
        this.id = id;
        this.quantity = quantity;
        this.salePrice = salePrice;
        this.size = size;
        this.color = color;
        this.product = product;
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Long getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Long salePrice) {
        this.salePrice = salePrice;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

//    public Order getOrder() {
//        return order;
//    }
//
//    public void setOrder(Order order) {
//        this.order = order;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}