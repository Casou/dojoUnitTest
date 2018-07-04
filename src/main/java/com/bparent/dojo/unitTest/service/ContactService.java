package com.bparent.dojo.unitTest.service;

import com.bparent.dojo.unitTest.bean.Contact;
import com.bparent.dojo.unitTest.bean.IIhmBean;
import com.bparent.dojo.unitTest.bean.IhmBean;
import com.bparent.dojo.unitTest.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.MissingResourceException;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public IIhmBean getContactById(Integer id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Contact not found for id " + id));

        String nomPrenom = contact.getAge() > 30 ?
                contact.getNom() + " " + contact.getPrenom() :
                contact.getPrenom() + " " + contact.getNom();

        return new IhmBean(contact.getId(), nomPrenom);
    }

}
