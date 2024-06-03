package com.eazybytes.eazyschool.controller;

import com.eazybytes.eazyschool.model.Person;
import com.eazybytes.eazyschool.model.Profile;
import com.eazybytes.eazyschool.service.ProfileService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {

    private ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @RequestMapping("/displayProfile")
    public String displayProfile(Model model, HttpSession session,
                                 @RequestParam(name = "update", required = false) String update,
                                 @RequestParam(name = "failed", required = false) String failed) {
        Person person = (Person) session.getAttribute("person");
        Profile profile = profileService.createProfile(person);
        model.addAttribute("profile",profile);
        if(update != null) {
            model.addAttribute("update","Successful Update");
        }
        if(failed != null) {
            model.addAttribute("failed","Successful Update");
        }

        return "profile";
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@Valid @ModelAttribute Profile profile, Errors errors,HttpSession session) {
        if(errors.hasErrors()) {
            return "profile";
        }
        Person person = (Person) session.getAttribute("person");
        boolean check = profileService.updatePerson(profile,person);
        if(check) {
            session.setAttribute("person",person);
            return "redirect:/displayProfile?update=true";
        }
        return "redirect:/displayProfile?update=false";
    }
}

















