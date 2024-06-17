package com.eazybytes.eazyschool.controller;

import com.eazybytes.eazyschool.model.Person;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/student")
@Controller
public class StudentController {
    @GetMapping("/displayCourses")
    public String displayCourses(Model model, HttpSession session) {
        Person person = (Person) session.getAttribute("person");
        model.addAttribute("person",person);
        return "courses_enrolled";
    }
}
