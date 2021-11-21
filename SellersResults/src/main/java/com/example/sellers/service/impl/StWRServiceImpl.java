package com.example.sellers.service.impl;

import com.example.sellers.model.entity.results.StoreWeekResultEntity;
import com.example.sellers.repository.results.StoreWeekResultRepository;
import com.example.sellers.service.SaleService;
import com.example.sellers.service.StWRService;
import com.example.sellers.service.StoreService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
public class StWRServiceImpl implements StWRService {

    private final StoreWeekResultRepository storeWeekResultRepository;
    private final StoreService storeService;
    private final SaleService saleService;

    public StWRServiceImpl(StoreWeekResultRepository storeWeekResultRepository, StoreService storeService, SaleService saleService) {
        this.storeWeekResultRepository = storeWeekResultRepository;
        this.storeService = storeService;
        this.saleService = saleService;
    }

    @Override
    public void weekResults() {
        LocalDate fromDate = LocalDate.now().minusDays(7L);
        LocalDate toDate = LocalDate.now().minusDays(1L);
        Set<String> storesNames = storeService.findAllStoresNames();

        for (String storesName : storesNames) {
            StoreWeekResultEntity storeResults = saleService.calculateStoreWeekResults(storesName , fromDate , toDate);
        }
    }
}
