package com.codegym.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Commenter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    private String content;
    private String date;
    private Boolean isEdit;
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "idProduct")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    public Commenter() {
    }

    public Commenter(Long id, String content, String date, Boolean isEdit, Product product, User user){
        this.id = id;
        this.content = content;
        this.date = date;
        this.isEdit = isEdit;
        this.product =  product;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getEdit() {
        return isEdit;
    }

    public void setEdit(Boolean edit) {
        isEdit = edit;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
