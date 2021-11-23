package com.example.sellers.model.entity.results;

import com.example.sellers.model.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;

@MappedSuperclass
public abstract class ResultAbs extends BaseEntity {

    private Integer countOfSales;
    private Integer countOfProducts;
    private BigDecimal averagePricePerBasket;
    private BigDecimal averagePricePerProducts;
    private BigDecimal upt;
    private BigDecimal turnover;

    public ResultAbs() {
    }

    @Column
    public Integer getCountOfSales() {
        return countOfSales;
    }

    public ResultAbs setCountOfSales(Integer countOfSales) {
        this.countOfSales = countOfSales;
        return this;
    }

    @Column
    public Integer getCountOfProducts() {
        return countOfProducts;
    }

    public ResultAbs setCountOfProducts(Integer countOfProducts) {
        this.countOfProducts = countOfProducts;
        return this;
    }

    @Column
    public BigDecimal getAveragePricePerBasket() {
        return averagePricePerBasket;
    }

    public ResultAbs setAveragePricePerBasket(BigDecimal averagePricePerBasket) {
        this.averagePricePerBasket = averagePricePerBasket;
        return this;
    }

    @Column
    public BigDecimal getAveragePricePerProducts() {
        return averagePricePerProducts;
    }

    public ResultAbs setAveragePricePerProducts(BigDecimal averagePricePerProducts) {
        this.averagePricePerProducts = averagePricePerProducts;
        return this;
    }

    @Column
    public BigDecimal getUpt() {
        return upt;
    }

    public ResultAbs setUpt(BigDecimal upt) {
        this.upt = upt;
        return this;
    }

    @Column
    public BigDecimal getTurnover() {
        return turnover;
    }

    public ResultAbs setTurnover(BigDecimal turnover) {
        this.turnover = turnover;
        return this;
    }
}
