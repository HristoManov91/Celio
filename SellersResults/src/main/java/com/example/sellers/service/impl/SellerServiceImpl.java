package com.example.sellers.service.impl;

import com.example.sellers.model.entity.SellerEntity;
import com.example.sellers.model.entity.UserRoleEntity;
import com.example.sellers.repository.SellerRepository;
import com.example.sellers.service.SellerService;
import com.example.sellers.service.ShopService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final ShopService shopService;

    public SellerServiceImpl(SellerRepository sellerRepository, ShopService shopService) {
        this.sellerRepository = sellerRepository;
        this.shopService = shopService;
    }


    @Override
    public void addSeller(String fullName, String password, String email, LocalDate dateOfAppointment ,
                          List<UserRoleEntity> roles , Long shopId) {

        SellerEntity seller = new SellerEntity()
                .setFullName(fullName)
                .setPassword(password)
                .setEmail(email)
                .setDateOfAppointment(dateOfAppointment)
                .setRoles(roles)
                .setShop(shopService.findById(shopId));

        sellerRepository.save(seller);
    }
}
