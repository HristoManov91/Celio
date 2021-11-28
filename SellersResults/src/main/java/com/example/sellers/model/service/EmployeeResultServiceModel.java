package com.example.sellers.model.service;

import java.math.BigDecimal;

public class EmployeeResultServiceModel {

    private String fullName;
    private Integer countOfSales;
    private Integer countOfProducts;
    private BigDecimal averagePricePerBasket;
    private BigDecimal averagePricePerProducts;
    private BigDecimal upt;
    private BigDecimal turnover;

    public EmployeeResultServiceModel() {
    }

    public String getFullName() {
        return fullName;
    }

    public EmployeeResultServiceModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Integer getCountOfSales() {
        return countOfSales;
    }

    public EmployeeResultServiceModel setCountOfSales(Integer countOfSales) {
        this.countOfSales = countOfSales;
        return this;
    }

    public Integer getCountOfProducts() {
        return countOfProducts;
    }

    public EmployeeResultServiceModel setCountOfProducts(Integer countOfProducts) {
        this.countOfProducts = countOfProducts;
        return this;
    }

    public BigDecimal getAveragePricePerBasket() {
        return averagePricePerBasket;
    }

    public EmployeeResultServiceModel setAveragePricePerBasket(BigDecimal averagePricePerBasket) {
        this.averagePricePerBasket = averagePricePerBasket;
        return this;
    }

    public BigDecimal getAveragePricePerProducts() {
        return averagePricePerProducts;
    }

    public EmployeeResultServiceModel setAveragePricePerProducts(BigDecimal averagePricePerProducts) {
        this.averagePricePerProducts = averagePricePerProducts;
        return this;
    }

    public BigDecimal getUpt() {
        return upt;
    }

    public EmployeeResultServiceModel setUpt(BigDecimal upt) {
        this.upt = upt;
        return this;
    }

    public BigDecimal getTurnover() {
        return turnover;
    }

    public EmployeeResultServiceModel setTurnover(BigDecimal turnover) {
        this.turnover = turnover;
        return this;
    }
}
