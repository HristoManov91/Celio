package com.example.sellers.service.impl;

import com.example.sellers.model.entity.ShopEntity;
import com.example.sellers.repository.ShopRepository;
import com.example.sellers.service.ShopService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;

    public ShopServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public void initShops() {
        ShopEntity theMall = new ShopEntity().setName("The Mall").setDescription("Description for TheMall");
        shopRepository.save(theMall);

        ShopEntity paradise = new ShopEntity().setName("Paradise").setDescription("Description for Paradise");
        shopRepository.save(paradise);

        ShopEntity mallOfSofia = new ShopEntity().setName("Mall Of Sofia").setDescription("Description for MallOfSofia");
        shopRepository.save(mallOfSofia);

        ShopEntity parkCenter = new ShopEntity().setName("Park Center").setDescription("Description for Park Center");
        shopRepository.save(parkCenter);
    }

    @Override
    public ShopEntity findById(Long shopId) {
        return shopRepository.findById(shopId).orElse(null);
    }

    @Override
    public List<String> findAllShopName() {
        return shopRepository.findAllShopName();
    }

    @Override
    public ShopEntity findByName(String name) {
        return shopRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
    }
}
