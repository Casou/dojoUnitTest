package com.bparent.dojo.unitTest.controller;

import com.bparent.dojo.unitTest.bean.Contact;
import com.bparent.dojo.unitTest.repository.ContactRepository;
import com.bparent.dojo.unitTest.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactService contactService;


    @GetMapping("/contacts")
    public List<Contact> getContacts() {
        return (List<Contact>) contactRepository.findAll();
    }

    @GetMapping("/contacts/filtered")
    public List<Contact> getContactsBetween25And35() {
        return contactService.findAllBetween25And35();
    }

    @PostMapping("/contacts")
    @ResponseBody
    public ResponseEntity<String> postContacts(@Valid @RequestBody Contact contact) {

        return ResponseEntity.ok("ok");
    }

}
