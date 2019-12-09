package com.training.salon.controller;

import com.training.salon.entity.User;
import com.training.salon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Optional;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/userlist")
    public String userList(Model model) {

        model.addAttribute("users", userService.findAllUsers());
        return "/admin/userlist";
    }

    @GetMapping("/edituser/{user}")
    public String userEditForm(@PathVariable User user,
                               @RequestParam(value = "error", required = false) String error,
                               Locale locale,
                               Model model) {
        Optional.ofNullable(error)
                .ifPresent(x-> model.addAttribute("passwordErrorDiffer",
                        messageSource.getMessage("password.different",null, locale)));
        model.addAttribute("user", user);
        return "/admin/useredit";
    }

    @PostMapping("/save")
    public String userUpdate(@RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam String password,
                             @RequestParam String password2,
                             @RequestParam("userId") User user) {

        if (!password.equals(password2)) return "redirect:/admin/edituser/"+user.getId()+"?error";
        userService.updateProfile(user, firstName, lastName, password);
        return "redirect:/admin/userlist";
    }
}
