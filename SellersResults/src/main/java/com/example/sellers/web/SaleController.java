package com.example.sellers.web;

import com.example.sellers.model.binding.SaleAddBindingModel;
import com.example.sellers.service.ProductService;
import com.example.sellers.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/sales")
public class SaleController {

    private final ProductService productService;
    private final UserService userService;

    public SaleController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/add")
    public String addSale(Model model){
        model.addAttribute("users" , userService.findAllUsersFullName());
        model.addAttribute("products" , productService.getAllProductsOrderByCategory());
        if (!model.containsAttribute("saleAddBindingModel")){
            model.addAttribute("saleAddBindingModel" , new SaleAddBindingModel());
        }
        return "add-sale";
    }

    @PostMapping("/add")
    public String addSaleConfirm(@Valid SaleAddBindingModel saleAddBindingModel,
                                 BindingResult bindingResult ,
                                 RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            //ToDo
        }

        return "redirect:add";
    }
}
