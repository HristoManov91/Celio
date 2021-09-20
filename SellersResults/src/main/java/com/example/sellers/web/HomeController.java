package com.example.sellers.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/login")
    public ModelAndView login(@AuthenticationPrincipal UserDetails principal){
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("login" , principal);
        return mav;
    }
}
