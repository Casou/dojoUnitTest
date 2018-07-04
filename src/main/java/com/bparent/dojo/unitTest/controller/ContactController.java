package com.bparent.dojo.unitTest.controller;

import com.bparent.dojo.unitTest.bean.Contact;
import com.bparent.dojo.unitTest.bean.IIhmBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactController {

    @GetMapping("/contacts")
    public List<Contact> getContacts() {
        return null;
    }

    @GetMapping("/contacts/age/{age}")
    public List<Contact> getContactsSupAge(@PathVariable Integer age) {
        return null;
    }

    @GetMapping("/contacts/{id]")
    public IIhmBean getContactById(@PathVariable Integer id) {
        return null;
    }

    @PostMapping("/contacts")
    @ResponseBody
    public ResponseEntity<String> postContacts(@RequestBody Contact contact) {

        return ResponseEntity.ok("ok");
    }

}
