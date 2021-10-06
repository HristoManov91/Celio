package com.example.sellers.service.impl;

import com.example.sellers.model.entity.UserEntity;
import com.example.sellers.repository.UserRepository;
import com.example.sellers.service.SaleService;
import com.example.sellers.service.UserRoleService;
import com.example.sellers.service.UserService;
import com.example.sellers.service.ShopService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ShopService shopService;
    private final UserRoleService userRoleService;
    private final SaleService saleService;

    public UserServiceImpl(UserRepository userRepository, ShopService shopService, UserRoleService userRoleService, SaleService saleService) {
        this.userRepository = userRepository;
        this.shopService = shopService;
        this.userRoleService = userRoleService;
        this.saleService = saleService;
    }


    @Override
    public Long count() {
        return userRepository.count();
    }

    @Override
    public void addUser(String fullName, String password, String email, Long shopId) {

        UserEntity seller = new UserEntity()
                .setFullName(fullName)
                .setPassword(password)
                .setEmail(email)
                .setDateOfAppointment(LocalDate.now())
                .setShop(shopService.findById(shopId))
                .addRole(userRoleService.findById(3L));

        userRepository.save(seller);
    }

    @Override
    public Optional<UserEntity> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public BigDecimal find_AB_betweenDate(UserEntity userEntity, LocalDate fromDate, LocalDate toDate) {
        saleService.findAllByUserAndDateBetween(userEntity, fromDate, toDate)
                .stream()
    }
}
