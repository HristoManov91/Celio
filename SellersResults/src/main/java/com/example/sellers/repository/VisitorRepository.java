package com.example.sellers.repository;

import com.example.sellers.model.entity.VisitorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface VisitorRepository extends JpaRepository<VisitorEntity , Long> {

    @Query("SELECT SUM(v.quantity) FROM VisitorEntity v WHERE v.store.name = ?1 AND v.date BETWEEN ?2 AND ?3 ")
    Integer countVisitorsBetweenDate(String storeName , LocalDate fromDate , LocalDate toDate);
}
