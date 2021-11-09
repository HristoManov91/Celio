package com.example.sellers.model.binding;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SaleAddBindingModel {

    private String seller;
    private List<String> products = new ArrayList<>();
    private LocalDate dateOfSale;
    private String store;

    public SaleAddBindingModel() {
    }

    @NotNull
    public String getSeller() {
        return seller;
    }

    public SaleAddBindingModel setSeller(String seller) {
        this.seller = seller;
        return this;
    }

    @NotNull
    public List<String> getProducts() {
        return products;
    }

    public SaleAddBindingModel setProducts(List<String> products) {
        this.products = products;
        return this;
    }

    @NotNull
    @PastOrPresent
    public LocalDate getDateOfSale() {
        return dateOfSale;
    }

    public SaleAddBindingModel setDateOfSale(LocalDate dateOfSale) {
        this.dateOfSale = dateOfSale;
        return this;
    }

    public void addProduct(String productName){
        this.products.add(productName);
    }

    @NotNull
    public String getStore() {
        return store;
    }

    public SaleAddBindingModel setStore(String store) {
        this.store = store;
        return this;
    }
}
