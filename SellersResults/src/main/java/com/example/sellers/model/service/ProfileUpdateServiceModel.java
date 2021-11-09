package com.example.sellers.model.service;

import com.example.sellers.model.entity.PictureEntity;
import com.example.sellers.model.entity.StoreEntity;
import com.example.sellers.model.entity.enums.UserRoleEnum;

import java.time.LocalDate;

public class ProfileUpdateServiceModel {

    private Long id;
    private String fullName;
    private LocalDate birthday;
    private LocalDate dateOfAppointment;
    private PictureEntity picture;
    private String description;
    private StoreEntity store;
    private UserRoleEnum role;

    public ProfileUpdateServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public ProfileUpdateServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public ProfileUpdateServiceModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public ProfileUpdateServiceModel setBirthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public LocalDate getDateOfAppointment() {
        return dateOfAppointment;
    }

    public ProfileUpdateServiceModel setDateOfAppointment(LocalDate dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
        return this;
    }

    public PictureEntity getPicture() {
        return picture;
    }

    public ProfileUpdateServiceModel setPicture(PictureEntity picture) {
        this.picture = picture;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProfileUpdateServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public StoreEntity getStore() {
        return store;
    }

    public ProfileUpdateServiceModel setStore(StoreEntity store) {
        this.store = store;
        return this;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public ProfileUpdateServiceModel setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}
