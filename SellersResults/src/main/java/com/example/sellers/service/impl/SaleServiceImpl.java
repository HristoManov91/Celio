package com.example.sellers.service.impl;

import com.example.sellers.model.entity.ProductEntity;
import com.example.sellers.model.entity.SaleEntity;
import com.example.sellers.model.entity.StoreEntity;
import com.example.sellers.model.entity.UserEntity;
import com.example.sellers.repository.SaleRepository;
import com.example.sellers.service.ProductService;
import com.example.sellers.service.SaleService;
import com.example.sellers.service.StoreService;
import com.example.sellers.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final ProductService productService;
    private final StoreService storeService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public SaleServiceImpl(SaleRepository saleRepository, UserService userService, ProductService productService, StoreService storeService, ModelMapper modelMapper) {
        this.saleRepository = saleRepository;
        this.productService = productService;
        this.userService = userService;
        this.storeService = storeService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<SaleEntity> findAllByUserAndDateBetween(UserEntity userEntity, LocalDate fromDate, LocalDate toDate) {
        return saleRepository.findAllByUserEntityAndDateOfSaleBetween(userEntity, fromDate, toDate).orElseThrow();
    }

    @Override
    public void addSale(List<ProductEntity> productEntities, UserEntity userEntity, StoreEntity storeEntity) {

        SaleEntity saleEntity = new SaleEntity()
                .setProducts(productEntities)
                .setDateOfSale(LocalDate.now())
                .setUserEntity(userEntity)
                .setStore(storeEntity);

        saleRepository.save(saleEntity);


        if (saleEntity.sumOfProductPrice().compareTo(userEntity.getBestBill() == null
                ? BigDecimal.ZERO
                : userEntity.getBestBill().sumOfProductPrice()) > 0) {
            userEntity.setBestBill(saleEntity);
            userService.saveUser(userEntity);
        }

        if (saleEntity.countOfProducts() > (
                userEntity.getMostProductsInBill() == null
                        ? 0
                        : userEntity.getMostProductsInBill().countOfProducts())) {
            userEntity.setMostProductsInBill(saleEntity);
            userService.saveUser(userEntity);
        }
    }

    @Override
    public void addSaleForTests() {
        if (saleRepository.count() == 0) {
            List<UserEntity> allUsers = userService.findAll();
            List<ProductEntity> allProducts = productService.findAll();
            List<StoreEntity> allStores = storeService.findAll();

            for (UserEntity userEntity : allUsers) {
                for (int j = 0; j < 2; j++) {
                    List<ProductEntity> products = new ArrayList<>();
                    for (int k = 0; k < 3; k++) {
                        int randomProductId = ThreadLocalRandom.current().nextInt(0, allProducts.size());
                        products.add(allProducts.get(randomProductId));
                    }
                    long randomShopId = ThreadLocalRandom.current().nextLong(1, allStores.size() + 1);
                    StoreEntity store = storeService.findById(randomShopId);
                    addSale(products, userEntity, store);
                }
            }
        }
    }
}
