package com.example.sellers.model.view;


import com.example.sellers.model.entity.SaleEntity;
import com.example.sellers.model.entity.StoreEntity;
import com.example.sellers.model.entity.UserRoleEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;

import java.util.Set;

public class ProfileViewModel {

    private Long id;
    private String fullName;
    private LocalDate birthday;
    private LocalDate dateOfAppointment;
    private String imageUrl;
    private String description;
    private StoreEntity store;
    private SaleEntity bestBill;
    private SaleEntity mostProductsInBill;
    private BigDecimal highestMonthlyTurnover;
    private Set<UserRoleEntity> roles = new HashSet<>();

    public ProfileViewModel() {
    }

    public Long getId() {
        return id;
    }

    public ProfileViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public ProfileViewModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public ProfileViewModel setBirthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public LocalDate getDateOfAppointment() {
        return dateOfAppointment;
    }

    public ProfileViewModel setDateOfAppointment(LocalDate dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ProfileViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProfileViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public StoreEntity getStore() {
        return store;
    }

    public ProfileViewModel setStore(StoreEntity store) {
        this.store = store;
        return this;
    }

    public SaleEntity getBestBill() {
        return bestBill;
    }

    public ProfileViewModel setBestBill(SaleEntity bestBill) {
        this.bestBill = bestBill;
        return this;
    }

    public SaleEntity getMostProductsInBill() {
        return mostProductsInBill;
    }

    public ProfileViewModel setMostProductsInBill(SaleEntity mostProductsInBill) {
        this.mostProductsInBill = mostProductsInBill;
        return this;
    }

    public BigDecimal getHighestMonthlyTurnover() {
        return highestMonthlyTurnover;
    }

    public ProfileViewModel setHighestMonthlyTurnover(BigDecimal highestMonthlyTurnover) {
        this.highestMonthlyTurnover = highestMonthlyTurnover;
        return this;
    }

    public Set<UserRoleEntity> getRoles() {
        return roles;
    }

    public ProfileViewModel setRoles(Set<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }
}
