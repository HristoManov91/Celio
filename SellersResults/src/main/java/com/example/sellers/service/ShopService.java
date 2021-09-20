package com.example.sellers.service;

import com.example.sellers.model.entity.ShopEntity;

public interface ShopService {
    void initShops();

    ShopEntity findById(Long shopId);
}
