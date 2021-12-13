package com.example.sellers.service.impl;

import com.example.sellers.model.entity.results.SellerWeekResultEntity;
import com.example.sellers.repository.results.SellerWeekResultRepository;
import com.example.sellers.service.SWRService;
import com.example.sellers.service.SaleService;
import com.example.sellers.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
public class SWRServiceImpl implements SWRService {

    private final SellerWeekResultRepository sellerWeekResultRepository;
    private final UserService userService;
    private final SaleService saleService;

    public SWRServiceImpl(SellerWeekResultRepository sellerWeekResultRepository, UserService userService, SaleService saleService) {
        this.sellerWeekResultRepository = sellerWeekResultRepository;
        this.userService = userService;
        this.saleService = saleService;
    }

    @Override
    public void weekResults() {
        LocalDate fromDate = LocalDate.now().minusDays(7L);
        LocalDate toDate = LocalDate.now().minusDays(1L);
        Set<String> allUserFullName = userService.findAllUsersFullName();

        for (String user : allUserFullName) {
            SellerWeekResultEntity sellerResults = saleService.calculateEmployeeWeekResults(user, fromDate, toDate);
            if (sellerResults != null) {
                sellerWeekResultRepository.save(sellerResults);
            }
        }
    }

    @Override
    public void weekTestResults() {
        LocalDate fromDate = LocalDate.of(2021, 11, 1);
        LocalDate toDate = LocalDate.of(2021, 11, 7);
        Set<String> allUserFullName = userService.findAllUsersFullName();

        while (toDate.isBefore(LocalDate.now())){
            for (String fullName : allUserFullName) {

                SellerWeekResultEntity sellerResults = saleService.calculateEmployeeWeekResults(fullName, fromDate, toDate);
                if (sellerResults != null) {
                    sellerWeekResultRepository.save(sellerResults);
                }
            }
            fromDate = fromDate.plusDays(7);
            toDate = toDate.plusDays(7);
        }
    }
}
