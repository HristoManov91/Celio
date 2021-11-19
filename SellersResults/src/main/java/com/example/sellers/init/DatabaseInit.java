package com.example.sellers.init;

import com.example.sellers.model.entity.enums.CategoryEnum;
import com.example.sellers.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DatabaseInit implements CommandLineRunner {

    private final UserRoleService userRoleService;
    private final ProductService productService;
    private final StoreService storeService;
    private final UserService userService;
    private final SaleService saleService;
    private final PasswordEncoder passwordEncoder;

    public DatabaseInit(UserRoleService userRoleService, ProductService productService,
                        StoreService storeService, UserService userService, SaleService saleService, PasswordEncoder passwordEncoder) {
        this.userRoleService = userRoleService;
        this.productService = productService;
        this.storeService = storeService;
        this.userService = userService;
        this.saleService = saleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        userRoleService.initUserRoles();
        addProducts();
        storeService.initStores();
        addUsers();
//        saleService.addSaleForTests();
    }

    private void addUsers() {
        if (userService.count() == 0) {
            userService.addAdmin("Hristo Manov", passwordEncoder.encode("topsecret"), "hristo@abv.bg");
            userService.addUser("Plamen Terziev", passwordEncoder.encode("topsecret"), "plamen@abv.bg", 1L);
            userService.addUser("Lubo Purgov", passwordEncoder.encode("topsecret"), "lubo@abv.bg", 1L);
            userService.addUser("Ema Petrova", passwordEncoder.encode("topsecret"), "ema@abv.bg", 1L);
            userService.addUser("Asmik Ovasian", passwordEncoder.encode("topsecret"), "asi@abv.bg", 1L);
            userService.addUser("Jordan Kostov", passwordEncoder.encode("topsecret"), "jordan@abv.bg", 1L);
            userService.addUser("Borislav Borisov", passwordEncoder.encode("topsecret"), "borislav@abv.bg", 1L);
            userService.addUser("Viktor Dimitrov", passwordEncoder.encode("topsecret"), "viktor@abv.bg", 2L);
            userService.addUser("Miglena Nikolova", passwordEncoder.encode("topsecret"), "miglena@abv.bg", 2L);
            userService.addUser("Nina Achilova", passwordEncoder.encode("topsecret"), "nina@abv.bg", 2L);
            userService.addUser("Stanislav Evlogiev", passwordEncoder.encode("topsecret"), "stani@abv.bg", 2L);
            userService.addUser("Desislava Vasileva", passwordEncoder.encode("topsecret"), "desi@abv.bg", 2L);
            userService.addUser("Radostina Asenova", passwordEncoder.encode("topsecret"), "radi@abv.bg", 2L);
            userService.addUser("Kristina Dimitrova", passwordEncoder.encode("topsecret"), "krisi@abv.bg", 3L);
            userService.addUser("Simeon Karadjinov", passwordEncoder.encode("topsecret"), "simo@abv.bg", 3L);
            userService.addUser("Adrian Belqshki", passwordEncoder.encode("topsecret"), "adi@abv.bg", 3L);
            userService.addUser("Nikolai Radkov", passwordEncoder.encode("topsecret"), "niki@abv.bg", 3L);
            userService.addUser("Hristina Zaharieva", passwordEncoder.encode("topsecret"), "hris@abv.bg", 3L);
            userService.addUser("Aleksandrina Ivanov", passwordEncoder.encode("topsecret"), "aleks@abv.bg", 3L);
            userService.addUser("Katq Vasileva", passwordEncoder.encode("topsecret"), "katq@abv.bg", 4L);
            userService.addUser("Dragomir Velev", passwordEncoder.encode("topsecret"), "drago@abv.bg", 4L);
            userService.addUser("Ivan Mutafchiev", passwordEncoder.encode("topsecret"), "ivan@abv.bg", 4L);
            userService.addUser("Dimitur Milev", passwordEncoder.encode("topsecret"), "mitko@abv.bg", 4L);
        }
    }

    private void addProducts() {
        if (productService.size() == 0) {
            productService.addProduct("Rolisse5", CategoryEnum.JEAN, BigDecimal.valueOf(119.99));
            productService.addProduct("Portal5", CategoryEnum.JEAN, BigDecimal.valueOf(119.99));
            productService.addProduct("Tonara5", CategoryEnum.JEAN, BigDecimal.valueOf(119.99));
            productService.addProduct("Rostay5", CategoryEnum.JEAN, BigDecimal.valueOf(119.99));
            productService.addProduct("Nobody15", CategoryEnum.JEAN, BigDecimal.valueOf(119.99));
            productService.addProduct("Roclean15", CategoryEnum.JEAN, BigDecimal.valueOf(119.99));
            productService.addProduct("Lokraw15", CategoryEnum.JEAN, BigDecimal.valueOf(119.99));
            productService.addProduct("Voblack15", CategoryEnum.JEAN, BigDecimal.valueOf(119.99));
            productService.addProduct("Robleach15", CategoryEnum.JEAN, BigDecimal.valueOf(119.99));
            productService.addProduct("Sowornever", CategoryEnum.JEAN, BigDecimal.valueOf(144.99));
            productService.addProduct("Rowling", CategoryEnum.JEAN, BigDecimal.valueOf(144.99));
            productService.addProduct("Nowoir", CategoryEnum.JEAN, BigDecimal.valueOf(144.99));
            productService.addProduct("Todestroy", CategoryEnum.JEAN, BigDecimal.valueOf(89.99));
            productService.addProduct("Fosloir25", CategoryEnum.JEAN, BigDecimal.valueOf(89.99));
            productService.addProduct("Voslone25", CategoryEnum.JEAN, BigDecimal.valueOf(89.99));

            productService.addProduct("Tohenri", CategoryEnum.PANTS, BigDecimal.valueOf(89.99));
            productService.addProduct("Tocharles", CategoryEnum.PANTS, BigDecimal.valueOf(89.99));
            productService.addProduct("Vocolors", CategoryEnum.PANTS, BigDecimal.valueOf(89.99));
            productService.addProduct("Tofiberflex", CategoryEnum.PANTS, BigDecimal.valueOf(144.99));
            productService.addProduct("Vojoggyoke", CategoryEnum.PANTS, BigDecimal.valueOf(79.99));
            productService.addProduct("Rouan2", CategoryEnum.PANTS, BigDecimal.valueOf(119.99));
            productService.addProduct("Romero3", CategoryEnum.PANTS, BigDecimal.valueOf(99.99));

            productService.addProduct("Roknit", CategoryEnum.BERMUDA, BigDecimal.valueOf(99.99));
            productService.addProduct("Roklue", CategoryEnum.BERMUDA, BigDecimal.valueOf(99.99));
            productService.addProduct("Roslack", CategoryEnum.BERMUDA, BigDecimal.valueOf(69.99));
            productService.addProduct("Mohito", CategoryEnum.BERMUDA, BigDecimal.valueOf(59.99));
            productService.addProduct("Rocourte", CategoryEnum.BERMUDA, BigDecimal.valueOf(99.99));
            productService.addProduct("Tolinbm", CategoryEnum.BERMUDA, BigDecimal.valueOf(79.99));
            productService.addProduct("Toshortbm", CategoryEnum.BERMUDA, BigDecimal.valueOf(59.99));

            productService.addProduct("Veyoke", CategoryEnum.SWEATER, BigDecimal.valueOf(89.99));
            productService.addProduct("Segilllou", CategoryEnum.SWEATER, BigDecimal.valueOf(89.99));
            productService.addProduct("Verzo", CategoryEnum.SWEATER, BigDecimal.valueOf(79.99));
            productService.addProduct("Verone", CategoryEnum.SWEATER, BigDecimal.valueOf(69.99));
            productService.addProduct("Avebenorm", CategoryEnum.SWEATER, BigDecimal.valueOf(59.99));
            productService.addProduct("Semerirond", CategoryEnum.SWEATER, BigDecimal.valueOf(89.99));
            productService.addProduct("Vemerzip", CategoryEnum.SWEATER, BigDecimal.valueOf(99.99));
            productService.addProduct("Semilano", CategoryEnum.SWEATER, BigDecimal.valueOf(99.99));
            productService.addProduct("Tepic", CategoryEnum.SWEATER, BigDecimal.valueOf(69.99));
            productService.addProduct("Techillpic", CategoryEnum.SWEATER, BigDecimal.valueOf(69.99));
            productService.addProduct("Viprice", CategoryEnum.SWEATER, BigDecimal.valueOf(49.99));

            productService.addProduct("Necetwo", CategoryEnum.POLO, BigDecimal.valueOf(49.99));
            productService.addProduct("Teone", CategoryEnum.POLO, BigDecimal.valueOf(39.99));
            productService.addProduct("Tebbosy", CategoryEnum.POLO, BigDecimal.valueOf(49.99));
            productService.addProduct("Vegenius", CategoryEnum.POLO, BigDecimal.valueOf(55.99));
            productService.addProduct("Vepetit", CategoryEnum.POLO, BigDecimal.valueOf(69.99));
            productService.addProduct("Vepalmito", CategoryEnum.POLO, BigDecimal.valueOf(69.99));
            productService.addProduct("Atedede", CategoryEnum.POLO, BigDecimal.valueOf(39.99));

            productService.addProduct("Neunir", CategoryEnum.TEE_SHIRT, BigDecimal.valueOf(24.99));
            productService.addProduct("Lvegod", CategoryEnum.TEE_SHIRT, BigDecimal.valueOf(39.99));
            productService.addProduct("Tebasic", CategoryEnum.TEE_SHIRT, BigDecimal.valueOf(19.99));
            productService.addProduct("Veblack", CategoryEnum.TEE_SHIRT, BigDecimal.valueOf(29.99));
            productService.addProduct("Vesushi", CategoryEnum.TEE_SHIRT, BigDecimal.valueOf(24.99));
            productService.addProduct("Atearum", CategoryEnum.TEE_SHIRT, BigDecimal.valueOf(39.99));

            productService.addProduct("Masantal", CategoryEnum.SHIRT, BigDecimal.valueOf(69.99));
            productService.addProduct("Veflaka", CategoryEnum.SHIRT, BigDecimal.valueOf(69.99));
            productService.addProduct("Vafla", CategoryEnum.SHIRT, BigDecimal.valueOf(69.99));
            productService.addProduct("Sactive", CategoryEnum.SHIRT, BigDecimal.valueOf(99.99));
            productService.addProduct("Ratamao", CategoryEnum.SHIRT, BigDecimal.valueOf(89.99));
            productService.addProduct("Sapik", CategoryEnum.SHIRT, BigDecimal.valueOf(59.99));
            productService.addProduct("Narox", CategoryEnum.SHIRT, BigDecimal.valueOf(89.99));
            productService.addProduct("Tacaro", CategoryEnum.SHIRT, BigDecimal.valueOf(79.99));

            productService.addProduct("Nucolor", CategoryEnum.JACKETS, BigDecimal.valueOf(119.99));
            productService.addProduct("Vebuble", CategoryEnum.JACKETS, BigDecimal.valueOf(119.99));
            productService.addProduct("Sunew", CategoryEnum.JACKETS, BigDecimal.valueOf(119.99));
            productService.addProduct("Rubiker", CategoryEnum.JACKETS, BigDecimal.valueOf(169.99));
            productService.addProduct("Vucoccon", CategoryEnum.JACKETS, BigDecimal.valueOf(249.99));
            productService.addProduct("Vushine", CategoryEnum.JACKETS, BigDecimal.valueOf(249.99));
            productService.addProduct("Vumes", CategoryEnum.JACKETS, BigDecimal.valueOf(239.99));
            productService.addProduct("Puhendrix", CategoryEnum.JACKETS, BigDecimal.valueOf(549.99));
            productService.addProduct("Puoffice", CategoryEnum.JACKETS, BigDecimal.valueOf(299.99));
            productService.addProduct("Sulles", CategoryEnum.JACKETS, BigDecimal.valueOf(79.99));

            productService.addProduct("Mike", CategoryEnum.UNDERWEAR, BigDecimal.valueOf(19.99));
            productService.addProduct("MITCH", CategoryEnum.UNDERWEAR, BigDecimal.valueOf(19.99));

            productService.addProduct("Niversible", CategoryEnum.OTHER, BigDecimal.valueOf(49.99));
            productService.addProduct("Fireverse", CategoryEnum.OTHER, BigDecimal.valueOf(49.99));
            productService.addProduct("Vibes", CategoryEnum.OTHER, BigDecimal.valueOf(34.99));
            productService.addProduct("Vigums", CategoryEnum.OTHER, BigDecimal.valueOf(79.99));
        }
    }
}

