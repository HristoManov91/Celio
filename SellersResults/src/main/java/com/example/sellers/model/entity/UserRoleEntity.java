package com.example.sellers.model.entity;

import com.example.sellers.model.entity.enums.UserRoleEnum;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity{

    private UserRoleEnum role;
    private String description;

    public UserRoleEntity() {
    }

    public UserRoleEntity(UserRoleEnum userRole, String description) {
        this.role = userRole;
        this.description = description;
    }

    @Enumerated(EnumType.STRING)
    public UserRoleEnum getRole() {
        return role;
    }

    public UserRoleEntity setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public UserRoleEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
