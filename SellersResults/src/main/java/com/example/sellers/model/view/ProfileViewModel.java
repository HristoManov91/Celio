package com.example.sellers.model.view;


import com.example.sellers.model.entity.StoreEntity;
import com.example.sellers.model.entity.UserRoleEntity;
import java.time.LocalDate;
import java.util.HashSet;

import java.util.Set;

public class ProfileViewModel {

    //ToDo да се добави полето за резултати
    private Long id;
    private String fullName;
    private String email;
    private LocalDate dateOfAppointment;
    private String imageUrl;
    private String description;
    private Set<UserRoleEntity> roles = new HashSet<>();
    private StoreEntity shop;

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

    public String getEmail() {
        return email;
    }

    public ProfileViewModel setEmail(String email) {
        this.email = email;
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

    public Set<UserRoleEntity> getRoles() {
        return roles;
    }

    public ProfileViewModel setRoles(Set<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public StoreEntity getShop() {
        return shop;
    }

    public ProfileViewModel setShop(StoreEntity shop) {
        this.shop = shop;
        return this;
    }
}
