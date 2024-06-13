package com.eazybytes.eazyschool.rest;

import com.eazybytes.eazyschool.constants.EazySchoolConstants;
import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.model.Response;
import com.eazybytes.eazyschool.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "/api/contact", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class ContactRestController {
    private ContactService contactService;

    @Autowired
    public ContactRestController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping(value = "/getMessagesByStatus")
//    @ResponseBody
    public List<Contact> getMessagesByStatus(@RequestParam(name = "Status") String status) {
        return contactService.findMsgsByStatus(status);
    }

    @GetMapping("/getAllMessagesByStatus/{pageNum}/{sortField}/{sortDir}")
//    @ResponseBody
    public List<Contact> getAllMessagesByStatus(@RequestBody Contact contact) {
        if(contact != null && contact.getStatus() != null) {
            return contactService.findMsgsByStatus(contact.getStatus());
        }else {
            return List.of();
        }
    }

    @PostMapping("/saveMsg")
    public ResponseEntity<Response> saveMsg(@RequestHeader("invocationFrom") String invocationFrom,
                                            @Valid @RequestBody Contact contact) {

        log.info("Header invocationFrom = " + invocationFrom);
        contactService.saveMessageDetails(contact);
        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusMessage("Message Saved Successfully");
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .header("isMsgSaved","true")
                .body(response);
    }

    @DeleteMapping("/deleteMsg")
    public ResponseEntity<Response> deleteMsg(RequestEntity<Contact> requestEntity) {
        HttpHeaders headers = requestEntity.getHeaders();
        headers.forEach((key,value)-> System.out.println(key + " = " + value));
        Contact contact = requestEntity.getBody();
        assert contact != null;
        contactService.deleteMsgById(contact.getContactId());
        Response response = new Response("200","Message successfully deleted");
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("isMsgDeleted","true")
                .body(response);
    }

    @PatchMapping("/closeMsg")
    public ResponseEntity<Response> closeMsg(@RequestBody Contact contactRequest) {
        Response response = new Response();
        Optional<Contact> contact = contactService.findContactById(contactRequest.getContactId());
        if(contact.isPresent()) {
            contact.get().setStatus(EazySchoolConstants.CLOSE);
            contactService.updateMsgStatus(contact.get().getContactId());
        }else {
            response.setStatusCode("400");
            response.setStatusMessage("Invalid contact id");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }
        response.setStatusCode("200");
        response.setStatusMessage("Message Successfully closed");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
