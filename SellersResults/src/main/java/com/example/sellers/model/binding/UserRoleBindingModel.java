package com.example.sellers.model.binding;

import com.example.sellers.model.entity.enums.UserRoleEnum;

import javax.validation.constraints.NotNull;

public class UserRoleBindingModel {

    @NotNull
    private String fullName;
    @NotNull
    private UserRoleEnum role;

    public UserRoleBindingModel() {
    }

    public String getFullName() {
        return fullName;
    }

    public UserRoleBindingModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public UserRoleBindingModel setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}
