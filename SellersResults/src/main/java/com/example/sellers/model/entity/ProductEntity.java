package com.example.sellers.model.entity;

import com.example.sellers.model.entity.enums.CategoryEnum;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity {

    private String name;
    //ToDo да проверя дали е ок да са само enum или да ги запазя в базата с отделно entity
    private CategoryEnum category;
    private BigDecimal price;

    public ProductEntity() {
    }

    public ProductEntity(String name ,CategoryEnum category, BigDecimal price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    @Column (length = 20 , nullable = false , unique = true)
    public String getName() {
        return name;
    }

    public ProductEntity setName(String name) {
        this.name = name;
        return this;
    }

    @Enumerated(EnumType.STRING)
    public CategoryEnum getCategory() {
        return category;
    }

    public ProductEntity setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    @Column(nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public ProductEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductEntity)) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(name, that.name) && category == that.category && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category, price);
    }
}
