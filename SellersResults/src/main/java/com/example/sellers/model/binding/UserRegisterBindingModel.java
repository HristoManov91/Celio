package com.example.sellers.model.binding;

import javax.validation.constraints.*;

public class UserRegisterBindingModel {
    //ToDo валидации
    private String email;
    private String password;
    private String confirmPassword;
    private String fullName;
    private String store;

    public UserRegisterBindingModel() {
    }

    @NotBlank
    @Size(min = 6 , max = 30)
    public String getFullName() {
        return fullName;
    }

    public UserRegisterBindingModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    @Email
    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    @NotBlank
    @Size(min = 6 , max = 20)
    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    @NotNull
    public String getStore() {
        return store;
    }

    public UserRegisterBindingModel setStore(String store) {
        this.store = store;
        return this;
    }

    @NotBlank
    @Size(min = 6 , max = 20)
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
