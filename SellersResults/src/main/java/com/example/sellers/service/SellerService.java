package com.example.sellers.service;

import com.example.sellers.model.entity.UserRoleEntity;

import java.time.LocalDate;
import java.util.List;

public interface SellerService {
    //private String fullName;
    //    private String password;
    //    private String email;
    //    private LocalDate dateOfAppointment;
    //    private String imageUrl;
    //    private String description;
    //    private List<SaleEntity> sales;
    //    private List<UserRoleEntity> roles;
    void addSeller(String fullName, String password, String email, LocalDate dateOfAppointment,
                   List<UserRoleEntity> roles , Long shopId);
}
