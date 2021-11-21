package com.example.sellers.service.impl;

import com.example.sellers.model.entity.StoreEntity;
import com.example.sellers.repository.StoreRepository;
import com.example.sellers.repository.VisitorRepository;
import com.example.sellers.service.StoreService;
import com.example.sellers.service.VisitorService;
import com.example.sellers.web.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final VisitorService visitorService;

    public StoreServiceImpl(StoreRepository storeRepository, VisitorService visitorService) {
        this.storeRepository = storeRepository;
        this.visitorService = visitorService;
    }

    @Override
    public void initStores() {
        if (storeRepository.count() == 0) {
            StoreEntity theMall = new StoreEntity().setName("The Mall").setDescription("Description for TheMall");
            storeRepository.save(theMall);

            StoreEntity paradise = new StoreEntity().setName("Paradise").setDescription("Description for Paradise");
            storeRepository.save(paradise);

            StoreEntity mallOfSofia = new StoreEntity().setName("Mall Of Sofia").setDescription("Description for MallOfSofia");
            storeRepository.save(mallOfSofia);

            StoreEntity parkCenter = new StoreEntity().setName("Park Center").setDescription("Description for Park Center");
            storeRepository.save(parkCenter);
        }
    }

    @Override
    public StoreEntity findById(Long storeId) {
        return storeRepository.findById(storeId).orElse(null);
    }

    @Override
    public Set<String> findAllStoresNames() {
        return storeRepository.findAllStoresNames();
    }

    @Override
    public StoreEntity findByName(String name) {
        return storeRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public BigDecimal calculatePercentage(String storeName, Integer salesCount, LocalDate fromDate, LocalDate toDate) {
        StoreEntity store = storeRepository
                .findByName(storeName)
                .orElseThrow(() -> new ObjectNotFoundException("Store with this name " + storeName + " not found!", storeName));

        Integer visitors = visitorService.countStoreVisitorsBetweenDate(storeName, fromDate, toDate);

        
    }

    @Override
    public List<StoreEntity> findAll() {
        return storeRepository.findAll();
    }
}
