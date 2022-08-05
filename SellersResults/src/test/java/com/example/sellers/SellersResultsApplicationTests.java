package com.example.sellers;

import com.example.sellers.model.entity.StoreEntity;
import com.example.sellers.repository.StoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class SellersResultsApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StoreRepository storeRepository;

    private StoreEntity testStore;

    @BeforeEach
    void setUp() {
        testStore = new StoreEntity().setName("Test store");
    }

    @Test
    void testGetStore() {
    }

}
