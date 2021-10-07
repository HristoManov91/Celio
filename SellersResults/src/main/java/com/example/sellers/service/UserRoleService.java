package com.example.sellers.service;

import com.example.sellers.model.entity.UserRoleEntity;

import java.util.Optional;

public interface UserRoleService {

    void initUserRoles();

    UserRoleEntity findById(Long id);
}
