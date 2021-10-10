package com.example.sellers.service.impl;

import com.example.sellers.model.entity.SaleEntity;
import com.example.sellers.model.entity.UserEntity;
import com.example.sellers.model.entity.UserRoleEntity;
import com.example.sellers.model.service.UserRegistrationServiceModel;
import com.example.sellers.repository.UserRepository;
import com.example.sellers.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ShopService shopService;
    private final UserRoleService userRoleService;
    private final SaleService saleService;
    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ShopService shopService, UserRoleService userRoleService, SaleService saleService, ProductService productService, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.shopService = shopService;
        this.userRoleService = userRoleService;
        this.saleService = saleService;
        this.productService = productService;
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
                .setShop(shopService.findById(shopId))
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
        return priceSum.divide(new BigDecimal(sales.size()),2 , RoundingMode.CEILING);
    }

    @Override
    public BigDecimal find_AP_betweenDate(UserEntity userEntity, LocalDate fromDate, LocalDate toDate) {
        Set<SaleEntity> sales = saleService.findAllByUserAndDateBetween(userEntity, fromDate, toDate);
        BigDecimal priceSum = sales.stream().map(SaleEntity::sumOfProductPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        long countOfAllProducts = sales.stream().map(SaleEntity::countOfProducts).count();

        return priceSum.divide(new BigDecimal(countOfAllProducts) , 2 , RoundingMode.CEILING);
    }

    @Override
    public BigDecimal find_UPT_betweenDate(UserEntity userEntity, LocalDate fromDate, LocalDate toDate) {
        Set<SaleEntity> sales = saleService.findAllByUserAndDateBetween(userEntity, fromDate, toDate);
        long countOfAllProducts = sales.stream().map(SaleEntity::countOfProducts).count();

        return BigDecimal.valueOf(countOfAllProducts)
                .divide(new BigDecimal(sales.size()),2 , RoundingMode.CEILING);
    }

    @Override
    public BigDecimal findTotalSumBetweenDate(UserEntity userEntity, LocalDate fromDate, LocalDate toDate) {
        return saleService
                .findAllByUserAndDateBetween(userEntity, fromDate, toDate)
                .stream()
                .map(SaleEntity::sumOfProductPrice)
                .reduce(BigDecimal.ZERO , BigDecimal::add)
                .setScale(2 , RoundingMode.CEILING);
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
        UserEntity user = modelMapper.map(userRegistrationServiceModel , UserEntity.class);
        user.setPassword(passwordEncoder.encode(userRegistrationServiceModel.getPassword()));

        UserRoleEntity userRole = userRoleService.findById(3L);
        user.addRole(userRole);

        user.setDateOfAppointment(LocalDate.now());

        user.setShop(shopService.findByName(userRegistrationServiceModel.getShop()));

        userRepository.save(user);
    }
}
