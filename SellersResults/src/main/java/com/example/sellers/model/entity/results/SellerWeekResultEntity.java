package com.example.sellers.model.entity.results;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sellers_weeks_results")
public class SellerWeekResultEntity extends ResultAbs {

    private String employeeName;
    private Integer weakOfYear;

    public SellerWeekResultEntity() {
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public SellerWeekResultEntity setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
        return this;
    }

    public Integer getWeakOfYear() {
        return weakOfYear;
    }

    public SellerWeekResultEntity setWeakOfYear(Integer weakOfYear) {
        this.weakOfYear = weakOfYear;
        return this;
    }
}
