package com.example.sellers.model.binding;

import com.example.sellers.model.entity.enums.UserRoleEnum;

import javax.validation.constraints.NotNull;

public class UserRoleBindingModel {

    private String fullName;
    private UserRoleEnum role;

    public UserRoleBindingModel() {
    }

    @NotNull
    public String getFullName() {
        return fullName;
    }

    public UserRoleBindingModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    @NotNull
    public UserRoleEnum getRole() {
        return role;
    }

    public UserRoleBindingModel setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}
