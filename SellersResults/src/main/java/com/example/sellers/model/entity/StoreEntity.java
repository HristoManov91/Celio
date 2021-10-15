package com.example.sellers.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "stores")
public class StoreEntity extends BaseEntity{

    private String name;
    private String description;
    private List<UserEntity> sellers;
    //ToDo да направя колекция от таргети да се пази
    private BigDecimal targets;
    private List<Traffic> traffic;
    //ToDo да добавя колекция със снимки

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
    public List<UserEntity> getSellers() {
        return sellers;
    }

    public StoreEntity setSellers(List<UserEntity> sellers) {
        this.sellers = sellers;
        return this;
    }

    public BigDecimal getTargets() {
        return targets;
    }

    public StoreEntity setTargets(BigDecimal targets) {
        this.targets = targets;
        return this;
    }

    @OneToMany(mappedBy = "store")
    public List<Traffic> getTraffic() {
        return traffic;
    }

    public StoreEntity setTraffic(List<Traffic> traffic) {
        this.traffic = traffic;
        return this;
    }
}
