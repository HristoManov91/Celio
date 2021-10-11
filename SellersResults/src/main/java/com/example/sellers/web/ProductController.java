package com.example.sellers.web;

import com.example.sellers.model.binding.ProductAddBindingModel;
import com.example.sellers.model.entity.enums.CategoryEnum;
import com.example.sellers.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        List<CategoryEnum> categories = Arrays.stream(CategoryEnum.values()).toList();
        model.addAttribute("categories", categories);
        if (!model.containsAttribute("productAddBindingModel")) {
            model.addAttribute("productAddBindingModel", new ProductAddBindingModel());
        }
        return "add-product";
    }

    @PostMapping("/add")
    public String addProductConfirm(@Valid ProductAddBindingModel productAddBindingModel ,
                                    BindingResult bindingResult , RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("productAddBindingModel" , productAddBindingModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.productAddBindingModel", bindingResult);
            return "redirect:add";
        }
        productService.addProduct(
                productAddBindingModel.getName(),
                CategoryEnum.valueOf(productAddBindingModel.getName().toUpperCase(Locale.ROOT)),
                productAddBindingModel.getPrice());
        //ToDo да проверя как може да изкара съобщение,че продукта е добавен успешно
        return "redirect:add-product";
    }

    @GetMapping("/remove")
    public String removeProduct() {
        return "remove-product";
    }

    @PostMapping("/remove")
    public String removeProductConfirm() {
        //ToDo
        return "redirect:remove-product";
    }
}
