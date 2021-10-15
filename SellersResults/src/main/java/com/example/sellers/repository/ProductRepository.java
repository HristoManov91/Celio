package com.example.sellers.repository;

import com.example.sellers.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity , Long> {

    Optional<ProductEntity> findByName(String name);

    @Query("SELECT p FROM ProductEntity p ORDER BY p.category")
    Optional<List<ProductEntity>> findAllOrderByCategory();
}
