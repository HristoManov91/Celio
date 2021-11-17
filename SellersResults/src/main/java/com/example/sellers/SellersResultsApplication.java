package com.example.sellers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SellersResultsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SellersResultsApplication.class, args);
    }

}
