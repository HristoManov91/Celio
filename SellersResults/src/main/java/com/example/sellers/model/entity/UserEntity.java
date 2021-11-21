package com.example.sellers.model.entity;

import com.example.sellers.model.entity.enums.UserRoleEnum;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    private String fullName;
    private String password;
    private String email;
    private LocalDate birthday;
    private LocalDate dateOfAppointment;
    private PictureEntity picture;
    private String description;
    private Set<SaleEntity> sales;
    private Set<UserRoleEntity> roles = new LinkedHashSet<>();
    private StoreEntity store;
    private boolean isApproved;
    private boolean leftEmployee;
    private SaleEntity bestBill;
    private SaleEntity mostProductsInBill;
    private BigDecimal highestMonthlyTurnover;

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

    @Column
    public LocalDate getBirthday() {
        return birthday;
    }

    public UserEntity setBirthday(LocalDate birthday) {
        this.birthday = birthday;
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

    @OneToOne(cascade = CascadeType.REMOVE)
    public PictureEntity getPicture() {
        return picture;
    }

    public UserEntity setPicture(PictureEntity picture) {
        this.picture = picture;
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

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    public Set<SaleEntity> getSales() {
        return sales;
    }

    public UserEntity setSales(Set<SaleEntity> sales) {
        this.sales = sales;
        return this;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    public Set<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(Set<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    @ManyToOne
    public StoreEntity getStore() {
        return store;
    }

    public UserEntity setStore(StoreEntity store) {
        this.store = store;
        return this;
    }

    @Column
    public boolean isApproved() {
        return isApproved;
    }

    public UserEntity setApproved(boolean approved) {
        isApproved = approved;
        return this;
    }

    @OneToOne
    public SaleEntity getBestBill() {
        return bestBill;
    }

    public UserEntity setBestBill(SaleEntity bestBill) {
        this.bestBill = bestBill;
        return this;
    }

    @OneToOne
    public SaleEntity getMostProductsInBill() {
        return mostProductsInBill;
    }

    public UserEntity setMostProductsInBill(SaleEntity mostProductsInBill) {
        this.mostProductsInBill = mostProductsInBill;
        return this;
    }

    @Column(name = "highest_monthly_turnover")
    public BigDecimal getHighestMonthlyTurnover() {
        return highestMonthlyTurnover;
    }

    public UserEntity setHighestMonthlyTurnover(BigDecimal highestMonthlyTurnover) {
        this.highestMonthlyTurnover = highestMonthlyTurnover;
        return this;
    }

    public UserEntity addRole(UserRoleEntity userRoleEntity) {
        this.roles.add(userRoleEntity);
        return this;
    }

    public boolean isLeftEmployee() {
        return leftEmployee;
    }

    public UserEntity setLeftEmployee(boolean leftEmployee) {
        this.leftEmployee = leftEmployee;
        return this;
    }

    public UserRoleEnum findHighestRole() {

        //ToDo ако добавя нова Роля трябва да се пренапише
        List<UserRoleEnum> list = new ArrayList<>();
        this.roles.forEach(userRoleEntity -> list.add(userRoleEntity.getRole()));

        if (list.contains(UserRoleEnum.MANAGER)) {
            return UserRoleEnum.MANAGER;
        } else {
            return UserRoleEnum.SELLER;
        }
    }
}
