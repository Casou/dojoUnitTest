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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
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
    public void getContactById_shouldCallRepository() {
        when(contactRepository.findById(1)).thenReturn(Optional.of(Contact.builder().age(25).build()));

        contactService.getContactById(1);
        verify(contactRepository).findById(eq(1));
    }

    @Test
    public void getContactById_shouldMapDtoWithNomPrenom() {
        when(contactRepository.findById(1)).thenReturn(Optional.of(
                Contact.builder()
                        .id(1)
                        .nom("Nom")
                        .prenom("Prenom")
                        .age(25)
                .build()));

        IIhmBean ihmBean = contactService.getContactById(1);

        assertEquals(Integer.valueOf(1), ihmBean.getId());
        assertEquals("Prenom Nom", ihmBean.getNomPrenom());
        assertEquals(LocalDate.now().atStartOfDay(), ihmBean.getHorodateur().toLocalDate().atStartOfDay());
    }

    @Test
    public void getContactById_shouldMapDtoWithPrenomNom() {
        when(contactRepository.findById(1)).thenReturn(Optional.of(
                Contact.builder()
                        .id(1)
                        .nom("Nom")
                        .prenom("Prenom")
                        .age(35)
                .build()));

        IIhmBean ihmBean = contactService.getContactById(1);

        assertEquals(Integer.valueOf(1), ihmBean.getId());
        assertEquals("Nom Prenom", ihmBean.getNomPrenom());
        assertEquals(LocalDate.now().atStartOfDay(), ihmBean.getHorodateur().toLocalDate().atStartOfDay());
    }

}