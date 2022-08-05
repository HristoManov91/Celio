package com.example.sellers.repository;

import com.example.sellers.model.entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Repository
public interface SaleRepository extends JpaRepository<SaleEntity, Long> {

    @Query("SELECT s FROM SaleEntity s WHERE s.user.fullName = ?1 AND s.dateOfSale BETWEEN ?2 and ?3 ")
    Optional<Set<SaleEntity>> findUserSalesBetweenDates
            (String  fullName, LocalDate fromDate, LocalDate toDate);

    @Query("SELECT s FROM SaleEntity s WHERE s.store.name = ?1 AND s.dateOfSale BETWEEN ?2 and ?3 ")
    Optional<Set<SaleEntity>> findStoreSalesBetweenDates
            (String storeName, LocalDate fromDate, LocalDate toDate);
}
