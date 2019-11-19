package com.training.salon.controller;

import com.training.salon.entity.User;
import com.training.salon.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

import static com.training.salon.controller.ITextConstant.USER_EXISTS;

@Controller
public class RegistrationController {

    private final UserService userService;
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration() {
        System.out.println("reg");
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (userService.loadUserByUsername(user.getUsername()) != null) {
            model.addAttribute("usernameError", USER_EXISTS);
            return "registration";
        }
        userService.saveNewUser(user);
        return "redirect:/login";
    }
}
