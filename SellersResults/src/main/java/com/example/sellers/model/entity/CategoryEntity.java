package com.example.sellers.model.entity;

import com.example.sellers.model.entity.enums.CategoryEnum;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {

    private CategoryEnum categoryEnum;
    //ToDo да проверя дали може да направим всяка категориа да има няколко цени

    public CategoryEntity() {
    }

    public CategoryEntity(CategoryEnum categoryEnum) {
        this.categoryEnum = categoryEnum;
    }

    @Enumerated(EnumType.STRING)
    public CategoryEnum getCategoryEnum() {
        return categoryEnum;
    }

    public CategoryEntity setCategoryEnum(CategoryEnum categoryEnum) {
        this.categoryEnum = categoryEnum;
        return this;
    }
}
