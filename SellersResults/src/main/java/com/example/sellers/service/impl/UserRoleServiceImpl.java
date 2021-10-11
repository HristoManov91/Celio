package com.example.sellers.service.impl;

import com.example.sellers.model.entity.UserRoleEntity;
import com.example.sellers.model.entity.enums.UserRoleEnum;
import com.example.sellers.repository.UserRoleRepository;
import com.example.sellers.service.UserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }


    @Override
    public void initUserRoles() {
        //Todo да добавя описание на всяка роля
        if (userRoleRepository.count() == 0) {
            UserRoleEntity admin = new UserRoleEntity(UserRoleEnum.ADMIN, "Description for Admin role");
            userRoleRepository.save(admin);

            UserRoleEntity manager = new UserRoleEntity(UserRoleEnum.MANAGER, "Description for Manager role");
            userRoleRepository.save(manager);

            UserRoleEntity seller = new UserRoleEntity(UserRoleEnum.SELLER, "Description for Seller role");
            userRoleRepository.save(seller);
        }
    }

    @Override
    public UserRoleEntity findById(Long id) {
        return userRoleRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("USER role not found. Please seed the roles."));
    }

    @Override
    public UserRoleEntity findUserRoleByName(UserRoleEnum role) {
        return userRoleRepository.findUserRoleEntityByRole(role)
                .orElseThrow(() -> new IllegalStateException("USER role not found."));
    }
}
