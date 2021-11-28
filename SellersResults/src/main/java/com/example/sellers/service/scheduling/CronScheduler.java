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
        LOGGER.info("Starts adding test sales method at {}", LocalDateTime.now());

//        saleService.addSaleForTests(); ToDo да го променя да добавя продажби само за деня

        LOGGER.info("Finish adding test sales method at {}", LocalDateTime.now());
    }

    //Week Results
    @Scheduled(cron = "* 0 1 * * 1")
    public void storesWeakResults() {
        LOGGER.info("Start stores weak results method at {}", LocalDateTime.now());

        stWRService.weekResults();

        LOGGER.info("Finish shops weak results method at {}", LocalDateTime.now());
    }

    @Scheduled(cron = "* 30 1 * * 1")
    public void employeesWeakResults() {
        LOGGER.info("Start employees weak results method at {}", LocalDateTime.now());

        swrService.weekResults();

        LOGGER.info("Finish employees weak results method at {}", LocalDateTime.now());
    }

    //Month Results
    @Scheduled(cron = "* 0 2 1 * *")
    public void storesMonthResults() {
        LOGGER.info("Start stores month results method at {}", LocalDateTime.now());
        //ToDo implement

        LOGGER.info("Finish stores month results method at {}", LocalDateTime.now());
    }

    @Scheduled(cron = "* 30 2 1 * *")
    public void employeesMonthResults() {
        LOGGER.info("A method for the monthly results of the employees has been launched at {}", LocalDateTime.now());
        //ToDo implement

        LOGGER.info("Finish employees month results method at {}", LocalDateTime.now());
    }

    //Year results
    @Scheduled(cron = "* 0 3 1 1 *")
    public void storesYearResults() {
        LOGGER.info("Start stores year results method at {}", LocalDateTime.now());
        //ToDo implement

        LOGGER.info("Finish stores year results method at {}", LocalDateTime.now());
    }

    @Scheduled(cron = "* 30 3 1 1 *")
    public void employeesYearResults() {
        LOGGER.info("Launched method for annual results of employees at {}", LocalDateTime.now());
        //ToDo implement

        LOGGER.info("The method for annual results of employees is completed at {}", LocalDateTime.now());
    }

    //Clear old results
    @Scheduled(cron = "* 0 6 1 1 *")
    public void clearingOldStoreResults(){
        LOGGER.info("Start shops year result method at {}", LocalDateTime.now());

        //ToDo implement

        LOGGER.info("Finish shops year result method at {}", LocalDateTime.now());
    }

    @Scheduled(cron = "* 30 6 1 1 *")
    public void clearingOldEmployeesResults(){
        LOGGER.info("Start shops year result method at {}", LocalDateTime.now());

        //ToDo implement

        LOGGER.info("Finish shops year result method at {}", LocalDateTime.now());
    }
}
