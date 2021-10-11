package com.example.sellers.model.binding;

import javax.validation.constraints.NotNull;

public class UserChangeStoreBindingModel {

    @NotNull
    private String fullName;
    @NotNull
    private String store;

    public UserChangeStoreBindingModel() {
    }

    public String getFullName() {
        return fullName;
    }

    public UserChangeStoreBindingModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getStore() {
        return store;
    }

    public UserChangeStoreBindingModel setStore(String store) {
        this.store = store;
        return this;
    }
}
