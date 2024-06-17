package com.eazybytes.eazyschool.controller;

import com.eazybytes.eazyschool.model.Person;
import com.eazybytes.eazyschool.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

    @Value("${eazyschool.pageSize}")
    private int pageSize;

    @Value(value = "${eazyschool.contact.successMsg}")
    private String message;

    @Autowired
    private Environment environment;

    private static final Logger log = LoggerFactory.getLogger(DashboardController.class);
    private PersonRepository personRepository;

    @Autowired
    public DashboardController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @RequestMapping("/dashboard")
    public String dashboard(Model model, Authentication auth, HttpSession session) {
        Person person = personRepository.getPersonByEmail(auth.getName());
        if(person.getEazyClass() != null && person.getEazyClass().getName() != null) {
            model.addAttribute("class",person.getEazyClass().getName());
        }
        session.setAttribute("person",person);
        model.addAttribute("username", person.getName());
        model.addAttribute("roles", auth.getAuthorities().toString());
//        throw new RuntimeException("It's been bad today");
        logMessages();
        return "dashboard";
    }

    private void logMessages() {
        log.error("Error message from the dashboard page");
        log.warn("Warn message from the dashboard page");
        log.info("Info message from the dashboard page");
        log.debug("Debug message from the dashboard page");
        log.trace("Trace message from the dashboard page");
        log.error("defaultPageSize with @Value annotation is " + pageSize);
        log.error("successMsg with @Value annotation is " + message);
        log.error("defaultPageSize with Environment annotation is " + environment.getProperty("eazyschool.pageSize"));
        log.error("successMsg with Environment annotation is " + environment.getProperty("eazyschool.contact.successMsg"));
        log.error("Java Home Environment Variable using Environment is" + environment.getProperty("Java_Home"));


    }
}
