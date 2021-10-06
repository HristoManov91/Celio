package com.example.sellers.repository;

import com.example.sellers.model.entity.SaleEntity;
import com.example.sellers.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    //ToDo може би е правилно да е по email?
    Optional<UserEntity> findByEmail (String email);

}
