package com.example.sellers.init;

import com.example.sellers.model.entity.enums.CategoryEnum;
import com.example.sellers.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DatabaseInit implements CommandLineRunner {

    private final CategoryService categoryService;
    private final UserRoleService userRoleService;
    private final ProductService productService;
    private final ShopService shopService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public DatabaseInit(CategoryService categoryService, UserRoleService userRoleService, ProductService productService,
                        ShopService shopService, UserService userService, PasswordEncoder passwordEncoder) {
        this.categoryService = categoryService;
        this.userRoleService = userRoleService;
        this.productService = productService;
        this.shopService = shopService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        categoryService.initCategories();
        userRoleService.initUserRoles();
        addProducts();
        shopService.initShops();
        addUsers();


    }

    private void addUsers() {
        if (userService.count() == 0) {
            userService.addUser("Hristo Manov", passwordEncoder.encode("topsecret"), "hristo@abv.bg", 1L);
            userService.addUser("Plamen Terziev", passwordEncoder.encode("topsecret"), "plamen@abv.bg", 1L);
            userService.addUser("Viktor Dimitrov", passwordEncoder.encode("topsecret"), "viktor@abv.bg", 2L);
            userService.addUser("Ivan Ivanov", passwordEncoder.encode("topsecret"), "ivan@abv.bg", 3L);
            userService.addUser("Pesho Peshev", passwordEncoder.encode("topsecret"), "pesho@abv.bg", 4L);
            userService.addUser("Georgi Manov", passwordEncoder.encode("topsecret"), "georgi@abv.bg", 3L);
            userService.addUser("Stanislav Evlogiev", passwordEncoder.encode("topsecret"), "stani@abv.bg", 2L);
        }
    }

    private void addProducts() {
        if (productService.size() == 0) {
            productService.addProduct("Rolisse5", CategoryEnum.JEAN, BigDecimal.valueOf(119.99));
            productService.addProduct("Necetwo", CategoryEnum.POLO, BigDecimal.valueOf(49.99));
            productService.addProduct("Tohenri", CategoryEnum.PANTS, BigDecimal.valueOf(89.99));
            productService.addProduct("Neunir", CategoryEnum.TEE_SHIRT, BigDecimal.valueOf(24.99));
            productService.addProduct("Masantal", CategoryEnum.SHIRT, BigDecimal.valueOf(69.99));
            productService.addProduct("Roknit", CategoryEnum.BERMUDA, BigDecimal.valueOf(99.99));
            productService.addProduct("Nucolor", CategoryEnum.JACKETS, BigDecimal.valueOf(119.99));
            productService.addProduct("Mike", CategoryEnum.UNDERWEAR, BigDecimal.valueOf(19.99));
            productService.addProduct("Jecloud", CategoryEnum.SWEATER, BigDecimal.valueOf(149.99));
        }
    }
}
