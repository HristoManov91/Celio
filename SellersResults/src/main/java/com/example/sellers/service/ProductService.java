package com.example.sellers.service;

import com.example.sellers.model.entity.enums.CategoryEnum;

import java.math.BigDecimal;

public interface ProductService {

    void addProduct(String name ,CategoryEnum category, BigDecimal price);

    long size();

    void removeProduct(String name);
}
