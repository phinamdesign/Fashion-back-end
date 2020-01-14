package com.codegym.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Oder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date deliveryTime;
    private String deliveryAddress;

    public Oder() {
    }

    public Oder(Long id, Date deliveryTime, String deliVeryAddress) {
        this.id = id;
        this.deliveryTime = deliveryTime;
        this.deliveryAddress = deliVeryAddress;
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
