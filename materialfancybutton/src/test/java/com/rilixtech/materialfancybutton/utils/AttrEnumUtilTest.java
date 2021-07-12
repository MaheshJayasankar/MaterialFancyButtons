package com.rilixtech.materialfancybutton.utils;

import ohos.agp.utils.TextAlignment;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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