package com.example.sellers.model.entity.results;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table (name = "stores_weeks_results")
public class StoreWeekResultEntity extends ResultAbs {

    private String storeName;
    private Integer weekOfYear;
    private BigDecimal percentageSales;

    public StoreWeekResultEntity() {
    }

    public String getStoreName() {
        return storeName;
    }

    public StoreWeekResultEntity setStoreName(String storeName) {
        this.storeName = storeName;
        return this;
    }

    public Integer getWeekOfYear() {
        return weekOfYear;
    }

    public StoreWeekResultEntity setWeekOfYear(Integer weekOfYear) {
        this.weekOfYear = weekOfYear;
        return this;
    }

    public BigDecimal getPercentageSales() {
        return percentageSales;
    }

    public StoreWeekResultEntity setPercentageSales(BigDecimal percentageSales) {
        this.percentageSales = percentageSales;
        return this;
    }
}
