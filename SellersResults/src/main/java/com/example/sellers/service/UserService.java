package com.example.sellers.service;

import com.example.sellers.model.entity.UserEntity;
import com.example.sellers.model.entity.enums.UserRoleEnum;
import com.example.sellers.model.service.ProfileUpdateServiceModel;
import com.example.sellers.model.service.UserRegistrationServiceModel;
import com.example.sellers.model.view.ProfileViewModel;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {

    Long count();

    void addAdmin(String fullName, String password, String email);

    void addUser(String fullName, String password, String email , Long shopId);

    void registrationUser(UserRegistrationServiceModel userRegistrationServiceModel);

    boolean emailExist(String email);

    void addUserRole(UserRoleEnum role , String userFullName);

    void saveUser(UserEntity userEntity);

    void approvedUser(String fullName);

    Optional<UserEntity> findUserByEmail(String email);

    Set<String> findAllUsersFullName();

    void changeUserStore(String fullName , String store);

    void removeUser(String fullName);

    List<ProfileViewModel> findAllUsersViewModel();

    ProfileViewModel findByIdProfileViewModel(Long id);

    List<UserEntity> findAll();

    ProfileViewModel findUserViewModelByEmail(String email);

    void editProfile(ProfileUpdateServiceModel serviceModel);

    Set<String> findAllUsersFullNameWithoutApproval();

    UserEntity findById(Long id);

    boolean isAdmin(UserEntity user);

    boolean isAuthorize(String email , Long id);
}
