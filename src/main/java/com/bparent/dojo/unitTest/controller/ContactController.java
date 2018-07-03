package com.bparent.dojo.unitTest.controller;

import com.bparent.dojo.unitTest.bean.Contact;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactController {

    @GetMapping("/contacts")
    public List<Contact> getContacts() {
        return null;
    }

    @GetMapping("/contacts/filtered")
    public List<Contact> getContactsBetween25And35() {
        return null;
    }

    @PostMapping("/contacts")
    @ResponseBody
    public ResponseEntity<String> postContacts(@RequestBody Contact contact) {

        return ResponseEntity.ok("ok");
    }

}
