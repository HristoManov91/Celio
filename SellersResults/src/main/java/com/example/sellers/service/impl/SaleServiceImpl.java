package com.example.sellers.service.impl;

import com.example.sellers.model.entity.ProductEntity;
import com.example.sellers.model.entity.SaleEntity;
import com.example.sellers.model.entity.UserEntity;
import com.example.sellers.repository.SaleRepository;
import com.example.sellers.service.ProductService;
import com.example.sellers.service.SaleService;
import com.example.sellers.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final UserService userService;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public SaleServiceImpl(SaleRepository saleRepository, UserService userService, ProductService productService, ModelMapper modelMapper) {
        this.saleRepository = saleRepository;
        this.userService = userService;
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<SaleEntity> findAllByUserAndDateBetween(UserEntity userEntity, LocalDate fromDate, LocalDate toDate) {
        return saleRepository.findAllByUserEntityAndDateOfSaleBetween(userEntity, fromDate, toDate).orElseThrow();
    }

    @Override
    public void addSale(List<ProductEntity> productEntities , UserEntity userEntity) {
        SaleEntity saleEntity =
                new SaleEntity().setProducts(productEntities).setDateOfSale(LocalDate.now()).setUserEntity(userEntity);

        saleRepository.save(saleEntity);

        if (saleEntity.sumOfProductPrice().compareTo(userEntity.getBestBill().sumOfProductPrice()) > 0){
            userEntity.setBestBill(saleEntity);
        }

        if (saleEntity.countOfProducts() > userEntity.getMostProductsInBill().countOfProducts()){
            userEntity.setMostProductsInBill(saleEntity);
        }
    }

    @Override
    public void addSaleForTests() {
        List<UserEntity> allUsers = userService.findAll();
        List<ProductEntity> allProducts = productService.findAll();
        Random random = new Random();

        for (int i = 0; i < allUsers.size(); i++) {
            List<SaleEntity> sales = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                long randomLong = random.nextLong(productService.count());
                sales.add()
            }
        }
    }
}
