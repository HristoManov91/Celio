package com.example.sellers.web;

import com.example.sellers.model.binding.UserRegisterBindingModel;
import com.example.sellers.model.entity.UserEntity;
import com.example.sellers.model.service.UserRegistrationServiceModel;
import com.example.sellers.service.ShopService;
import com.example.sellers.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ShopService shopService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ShopService shopService, ModelMapper modelMapper) {
        this.userService = userService;
        this.shopService = shopService;
        this.modelMapper = modelMapper;
    }

//    @ModelAttribute("registrationBindingModel")
//    public UserRegisterBindingModel createUserRegisterBindingModel(){
//        return new UserRegisterBindingModel();
//    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login-error")
    public ModelAndView failedLogin(@ModelAttribute String email) {
        //ToDo да проверя как може да ми запазва email при грешно въведени данни
        ModelAndView mvc = new ModelAndView();

        mvc.addObject("bad_credentials" , true);
        mvc.addObject("email" , email);

        mvc.setViewName("login");

        return mvc;
    }

//    @PostMapping("/login")
//    public ModelAndView loginConfirm(@AuthenticationPrincipal UserDetails principal) {
//        ModelAndView mav = new ModelAndView("login");
//        mav.addObject("login", principal);
//        return mav;
//    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("shops", shopService.findAllShopName());
        if(!model.containsAttribute("userRegisterBindingModel")){
            model.addAttribute("userRegisterBindingModel" , new UserRegisterBindingModel());
            model.addAttribute("userExistError" , false);
            model.addAttribute("equalsPassword" , true);
        }
        return "register";
    }

    @PostMapping("/register")
    public String confirmRegister(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        boolean equalsPassword = userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword());
        if (bindingResult.hasErrors() || !equalsPassword) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            redirectAttributes.addFlashAttribute("equalsPassword", false);
            return "redirect:register";
        }

        boolean emailExist = userService.emailExist(userRegisterBindingModel.getEmail());

        if (emailExist) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("userExistError", true);

            return "redirect:register";
        }

        UserRegistrationServiceModel userRegistrationServiceModel =
                modelMapper.map(userRegisterBindingModel, UserRegistrationServiceModel.class);

        userService.registrationUser(userRegistrationServiceModel);

        return "redirect:login";
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @PostMapping("/profile")
    public String confirmProfile() {
        //ToDo
        return "redirect:profile";
    }

    @GetMapping("/set-seller-store")
    public String setSellerStore() {
        return "set-seller-store";
    }

    @PostMapping("/set-seller-store")
    public String setSellerStoreConfirm() {
        //ToDo
        return "redirect:set-seller-store";
    }

    @GetMapping("/remove-sale")
    public String removeSale() {
        return "remove-sale";
    }

    @PostMapping("remove-sale")
    public String removeSaleConfirm() {
        //ToDo
        return "redirect:remove-sale";
    }

    @GetMapping("/remove-seller")
    public String removeSeller() {
        return "remove-seller";
    }

    @PostMapping("/remove-seller")
    public String removeSellerConfirm() {
        //ToDo
        return "redirect:remove-seller";
    }
}
