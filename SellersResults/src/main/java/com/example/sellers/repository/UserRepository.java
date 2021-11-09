package com.example.sellers.repository;

import com.example.sellers.model.entity.SaleEntity;
import com.example.sellers.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail (String email);

    @Query("SELECT u.fullName FROM UserEntity u WHERE u.leftEmployee = false AND u.approved = true ")
    Set<String> findAllUserFullName();

    Optional<UserEntity> findUserEntityByFullNameAndLeftEmployeeIsFalseAndApprovedIsTrue(String fullName);

    Optional<UserEntity> findUserEntityByFullNameAndLeftEmployeeIsFalseAndApprovedIsFalse(String fullName);

    List<UserEntity> findAllByLeftEmployeeIsFalse();

    @Query("SELECT u.fullName FROM UserEntity u WHERE u.approved = false ")
    Set<String> findAllUsersFullNameWithoutApproval();
}
