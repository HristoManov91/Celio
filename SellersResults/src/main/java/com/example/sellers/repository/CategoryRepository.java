package com.example.sellers.repository;

import com.example.sellers.model.entity.CategoryEntity;
import com.example.sellers.model.entity.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity , Long> {

    CategoryEntity findByCategoryEnumEquals(CategoryEnum categoryEnum);
}
