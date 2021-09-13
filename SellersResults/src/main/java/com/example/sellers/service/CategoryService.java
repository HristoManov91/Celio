package com.example.sellers.service;

import com.example.sellers.model.entity.CategoryEntity;
import com.example.sellers.model.entity.enums.CategoryEnum;

public interface CategoryService {

    void initCategories();

    CategoryEntity findByName(CategoryEnum category);
}
