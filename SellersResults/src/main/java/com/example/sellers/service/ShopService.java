package com.example.sellers.service;

import com.example.sellers.model.entity.ShopEntity;
import com.example.sellers.model.entity.enums.MonthEnum;

import java.math.BigDecimal;

public interface ShopService {
    void initShops();

    ShopEntity findById(Long shopId);

    void setTarget(long shopId, BigDecimal target, MonthEnum month);
}
