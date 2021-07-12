package com.rilixtech.materialfancybutton.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class TextUtilsTest {

    @Test
    public void testIsEmpty() {
        assertTrue(TextUtils.isEmpty(""));
        assertTrue(TextUtils.isEmpty(null));
        assertFalse(TextUtils.isEmpty("abc"));
    }
}