package com.bparent.dojo.unitTest.controller;

import com.bparent.dojo.unitTest.bean.Contact;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class ContactController {

    @GetMapping("/contacts")
    public List<Contact> getContacts() {
        return Arrays.asList(
                Contact.builder().nom("nom 1").prenom("prenom 1").email("email@email.com").age(18).build(),
                Contact.builder().nom("nom 2").prenom("prenom 2").email("email@email.com").age(19).build(),
                Contact.builder().nom("nom 3").prenom("prenom 3").email("email@email.com").age(20).build()
        );
    }

    @PostMapping("/contacts")
    @ResponseBody
    public ResponseEntity<String> postContacts(@RequestBody Contact contact) {

        return ResponseEntity.ok("ok");
    }

}
