package com.example.sellers.model.binding;

import com.example.sellers.model.entity.enums.CategoryEnum;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class ProductAddBindingModel {

    private String name;
    private BigDecimal price;
    private CategoryEnum category;

    public ProductAddBindingModel() {
    }

    @NotBlank
    @Size(min = 4 , max = 15)
    public String getName() {
        return name;
    }

    public ProductAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    @DecimalMin("5")
    @Positive
    @NotNull
    public BigDecimal getPrice() {
        return price;
    }

    public ProductAddBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @NotNull
    public CategoryEnum getCategory() {
        return category;
    }

    public ProductAddBindingModel setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }
}
