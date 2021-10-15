package com.example.sellers.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class SaleAddBindingModel {

    @NotNull
    private String seller;
    @NotNull
    private List<String> products = new ArrayList<>();

    public SaleAddBindingModel() {
    }

    public String getSeller() {
        return seller;
    }

    public SaleAddBindingModel setSeller(String seller) {
        this.seller = seller;
        return this;
    }

    public List<String> getProducts() {
        return products;
    }

    public SaleAddBindingModel setProducts(List<String> products) {
        this.products = products;
        return this;
    }

    public void addProduct(String productName){
        this.products.add(productName);
    }
}
