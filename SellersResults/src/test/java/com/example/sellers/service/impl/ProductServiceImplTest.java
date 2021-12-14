package com.example.sellers.service.impl;

import com.example.sellers.model.entity.ProductEntity;
import com.example.sellers.model.entity.enums.CategoryEnum;
import com.example.sellers.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith({MockitoExtension.class})
class ProductServiceImplTest {

    private ProductServiceImpl productService;
    private ProductEntity product1, product2;

    @Mock
    private ProductRepository mockProductRepository;

    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl(mockProductRepository);

        product1 = new ProductEntity().setName("Rolisse5").setCategory(CategoryEnum.JEAN).setPrice(BigDecimal.valueOf(119.99));

        product2 = new ProductEntity().setName("Necetwo").setCategory(CategoryEnum.POLO).setPrice(BigDecimal.valueOf(49.99));
    }

    @Test
    void addProduct() {

    }

    @Test
    void size() {
    }

    @Test
    void removeProduct() {
    }

    @Test
    void getAllProductsOrderByCategory() {
    }

    @Test
    void findAll() {
    }

    @Test
    void count() {
    }

    @Test
    void findProductByName() {
    }
}