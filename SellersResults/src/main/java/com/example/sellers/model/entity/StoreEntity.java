package com.example.sellers.model.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "stores")
public class StoreEntity extends BaseEntity{

    private String name;
    private String description;
    private List<UserEntity> sellers;
    private Set<SaleEntity> sales;
    private List<VisitorEntity> visitors;
    private List<TargetEntity> targets;
    private List<PictureEntity> pictures;

    public StoreEntity() {
    }

    @Column(length = 50)
    public String getName() {
        return name;
    }

    public StoreEntity setName(String name) {
        this.name = name;
        return this;
    }

    @Column(columnDefinition = "TEXT")
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
    public List<UserEntity> getSellers() {
        return sellers;
    }

    public StoreEntity setSellers(List<UserEntity> sellers) {
        this.sellers = sellers;
        return this;
    }

    @OneToMany
    public List<TargetEntity> getTargets() {
        return targets;
    }

    public StoreEntity setTargets(List<TargetEntity> targets) {
        this.targets = targets;
        return this;
    }

    @OneToMany
    public List<PictureEntity> getPictures() {
        return pictures;
    }

    public StoreEntity setPictures(List<PictureEntity> pictures) {
        this.pictures = pictures;
        return this;
    }

    @OneToMany()
    public List<VisitorEntity> getVisitors() {
        return visitors;
    }

    public StoreEntity setVisitors(List<VisitorEntity> visitors) {
        this.visitors = visitors;
        return this;
    }
}
