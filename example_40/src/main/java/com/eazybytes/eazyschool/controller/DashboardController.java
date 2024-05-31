package com.eazybytes.eazyschool.controller;

import com.eazybytes.eazyschool.model.Person;
import com.eazybytes.eazyschool.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

    private PersonRepository personRepository;

    @Autowired
    public DashboardController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @RequestMapping("/dashboard")
    public String dashboard(Model model, Authentication auth, HttpSession session) {
        Person person = personRepository.getPersonByEmail(auth.getName());
        session.setAttribute("person",person);
        model.addAttribute("username", person.getName());
        model.addAttribute("roles", auth.getAuthorities().toString());
//        throw new RuntimeException("It's been bad today");
        return "dashboard";
    }
}
