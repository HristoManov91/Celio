package com.example.sellers.service.impl;

import com.example.sellers.model.entity.PictureEntity;
import com.example.sellers.repository.PictureRepository;
import com.example.sellers.service.CloudinaryImage;
import com.example.sellers.service.CloudinaryService;
import com.example.sellers.service.PictureService;
import com.example.sellers.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;
    private final CloudinaryService cloudinaryService;

    public PictureServiceImpl(PictureRepository pictureRepository, CloudinaryService cloudinaryService) {
        this.pictureRepository = pictureRepository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public String findProfilePictureByUser() {
        return null;
    }

    @Override
    public PictureEntity createPicture(MultipartFile multipartFile) throws IOException {
        CloudinaryImage uploaded = cloudinaryService.upload(multipartFile);

        PictureEntity picture = new PictureEntity()
                .setPublicId(uploaded.getPublicId())
                .setUrl(uploaded.getUrl());

        return pictureRepository.save(picture);
    }
}
