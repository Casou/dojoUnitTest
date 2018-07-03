package com.bparent.dojo.unitTest.repository;

import com.bparent.dojo.unitTest.bean.Contact;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@DataJpaTest
@RunWith(SpringJUnit4ClassRunner.class)
@Sql({"/schema.sql", "/data.sql"})
public class ContactRepositoryTest {

    @Autowired
    private ContactRepository contactRepository;

    @Test
    public void findById_shouldReturnOneRecord() {
        Contact contact = this.contactRepository.findById(1).orElse(null);
        assertNotNull(contact);
        assertEquals(Integer.valueOf(1), contact.getId());
        assertEquals("nom", contact.getNom());
        assertEquals("prenom", contact.getPrenom());
        assertEquals("nom.prenom@email.com", contact.getEmail());
        assertEquals(Integer.valueOf(25), contact.getAge());
    }

    @Test
    public void findByAll_shouldReturnAllRecords() {
        List<Contact> contacts = (List<Contact>) this.contactRepository.findAll();
        assertNotNull(contacts);
        assertEquals(4, contacts.size());
    }

}

