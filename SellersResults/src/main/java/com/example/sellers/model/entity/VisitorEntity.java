package com.example.sellers.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "visitors")
public class VisitorEntity extends BaseEntity {

    private LocalDate date;
    private Integer quantity;
    private StoreEntity store;

    public VisitorEntity() {
    }

    @Column
    public LocalDate getDate() {
        return date;
    }

    public VisitorEntity setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    @Column
    public Integer getQuantity() {
        return quantity;
    }

    public VisitorEntity setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    @ManyToOne
    public StoreEntity getStore() {
        return store;
    }

    public VisitorEntity setStore(StoreEntity store) {
        this.store = store;
        return this;
    }
}
