package com.example.sellers.model.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "stores")
public class StoreEntity extends BaseEntity{

    private String name;
    private String description;
    private Set<UserEntity> sellers;
    private Set<SaleEntity> sales;
    private Set<VisitorEntity> visitors;
    private Set<TargetEntity> targets;
    private Set<PictureEntity> pictures;

    public StoreEntity() {
    }

    @Column(length = 50 , nullable = false)
    public String getName() {
        return name;
    }

    public StoreEntity setName(String name) {
        this.name = name;
        return this;
    }

    @Lob
    public String getDescription() {
        return description;
    }

    public StoreEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    @OneToMany(mappedBy = "store")
    public Set<SaleEntity> getSales() {
        return sales;
    }

    public StoreEntity setSales(Set<SaleEntity> sales) {
        this.sales = sales;
        return this;
    }

    @OneToMany(mappedBy = "store")
    public Set<UserEntity> getSellers() {
        return sellers;
    }

    public StoreEntity setSellers(Set<UserEntity> sellers) {
        this.sellers = sellers;
        return this;
    }

    @OneToMany
    public Set<TargetEntity> getTargets() {
        return targets;
    }

    public StoreEntity setTargets(Set<TargetEntity> targets) {
        this.targets = targets;
        return this;
    }

    @OneToMany
    public Set<PictureEntity> getPictures() {
        return pictures;
    }

    public StoreEntity setPictures(Set<PictureEntity> pictures) {
        this.pictures = pictures;
        return this;
    }

    @OneToMany(mappedBy = "store")
    public Set<VisitorEntity> getVisitors() {
        return visitors;
    }

    public StoreEntity setVisitors(Set<VisitorEntity> visitors) {
        this.visitors = visitors;
        return this;
    }
}
