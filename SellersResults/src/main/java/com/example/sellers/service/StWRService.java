package com.example.sellers.service;

import com.example.sellers.model.dto.StoreWeekResultDTO;

import java.util.List;
import java.util.Optional;

public interface StWRService {

    void weekResults();

    void weekTestResults();

    List<StoreWeekResultDTO> getAllStoresWeekResults();

    Optional<StoreWeekResultDTO> getById(Long id);
}
