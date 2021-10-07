package com.example.sellers.service;

import com.example.sellers.model.entity.ShopEntity;

import java.util.List;

public interface ShopService {
    void initShops();

    ShopEntity findById(Long shopId);

    List<String> findAllShopName();

    ShopEntity findByName(String name);
}
