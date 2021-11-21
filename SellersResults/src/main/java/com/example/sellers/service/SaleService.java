package com.example.sellers.service;

import com.example.sellers.model.entity.*;
import com.example.sellers.model.entity.results.SellerWeekResultEntity;
import com.example.sellers.model.entity.results.StoreWeekResultEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface SaleService {

    Set<SaleEntity> findUserSalesBetweenDates(String fullName , LocalDate fromDate , LocalDate toDate);

    Set<SaleEntity> findStoreSalesBetweenDates(String storeName , LocalDate fromDate , LocalDate toDate);

    void addSale(List<ProductEntity> productEntities , UserEntity userEntity , StoreEntity storeEntity);

    void addSaleForTests();

    BigDecimal findAB(Set<SaleEntity> sales);

    BigDecimal findAP(Set<SaleEntity> sales);

    BigDecimal findUPT(Set<SaleEntity> sales);

    BigDecimal findTurnover(Set<SaleEntity> sales);

    SellerWeekResultEntity calculateEmployeeWeekResults(String fullName , LocalDate fromDate , LocalDate toDate);

    StoreWeekResultEntity calculateStoreWeekResults(String storeName , LocalDate fromDate , LocalDate toDate);
}
