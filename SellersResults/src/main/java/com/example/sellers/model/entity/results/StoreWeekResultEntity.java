package com.example.sellers.model.entity.results;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table (name = "stores_weeks_results")
public class StoreWeekResultEntity extends ResultAbs {

    private String storeName;
    private Integer visitors;
    private BigDecimal percentageSales;

    public StoreWeekResultEntity() {
    }

    @Column
    public String getStoreName() {
        return storeName;
    }

    public StoreWeekResultEntity setStoreName(String storeName) {
        this.storeName = storeName;
        return this;
    }

    @Column
    public BigDecimal getPercentageSales() {
        return percentageSales;
    }

    public StoreWeekResultEntity setPercentageSales(BigDecimal percentageSales) {
        this.percentageSales = percentageSales;
        return this;
    }

    @Column
    public Integer getVisitors() {
        return visitors;
    }

    public StoreWeekResultEntity setVisitors(Integer visitors) {
        this.visitors = visitors;
        return this;
    }
}
