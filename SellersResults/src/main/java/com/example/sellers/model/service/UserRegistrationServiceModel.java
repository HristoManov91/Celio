package com.example.sellers.model.service;

public class UserRegistrationServiceModel {

    private String email;
    private String password;
    private String fullName;
    private String store;

    public UserRegistrationServiceModel() {
    }

    public String getEmail() {
        return email;
    }

    public UserRegistrationServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserRegistrationServiceModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getStore() {
        return store;
    }

    public UserRegistrationServiceModel setStore(String store) {
        this.store = store;
        return this;
    }
}
