package com.example.sellers.web;

import com.example.sellers.model.binding.SaleAddBindingModel;
import com.example.sellers.model.service.SaleAddServiceModel;
import com.example.sellers.service.ProductService;
import com.example.sellers.service.SaleService;
import com.example.sellers.service.StoreService;
import com.example.sellers.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;
    private final ProductService productService;
    private final StoreService storeService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public SaleController(SaleService saleService, ProductService productService, StoreService storeService, UserService userService, ModelMapper modelMapper) {
        this.saleService = saleService;
        this.productService = productService;
        this.storeService = storeService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String addSale(Model model) {
        model.addAttribute("sellers", userService.findAllUsersFullName());
        model.addAttribute("products", productService.getAllProductsOrderByCategory());
        model.addAttribute("stores", storeService.findAllStoresNames());
        SaleAddBindingModel sale = new SaleAddBindingModel();
        sale.setProducts(List.of(""));
        model.addAttribute("saleAddBindingModel", sale);

        return "add-sale";
    }

    @PostMapping("/add")
    public String addSaleConfirm(@Valid SaleAddBindingModel saleAddBindingModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("saleAddBindingModel", saleAddBindingModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.saleAddBindingModel", bindingResult);
            return "redirect:add";
        }

        SaleAddServiceModel map = modelMapper.map(saleAddBindingModel, SaleAddServiceModel.class);
        saleService.createSale(map);

        return "redirect:add";
    }

    @GetMapping("/remove")
    public String removeSale() {
        return "remove-sale";
    }
}
