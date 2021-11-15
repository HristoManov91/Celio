package com.example.sellers.web;

import com.example.sellers.model.binding.*;
import com.example.sellers.model.entity.enums.UserRoleEnum;
import com.example.sellers.model.service.ProfileUpdateServiceModel;
import com.example.sellers.model.service.UserRegistrationServiceModel;
import com.example.sellers.model.view.ProfileViewModel;
import com.example.sellers.service.CloudinaryService;
import com.example.sellers.service.PictureService;
import com.example.sellers.service.StoreService;
import com.example.sellers.service.UserService;
import com.example.sellers.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;

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
    public String profile(Principal principal, Model model) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        model.addAttribute("dateFormat", dateFormat);
        String email = principal.getName();
        model.addAttribute("user", userService.findUserViewModelByEmail(email));
        return "profile";
    }

    @GetMapping("/profile/{id}/details")
    public String showProfileDetails(@PathVariable Long id, Model model) {
        model.addAttribute("user", this.userService.findByIdProfileViewModel(id));
        return "profile";
    }

    @GetMapping("/profile/{id}/edit")
    public String editUser(@PathVariable Long id, Model model) {
        ProfileViewModel view = this.userService.findByIdProfileViewModel(id);
        ProfileUpdateBindingModel map = modelMapper.map(view, ProfileUpdateBindingModel.class);
        model.addAttribute("user", map);
        return "edit-user";
    }

    @PatchMapping("/profile/{id}/edit")
    public String editUser(@PathVariable Long id, @Valid ProfileUpdateBindingModel profileUpdateBindingModel,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {

        System.out.println();
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("profileUpdateBindingModel", profileUpdateBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.profileUpdateBindingModel",
                            bindingResult);

            return "redirect:/users/profile/" + id + "/edit/errors";
        }
        ProfileUpdateServiceModel model = modelMapper.map(profileUpdateBindingModel, ProfileUpdateServiceModel.class);
        model.setPicture(pictureService.createPicture(profileUpdateBindingModel.getPicture()));
        userService.editProfile(model);

        return "redirect:/users/profile";
    }

    @GetMapping("/profile/all")
    public String allUsers(Model model) {
        model.addAttribute("users", userService.findAllUsersViewModel());
        return "all-users";
    }

    @GetMapping("/change-user-store")
    public String changeSellerStore(Model model) {
        model.addAttribute("stores", storeService.findAllStoresNames());
        model.addAttribute("users", userService.findAllUsersFullName());
        if (!model.containsAttribute("userChangeStoreBindingModel")) {
            model.addAttribute("userChangeStoreBindingModel", new UserChangeStoreBindingModel());
        }
        return "change-user-store";
    }

    @PostMapping("/change-user-store")
    public String changeSellerStoreConfirm(@Valid UserChangeStoreBindingModel userChangeStoreBindingModel,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:change-user-stores";
        }
        userService.changeUserStore(userChangeStoreBindingModel.getFullName(), userChangeStoreBindingModel.getStore());

        //ToDo да изписва съобшение за успешно сменен магазин
        return "redirect:/users/profile/all";
    }

    @GetMapping("/add-role")
    public String addRole(Model model) {
        model.addAttribute("users", userService.findAllUsersFullName());
        model.addAttribute("roles", Arrays.stream(UserRoleEnum.values()).toList());
        if (!model.containsAttribute("userRoleBindingModel")) {
            model.addAttribute("userRoleBindingModel", new UserRoleBindingModel());
        }

        return "add-role";
    }

    @PostMapping("/add-role")
    public String addRoleConfirm(@Valid UserRoleBindingModel userRoleBindingModel,
                                 BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            return "redirect:add-role";
        }
        userService.addUserRole(userRoleBindingModel.getRole(), userRoleBindingModel.getFullName());

        //ToDo да проверя как да изкарам съобшение за успешно добавена роля
        return "redirect:/users/profile/all";
    }

    @GetMapping("/remove-user")
    public String removeSeller(Model model) {
        Set<String> users = userService.findAllUsersFullName();
        model.addAttribute("users", users);
        if (!model.containsAttribute("userRemoveBindingModel")) {
            model.addAttribute("userRemoveBindingModel", new UserRemoveBindingModel());
        }
        return "remove-user";
    }

    @PostMapping("/remove-user")
    public String removeSellerConfirm(@Valid UserRemoveBindingModel userRemoveBindingModel,
                                      BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:remove-user";
        }

        userService.removeUser(userRemoveBindingModel.getFullName());
        //ToDo съобщение за успешно премахнат User
        return "redirect:/users/profile/all";
    }

    @GetMapping("/approval")
    public String approvalUser(Model model) {
        Set<String> users = userService.findAllUsersFullNameWithoutApproval();
        model.addAttribute("users", users);
        if (!model.containsAttribute("userBindingModel")) {
            model.addAttribute("userBindingModel", new UserRemoveBindingModel());
        }
        return "user-for-approval";
    }

    @PostMapping("/approval")
    public String approvalUserConfirm(@Valid UserRemoveBindingModel userBindingModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            //ToDo да оправя валидацията като не се избере да е Null а не modela
            return "redirect:approval";
        }
        userService.approvedUser(userBindingModel.getFullName());

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
