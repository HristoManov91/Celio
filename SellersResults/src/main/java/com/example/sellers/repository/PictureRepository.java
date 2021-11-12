package com.example.sellers.repository;

import com.example.sellers.model.entity.PictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<PictureEntity , Long> {

    void deletePictureEntityByPublicId(String publicId);
}
