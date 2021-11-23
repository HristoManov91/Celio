package com.example.sellers.service;

import com.example.sellers.model.entity.StoreEntity;

import java.time.LocalDate;
import java.util.List;

public interface VisitorService {

    Integer countStoreVisitorsBetweenDate(String storeName , LocalDate fromDate , LocalDate toDate);

    void addTestVisitors(List<StoreEntity> stores);
}
