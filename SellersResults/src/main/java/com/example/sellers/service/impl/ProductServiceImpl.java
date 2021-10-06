package com.example.sellers.service.impl;

import com.example.sellers.model.entity.enums.CategoryEnum;
import com.example.sellers.model.entity.ProductEntity;
import com.example.sellers.repository.ProductRepository;
import com.example.sellers.service.CategoryService;
import com.example.sellers.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }


    @Override
    public void addProduct(String name , CategoryEnum category, BigDecimal price) {
        ProductEntity product = new ProductEntity(name , categoryService.findByName(category) , price);

        productRepository.save(product);
    }

    @Override
    public long size() {
        return productRepository.count();
    }

    @Override
    public void removeProduct(String name) {
        ProductEntity productEntity = productRepository.findByName(name).orElseThrow();
        productRepository.delete(productEntity);
    }
}
