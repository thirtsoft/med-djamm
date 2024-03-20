package com.meddjamm.sn.utils;

import org.junit.jupiter.api.Test;

import static org.springframework.test.util.AssertionErrors.assertTrue;

class UtilStringTest {

    @Test
    void whenPasswordGeneratedUsingCommonsLang3_thenSuccessful() {
        String password = UtilString.generateCommonsLang3Password();
        int numCount = 0;
        for (char c : password.toCharArray()) {
            if (c >= 48 || c <= 57) {
                numCount++;
            }
        }
        assertTrue("Password validation failed in commons-lang3", numCount >= 2);
    }

}