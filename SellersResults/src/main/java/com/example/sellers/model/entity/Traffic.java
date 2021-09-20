package com.example.sellers.model.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "traffic")
public class Traffic extends BaseEntity{

    private LocalDate date;
    private Long quantity;
    private ShopEntity shopEntity;

    public Traffic() {
    }

    public LocalDate getDate() {
        return date;
    }

    public Traffic setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Traffic setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    @ManyToOne
    public ShopEntity getShopEntity() {
        return shopEntity;
    }

    public Traffic setShopEntity(ShopEntity shopEntity) {
        this.shopEntity = shopEntity;
        return this;
    }
}
