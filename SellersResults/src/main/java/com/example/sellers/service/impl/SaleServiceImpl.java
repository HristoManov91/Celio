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
    public void addSale(List<ProductEntity> productEntities, UserEntity userEntity, StoreEntity storeEntity, LocalDate date) {

        SaleEntity saleEntity = new SaleEntity()
                .setProducts(productEntities)
                .setDateOfSale(date)
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
    public BigDecimal findAB(BigDecimal turnover, Integer countOfSales) {
        return turnover.divide(new BigDecimal(countOfSales), 2, RoundingMode.CEILING);
    }

    @Override
    public BigDecimal findAP(BigDecimal turnover, Integer countOfProducts) {
        return turnover.divide(new BigDecimal(countOfProducts), 2, RoundingMode.CEILING);
    }

    @Override
    public BigDecimal findUPT(Integer countOfProducts, Integer countOfSales) {
        return BigDecimal.valueOf(countOfProducts).divide(new BigDecimal(countOfSales), 2, RoundingMode.CEILING);
    }

    @Override
    public BigDecimal findTurnover(Set<SaleEntity> sales) {
        return sales.stream()
                .map(SaleEntity::sumOfProductPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.CEILING);
    }

    @Override
    public Integer findCountOfProducts(Set<SaleEntity> sales) {
        return sales.stream().mapToInt(SaleEntity::countOfProducts).sum();
    }

    @Override
    public SellerWeekResultEntity calculateEmployeeWeekResults(String fullName, LocalDate fromDate, LocalDate toDate) {
        Set<SaleEntity> sales = findUserSalesBetweenDates(fullName, fromDate, toDate);

        if (sales == null) {
            return null;
        }

        int countOfSales = sales.size();
        int countOfProducts = findCountOfProducts(sales);
        BigDecimal turnover = findTurnover(sales);

        int woy = fromDate.minusDays(1).get(WeekFields.of(Locale.getDefault()).weekOfYear());

        return (SellerWeekResultEntity) new SellerWeekResultEntity()
                .setEmployeeName(fullName)
                .setAveragePricePerBasket(findAB(turnover, countOfSales))
                .setAveragePricePerProducts(findAP(turnover, countOfProducts))
                .setUpt(findUPT(countOfProducts, countOfSales))
                .setCountOfSales(countOfSales)
                .setCountOfProducts(countOfProducts)
                .setTurnover(turnover)
                .setWeekOfYear(woy)
                .setYear(fromDate.getYear());
    }

    @Override
    public StoreWeekResultEntity calculateStoreWeekResults(String storeName, LocalDate fromDate, LocalDate toDate) {
        Set<SaleEntity> sales = findStoreSalesBetweenDates(storeName, fromDate, toDate);

        if (sales == null) {
            return null;
        }

        int countOfSales = sales.size();
        int countOfProducts = findCountOfProducts(sales);
        BigDecimal turnover = findTurnover(sales);
        Integer visitors = storeService.findVisitorsBetweenDates(storeName, fromDate, toDate);
        BigDecimal percentageSales = BigDecimal.valueOf((countOfSales * 1.0 / visitors) * 100).setScale(2, RoundingMode.CEILING);

        int woy = fromDate.minusDays(1).get(WeekFields.of(Locale.getDefault()).weekOfYear());

        return (StoreWeekResultEntity) new StoreWeekResultEntity()
                .setStoreName(storeName)
                .setVisitors(visitors)
                .setPercentageSales(percentageSales)
                .setCountOfSales(countOfSales)
                .setCountOfProducts(countOfProducts)
                .setAveragePricePerBasket(findAB(turnover, countOfSales))
                .setAveragePricePerProducts(findAP(turnover, countOfProducts))
                .setUpt(findUPT(countOfProducts, countOfSales))
                .setTurnover(findTurnover(sales))
                .setWeekOfYear(woy)
                .setYear(fromDate.getYear());
    }

    @Override
    public void addSaleForTests() {
        List<UserEntity> allUsers = userService.findAll();
        List<ProductEntity> allProducts = productService.findAll();
        int productSize = allProducts.size();
        //Work only for November
        LocalDate date = LocalDate.of(2021, 11, 1);
        int dayOfMonth = LocalDate.now().getDayOfMonth();

        for (int i = 1; i <= dayOfMonth; i++) {
            for (UserEntity userEntity : allUsers) {

                int countSales = ThreadLocalRandom.current().nextInt(0, 10);
                for (int j = 0; j < countSales; j++) {

                    List<ProductEntity> products = new ArrayList<>();

                    int countProducts = ThreadLocalRandom.current().nextInt(1, 10);
                    for (int k = 0; k < countProducts; k++) {

                        int randomProductId = ThreadLocalRandom.current().nextInt(0, productSize);
                        products.add(allProducts.get(randomProductId));

                    }
                    long shopId = userEntity.getStore().getId();
                    StoreEntity store = storeService.findById(shopId);
                    addSale(products, userEntity, store, date);
                }
            }
            date = date.plusDays(1);
        }
    }
}
