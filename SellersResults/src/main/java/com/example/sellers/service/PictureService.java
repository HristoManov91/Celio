package com.example.sellers.service;

import com.example.sellers.model.entity.PictureEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PictureService {

    PictureEntity createPicture(MultipartFile multipartFile) throws IOException;

    void deletePictureByPublicId(String pictureId);
}
