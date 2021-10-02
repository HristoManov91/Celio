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
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String confirmRegister() {

        return "redirect:login";
    }
}
