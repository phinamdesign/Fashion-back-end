package com.codegym.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long price;
    private String description;
    private Long quantity;
//    private Date dateCreate;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "supplierId")
    private Supplier supplier;

    @OneToMany(targetEntity = Picture.class)
    private List<Picture> pictures;


    public Product() {
    }

    public Product( String name, Long price, String description, Long quantity,
                    Category category, Supplier supplier, List<Picture> pictures) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.category = category;
        this.supplier = supplier;
        this.pictures = pictures;
    }

    public Product(String name, Long price, String description, Long quantity, List<Picture> pictures, Supplier supplier, Category category, Supplier supplier1, Date date) {
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
