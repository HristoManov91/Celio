package com.example.sellers.model.entity;

import org.apache.catalina.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    //ToDo да направя username да е email и да се влиза с него
    private String fullName;
    private String password;
    private String email;
    private LocalDate dateOfAppointment;
    private String imageUrl;
    private String description;
    private List<SaleEntity> sales = new LinkedList<>();
    private Set<UserRoleEntity> roles = new HashSet<>();
    private ShopEntity shop;

    public UserEntity() {
    }

    @Column(name = "full_name", length = 50, nullable = false, unique = true)
    public String getFullName() {
        return fullName;
    }

    public UserEntity setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    @Column(name = "email", nullable = false, unique = true, length = 100)
    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    @Column(name = "date_of_appointment", nullable = false)
    public LocalDate getDateOfAppointment() {
        return dateOfAppointment;
    }

    public UserEntity setDateOfAppointment(LocalDate dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
        return this;
    }

    //ToDo да проверя дали трябва да го сменя на BLOB
    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public UserEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public UserEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    @OneToMany(mappedBy = "userEntity")
    public List<SaleEntity> getSales() {
        return sales;
    }

    public UserEntity setSales(List<SaleEntity> sales) {
        this.sales = sales;
        return this;
    }

    @ManyToMany //(fetch = FetchType.EAGER)
    public Set<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(Set<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    @ManyToOne
    public ShopEntity getShop() {
        return shop;
    }

    public UserEntity setShop(ShopEntity shop) {
        this.shop = shop;
        return this;
    }

    public void addSale(SaleEntity saleEntity) {
        this.sales.add(saleEntity);
    }

    public UserEntity addRole(UserRoleEntity userRoleEntity) {
        this.roles.add(userRoleEntity);
        return this;
    }
}
