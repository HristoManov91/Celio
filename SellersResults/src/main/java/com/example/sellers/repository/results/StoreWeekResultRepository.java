package com.example.sellers.repository.results;

import com.example.sellers.model.entity.results.StoreWeekResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreWeekResultRepository extends JpaRepository<StoreWeekResultEntity , Long> {
}
