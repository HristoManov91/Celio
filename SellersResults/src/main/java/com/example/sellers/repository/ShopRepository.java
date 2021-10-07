package com.example.sellers.repository;

import com.example.sellers.model.entity.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<ShopEntity , Long> {

    @Query ("SELECT se.name FROM ShopEntity se")
    List<String> findAllShopName();

    Optional<ShopEntity> findByName(String name);
}
