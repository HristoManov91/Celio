package com.example.sellers.service.impl;

import com.example.sellers.model.entity.*;
import com.example.sellers.model.entity.results.SellerWeekResultEntity;
import com.example.sellers.model.entity.results.StoreWeekResultEntity;
import com.example.sellers.repository.SaleRepository;
import com.example.sellers.service.ProductService;
import com.example.sellers.service.SaleService;
import com.example.sellers.service.StoreService;
import com.example.sellers.service.UserService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final ProductService productService;
    private final StoreService storeService;
    private final UserService userService;

    public SaleServiceImpl(SaleRepository saleRepository, UserService userService, ProductService productService, StoreService storeService) {
        this.saleRepository = saleRepository;
        this.productService = productService;
        this.userService = userService;
        this.storeService = storeService;
    }

    @Override
    public Set<SaleEntity> findUserSalesBetweenDates(String fullName, LocalDate fromDate, LocalDate toDate) {
        return saleRepository
                .findUserSalesBetweenDates(fullName, fromDate, toDate)
                .orElse(null);
    }

    @Override
    public Set<SaleEntity> findStoreSalesBetweenDates(String storeName, LocalDate fromDate, LocalDate toDate) {
        return saleRepository
                .findStoreSalesBetweenDates(storeName, fromDate, toDate)
                .orElse(null);
    }

    @Override
    public void addSale(List<ProductEntity> productEntities, UserEntity userEntity, StoreEntity storeEntity) {

        SaleEntity saleEntity = new SaleEntity()
                .setProducts(productEntities)
                .setDateOfSale(LocalDate.now())
                .setUser(userEntity)
                .setStore(storeEntity);

        saleRepository.save(saleEntity);


        if (saleEntity.sumOfProductPrice().compareTo(userEntity.getBestBill() == null
                ? BigDecimal.ZERO
                : userEntity.getBestBill().sumOfProductPrice()) > 0) {
            userEntity.setBestBill(saleEntity);
        }

        if (saleEntity.countOfProducts() >= (
                userEntity.getMostProductsInBill() == null
                        ? 0
                        : userEntity.getMostProductsInBill().countOfProducts())) {
            userEntity.setMostProductsInBill(saleEntity);
        }
        userService.saveUser(userEntity);
    }

    @Override
    public BigDecimal findAB(Set<SaleEntity> sales) {
        BigDecimal reduce = sales.stream()
                .map(SaleEntity::sumOfProductPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        //ToDo да се тества дали работи правилно
        return reduce.divide(new BigDecimal(sales.size()), 2, RoundingMode.CEILING);
    }

    @Override
    public BigDecimal findAP(Set<SaleEntity> sales) {
        BigDecimal priceSum = sales
                .stream()
                .map(SaleEntity::sumOfProductPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        long countOfAllProducts = sales
                .stream()
                .map(SaleEntity::countOfProducts).count();

        return priceSum.divide(new BigDecimal(countOfAllProducts), 2, RoundingMode.CEILING);
    }

    @Override
    public BigDecimal findUPT(Set<SaleEntity> sales) {
        long countOfAllProducts = sales
                .stream()
                .map(SaleEntity::countOfProducts)
                .count();

        return BigDecimal.valueOf(countOfAllProducts)
                .divide(new BigDecimal(sales.size()), 2, RoundingMode.CEILING);
    }

    @Override
    public BigDecimal findTurnover(Set<SaleEntity> sales) {
        return sales.stream()
                .map(SaleEntity::sumOfProductPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.CEILING);
    }

    @Override
    public SellerWeekResultEntity calculateEmployeeWeekResults(String fullName, LocalDate fromDate, LocalDate toDate) {
        Set<SaleEntity> sales = findUserSalesBetweenDates(fullName, fromDate, toDate);

        if (sales == null){
            return null;
        }
        int woy = fromDate.minusDays(1).get(WeekFields.of(Locale.getDefault()).weekOfYear());

        return (SellerWeekResultEntity) new SellerWeekResultEntity()
                .setEmployeeName(fullName)
                .setWeakOfYear(woy)
                .setAveragePricePerBasket(findAB(sales))
                .setAveragePricePerProducts(findAP(sales))
                .setUpt(findUPT(sales))
                .setCountOfSales(sales.size())
                .setTurnover(findTurnover(sales));
    }

    @Override
    public StoreWeekResultEntity calculateStoreWeekResults(String storeName, LocalDate fromDate, LocalDate toDate) {
        Set<SaleEntity> sales = findStoreSalesBetweenDates(storeName, fromDate, toDate);

        if (sales == null){
            return null;
        }
        int woy = fromDate.minusDays(1).get(WeekFields.of(Locale.getDefault()).weekOfYear());

        new StoreWeekResultEntity()
                .setStoreName(storeName)
                .setWeekOfYear(woy)
                .setPercentageSales(storeService.calculatePercentage(storeName , sales.size() , fromDate , toDate))
                .setAveragePricePerBasket(findAB(sales))
                .setAveragePricePerProducts(findAP(sales))
                .setUpt(findUPT(sales))
                .setCountOfSales(sales.size())
                .setTurnover(findTurnover(sales));
    }

    @Override
    public void addSaleForTests() {
        List<UserEntity> allUsers = userService.findAll();
        List<ProductEntity> allProducts = productService.findAll();
//        List<StoreEntity> allStores = storeService.findAll();

        for (UserEntity userEntity : allUsers) {
            int countSales = ThreadLocalRandom.current().nextInt(0, 10);
            for (int j = 0; j < countSales; j++) {
                List<ProductEntity> products = new ArrayList<>();
                int countProducts = ThreadLocalRandom.current().nextInt(1, 10);
                for (int k = 0; k < countProducts; k++) {
                    int randomProductId = ThreadLocalRandom.current().nextInt(0, allProducts.size());
                    products.add(allProducts.get(randomProductId));
                }
//                long randomShopId = ThreadLocalRandom.current().nextLong(1, allStores.size() + 1);
                long shopId = userEntity.getStore().getId();
                StoreEntity store = storeService.findById(shopId);
                addSale(products, userEntity, store);
            }
        }
    }
}
