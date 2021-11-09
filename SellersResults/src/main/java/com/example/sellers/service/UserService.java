package com.example.sellers.service;

import com.example.sellers.model.entity.SaleEntity;
import com.example.sellers.model.entity.UserEntity;
import com.example.sellers.model.entity.enums.UserRoleEnum;
import com.example.sellers.model.service.ProfileUpdateServiceModel;
import com.example.sellers.model.service.UserRegistrationServiceModel;
import com.example.sellers.model.view.ProfileViewModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {

    Long count();

    void addAdmin(String fullName, String password, String email);

    void addUser(String fullName, String password, String email , Long shopId);

    Optional<UserEntity> findUserByEmail(String email);
    //ToDo може да ги рефакторирам да не се повтаря толкова като има 1 метод да намира продажбите
    // и останалите вече си вършат тяхната работа

    BigDecimal find_AB_betweenDate(UserEntity userEntity , LocalDate fromDate , LocalDate toDate);

    BigDecimal find_AP_betweenDate(UserEntity userEntity , LocalDate fromDate , LocalDate toDate);

    BigDecimal find_UPT_betweenDate(UserEntity userEntity , LocalDate fromDate , LocalDate toDate);

    BigDecimal findTotalSumBetweenDate(UserEntity userEntity , LocalDate fromDate , LocalDate toDate);

    int countTotalSalesBetweenDate(UserEntity userEntity , LocalDate fromDate , LocalDate toDate);

    long countTotalProductsBetweenDate(UserEntity userEntity , LocalDate fromDate , LocalDate toDate);

    boolean emailExist(String email);

    void registrationUser(UserRegistrationServiceModel userRegistrationServiceModel);

    Set<String> findAllUsersFullName();

    void changeUserStore(String fullName , String store);

    void addUserRole(UserRoleEnum role , String userFullName);

    void removeUser(String fullName);

    List<ProfileViewModel> findAllUsersViewModel();

    ProfileViewModel findByIdProfileViewModel(Long id);

    List<UserEntity> findAll();

    void saveUser(UserEntity userEntity);

    ProfileViewModel findUserViewModelByEmail(String email);

    void editProfile(ProfileUpdateServiceModel serviceModel);

    Set<String> findAllUsersFullNameWithoutApproval();

    void approvedUser(String fullName);

    UserEntity findById(Long id);
}
