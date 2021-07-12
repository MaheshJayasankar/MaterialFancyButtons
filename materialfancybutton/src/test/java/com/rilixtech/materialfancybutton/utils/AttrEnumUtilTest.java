package com.rilixtech.materialfancybutton.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AttrEnumUtilTest {

    @Test
    public void testMfbIconPositionLabelOfValue() {
        assertEquals(AttrEnumUtil.MfbIconPosition.left, AttrEnumUtil.MfbIconPosition.labelOfValue(1));
    }

    @Test
    public void testMfbIconPositionValues() {
        assertEquals(1, AttrEnumUtil.MfbIconPosition.left.value);
    }
}