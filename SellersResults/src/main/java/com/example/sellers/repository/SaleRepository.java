package com.example.sellers.repository;

import com.example.sellers.model.entity.SaleEntity;
import com.example.sellers.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Repository
public interface SaleRepository extends JpaRepository<SaleEntity, Long> {

    Optional<Set<SaleEntity>> findAllByUserEntityAndDateOfSaleBetween
            (UserEntity userEntity, LocalDate fromDate, LocalDate toDate);


}
