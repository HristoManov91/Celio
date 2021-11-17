package com.example.sellers.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "visitors")
public class VisitorEntity extends BaseEntity{

    private LocalDate date;
    private Long quantity;

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
    public Long getQuantity() {
        return quantity;
    }

    public VisitorEntity setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }
}
