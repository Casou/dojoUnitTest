package com.bparent.dojo.unitTest.controller;

import com.bparent.dojo.unitTest.bean.Contact;
import com.bparent.dojo.unitTest.bean.IIhmBean;
import com.bparent.dojo.unitTest.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getContacts_shouldReturnListOfContacts() throws Exception {
        // TODO AJOUTER LES MOCKS

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
    public void getContactsSupAge_shouldReturnListOfContacts() throws Exception {
        // TODO AJOUTER LES MOCKS

        String resultString = this.mockMvc.perform(get("/contacts/age/25"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<Contact> contacts = JsonUtil.toObjectList(resultString, Contact[].class);
        assertEquals(2, contacts.size());
        assertEquals("nom 1", contacts.get(0).getNom());
        assertEquals("nom 2", contacts.get(1).getNom());
    }

    @Test
    public void getContactById_shouldReturnABeanWithNameAndDate() throws Exception {
        // TODO AJOUTER LES MOCKS

        String resultString = this.mockMvc.perform(get("/contacts/1"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        IIhmBean iIhmBean = JsonUtil.toObject(resultString, IIhmBean.class);
        assertEquals(Integer.valueOf(1), iIhmBean.getId());
        assertEquals("Nom Prenom", iIhmBean.getNomPrenom());
        assertEquals(LocalDate.now().atStartOfDay(), iIhmBean.getHorodateur().toLocalDate().atStartOfDay());
    }

    @Test
    public void postContact_shouldReturnOkStatus() throws Exception {
        // TODO AJOUTER LES MOCKS

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
        // TODO AJOUTER LES MOCKS

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
        // TODO AJOUTER LES MOCKS

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
        // TODO AJOUTER LES MOCKS

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