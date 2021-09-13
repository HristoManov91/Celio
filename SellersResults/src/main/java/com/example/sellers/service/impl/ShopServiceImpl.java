package com.example.sellers.service.impl;

import com.example.sellers.model.entity.ShopEntity;
import com.example.sellers.model.entity.TargetEntity;
import com.example.sellers.model.entity.enums.MonthEnum;
import com.example.sellers.repository.ShopRepository;
import com.example.sellers.service.ShopService;
import com.example.sellers.service.TargetService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final TargetService targetService;

    public ShopServiceImpl(ShopRepository shopRepository, TargetService targetService) {
        this.shopRepository = shopRepository;
        this.targetService = targetService;
    }

    @Override
    public void initShops() {
        ShopEntity theMall = new ShopEntity().setName("TheMall").setDescription("Description for TheMall");
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
    public void setTarget(long shopId, BigDecimal value, MonthEnum month) {
        TargetEntity target = targetService.createTarget(month, value);
        ShopEntity shop = shopRepository.findById(shopId).orElse(null);
        //ToDo да измисля как да направя цел на всеки магазин
    }
}
