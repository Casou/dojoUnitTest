package com.bparent.dojo.unitTest.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmailUtilTest {

    @Test
    public void isValidEmail_shouldReturnTrueIfValidEmail() {
        assertTrue(EmailUtil.isValidEmail("un.email@valid.ok"));
    }

    @Test
    public void isValidEmail_shouldReturnFalseIfNotValidEmail() {
        assertFalse(EmailUtil.isValidEmail("un.email.non.valide"));
    }

    @Test
    public void isValidEmail_shouldReturnFalseIfEmailIsNull() {
        assertFalse(EmailUtil.isValidEmail(null));
    }

}