package com.rilixtech.materialfancybutton.utils;

import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;
import ohos.agp.components.AttrHelper;
import ohos.app.Context;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FontUtilTest {

    private final Context context = AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();

    @Test
    public void testPxToFp() {
        int fpVal = 32;
        int pxConvertedVal = AttrHelper.fp2px(fpVal, context);
        assertEquals(fpVal, FontUtil.pxToFp(context, pxConvertedVal));
    }

    @Test
    public void testFpToPx() {
        int fpVal = 32;
        int pxConvertedVal = AttrHelper.fp2px(fpVal, context);
        assertEquals(pxConvertedVal, FontUtil.fpToPx(context, fpVal));
    }
}
