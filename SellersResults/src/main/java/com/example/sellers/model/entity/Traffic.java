package com.example.sellers.model.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "traffic")
public class Traffic extends BaseEntity{

    private LocalDate date;
    private Long quantity;
    private StoreEntity store;

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
    public StoreEntity getStore() {
        return store;
    }

    public Traffic setStore(StoreEntity store) {
        this.store = store;
        return this;
    }
}
