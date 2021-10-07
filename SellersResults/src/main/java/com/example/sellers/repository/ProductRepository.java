package com.example.sellers.repository;

import com.example.sellers.model.entity.CategoryEntity;
import com.example.sellers.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity , Long> {

    Optional<ProductEntity> findByName(String name);

    long countAllByCategory(CategoryEntity category);
}
