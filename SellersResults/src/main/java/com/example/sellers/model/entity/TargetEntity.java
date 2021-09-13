package com.example.sellers.model.entity;

import com.example.sellers.model.entity.enums.MonthEnum;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "targets")
public class TargetEntity extends BaseEntity{

    private MonthEnum month;
    private BigDecimal target;

    public TargetEntity() {
    }

    @Enumerated(EnumType.STRING)
    public MonthEnum getMonth() {
        return month;
    }

    public TargetEntity setMonth(MonthEnum month) {
        this.month = month;
        return this;
    }

    @Column
    public BigDecimal getTarget() {
        return target;
    }

    public TargetEntity setTarget(BigDecimal target) {
        this.target = target;
        return this;
    }
}
