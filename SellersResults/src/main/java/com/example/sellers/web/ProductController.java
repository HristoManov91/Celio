package com.example.sellers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/add")
    public String addProduct(){
        return "add-product";
    }

    @PostMapping("/add")
    public String addProductConfirm(){
        //ToDo
        return "redirect:add-product";
    }

    @GetMapping("/remove")
    public String removeProduct(){
        return "remove-product";
    }

    @PostMapping("/remove")
    public String removeProductConfirm(){
        //ToDo
        return "redirect:remove=product";
    }
}
