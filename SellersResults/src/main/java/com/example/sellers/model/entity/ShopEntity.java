package com.example.sellers.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "shops")
public class ShopEntity extends BaseEntity{

    private String name;
    private String description;
    private List<UserEntity> sellers;
    private BigDecimal targets;
    private List<Traffic> traffic;

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
    public List<UserEntity> getSellers() {
        return sellers;
    }

    public ShopEntity setSellers(List<UserEntity> sellers) {
        this.sellers = sellers;
        return this;
    }

    public BigDecimal getTargets() {
        return targets;
    }

    public ShopEntity setTargets(BigDecimal targets) {
        this.targets = targets;
        return this;
    }

    @OneToMany(mappedBy = "shopEntity")
    public List<Traffic> getTraffic() {
        return traffic;
    }

    public ShopEntity setTraffic(List<Traffic> traffic) {
        this.traffic = traffic;
        return this;
    }
}
