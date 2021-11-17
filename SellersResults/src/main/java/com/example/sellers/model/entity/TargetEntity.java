package com.example.sellers.model.entity;

import com.example.sellers.model.entity.enums.MonthEnum;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "targets")
public class TargetEntity extends BaseEntity{

    private BigDecimal target;
    private MonthEnum month;
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

    @Enumerated(EnumType.STRING)
    public MonthEnum getMonth() {
        return month;
    }

    public TargetEntity setMonth(MonthEnum month) {
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
