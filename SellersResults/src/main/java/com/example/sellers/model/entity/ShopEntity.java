package com.example.sellers.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shops")
public class ShopEntity extends BaseEntity{

    private String name;
    private String description;
    private List<SellerEntity> sellers;
    private List<TargetEntity> targets;

    public ShopEntity() {
    }

    @Column(length = 50)
    public String getName() {
        return name;
    }

    public ShopEntity setName(String name) {
        this.name = name;
        return this;
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public ShopEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    @OneToMany(mappedBy = "shop")
    public List<SellerEntity> getSellers() {
        return sellers;
    }

    public ShopEntity setSellers(List<SellerEntity> sellers) {
        this.sellers = sellers;
        return this;
    }

    @OneToMany
    public List<TargetEntity> getTargets() {
        return targets;
    }

    public ShopEntity setTargets(List<TargetEntity> targets) {
        this.targets = targets;
        return this;
    }
}
