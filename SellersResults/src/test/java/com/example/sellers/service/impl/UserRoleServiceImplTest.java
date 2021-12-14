package com.example.sellers.service.impl;

import com.example.sellers.model.entity.UserRoleEntity;
import com.example.sellers.model.entity.enums.UserRoleEnum;
import com.example.sellers.repository.UserRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserRoleServiceImplTest {

    private UserRoleEntity adminRole , managerRole , sellerRole;

    private UserRoleServiceImpl serviceToTest;

    @Mock
    private UserRoleRepository mockUserRepository;

    @BeforeEach
    void setUp() {

        serviceToTest = new UserRoleServiceImpl(mockUserRepository);

        adminRole = (UserRoleEntity) new UserRoleEntity()
                .setRole(UserRoleEnum.ADMIN)
                .setDescription("Description for Admin").setId(1L);

        managerRole = (UserRoleEntity) new UserRoleEntity()
                .setRole(UserRoleEnum.MANAGER)
                .setDescription("Description for Manager").setId(2L);

        sellerRole = (UserRoleEntity) new UserRoleEntity()
                .setRole(UserRoleEnum.SELLER)
                .setDescription("Description for Seller").setId(3L);
    }

    @Test
    void initUserRoles() {
        Mockito.when(mockUserRepository.count()).thenReturn(3L);

        assertEquals(3 , mockUserRepository.count());
    }

    @Test
    void findByInvalidIdAndThrowException(){
        assertThrows(IllegalArgumentException.class , () -> serviceToTest.findById(-1L));
    }

    @Test
    void findByValidId() {
        Mockito.when(mockUserRepository.findById(1L)).thenReturn(Optional.of(adminRole));

        UserRoleEntity role = serviceToTest.findById(1L);

        assertEquals(adminRole.getId() , role.getId());
        assertEquals(adminRole.getRole().name() , role.getRole().name());
    }

    @Test
    void findUserRoleByValidName() {
        Mockito.when(mockUserRepository.findUserRoleEntityByRole(UserRoleEnum.MANAGER)).thenReturn(Optional.of(managerRole));

        UserRoleEntity userRoleByName = serviceToTest.findUserRoleByName(UserRoleEnum.MANAGER);

        assertEquals(managerRole.getRole().name() , userRoleByName.getRole().name());
        assertEquals(managerRole.getId() , userRoleByName.getId());
        assertEquals(managerRole.getDescription() , userRoleByName.getDescription());
    }
}