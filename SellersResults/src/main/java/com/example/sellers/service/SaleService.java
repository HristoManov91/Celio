package com.example.sellers.service;

import com.example.sellers.model.entity.ProductEntity;
import com.example.sellers.model.entity.SaleEntity;
import com.example.sellers.model.entity.StoreEntity;
import com.example.sellers.model.entity.UserEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface SaleService {

    Set<SaleEntity> findAllByUserAndDateBetween(UserEntity userEntity , LocalDate fromDate , LocalDate toDate);

    void addSale(List<ProductEntity> productEntities , UserEntity userEntity , StoreEntity storeEntity);

    void addSaleForTests();
}
