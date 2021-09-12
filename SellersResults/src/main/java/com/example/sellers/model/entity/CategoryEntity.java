package com.example.sellers.model.entity;

import org.springframework.stereotype.Controller;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {

    private CategoryEnum categoryEnum;
    private String description;

    public CategoryEntity() {
    }

    //ToDo валидации

    @Column
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
