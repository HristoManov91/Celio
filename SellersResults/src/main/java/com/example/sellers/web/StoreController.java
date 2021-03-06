package com.example.sellers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stores")
public class StoreController {
    //ToDo add store
    @GetMapping("/store-information")
    public String storeInformation(){
        //ToDo
        return "store-information";
    }

    @PostMapping("/shop-information")
    public String storeInformationConfirm(){
        //ToDo
        return "store-information";
    }

    @GetMapping("/store-results")
    public String storeResults(){
        //ToDo
        return "/main/resources/static/js/store-results.html";
    }

    @GetMapping("/all-stores-results")
    public String allStoresResults(){
        //ToDo
        return "all-stores-results";
    }
}
