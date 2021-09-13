package com.example.sellers.service;

import com.example.sellers.model.entity.UserRoleEntity;

public interface UserRoleService {

    void initUserRoles();

    UserRoleEntity findById(Long id);
}
