package com.example.sellers.service.scheduling;

import com.example.sellers.service.SWRService;
import com.example.sellers.service.SaleService;
import com.example.sellers.service.StWRService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CronScheduler {

    private final static Logger LOGGER = LoggerFactory.getLogger(CronScheduler.class);

    private final StWRService stWRService;
    private final SWRService swrService;
    private final SaleService saleService;

    public CronScheduler(StWRService stWRService, SWRService swrService, SaleService saleService) {
        this.stWRService = stWRService;
        this.swrService = swrService;
        this.saleService = saleService;
    }

    //Add sales for test
    @Scheduled(cron = "* 5 0 * * *")
    public void addSales() {
        LOGGER.info("Start shops weak result method at {}", LocalDateTime.now());

        saleService.addSaleForTests();

        LOGGER.info("Finish shops weak result method at {}", LocalDateTime.now());
    }

    //Week Results
    @Scheduled(cron = "* 0 1 * * 1")
    public void storesWeakResults() {
        LOGGER.info("Start shops weak result method at {}", LocalDateTime.now());

        stWRService.weekResults();

        LOGGER.info("Finish shops weak result method at {}", LocalDateTime.now());
    }

    @Scheduled(cron = "* 30 1 * * 1")
    public void employeesWeakResults() {
        LOGGER.info("Start employees weak result method at {}", LocalDateTime.now());

        swrService.weekResults();

        LOGGER.info("Finish employees weak result method at {}", LocalDateTime.now());
    }

    //Month Results
    @Scheduled(cron = "* 0 2 1 * *")
    public void storesMonthResults() {
        LOGGER.info("Start shops month result method at {}", LocalDateTime.now());
        //ToDo implement

        LOGGER.info("Finish shops month result method at {}", LocalDateTime.now());
    }

    @Scheduled(cron = "* 30 2 1 * *")
    public void employeesMonthResults() {
        LOGGER.info("Start employees month result method at {}", LocalDateTime.now());
        //ToDo implement

        LOGGER.info("Finish employees month result method at {}", LocalDateTime.now());
    }

    //Year results
    @Scheduled(cron = "* 0 3 1 1 *")
    public void storesYearResults() {
        LOGGER.info("Start shops year result method at {}", LocalDateTime.now());
        //ToDo implement

        LOGGER.info("Finish shops year result method at {}", LocalDateTime.now());
    }

    @Scheduled(cron = "* 30 3 1 1 *")
    public void employeesYearResults() {
        LOGGER.info("Start shops year result method at {}", LocalDateTime.now());
        //ToDo implement

        LOGGER.info("Finish shops year result method at {}", LocalDateTime.now());
    }
}
