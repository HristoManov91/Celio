package com.example.sellers.repository;

import com.example.sellers.model.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Long> {

    @Query ("SELECT se.name FROM StoreEntity se")
    List<String> findAllStoresNames();

    Optional<StoreEntity> findByName(String name);
}
