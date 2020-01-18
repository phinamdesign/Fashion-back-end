//package com.codegym.model;
//
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//
//@Entity
//@Table
//public class OderDetail {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @NotNull
//    private long price;
//    @NotNull
//    private long quantity;
//
//    @ManyToOne
//    private Size size;
//    @ManyToOne
//    private Color color;
//    @ManyToOne
//    private Product product;
//
//    public OderDetail() {
//    }
//
//    public OderDetail(Long id, @NotNull long price, @NotNull long quantity, Size size, Color color, Product product) {
//        this.id = id;
//        this.price = price;
//        this.quantity = quantity;
//        this.size = size;
//        this.color = color;
//        this.product = product;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public long getPrice() {
//        return price;
//    }
//
//    public void setPrice(long price) {
//        this.price = price;
//    }
//
//    public long getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(long quantity) {
//        this.quantity = quantity;
//    }
//
//    public Size getSize() {
//        return size;
//    }
//
//    public void setSize(Size size) {
//        this.size = size;
//    }
//
//    public Color getColor() {
//        return color;
//    }
//
//    public void setColor(Color color) {
//        this.color = color;
//    }
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
//}
