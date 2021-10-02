package com.example.sellers.service;

import com.example.sellers.model.entity.UserEntity;
import com.example.sellers.model.entity.UserRoleEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserService {

    Long count();

    void addUser(String fullName, String password, String email, LocalDate dateOfAppointment,
                 List<UserRoleEntity> roles , Long shopId);

    Optional<UserEntity> findUserByEmail(String fullName);
}
