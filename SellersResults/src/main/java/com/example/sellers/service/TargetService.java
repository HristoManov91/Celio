package com.example.sellers.service;

import com.example.sellers.model.entity.TargetEntity;
import com.example.sellers.model.entity.enums.MonthEnum;

import java.math.BigDecimal;

public interface TargetService {

    TargetEntity createTarget(MonthEnum month , BigDecimal value);
}
