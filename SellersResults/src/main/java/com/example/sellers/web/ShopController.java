package com.example.sellers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("shops")
public class ShopController {

    @GetMapping("/shop-information")
    public String shopInformation(){
        //ToDo
        return "shop-information";
    }

    @PostMapping("/shop-information")
    public String shopInformationConfirm(){
        //ToDo
        return "shop-information";
    }

    @GetMapping("/shop-results")
    public String shopResults(){
        //ToDo
        return "shop-results";
    }

    @GetMapping("/all-shops-results")
    public String allShopsResults(){
        //ToDo
        return "all-shops-results";
    }
}
