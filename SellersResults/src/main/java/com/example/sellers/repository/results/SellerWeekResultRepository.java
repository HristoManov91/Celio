package com.example.sellers.repository.results;

import com.example.sellers.model.entity.results.SellerWeekResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerWeekResultRepository extends JpaRepository<SellerWeekResultEntity , Long> {
}
