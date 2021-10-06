package com.example.sellers.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController {


    @GetMapping("/login")
    public ModelAndView login(@AuthenticationPrincipal UserDetails principal) {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("login", principal);
        return mav;
    }

    @PostMapping("/login")
    public String loginConfirm() {
        //ToDо
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String confirmRegister() {
        //ToDo да оправя формата за регистриране
        return "redirect:login";
    }

    @GetMapping("/profile")
    public String profile(){
        return "profile";
    }

    @PostMapping("/profile")
    public String confirmProfile(){
        //ToDo
        return "redirect:profile";
    }

    @GetMapping("/set-seller-store")
    public String setSellerStore(){
        return "set-seller-store";
    }

    @PostMapping("/set-seller-store")
    public String setSellerStoreConfirm(){
        //ToDo
        return "redirect:set-seller-store";
    }

    @GetMapping("/remove-sale")
    public String removeSale(){
        return "remove-sale";
    }

    @PostMapping("remove-sale")
    public String removeSaleConfirm(){
        //ToDo
        return "redirect:remove-sale";
    }

    @GetMapping("/remove-seller")
    public String removeSeller(){
        return "remove-seller";
    }

    @PostMapping("/remove-seller")
    public String removeSellerConfirm(){
        //ToDo
        return "redirect:remove-seller";
    }
}
