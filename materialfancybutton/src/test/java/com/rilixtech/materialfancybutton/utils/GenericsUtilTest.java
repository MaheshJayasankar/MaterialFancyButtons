package com.rilixtech.materialfancybutton.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class GenericsUtilTest {

    @Test
    public void testGetFields() {
        String[] fields = GenericsUtil.getFields();
        assertTrue(fields.length > 0);
    }
}