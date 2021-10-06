package com.example.sellers.service;

import com.example.sellers.model.entity.SaleEntity;
import com.example.sellers.model.entity.UserEntity;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

public interface SaleService {

    Set<SaleEntity> findAllByUserAndDateBetween(UserEntity userEntity , LocalDate fromDate , LocalDate toDate);
}
