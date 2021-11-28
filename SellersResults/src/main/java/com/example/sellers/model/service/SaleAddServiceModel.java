package com.example.sellers.model.service;

import java.time.LocalDate;
import java.util.List;

public class SaleAddServiceModel {

    private String seller;
    private List<String> products;
    private LocalDate dateOfSale;
    private String store;

    public SaleAddServiceModel() {
    }

    public String getSeller() {
        return seller;
    }

    public SaleAddServiceModel setSeller(String seller) {
        this.seller = seller;
        return this;
    }

    public List<String> getProducts() {
        return products;
    }

    public SaleAddServiceModel setProducts(List<String> products) {
        this.products = products;
        return this;
    }

    public LocalDate getDateOfSale() {
        return dateOfSale;
    }

    public SaleAddServiceModel setDateOfSale(LocalDate dateOfSale) {
        this.dateOfSale = dateOfSale;
        return this;
    }

    public String getStore() {
        return store;
    }

    public SaleAddServiceModel setStore(String store) {
        this.store = store;
        return this;
    }
}
