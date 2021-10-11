package com.example.sellers.web;

import com.example.sellers.model.binding.UserChangeStoreBindingModel;
import com.example.sellers.model.binding.UserRegisterBindingModel;
import com.example.sellers.model.binding.UserRemoveBindingModel;
import com.example.sellers.model.binding.UserRoleBindingModel;
import com.example.sellers.model.entity.enums.UserRoleEnum;
import com.example.sellers.model.service.UserRegistrationServiceModel;
import com.example.sellers.service.StoreService;
import com.example.sellers.service.UserRoleService;
import com.example.sellers.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Set;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final StoreService storeService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, StoreService storeService, ModelMapper modelMapper) {
        this.userService = userService;
        this.storeService = storeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login-error")
    public ModelAndView failedLogin(@ModelAttribute String email) {
        //ToDo да проверя как може да ми запазва email при грешно въведени данни
        //ToDo да проверя как може да проверя дали е одобрен или не User от Admin и да хвърля грешка
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
        model.addAttribute("stores", storeService.findAllStoresNames());
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
        //ToDo
        return "profile";
    }

    @PostMapping("/profile")
    public String confirmProfile() {
        //ToDo
        return "redirect:profile";
    }

    @GetMapping("/change-user-store")
    public String changeSellerStore(Model model) {
        model.addAttribute("stores", storeService.findAllStoresNames());
        model.addAttribute("users" , userService.findAllUsers());
        if (!model.containsAttribute("userChangeStoreBindingModel")){
            model.addAttribute("userChangeStoreBindingModel" , new UserChangeStoreBindingModel());
        }
        return "change-user-store";
    }

    @PostMapping("/change-user-store")
    public String changeSellerStoreConfirm(@Valid UserChangeStoreBindingModel userChangeStoreBindingModel,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            return "redirect:change-user-stores";
        }
        userService.changeUserStore(userChangeStoreBindingModel.getFullName(), userChangeStoreBindingModel.getStore());

        //ToDo да изписва съобшение за успешно сменен магазин
        return "redirect:all-users";
    }

    @GetMapping("/add-role")
    public String addRole(Model model){
        model.addAttribute("users" , userService.findAllUsers());
        model.addAttribute("roles" , Arrays.stream(UserRoleEnum.values()).toList());
        if (!model.containsAttribute("userRoleBindingModel")){
            model.addAttribute("userRoleBindingModel" , new UserRoleBindingModel());
        }

        return "add-role";
    }

    @PostMapping("/add-role")
    public String addRoleConfirm(@Valid UserRoleBindingModel userRoleBindingModel,
                                 BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "redirect:add-role";
        }
        userService.addUserRole(userRoleBindingModel.getRole() , userRoleBindingModel.getFullName());

        //ToDo да проверя как да изкарам съобшение за успешно добавена роля
        return "redirect:all-users";
    }

    @GetMapping("/remove-sale")
    public String removeSale() {
        //ToDo
        return "remove-sale";
    }

    @PostMapping("remove-sale")
    public String removeSaleConfirm() {
        //ToDo
        return "redirect:remove-sale";
    }

    @GetMapping("/remove-user")
    public String removeSeller(Model model) {
        Set<String> users = userService.findAllUsers();
        model.addAttribute("users" , users);
        if(!model.containsAttribute("userRemoveBindingModel")){
            model.addAttribute("userRemoveBindingModel" , new UserRemoveBindingModel());
        }
        return "remove-user";
    }

    @PostMapping("/remove-user")
    public String removeSellerConfirm(@Valid UserRemoveBindingModel userRemoveBindingModel,
                                      BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            return "redirect:remove-user";
        }

        userService.removeUser(userRemoveBindingModel.getFullName());
        //ToDo съобщение за успешно премахнат User
        return "redirect:all-users";
    }
}
