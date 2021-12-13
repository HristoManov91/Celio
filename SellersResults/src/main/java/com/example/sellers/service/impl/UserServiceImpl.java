package com.example.sellers.service.impl;

import com.example.sellers.model.entity.UserEntity;
import com.example.sellers.model.entity.UserRoleEntity;
import com.example.sellers.model.entity.enums.UserRoleEnum;
import com.example.sellers.model.service.ProfileUpdateServiceModel;
import com.example.sellers.model.service.UserRegistrationServiceModel;
import com.example.sellers.model.view.ProfileUpdateViewModel;
import com.example.sellers.model.view.ProfileViewModel;
import com.example.sellers.repository.UserRepository;
import com.example.sellers.service.*;
import com.example.sellers.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final StoreService storeService;
    private final UserRoleService userRoleService;
    private final PictureService pictureService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, StoreService storeService, UserRoleService userRoleService,
                           PictureService pictureService, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.storeService = storeService;
        this.userRoleService = userRoleService;
        this.pictureService = pictureService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Long count() {
        return userRepository.count();
    }

    @Override
    public void addAdmin(String fullName, String password, String email) {

        UserEntity admin = new UserEntity()
                .setFullName(fullName)
                .setPassword(password)
                .setEmail(email)
                .setStore(storeService.findById(1L))
                .setDateOfAppointment(LocalDate.now())
                .addRole(userRoleService.findById(3L))
                .addRole(userRoleService.findById(2L))
                .addRole(userRoleService.findById(1L));

        userRepository.save(admin);
    }

    @Override
    public void addUser(String fullName, String password, String email, Long shopId) {

        UserEntity seller = new UserEntity()
                .setFullName(fullName)
                .setPassword(password)
                .setEmail(email)
                .setDateOfAppointment(LocalDate.now())
                .setStore(storeService.findById(shopId))
                .addRole(userRoleService.findById(3L));

        userRepository.save(seller);
    }

    @Override
    public Optional<UserEntity> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean emailExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public void registrationUser(UserRegistrationServiceModel userRegistrationServiceModel) {
        UserEntity user = modelMapper.map(userRegistrationServiceModel, UserEntity.class);
        user.setPassword(passwordEncoder.encode(userRegistrationServiceModel.getPassword()));

        UserRoleEntity userRole = userRoleService.findById(3L);
        user.addRole(userRole);

        user.setDateOfAppointment(LocalDate.now());

        user.setStore(storeService.findByName(userRegistrationServiceModel.getStore()));

        userRepository.save(user);
    }

    @Override
    public Set<String> findAllUsersFullName() {
        return userRepository.findAllUserFullName();
    }

    @Override
    public void changeUserStore(String fullName, String store) {
        UserEntity user = userRepository
                .findUserEntityByFullNameAndLeftEmployeeIsFalseAndApprovedIsTrue(fullName)
                .orElseThrow(() ->
                        new ObjectNotFoundException("User with this full name " + fullName + " not found", fullName));

        user.setStore(storeService.findByName(store));

        userRepository.save(user);
    }

    @Override
    public void addUserRole(UserRoleEnum role, String userFullName) {
        UserRoleEntity userRole = userRoleService.findUserRoleByName(role);

        UserEntity user = userRepository.findUserEntityByFullNameAndLeftEmployeeIsFalseAndApprovedIsTrue(userFullName)
                .orElseThrow(() ->
                        new ObjectNotFoundException("User with this full name " + userFullName + " not found", userFullName));

        user.addRole(userRole);

        userRepository.save(user);
    }

    @Override
    public void removeUser(String fullName) {
        UserEntity user = userRepository.findUserEntityByFullNameAndLeftEmployeeIsFalseAndApprovedIsTrue(fullName)
                .orElseThrow(() ->
                        new ObjectNotFoundException("User with this full name " + fullName + " not found", fullName));

        user.setLeftEmployee(true);
        userRepository.save(user);
    }

    @Override
    public List<ProfileViewModel> findAllUsersViewModel() {

        return userRepository.findAllByLeftEmployeeIsFalse()
                .stream()
                .map(this::mapToProfileView)
                .collect(Collectors.toList());
    }

    @Override
    public ProfileViewModel findByIdProfileViewModel(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("User with this id " + id + " not found", id.toString()));

        return mapToProfileView(userEntity);
    }

    @Override
    public ProfileUpdateViewModel findProfileUpdateViewModelById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("User with this id " + id + " not found", id.toString()));

        return mapToProfileUpdateViewModel(userEntity);
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Override
    public ProfileViewModel findUserViewModelByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException("User with this email " + email + " not found", email));

        return mapToProfileView(userEntity);
    }

    @Override
    public void editProfile(ProfileUpdateServiceModel profileModel) {
        UserEntity userEntity = userRepository.findById(profileModel.getId())
                .orElseThrow(() ->
                        new ObjectNotFoundException("User with id " + profileModel.getId() + " not found!",
                                profileModel.getId().toString()));

        if (userEntity.getPicture() != null) {
            String pictureId = userEntity.getPicture().getPublicId();
            userEntity.setPicture(null);
            userRepository.save(userEntity);
            pictureService.deletePictureByPublicId(pictureId);
        }

        userEntity
                .setPicture(profileModel.getPicture())
                .setBirthday(profileModel.getBirthday())
                .setDateOfAppointment(profileModel.getDateOfAppointment())
                .setDescription(profileModel.getDescription());

        userRepository.save(userEntity);
    }

    @Override
    public Set<String> findAllUsersFullNameWithoutApproval() {
        return userRepository.findAllUsersFullNameWithoutApproval();
    }

    @Override
    public UserEntity findUserByName(String fullName) {

        return userRepository.findByFullName(fullName).orElseThrow(() ->
                new ObjectNotFoundException("User with this full name " + fullName + " not found", fullName));
    }

    @Override
    public void approvedUser(String fullName) {
        UserEntity user = userRepository.findUserEntityByFullNameAndLeftEmployeeIsFalseAndApprovedIsFalse(fullName)
                .orElseThrow(() ->
                        new ObjectNotFoundException("User with this full name " + fullName + " not found", fullName));

        user.setApproved(true);

        userRepository.save(user);
    }

    @Override
    public UserEntity findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("User with this id " + id + " not found", id.toString()));
    }

    private ProfileViewModel mapToProfileView(UserEntity user) {
        ProfileViewModel profile = modelMapper.map(user, ProfileViewModel.class);
        //ToDo да направя по-добър формат за датите

        if (user.getPicture() != null) {
            profile.setPictureUrl(user.getPicture().getUrl());
        }

        profile
                .setStore(user.getStore())
                .setRole(user.findHighestRole());

        return profile;
    }

    private ProfileUpdateViewModel mapToProfileUpdateViewModel(UserEntity user) {
        ProfileUpdateViewModel profile = modelMapper.map(user , ProfileUpdateViewModel.class);

        profile
                .setPicture(user.getPicture())
                .setRole(user.findHighestRole());

        return profile;
    }

    @Override
    public boolean isAuthorize(String email, Long id) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException("User with this email " + email + " not found!", email));

        return isAdmin(user) || user.getId().equals(id);
    }

    @Override
    public boolean isAdmin(UserEntity user) {
        return user.getRoles()
                .stream()
                .map(UserRoleEntity::getRole)
                .anyMatch(r -> r == UserRoleEnum.ADMIN);
    }
}