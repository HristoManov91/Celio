package com.example.sellers.repository.results;

import com.example.sellers.model.entity.results.StoreWeekResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreWeekResultRepository extends JpaRepository<StoreWeekResultEntity , Long> {

    @Query("SELECT s FROM StoreWeekResultEntity s ORDER BY s.year DESC , s.weekOfYear DESC ")
    List<StoreWeekResultEntity> findAllByOrder();
}
