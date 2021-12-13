package com.example.sellers.model.view;

import com.example.sellers.model.entity.PictureEntity;
import com.example.sellers.model.entity.StoreEntity;
import com.example.sellers.model.entity.enums.UserRoleEnum;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class ProfileUpdateViewModel {

    private Long id;
    private String fullName;
    private LocalDate birthday;
    private LocalDate dateOfAppointment;
    private PictureEntity picture;
    private String description;
    private StoreEntity store;
    private UserRoleEnum role;

    public ProfileUpdateViewModel() {
    }

    public Long getId() {
        return id;
    }

    public ProfileUpdateViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public ProfileUpdateViewModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getBirthday() {
        return birthday;
    }

    public ProfileUpdateViewModel setBirthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getDateOfAppointment() {
        return dateOfAppointment;
    }

    public ProfileUpdateViewModel setDateOfAppointment(LocalDate dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
        return this;
    }

    public PictureEntity getPicture() {
        return picture;
    }

    public ProfileUpdateViewModel setPicture(PictureEntity picture) {
        this.picture = picture;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProfileUpdateViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public StoreEntity getStore() {
        return store;
    }

    public ProfileUpdateViewModel setStore(StoreEntity store) {
        this.store = store;
        return this;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public ProfileUpdateViewModel setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}
