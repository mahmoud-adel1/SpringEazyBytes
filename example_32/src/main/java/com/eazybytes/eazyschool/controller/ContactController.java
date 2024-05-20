package com.eazybytes.eazyschool.controller;

import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class ContactController {
    private ContactService contactService;


    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping("/contact")
    public String displayContactPage(Model model) {
        Contact contact = new Contact();
        model.addAttribute("contact",contact);
        return "contact";
    }

//    @RequestMapping(value = "/saveMsg",method = RequestMethod.POST)
//    public String saveMessage(@RequestParam String name, @RequestParam String mobileNum,
//                                    @RequestParam String email, @RequestParam String subject,
//                                    @RequestParam String message) {
//
//        log.info("Name: " + name);
//        log.info("Mobile Number: " + mobileNum);
//        log.info("Email: " + email);
//        log.info("Subject: " + subject);
//        log.info("Message: " + message);
//
////        return new ModelAndView("redirect:/contact");
//        return "redirect:/contact";
//    }

    @PostMapping(value = "/saveMsg")
    public ModelAndView saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors) {
        if(errors.hasErrors()) {
            log.error("Contact form validation failed due to " + errors);
            return new ModelAndView("contact");
        }
        contactService.saveMessageDetails(contact);
        return new ModelAndView("redirect:/contact");
    }

    @RequestMapping("/displayMessages")
    public String displayMessages(Model model) {
        List<Contact> contactMsgs = contactService.findMsgsWithOpenStatus();
        model.addAttribute("messages",contactMsgs);
        return "messages";
    }

    @RequestMapping("/closeMsg")
    public String closeMsg(@RequestParam int id, Authentication authentication) {
        contactService.updateMsgStatus(id, authentication.getName());
        return "redirect:/displayMessages";
    }
}
