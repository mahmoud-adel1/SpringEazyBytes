package com.eazybytes.eazyschool.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {
    @RequestMapping("/dashboard")
    public String dashboard(Model model, Authentication auth) {
        model.addAttribute("username", auth.getName());
        model.addAttribute("roles", auth.getAuthorities().toString());
//        throw new RuntimeException("It's been bad today");
        return "dashboard";
    }
}
