package com.bparent.dojo.unitTest.service;

import com.bparent.dojo.unitTest.bean.Contact;
import com.bparent.dojo.unitTest.bean.IIhmBean;
import com.bparent.dojo.unitTest.repository.ContactRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ContactServiceTest {

    @InjectMocks
    private ContactService contactService;

    @Mock
    private ContactRepository contactRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getContactById_shouldCallRepositoryAndMaDtop() {
        when(contactRepository.findById(1)).thenReturn(Optional.of(
                Contact.builder()
                        .id(1)
                        .nom("Nom")
                        .prenom("Prenom")
                .build()));

        IIhmBean ihmBean = contactService.getContactById(1);

        assertEquals(Integer.valueOf(1), ihmBean.getId());
        assertEquals("Nom Prenom", ihmBean.getNomPrenom());
        assertEquals(LocalDate.now().atStartOfDay(), ihmBean.getHorodateur().toLocalDate().atStartOfDay());
    }

}