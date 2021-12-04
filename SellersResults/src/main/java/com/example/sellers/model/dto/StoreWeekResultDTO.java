package com.example.sellers.model.dto;

import java.math.BigDecimal;

public class StoreWeekResultDTO {

    private Long id;
    private String storeName;
    private Integer weekOfYear;
    private Integer visitors;
    private BigDecimal percentageSales;
    private Integer countOfSales;
    private Integer countOfProducts;
    private BigDecimal averagePricePerBasket;
    private BigDecimal averagePricePerProducts;
    private BigDecimal upt;
    private BigDecimal turnover;
    private Integer year;

    public StoreWeekResultDTO() {
    }

    public Long getId() {
        return id;
    }

    public StoreWeekResultDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getStoreName() {
        return storeName;
    }

    public StoreWeekResultDTO setStoreName(String storeName) {
        this.storeName = storeName;
        return this;
    }

    public Integer getWeekOfYear() {
        return weekOfYear;
    }

    public StoreWeekResultDTO setWeekOfYear(Integer weekOfYear) {
        this.weekOfYear = weekOfYear;
        return this;
    }

    public Integer getVisitors() {
        return visitors;
    }

    public StoreWeekResultDTO setVisitors(Integer visitors) {
        this.visitors = visitors;
        return this;
    }

    public BigDecimal getPercentageSales() {
        return percentageSales;
    }

    public StoreWeekResultDTO setPercentageSales(BigDecimal percentageSales) {
        this.percentageSales = percentageSales;
        return this;
    }

    public Integer getCountOfSales() {
        return countOfSales;
    }

    public StoreWeekResultDTO setCountOfSales(Integer countOfSales) {
        this.countOfSales = countOfSales;
        return this;
    }

    public Integer getCountOfProducts() {
        return countOfProducts;
    }

    public StoreWeekResultDTO setCountOfProducts(Integer countOfProducts) {
        this.countOfProducts = countOfProducts;
        return this;
    }

    public BigDecimal getAveragePricePerBasket() {
        return averagePricePerBasket;
    }

    public StoreWeekResultDTO setAveragePricePerBasket(BigDecimal averagePricePerBasket) {
        this.averagePricePerBasket = averagePricePerBasket;
        return this;
    }

    public BigDecimal getAveragePricePerProducts() {
        return averagePricePerProducts;
    }

    public StoreWeekResultDTO setAveragePricePerProducts(BigDecimal averagePricePerProducts) {
        this.averagePricePerProducts = averagePricePerProducts;
        return this;
    }

    public BigDecimal getUpt() {
        return upt;
    }

    public StoreWeekResultDTO setUpt(BigDecimal upt) {
        this.upt = upt;
        return this;
    }

    public BigDecimal getTurnover() {
        return turnover;
    }

    public StoreWeekResultDTO setTurnover(BigDecimal turnover) {
        this.turnover = turnover;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public StoreWeekResultDTO setYear(Integer year) {
        this.year = year;
        return this;
    }
}
