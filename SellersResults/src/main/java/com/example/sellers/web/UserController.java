package com.example.sellers.web;

import com.example.sellers.model.binding.*;
import com.example.sellers.model.entity.enums.UserRoleEnum;
import com.example.sellers.model.service.ProfileUpdateServiceModel;
import com.example.sellers.model.service.UserRegistrationServiceModel;
import com.example.sellers.model.view.ProfileViewModel;
import com.example.sellers.service.PictureService;
import com.example.sellers.service.StoreService;
import com.example.sellers.service.UserService;
import com.example.sellers.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final StoreService storeService;
    private final PictureService pictureService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, StoreService storeService, PictureService pictureService,
                          ModelMapper modelMapper) {
        this.userService = userService;
        this.storeService = storeService;
        this.pictureService = pictureService;
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

        mvc.addObject("bad_credentials", true);
        mvc.addObject("email", email);

        mvc.setViewName("login");

        return mvc;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("stores", storeService.findAllStoresNames());
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
            model.addAttribute("userExistError", false);
            model.addAttribute("equalsPassword", true);
        }
        return "register";
    }

    @PostMapping("/register")
    public String confirmRegister(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        boolean emailExist = userService.emailExist(userRegisterBindingModel.getEmail());
        boolean equalsPassword = userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword());

        if (bindingResult.hasErrors() || !equalsPassword || emailExist) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            redirectAttributes.addFlashAttribute("equalsPassword", equalsPassword);
            redirectAttributes.addFlashAttribute("userExistError", emailExist);
            return "redirect:register";
        }

        UserRegistrationServiceModel userRegistrationServiceModel =
                modelMapper.map(userRegisterBindingModel, UserRegistrationServiceModel.class);

        userService.registrationUser(userRegistrationServiceModel);

        return "redirect:login";
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        String email = principal.getName();
        model.addAttribute("isAuthorize", true);
        model.addAttribute("user", userService.findUserViewModelByEmail(email));
        return "profile";
    }

    @GetMapping("/profile/{id}/details")
    public String showProfileDetails(@PathVariable Long id, Model model, Principal principal) {
        boolean authorize = userService.isAuthorize(principal.getName(), id);
        model.addAttribute("isAuthorize", authorize);
        model.addAttribute("user", this.userService.findByIdProfileViewModel(id));
        return "profile";
    }

    @PreAuthorize("@userServiceImpl.isAuthorize(#principal.name , #id)")
    @GetMapping("/profile/{id}/edit")
    public String editUser(@PathVariable Long id, Model model, Principal principal) {
        ProfileViewModel view = this.userService.findByIdProfileViewModel(id);
        ProfileUpdateBindingModel map = modelMapper.map(view, ProfileUpdateBindingModel.class);
        model.addAttribute("user", map);
        return "edit-user";
    }

    @PreAuthorize("@userServiceImpl.isAuthorize(#principal.name , #id)")
    @PatchMapping("/profile/{id}/edit")
    public String editUser(@PathVariable Long id, @Valid ProfileUpdateBindingModel profileUpdateBindingModel,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes,
                           Principal principal) throws IOException {

        System.out.println();
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("profileUpdateBindingModel", profileUpdateBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.profileUpdateBindingModel",
                            bindingResult);

            return "redirect:/users/profile/" + id + "/edit";
        }
        ProfileUpdateServiceModel model = modelMapper.map(profileUpdateBindingModel, ProfileUpdateServiceModel.class);
        if (profileUpdateBindingModel.getPicture() != null) {
            model.setPicture(pictureService.createPicture(profileUpdateBindingModel.getPicture()));
        }
        userService.editProfile(model);

        return "redirect:/users/profile/" + id + "/details";
    }

    @GetMapping("/profile/all")
    public String allUsers(Model model) {
        model.addAttribute("users", userService.findAllUsersViewModel());
        return "all-users";
    }

    @GetMapping("/change-employee-store")
    public String changeSellerStore(Model model) {
        model.addAttribute("stores", storeService.findAllStoresNames());
        model.addAttribute("employees", userService.findAllUsersFullName());
        if (!model.containsAttribute("userChangeStoreBindingModel")) {
            model.addAttribute("userChangeStoreBindingModel", new UserChangeStoreBindingModel());
        }
        return "change-employee-store";
    }

    @PostMapping("/change-employee-store")
    public String changeSellerStoreConfirm(@Valid UserChangeStoreBindingModel userChangeStoreBindingModel,
                                           BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userChangeStoreBindingModel", userChangeStoreBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.userChangeStoreBindingModel",
                            bindingResult);

            return "redirect:change-employee-store";
        }

        userService.changeUserStore(userChangeStoreBindingModel.getFullName(), userChangeStoreBindingModel.getStore());

        //ToDo да изписва съобшение за успешно сменен магазин
        return "redirect:/users/profile/all";
    }

    @GetMapping("/add-role")
    public String addRole(Model model) {
        model.addAttribute("employees", userService.findAllUsersFullName());
        model.addAttribute("roles", Arrays.stream(UserRoleEnum.values()).toList());
        if (!model.containsAttribute("userRoleBindingModel")) {
            model.addAttribute("userRoleBindingModel", new UserRoleBindingModel());
        }

        return "add-role";
    }

    @PostMapping("/add-role")
    public String addRoleConfirm(@Valid UserRoleBindingModel userRoleBindingModel,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        //ToDo да направя проверка за валидни User и Role и дали User-a ги има вече
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRoleBindingModel", userRoleBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRoleBindingModel",
                            bindingResult);

            return "redirect:add-role";
        }

        userService.addUserRole(userRoleBindingModel.getRole(), userRoleBindingModel.getFullName());

        //ToDo да проверя как да изкарам съобшение за успешно добавена роля
        return "redirect:/users/profile/all";
    }

    @GetMapping("/remove-user")
    public String removeSeller(Model model) {
        model.addAttribute("employees", userService.findAllUsersFullName());
        if (!model.containsAttribute("userFullNameBindingModel")) {
            model.addAttribute("userFullNameBindingModel", new UserFullNameBindingModel());
        }

        return "remove-user";
    }

    @PostMapping("/remove-user")
    public String removeSellerConfirm(@Valid UserFullNameBindingModel userFullNameBindingModel,
                                      BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userFullNameBindingModel", userFullNameBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.userFullNameBindingModel",
                            bindingResult);

            return "redirect:remove-user";
        }

        userService.removeUser(userFullNameBindingModel.getFullName());
        //ToDo съобщение за успешно премахнат User
        return "redirect:/users/profile/all";
    }

    @GetMapping("/approval")
    public String approvalUser(Model model) {

        model.addAttribute("employees", userService.findAllUsersFullNameWithoutApproval());

        if (!model.containsAttribute("userFullNameBindingModel")) {
            model.addAttribute("userFullNameBindingModel", new UserFullNameBindingModel());
        }

        return "user-for-approval";
    }

    @PostMapping("/approval")
    public String approvalUserConfirm(@Valid UserFullNameBindingModel userFullNameBindingModel,
                                      BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userFullNameBindingModel", userFullNameBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.userFullNameBindingModel",
                            bindingResult);

            return "redirect:approval";
        }

        //ToDo да изкарва съобщение за успешна операция
        userService.approvedUser(userFullNameBindingModel.getFullName());

        return "redirect:approval";
    }

    //Exception
    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView handleObException(ObjectNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("error-404");
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.addObject("Id", ex.getId());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}
