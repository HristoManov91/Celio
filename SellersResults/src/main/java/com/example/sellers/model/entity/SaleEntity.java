package com.example.sellers.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "sales")
public class SaleEntity extends BaseEntity {

    private LocalDate dateOfSale;
    List<ProductEntity> products = new ArrayList<>();
    private UserEntity userEntity;
    private StoreEntity store;

    public SaleEntity() {
    }

    @Column(name = "date_of_sale", nullable = false)
    public LocalDate getDateOfSale() {
        return dateOfSale;
    }

    public SaleEntity setDateOfSale(LocalDate dateOfSale) {
        this.dateOfSale = dateOfSale;
        return this;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public List<ProductEntity> getProducts() {
        return products;
    }

    public SaleEntity setProducts(List<ProductEntity> products) {
        this.products = products;
        return this;
    }

    @ManyToOne
    public StoreEntity getStore() {
        return store;
    }

    public SaleEntity setStore(StoreEntity storeEntity) {
        this.store = storeEntity;
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

    public BigDecimal sumOfProductPrice() {
        if (this.getProducts() == null) {
            return BigDecimal.ZERO;
        }

        return this.getProducts()
                .stream()
                .map(ProductEntity::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public int countOfProducts() {
        if (this.getProducts() == null) {
            return 0;
        }
        return this.getProducts().size();
    }
}
