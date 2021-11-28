package com.example.sellers.web;

import com.example.sellers.model.binding.SellerResultBindingModel;
import com.example.sellers.model.service.EmployeeResultServiceModel;
import com.example.sellers.service.SaleService;
import com.example.sellers.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping("/seller-results")
public class SellerResultController {

    private final SaleService saleService;

    public SellerResultController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping()
    public String results(Model model, SellerResultBindingModel sellerResultBindingModel) {
        if (!model.containsAttribute("sellerResultBindingModel")) {
            model.addAttribute("sellerResultBindingModel", sellerResultBindingModel);
        }
        return "sellers-results";
    }

    @PostMapping()
    public String resultsConfirm(@Valid SellerResultBindingModel sellerResultBindingModel,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                 Model model) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("sellerResultBindingModel", sellerResultBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.sellerResultBindingModel",
                            bindingResult);

            return "redirect:/seller-results";
        }

        Set<EmployeeResultServiceModel> employeeResultServiceModels = saleService.calculateEmployeeResultsBetweenDate(
                sellerResultBindingModel.getFromDate(),
                sellerResultBindingModel.getEndDate());

        model.addAttribute(employeeResultServiceModels);

        return "sellers-results";
    }
}
