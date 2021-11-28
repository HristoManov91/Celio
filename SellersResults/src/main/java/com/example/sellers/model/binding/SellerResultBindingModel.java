package com.example.sellers.model.binding;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

public class SellerResultBindingModel {

    private LocalDate fromDate;
    private LocalDate endDate;

    public SellerResultBindingModel() {
    }

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    public LocalDate getFromDate() {
        return fromDate;
    }

    public SellerResultBindingModel setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
        return this;
    }

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    public LocalDate getEndDate() {
        return endDate;
    }

    public SellerResultBindingModel setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }
}
