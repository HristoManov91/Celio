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

    @Override
    public String toString() {
        return "Traffic{" +
                "date=" + date +
                ", quantity=" + quantity +
                ", store=" + store +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Traffic)) return false;
        Traffic traffic = (Traffic) o;
        return Objects.equals(date, traffic.date) && Objects.equals(quantity, traffic.quantity) && Objects.equals(store, traffic.store);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, quantity, store);
    }
}
