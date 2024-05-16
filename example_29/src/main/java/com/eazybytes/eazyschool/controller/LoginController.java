package com.eazybytes.eazyschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login(@RequestParam(name = "error", required = false) String error,
                        @RequestParam(name = "logout", required = false) String logout,
                        Model model) {
        String errorMessage = null;
        if(error != null) {
            errorMessage = "Invalid username or password!";
        }
        if(logout != null) {
            errorMessage = "You have logged out successfully!";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }
}
