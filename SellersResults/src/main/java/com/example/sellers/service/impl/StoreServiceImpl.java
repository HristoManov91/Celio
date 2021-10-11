package com.example.sellers.service.impl;

import com.example.sellers.model.entity.StoreEntity;
import com.example.sellers.repository.StoreRepository;
import com.example.sellers.service.StoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public void initShops() {
        StoreEntity theMall = new StoreEntity().setName("The Mall").setDescription("Description for TheMall");
        storeRepository.save(theMall);

        StoreEntity paradise = new StoreEntity().setName("Paradise").setDescription("Description for Paradise");
        storeRepository.save(paradise);

        StoreEntity mallOfSofia = new StoreEntity().setName("Mall Of Sofia").setDescription("Description for MallOfSofia");
        storeRepository.save(mallOfSofia);

        StoreEntity parkCenter = new StoreEntity().setName("Park Center").setDescription("Description for Park Center");
        storeRepository.save(parkCenter);
    }

    @Override
    public StoreEntity findById(Long storeId) {
        return storeRepository.findById(storeId).orElse(null);
    }

    @Override
    public List<String> findAllStoresNames() {
        return storeRepository.findAllStoresNames();
    }

    @Override
    public StoreEntity findByName(String name) {
        return storeRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
    }
}
