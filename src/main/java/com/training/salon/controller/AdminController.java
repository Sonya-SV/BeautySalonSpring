package com.training.salon.controller;

import com.training.salon.entity.Role;
import com.training.salon.entity.User;
import com.training.salon.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller

@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userlist")
    public String userList(Model model) {

        model.addAttribute("users", userService.findAllUsers());
        return "/admin/userlist";
    }

    @GetMapping("/edituser/{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "/admin/useredit";
    }

    @PostMapping("/save")
    public String userUpdate(
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user) {

        userService.updateUserByAdmin(user, form);
        return "redirect:/admin/userlist";
    }
}
