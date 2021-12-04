package com.example.sellers.service.impl;

import com.example.sellers.model.dto.StoreWeekResultDTO;
import com.example.sellers.model.entity.results.StoreWeekResultEntity;
import com.example.sellers.repository.results.StoreWeekResultRepository;
import com.example.sellers.service.SaleService;
import com.example.sellers.service.StWRService;
import com.example.sellers.service.StoreService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StWRServiceImpl implements StWRService {

    private final StoreWeekResultRepository storeWeekResultRepository;
    private final StoreService storeService;
    private final SaleService saleService;
    private final ModelMapper modelMapper;

    public StWRServiceImpl(StoreWeekResultRepository storeWeekResultRepository, StoreService storeService, SaleService saleService, ModelMapper modelMapper) {
        this.storeWeekResultRepository = storeWeekResultRepository;
        this.storeService = storeService;
        this.saleService = saleService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void weekResults() {
        LocalDate fromDate = LocalDate.now().minusDays(7L);
        LocalDate toDate = LocalDate.now().minusDays(1L);
        Set<String> storesNames = storeService.findAllStoresNames();

        for (String storesName : storesNames) {
            StoreWeekResultEntity storeResults = saleService.calculateStoreWeekResults(storesName , fromDate , toDate);
            if (storeResults != null){
                storeWeekResultRepository.save(storeResults);
            }
        }
    }

    @Override
    public void weekTestResults() {
        LocalDate fromDate = LocalDate.of(2021, 11, 1);
        LocalDate toDate = LocalDate.of(2021, 11, 7);
        Set<String> storesNames = storeService.findAllStoresNames();

        for (int i = 0; i < 3; i++) {
            for (String storesName : storesNames) {

                StoreWeekResultEntity storeResults = saleService.calculateStoreWeekResults(storesName , fromDate , toDate);
                if (storeResults != null){
                    storeWeekResultRepository.save(storeResults);
                }
            }
            fromDate = fromDate.plusDays(7);
            toDate = toDate.plusDays(7);
        }
    }

    @Override
    public List<StoreWeekResultDTO> getAllStoresWeekResults() {
        return storeWeekResultRepository.findAllByOrder().stream().map(this::asStoreWeekResult).toList();
    }

    @Override
    public Optional<StoreWeekResultDTO> getById(Long id) {
        return storeWeekResultRepository.findById(id).map(this::asStoreWeekResult);
    }

    private StoreWeekResultDTO asStoreWeekResult(StoreWeekResultEntity weekResults){
        return modelMapper.map(weekResults , StoreWeekResultDTO.class);
    }
}
