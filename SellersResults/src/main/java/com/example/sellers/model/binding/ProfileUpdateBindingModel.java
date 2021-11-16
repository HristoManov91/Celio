package com.example.sellers.model.binding;

import com.example.sellers.model.entity.StoreEntity;
import com.example.sellers.model.entity.enums.UserRoleEnum;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

public class ProfileUpdateBindingModel {

    private Long id;
    private String fullName;
    private LocalDate birthday;
    private LocalDate dateOfAppointment;
    private MultipartFile picture;
    private String description;
    private StoreEntity store;
    private UserRoleEnum role;

    public ProfileUpdateBindingModel() {
    }

    public Long getId() {
        return id;
    }

    public ProfileUpdateBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public ProfileUpdateBindingModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    public LocalDate getBirthday() {
        return birthday;
    }

    public ProfileUpdateBindingModel setBirthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    public LocalDate getDateOfAppointment() {
        return dateOfAppointment;
    }

    public ProfileUpdateBindingModel setDateOfAppointment(LocalDate dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
        return this;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public ProfileUpdateBindingModel setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }

    @NotBlank
    public String getDescription() {
        return description;
    }

    public ProfileUpdateBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public StoreEntity getStore() {
        return store;
    }

    public ProfileUpdateBindingModel setStore(StoreEntity store) {
        this.store = store;
        return this;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public ProfileUpdateBindingModel setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}
