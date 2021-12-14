package com.example.sellers.service;

import com.example.sellers.model.entity.ProductEntity;
import com.example.sellers.model.entity.enums.CategoryEnum;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductService {

    void addProduct(String name ,CategoryEnum category, BigDecimal price);

    long size();

    void removeProduct(String name);

    List<ProductEntity> getAllProductsOrderByCategory();

    List<ProductEntity> findAll();

    ProductEntity findProductByName(String name);
}
