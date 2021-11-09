package com.example.sellers.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserRemoveBindingModel {

    private String fullName;

    public UserRemoveBindingModel() {
    }

    @NotNull
    public String getFullName() {
        return fullName;
    }

    public UserRemoveBindingModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }
}
