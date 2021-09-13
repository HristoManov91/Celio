package com.example.sellers.model.entity;

import com.example.sellers.model.entity.enums.CategoryEnum;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {

    private CategoryEnum categoryEnum;
    private String description;

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

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public CategoryEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
