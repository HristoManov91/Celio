package com.example.sellers.service.scheduling;

import com.example.sellers.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CronScheduler {

    private final static Logger LOGGER = LoggerFactory.getLogger(CronScheduler.class);

    private final StoreService storeService;

    public CronScheduler(StoreService storeService) {
        this.storeService = storeService;
    }

    @Scheduled(cron = "* 0 1 1 * *")
    public void shopsMonthResults(){
        LOGGER.info("Start shops month result method at {}" , LocalDateTime.now());


        LOGGER.info("Finish shops month result method at {}" , LocalDateTime.now());
    }
}
