package com.rilixtech.materialfancybuttons;

import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;
import ohos.agp.colors.RgbColor;
import ohos.agp.colors.RgbPalette;
import ohos.agp.utils.Color;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExampleOhosTest {
    @Test
    public void testBundleName() {
        final String actualBundleName = AbilityDelegatorRegistry.getArguments().getTestBundleName();
        assertEquals("com.rilixtech.materialfancybuttons", actualBundleName);
    }
    @Test
    public void testColorStuff(){
        Color utilColor = Color.BLACK;
        RgbColor util2Rgb = RgbColor.fromArgbInt(utilColor.getValue());
        RgbColor rgbColor = RgbPalette.BLACK;

        assertEquals(rgbColor.asArgbInt(), util2Rgb.asArgbInt());
    }
}