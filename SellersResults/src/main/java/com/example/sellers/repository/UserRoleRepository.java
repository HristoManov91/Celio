package com.example.sellers.repository;

import com.example.sellers.model.entity.UserRoleEntity;
import com.example.sellers.model.entity.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity , Long> {

    Optional<UserRoleEntity> findUserRoleEntityByRole(UserRoleEnum role);
}
