package com.example.sellers.model.entity.results;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sellers_weeks_results")
public class SellerWeekResultEntity extends ResultAbs {

    private String employeeName;

    public SellerWeekResultEntity() {
    }

    @Column
    public String getEmployeeName() {
        return employeeName;
    }

    public SellerWeekResultEntity setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
        return this;
    }
}
