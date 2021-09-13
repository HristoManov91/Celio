package com.example.sellers.init;

import com.example.sellers.model.entity.UserRoleEntity;
import com.example.sellers.model.entity.enums.CategoryEnum;
import com.example.sellers.model.entity.enums.MonthEnum;
import com.example.sellers.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class DatabaseInit implements CommandLineRunner {

    private final CategoryService categoryService;
    private final UserRoleService userRoleService;
    private final ProductService productService;
    private final ShopService shopService;
    private final SellerService sellerService;

    public DatabaseInit(CategoryService categoryService, UserRoleService userRoleService, ProductService productService,
                        ShopService shopService, SellerService sellerService) {
        this.categoryService = categoryService;
        this.userRoleService = userRoleService;
        this.productService = productService;
        this.shopService = shopService;
        this.sellerService = sellerService;
    }

    @Override
    public void run(String... args) throws Exception {
        categoryService.initCategories();
        userRoleService.initUserRoles();
        addProducts();
        shopService.initShops();
        shopService.setTarget(1L , BigDecimal.valueOf(94000) , MonthEnum.SEPTEMBER);
        addSellers();


    }

    private void addSellers() {
        sellerService.addSeller("Hristo Manov", "topsecret", "hristo@abv.bg", LocalDate.now(),
                List.of(userRoleService.findById(1L), userRoleService.findById(2L), userRoleService.findById(3L)), 1L);
        sellerService.addSeller("Plamen Terziev", "topsecret", "plamen@abv.bg", LocalDate.now(),
                List.of(userRoleService.findById(3L)), 1L);
        sellerService.addSeller("Viktor Dimitrov", "topsecret", "viktor@abv.bg", LocalDate.now(),
                List.of(userRoleService.findById(3L)), 2L);
        sellerService.addSeller("Ivan Ivanov", "topsecret", "ivan@abv.bg", LocalDate.now(),
                List.of(userRoleService.findById(3L)), 3L);
        sellerService.addSeller("Pesho Peshev", "topsecret", "pesho@abv.bg", LocalDate.now(),
                List.of(userRoleService.findById(3L)), 4L);
        sellerService.addSeller("Georgi Manov", "topsecret", "georgi@abv.bg", LocalDate.now(),
                List.of(userRoleService.findById(3L)), 3L);
        sellerService.addSeller("Stanislav Evlogiev", "topsecret", "stani@abv.bg", LocalDate.now(),
                List.of(userRoleService.findById(3L)), 2L);
    }

    private void addProducts() {
        if (productService.size() == 0) {
            productService.addProduct("Rolisse5", CategoryEnum.JEAN, BigDecimal.valueOf(119.99));
            productService.addProduct("Necetwo", CategoryEnum.POLO, BigDecimal.valueOf(49.99));
            productService.addProduct("Tohenri", CategoryEnum.PANTS, BigDecimal.valueOf(89.99));
            productService.addProduct("Neunir", CategoryEnum.TEE_SHIRT, BigDecimal.valueOf(24.99));
            productService.addProduct("Masantal", CategoryEnum.SHIRT, BigDecimal.valueOf(69.99));
            productService.addProduct("Fireverse", CategoryEnum.BELT, BigDecimal.valueOf(49.99));
            productService.addProduct("Roknit", CategoryEnum.BERMUDA, BigDecimal.valueOf(99.99));
            productService.addProduct("Nucolor", CategoryEnum.JACKETS, BigDecimal.valueOf(119.99));
            productService.addProduct("Mike", CategoryEnum.UNDERWEAR, BigDecimal.valueOf(19.99));
            productService.addProduct("Jecloud", CategoryEnum.SWEATER, BigDecimal.valueOf(149.99));
            productService.addProduct("Repop", CategoryEnum.SWEATSHIRT, BigDecimal.valueOf(89.99));
            productService.addProduct("Mifunky", CategoryEnum.SOCKS, BigDecimal.valueOf(9.99));
            productService.addProduct("Nysport", CategoryEnum.SHOES, BigDecimal.valueOf(89.99));
        }
    }
}
