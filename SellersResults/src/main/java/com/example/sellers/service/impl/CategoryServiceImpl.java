package com.example.sellers.service.impl;

import com.example.sellers.model.entity.CategoryEntity;
import com.example.sellers.model.entity.enums.CategoryEnum;
import com.example.sellers.repository.CategoryRepository;
import com.example.sellers.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategories() {
        if (categoryRepository.count() == 0) {
            Arrays.stream(CategoryEnum.values())
                    .forEach(categoryEnum -> {
                        CategoryEntity category = new CategoryEntity(categoryEnum);

                        categoryRepository.save(category);
                    });
        }
    }

    @Override
    public CategoryEntity findByName(CategoryEnum category) {
        return categoryRepository.findByCategoryEnumEquals(category);
    }
}
