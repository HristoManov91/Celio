package com.example.sellers.service.impl;

import com.example.sellers.model.entity.ProductEntity;
import com.example.sellers.model.entity.enums.CategoryEnum;
import com.example.sellers.repository.ProductRepository;
import com.example.sellers.service.ProductService;
import com.example.sellers.web.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void addProduct(String name, CategoryEnum category, BigDecimal price) {
        ProductEntity product = new ProductEntity()
                .setName(name)
                .setCategory(category)
                .setPrice(price);

        productRepository.save(product);
    }

    @Override
    public long size() {
        return productRepository.count();
    }

    @Override
    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductEntity> getAllProductsOrderByCategory() {
        return productRepository.findAllOrderByCategory()
                .orElseThrow(() -> new IllegalStateException("There are no products in the database"));
    }

    @Override
    public ProductEntity findProductByName(String name) {

        return productRepository.findByName(name)
                .orElseThrow(() -> new ObjectNotFoundException("Product with this name " + name + " not found" , name));
    }

    @Override
    public void removeProduct(String name) {

        ProductEntity product = productRepository.findByName(name)
                .orElseThrow(() -> new ObjectNotFoundException("Product with this name " + name + " not found" , name));

        productRepository.delete(product);
    }
}
