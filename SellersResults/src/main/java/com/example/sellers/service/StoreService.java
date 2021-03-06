package com.example.sellers.service;

import com.example.sellers.model.entity.SaleEntity;
import com.example.sellers.model.entity.StoreEntity;
import org.apache.tomcat.jni.Local;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface StoreService {
    void initStores();

    void addStore();

    StoreEntity findById(Long shopId);

    Set<String> findAllStoresNames();

    StoreEntity findByName(String name);

    Integer findVisitorsBetweenDates(String storeName , LocalDate fromDate , LocalDate toDate);

    List<StoreEntity> findAll();
}
