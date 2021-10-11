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

    //ToDo да проверя дали не трябва да е @ManyToMany
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

    @Override
    public String toString() {
        return "SaleEntity{" +
                "dateOfSale=" + dateOfSale +
                ", products=" + products +
                ", userEntity=" + userEntity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SaleEntity)) return false;
        SaleEntity that = (SaleEntity) o;
        return Objects.equals(dateOfSale, that.dateOfSale) && Objects.equals(products, that.products) && Objects.equals(userEntity, that.userEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateOfSale, products, userEntity);
    }
}
