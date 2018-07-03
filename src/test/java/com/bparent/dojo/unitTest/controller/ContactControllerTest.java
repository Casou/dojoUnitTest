package com.bparent.dojo.unitTest.controller;

import com.bparent.dojo.unitTest.bean.Contact;
import com.bparent.dojo.unitTest.repository.ContactRepository;
import com.bparent.dojo.unitTest.service.ContactService;
import com.bparent.dojo.unitTest.util.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ContactControllerTest {

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private ContactService contactService;

    @InjectMocks
    private ContactController contactController;

    private MockMvc mockMvc;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(contactController).build();
    }

    @Test
    public void getContacts_shouldReturnListOfContacts() throws Exception {
        when(contactRepository.findAll()).thenReturn(Arrays.asList(
                Contact.builder().nom("nom 1").build(),
                Contact.builder().nom("nom 2").build(),
                Contact.builder().nom("nom 3").build()
        ));

        String resultString = this.mockMvc.perform(get("/contacts"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<Contact> contacts = JsonUtil.toObjectList(resultString, Contact[].class);
        assertEquals(3, contacts.size());
        assertEquals("nom 1", contacts.get(0).getNom());
        assertEquals("nom 2", contacts.get(1).getNom());
        assertEquals("nom 3", contacts.get(2).getNom());
    }

    @Test
    public void getContactsBetween25And35_shouldReturnListOfContacts() throws Exception {
        when(contactService.findAllBetween25And35()).thenReturn(
                Arrays.asList(
                        Contact.builder().nom("nom 1").build(),
                        Contact.builder().nom("nom 2").build()
                )
        );

        String resultString = this.mockMvc.perform(get("/contacts/filtered"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<Contact> contacts = JsonUtil.toObjectList(resultString, Contact[].class);
        assertEquals(2, contacts.size());
        assertEquals("nom 1", contacts.get(0).getNom());
        assertEquals("nom 2", contacts.get(1).getNom());
    }

    @Test
    public void postContact_shouldReturnOkStatus() throws Exception {
        String jsonContact = JsonUtil.toJson(Contact.builder()
                .nom("nom")
                .prenom("prenom")
                .email("email@email.com")
                .age(18)
                .build());

        this.mockMvc.perform(
                post("/contacts")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonContact)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("ok"));
    }

    @Test
    public void postContact_shouldRejectBecauseEmailIsMalformed() throws Exception {
        String jsonContact = JsonUtil.toJson(Contact.builder()
                .nom("nom")
                .prenom("prenom")
                .email("WRONG MAIL")
                .age(18)
                .build());

        this.mockMvc.perform(
                post("/contacts")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonContact)
                )
                .andExpect(status().isBadRequest());
    }


    @Test
    public void postContact_shouldRejectIfBothNameAndFirstNameAreEmpty() throws Exception {
        String jsonContact = JsonUtil.toJson(Contact.builder()
                .nom(null)
                .prenom(null)
                .email("email@email.com")
                .age(18)
                .build());

        this.mockMvc.perform(
                post("/contacts")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonContact)
                )
                .andExpect(status().isBadRequest());
    }


    @Test
    public void postContact_shouldReturnExceptionStatusIfSomethingGoesWrong() throws Exception {
        // http://www.baeldung.com/spring-mvc-custom-validator

        String jsonContact = JsonUtil.toJson(Contact.builder()
                .nom("nom")
                .prenom("prenom")
                .email("email@email.com")
                .age(18)
                .build());

        // Mock something that goes wrong

        this.mockMvc.perform(
                post("/contacts")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContact)
        )
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Exception !!!"));
    }


}