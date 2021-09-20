package com.example.sellers.service.impl;

import com.example.sellers.model.entity.UserEntity;
import com.example.sellers.model.entity.UserRoleEntity;
import com.example.sellers.repository.UserRepository;
import com.example.sellers.service.UserService;
import com.example.sellers.service.ShopService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ShopService shopService;

    public UserServiceImpl(UserRepository userRepository, ShopService shopService) {
        this.userRepository = userRepository;
        this.shopService = shopService;
    }


    @Override
    public Long count() {
        return userRepository.count();
    }

    @Override
    public void addUser(String fullName, String password, String email, LocalDate dateOfAppointment ,
                        List<UserRoleEntity> roles , Long shopId) {

        UserEntity seller = new UserEntity()
                .setFullName(fullName)
                .setPassword(password)
                .setEmail(email)
                .setDateOfAppointment(dateOfAppointment)
                .setRoles(roles)
                .setShop(shopService.findById(shopId));

        userRepository.save(seller);
    }

    @Override
    public Optional<UserEntity> findUserByName(String fullName) {
        return userRepository.findByFullName(fullName);
    }
}
