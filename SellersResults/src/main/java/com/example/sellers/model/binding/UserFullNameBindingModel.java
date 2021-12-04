package com.example.sellers.model.binding;

import javax.validation.constraints.NotBlank;

public class UserFullNameBindingModel {

    private String fullName;

    public UserFullNameBindingModel() {
    }

    @NotBlank
    public String getFullName() {
        return fullName;
    }

    public UserFullNameBindingModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }
}
