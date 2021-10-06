package com.example.sellers.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "sales")
public class SaleEntity extends BaseEntity {

    private LocalDate dateOfSale;
    List<ProductEntity> products;
    private BigDecimal totalPrice;
    private UserEntity userEntity;

    public SaleEntity() {
    }

    @Column(name = "date_of_sale" , nullable = false)
    public LocalDate getDateOfSale() {
        return dateOfSale;
    }

    public SaleEntity setDateOfSale(LocalDate dateOfSale) {
        this.dateOfSale = dateOfSale;
        return this;
    }

    @OneToMany
    public List<ProductEntity> getProducts() {
        return products;
    }

    public SaleEntity setProducts(List<ProductEntity> products) {
        this.products = products;
        return this;
    }

    //ToDo може да се напише да се смята само или да го въвежда консултанта
    @Column(nullable = false)
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public SaleEntity setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    @ManyToOne
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public SaleEntity setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }
}
