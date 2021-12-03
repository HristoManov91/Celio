package com.example.sellers.web;

import com.example.sellers.model.service.EmployeeResultServiceModel;
import com.example.sellers.service.SaleService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/seller-results")
public class SellerResultController {

    private final SaleService saleService;

    public SellerResultController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping()
    public String results(@RequestParam(name = "fromDate" , required = false) LocalDate fromDate,
                          @RequestParam(name = "endDate" , required = false) LocalDate endDate,
                          Model model) {

        if (fromDate != null) {
            model.addAttribute("employeeResultServiceModels",
                    saleService.calculateEmployeeResultsBetweenDate(fromDate, endDate));
        }
        return "sellers-results";
    }
}
