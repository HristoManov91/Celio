package com.example.sellers.service.impl;

import com.example.sellers.model.entity.TargetEntity;
import com.example.sellers.model.entity.enums.MonthEnum;
import com.example.sellers.repository.TargetRepository;
import com.example.sellers.service.TargetService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TargetServiceImpl implements TargetService {

    private final TargetRepository targetRepository;

    public TargetServiceImpl(TargetRepository targetRepository) {
        this.targetRepository = targetRepository;
    }

    @Override
    public TargetEntity createTarget(MonthEnum month, BigDecimal value) {
        return new TargetEntity().setMonth(month).setTarget(value);
    }
}
