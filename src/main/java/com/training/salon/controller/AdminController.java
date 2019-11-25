package com.training.salon.controller;

import com.training.salon.entity.Role;
import com.training.salon.entity.User;
import com.training.salon.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.training.salon.controller.ITextConstant.DIFFERENT_PASSWORDS;
import static com.training.salon.controller.ITextConstant.SAVED_SUCCESSFULLY;

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
    public String userUpdate(@RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam String password,
                             @RequestParam String password2,
                             @RequestParam("userId") User user,
                             Model model) {

        if (!password.equals(password2)) {
            model.addAttribute("passwordErrorDiffer", DIFFERENT_PASSWORDS);
            return "/user/profile";
        }
        userService.updateProfile(user, firstName, lastName, password);
        model.addAttribute("successSave", SAVED_SUCCESSFULLY);
        return "redirect:/admin/userlist";
    }
}
