package com.training.salon.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UnknownUrlController implements ErrorController {

    @GetMapping("/404")
    public String getUnknownPage() {
        return "404";
    }

    @GetMapping("/error")
    public String redirectNonExistentUrlsToHome(){
       return "redirect:/404";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}