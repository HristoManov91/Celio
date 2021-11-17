package com.example.sellers.service;

import com.example.sellers.model.entity.StoreEntity;

import java.util.List;

public interface StoreService {
    void initStores();

    StoreEntity findById(Long shopId);

    List<String> findAllStoresNames();

    StoreEntity findByName(String name);

    List<StoreEntity> findAll();
}
