package com.example.sellers.model.binding;

import javax.validation.constraints.NotNull;

public class UserChangeStoreBindingModel {

    private String fullName;
    private String store;

    public UserChangeStoreBindingModel() {
    }

    @NotNull
    public String getFullName() {
        return fullName;
    }

    public UserChangeStoreBindingModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    @NotNull
    public String getStore() {
        return store;
    }

    public UserChangeStoreBindingModel setStore(String store) {
        this.store = store;
        return this;
    }
}
