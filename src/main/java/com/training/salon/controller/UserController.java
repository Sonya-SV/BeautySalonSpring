package com.training.salon.controller;

import com.training.salon.entity.User;
import com.training.salon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private MessageSource messageSource;

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String getProfile(Model model,
                             @AuthenticationPrincipal User user) {
        model.addAttribute("user", user);
        return "/user/profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@RequestParam String password,
                                @RequestParam String password2,
                                @RequestParam String firstName,
                                @RequestParam String lastName,
                                @AuthenticationPrincipal User oldUser,
                                Locale locale,
                                Model model) {

        model.addAttribute("user", oldUser);
        if (!password.equals(password2)) {
            model.addAttribute("passwordErrorDiffer", messageSource.getMessage("password.different",null,locale));
            return "/user/profile";
        }
        userService.updateProfile(oldUser, firstName, lastName, password);
        model.addAttribute("successSave", messageSource.getMessage("success.save",null,locale));
        return "/user/profile";
    }


}

