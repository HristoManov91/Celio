package com.example.sellers.model.binding;

import com.example.sellers.model.entity.enums.CategoryEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class ProductAddBindingModel {

    @NotBlank
    private String name;
    @NotNull
    private BigDecimal price;
    @NotNull
    private CategoryEnum category;

    public ProductAddBindingModel() {
    }

    @NotBlank
    public String getName() {
        return name;
    }

    public ProductAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    @Positive
    public BigDecimal getPrice() {
        return price;
    }

    public ProductAddBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @NotBlank
    public CategoryEnum getCategory() {
        return category;
    }

    public ProductAddBindingModel setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }
}
