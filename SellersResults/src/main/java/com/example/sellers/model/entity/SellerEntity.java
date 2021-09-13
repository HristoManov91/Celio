package com.example.sellers.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "sellers")
public class SellerEntity extends BaseEntity {

    private String fullName;
    private String password;
    private String email;
    private LocalDate dateOfAppointment;
    private String imageUrl;
    private String description;
    private List<SaleEntity> sales;
    private List<UserRoleEntity> roles;
    private ShopEntity shop;

    public SellerEntity() {
    }

    @Column(name = "full_name" , length = 50 , nullable = false , unique = true)
    public String getFullName() {
        return fullName;
    }

    public SellerEntity setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    @Column(length = 20)
    public String getPassword() {
        return password;
    }

    public SellerEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    @Column(name = "email" , nullable = false , unique = true , length = 100)
    public String getEmail() {
        return email;
    }

    public SellerEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    @Column(name = "date_of_appointment" , nullable = false)
    public LocalDate getDateOfAppointment() {
        return dateOfAppointment;
    }

    public SellerEntity setDateOfAppointment(LocalDate dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
        return this;
    }

    //ToDo да проверя дали трябва да го сменя на BLOB
    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public SellerEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public SellerEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    @ManyToMany
    public List<SaleEntity> getSales() {
        return sales;
    }

    public SellerEntity setSales(List<SaleEntity> sales) {
        this.sales = sales;
        return this;
    }

    @ManyToMany
    public List<UserRoleEntity> getRoles() {
        return roles;
    }

    public SellerEntity setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    @ManyToOne
    public ShopEntity getShop() {
        return shop;
    }

    public SellerEntity setShop(ShopEntity shop) {
        this.shop = shop;
        return this;
    }
}
