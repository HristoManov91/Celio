package com.example.sellers.repository;

import com.example.sellers.model.entity.TargetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TargetRepository extends JpaRepository<TargetEntity, Long> {
}
