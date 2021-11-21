package com.example.sellers.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Month;
import java.time.Year;

@Entity
@Table(name = "targets")
public class TargetEntity extends BaseEntity{

    private BigDecimal target;
    private Month month;
    private Integer year;

    public TargetEntity() {
    }

    @Column(nullable = false)
    public BigDecimal getTarget() {
        return target;
    }

    public TargetEntity setTarget(BigDecimal target) {
        this.target = target;
        return this;
    }

    public Month getMonth() {
        return month;
    }

    public TargetEntity setMonth(Month month) {
        this.month = month;
        return this;
    }

    @Column(nullable = false)
    public Integer getYear() {
        return year;
    }

    public TargetEntity setYear(Integer year) {
        this.year = year;
        return this;
    }
}
