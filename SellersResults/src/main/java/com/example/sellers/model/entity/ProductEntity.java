package com.example.sellers.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity {

    private String name;
    private CategoryEntity category;
    private BigDecimal price;

    public ProductEntity() {
    }

    public ProductEntity(String name ,CategoryEntity category, BigDecimal price) {
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

    @ManyToOne
    public CategoryEntity getCategory() {
        return category;
    }

    public ProductEntity setCategory(CategoryEntity category) {
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
}
