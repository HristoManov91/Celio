package com.example.sellers.service.impl;

import com.example.sellers.model.entity.SaleEntity;
import com.example.sellers.model.entity.UserEntity;
import com.example.sellers.model.entity.UserRoleEntity;
import com.example.sellers.model.entity.enums.UserRoleEnum;
import com.example.sellers.model.service.ProfileUpdateServiceModel;
import com.example.sellers.model.service.UserRegistrationServiceModel;
import com.example.sellers.model.view.ProfileViewModel;
import com.example.sellers.repository.UserRepository;
import com.example.sellers.service.*;
import com.example.sellers.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private final SaleService saleService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, StoreService storeService, UserRoleService userRoleService,
                           PictureService pictureService, @Lazy SaleService saleService, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.storeService = storeService;
        this.userRoleService = userRoleService;
        this.pictureService = pictureService;
        this.saleService = saleService;
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
    public BigDecimal find_AB_betweenDate(UserEntity userEntity, LocalDate fromDate, LocalDate toDate) {
        Set<SaleEntity> sales = saleService.findAllByUserAndDateBetween(userEntity, fromDate, toDate);
        BigDecimal priceSum = sales.stream().map(SaleEntity::sumOfProductPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        //ToDo да се тества дали работи правилно
        return priceSum.divide(new BigDecimal(sales.size()), 2, RoundingMode.CEILING);
    }

    @Override
    public BigDecimal find_AP_betweenDate(UserEntity userEntity, LocalDate fromDate, LocalDate toDate) {
        Set<SaleEntity> sales = saleService.findAllByUserAndDateBetween(userEntity, fromDate, toDate);
        BigDecimal priceSum = sales.stream().map(SaleEntity::sumOfProductPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        long countOfAllProducts = sales.stream().map(SaleEntity::countOfProducts).count();

        return priceSum.divide(new BigDecimal(countOfAllProducts), 2, RoundingMode.CEILING);
    }

    @Override
    public BigDecimal find_UPT_betweenDate(UserEntity userEntity, LocalDate fromDate, LocalDate toDate) {
        Set<SaleEntity> sales = saleService.findAllByUserAndDateBetween(userEntity, fromDate, toDate);
        long countOfAllProducts = sales.stream().map(SaleEntity::countOfProducts).count();

        return BigDecimal.valueOf(countOfAllProducts)
                .divide(new BigDecimal(sales.size()), 2, RoundingMode.CEILING);
    }

    @Override
    public BigDecimal findTotalSumBetweenDate(UserEntity userEntity, LocalDate fromDate, LocalDate toDate) {
        return saleService
                .findAllByUserAndDateBetween(userEntity, fromDate, toDate)
                .stream()
                .map(SaleEntity::sumOfProductPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.CEILING);
    }

    @Override
    public int countTotalSalesBetweenDate(UserEntity userEntity, LocalDate fromDate, LocalDate toDate) {
        return saleService.findAllByUserAndDateBetween(userEntity, fromDate, toDate).size();
    }

    @Override
    public long countTotalProductsBetweenDate(UserEntity userEntity, LocalDate fromDate, LocalDate toDate) {
        Set<SaleEntity> sales = saleService.findAllByUserAndDateBetween(userEntity, fromDate, toDate);
        return sales.stream().map(SaleEntity::countOfProducts).count();
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

        user.setStore(storeService.findByName(userRegistrationServiceModel.getShop()));

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
                .orElseThrow(IllegalArgumentException::new);

        user.setStore(storeService.findByName(store));

        userRepository.save(user);
    }

    @Override
    public void addUserRole(UserRoleEnum role, String userFullName) {
        UserRoleEntity userRole = userRoleService.findUserRoleByName(role);

        UserEntity user = userRepository.findUserEntityByFullNameAndLeftEmployeeIsFalseAndApprovedIsTrue(userFullName)
                .orElseThrow(() -> new IllegalArgumentException("User with this full name not found"));

        user.addRole(userRole);


        userRepository.save(user);
    }

    @Override
    public void removeUser(String fullName) {
        UserEntity user = userRepository.findUserEntityByFullNameAndLeftEmployeeIsFalseAndApprovedIsTrue(fullName)
                .orElseThrow(() -> new IllegalStateException("User with this full name not found"));

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
        return userRepository.findById(id).map(this::mapToProfileView).get();
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
                .orElseThrow(() -> new IllegalArgumentException("User with this email not found"));

        return mapToProfileView(userEntity);
    }

    @Override
    public void editProfile(ProfileUpdateServiceModel profileModel) {
        UserEntity userEntity = userRepository.findById(profileModel.getId())
                .orElseThrow(() -> new ObjectNotFoundException("Offer with id " + profileModel.getId() + " not found!"));

        if (userEntity.getPicture() != null){
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
    public void approvedUser(String fullName) {
        UserEntity user = userRepository.findUserEntityByFullNameAndLeftEmployeeIsFalseAndApprovedIsFalse(fullName)
                .orElseThrow(() -> new IllegalStateException("User with this full name not found"));

        user.setApproved(true);

        userRepository.save(user);
    }

    @Override
    public UserEntity findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("User with this full name not found"));
    }

    private ProfileViewModel mapToProfileView(UserEntity user) {
        ProfileViewModel profile = modelMapper.map(user, ProfileViewModel.class);
        //ToDo да направя по-добър формат за датите

        if (user.getPicture() != null){
            profile.setPictureUrl(user.getPicture().getUrl());
        }

        profile.setStore(user.getStore())
                .setBestBill(user.getBestBill())
                .setMostProductsInBill(user.getMostProductsInBill())
                .setRole(user.findHighestRole());

        return profile;
    }

}