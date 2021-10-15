package com.example.sellers.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "sales")
public class SaleEntity extends BaseEntity {

    private LocalDate dateOfSale;
    List<ProductEntity> products;
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

    @ManyToMany
    public List<ProductEntity> getProducts() {
        return products;
    }

    public SaleEntity setProducts(List<ProductEntity> products) {
        this.products = products;
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

    public BigDecimal sumOfProductPrice(){
        return this.getProducts().stream().map(ProductEntity::getPrice).reduce(BigDecimal.ZERO , BigDecimal::add);
    }

    public int countOfProducts(){
        return this.getProducts().size();
    }
}
