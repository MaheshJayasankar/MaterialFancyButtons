package com.rilixtech.materialfancybuttons;

import com.rilixtech.materialfancybutton.MaterialFancyButton;
import com.rilixtech.materialfancybutton.utils.AttrEnumUtil;
import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;
import ohos.agp.components.Attr;
import ohos.agp.components.AttrSet;
import ohos.agp.components.element.Element;
import ohos.agp.utils.Color;
import ohos.app.Context;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;

public class MaterialFancyButtonTest {

    private final AttrSet dummyAttrSet = new TestAttrSet();
    private final Context context = AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();
    private MaterialFancyButton defaultMaterialFancyButton;

    @Before
    public void initMaterialFancyButton() {
        defaultMaterialFancyButton = new MaterialFancyButton(context, dummyAttrSet);
    }

    @Test
    public void testInitBasic() {
        assertNotNull(defaultMaterialFancyButton);
    }

    @Test
    public void testInitWithoutAttrs() {
        MaterialFancyButton materialFancyButton = new MaterialFancyButton(context);
        assertNotNull(materialFancyButton);
    }

    @Test
    public void testColorAttrs() {
        HashMap<String, Color> colorAttrs = new HashMap<>();
        colorAttrs.put("mfb_defaultColor", Color.WHITE);
        colorAttrs.put("mfb_focusColor", Color.BLACK);
        colorAttrs.put("mfb_disabledColor", Color.BLUE);
        colorAttrs.put("mfb_disabledTextColor", Color.CYAN);
        colorAttrs.put("mfb_disabledBorderColor", Color.DKGRAY);
        colorAttrs.put("mfb_textColor", Color.GRAY);
        colorAttrs.put("mfb_iconColor", Color.GREEN);
        colorAttrs.put("mfb_borderColor", Color.RED);

        TestAttrSet attrSet = new TestAttrSet(null, null, null, colorAttrs, null, null);
        MaterialFancyButton materialFancyButton = new MaterialFancyButton(context, attrSet);

        assertEquals(colorAttrs.get("mfb_defaultColor").getValue(), materialFancyButton.getBackgroundColor());
        assertEquals(colorAttrs.get("mfb_focusColor").getValue(), materialFancyButton.getFocusBackgroundColor());
        assertEquals(colorAttrs.get("mfb_disabledColor").getValue(), materialFancyButton.getDisableBackgroundColor());
        assertEquals(colorAttrs.get("mfb_disabledTextColor").getValue(), materialFancyButton.getDisableTextColor());
        assertEquals(colorAttrs.get("mfb_disabledBorderColor").getValue(), materialFancyButton.getDisableBorderColor());
        assertEquals(colorAttrs.get("mfb_textColor").getValue(), materialFancyButton.getTextColor());
        assertEquals(colorAttrs.get("mfb_iconColor").getValue(), materialFancyButton.getIconColor());
        assertEquals(colorAttrs.get("mfb_borderColor").getValue(), materialFancyButton.getBorderColor());
    }

    @Test
    public void testStringAttrs() {
        HashMap<String, String> stringAttrs = new HashMap<>();
        stringAttrs.put("mfb_text", "Test Button Text");

        TestAttrSet attrSet = new TestAttrSet(stringAttrs, null, null, null, null, null);
        MaterialFancyButton materialFancyButton = new MaterialFancyButton(context, attrSet);

        assertEquals(stringAttrs.get("mfb_text"), materialFancyButton.getText());
    }

    @Test
    public void testBoolAttrs() {
        HashMap<String, Boolean> boolAttrs = new HashMap<>();
        boolAttrs.put("enabled", true);
        boolAttrs.put("mfb_textAllCaps", true);
        boolAttrs.put("mfb_ghost", true);

        TestAttrSet attrSet = new TestAttrSet(null, null, boolAttrs, null, null, null);
        MaterialFancyButton materialFancyButton = new MaterialFancyButton(context, attrSet);

        assertEquals(boolAttrs.get("enabled"), materialFancyButton.isEnabled());
        assertEquals(boolAttrs.get("mfb_textAllCaps"), materialFancyButton.isTextAllCaps());
        assertEquals(boolAttrs.get("mfb_ghost"), materialFancyButton.isGhost());
    }
    @Test
    public void testDimensionAttrs() {
        HashMap<String, String> dimensionAttrs = new HashMap<>();
        int textSize = 16;
        dimensionAttrs.put("mfb_textSize", textSize + "fp");
        int borderWidth = 8;
        dimensionAttrs.put("mfb_borderWidth", borderWidth + "vp");
        int radiusTopLeft = 4;
        dimensionAttrs.put("mfb_radiusTopLeft", radiusTopLeft + "px");
        int radiusTopRight = 6;
        dimensionAttrs.put("mfb_radiusTopRight", radiusTopRight + "px");
        int radiusBottomLeft = 9;
        dimensionAttrs.put("mfb_radiusBottomLeft", radiusBottomLeft + "px");
        int radiusBottomRight = 20;
        dimensionAttrs.put("mfb_radiusBottomRight",  radiusBottomRight + "px");
        int fontIconSize = 4;
        dimensionAttrs.put("mfb_fontIconSize", fontIconSize + "fp");
        int iconPaddingLeft = 30;
        dimensionAttrs.put("mfb_iconPaddingLeft", iconPaddingLeft + "vp");
        int iconPaddingRight = 80;
        dimensionAttrs.put("mfb_iconPaddingRight", iconPaddingRight + "vp");
        int iconPaddingTop = 40;
        dimensionAttrs.put("mfb_iconPaddingTop", iconPaddingTop + "vp");
        int iconPaddingBottom = 64;
        dimensionAttrs.put("mfb_iconPaddingBottom", iconPaddingBottom + "vp");

        TestAttrSet attrSet = new TestAttrSet(null, dimensionAttrs, null, null, null, null);
        MaterialFancyButton mfb = new MaterialFancyButton(context, attrSet);

        assertEquals(textSize, mfb.getTextSize());
        assertEquals(borderWidth, mfb.getBorderWidth());
        assertEquals(radiusTopLeft, mfb.getRadiusTopLeft());
        assertEquals(radiusTopRight, mfb.getRadiusTopRight());
        assertEquals(radiusBottomLeft, mfb.getRadiusBottomLeft());
        assertEquals(radiusBottomRight, mfb.getRadiusBottomRight());
        assertEquals(fontIconSize, mfb.getFontIconSize());
        assertEquals(iconPaddingLeft, mfb.getIconPaddingLeft());
        assertEquals(iconPaddingRight, mfb.getIconPaddingRight());
        assertEquals(iconPaddingTop, mfb.getIconPaddingTop());
        assertEquals(iconPaddingBottom, mfb.getIconPaddingBottom());
    }

    @Test
    public void testEnumAttrs() {
        HashMap<String, String> enumAttrs = new HashMap<>();
        AttrEnumUtil.MfbTextGravity textGravityStart = AttrEnumUtil.MfbTextGravity.start;
        enumAttrs.put("mfb_textGravity", textGravityStart.name());
        AttrEnumUtil.MfbIconPosition iconPositionRight = AttrEnumUtil.MfbIconPosition.right;
        enumAttrs.put("mfb_iconPosition", iconPositionRight.name());

        TestAttrSet attrSet = new TestAttrSet(null, null, null, null, enumAttrs, null);
        MaterialFancyButton materialFancyButton = new MaterialFancyButton(context, attrSet);

        assertEquals(textGravityStart.getValue(), materialFancyButton.getTextGravity());
        assertEquals(iconPositionRight.value, materialFancyButton.getIconPosition());
    }

    private static class TestAttrSet implements AttrSet {

        Map<String, Optional<Attr>> attrMap= new HashMap<>();

        public TestAttrSet() {
            super();
        }

        public TestAttrSet(Map<String, String> stringAttrs,
                           Map<String, String> dimensionAttrs,
                           Map<String, Boolean> boolAttrs,
                           Map<String, Color> colorAttrs,
                           Map<String, String> enumAttrs,
                           Map<String, Element> elementAttrs) {
            super();
            if (stringAttrs != null)
                for (String strKey : stringAttrs.keySet()) {
                    attrMap.put(strKey, Optional.of(new TestAttr(strKey, stringAttrs.get(strKey))));
                }
            if (dimensionAttrs != null)
                for (String strKey : dimensionAttrs.keySet()) {
                    TestAttr dimensionAttr = TestAttr.createDimensionAttr(strKey, dimensionAttrs.get(strKey));
                    if (dimensionAttr != null) {
                        attrMap.put(strKey, Optional.of(dimensionAttr));
                    }
                }
            if (boolAttrs != null)
                for (String strKey : boolAttrs.keySet()) {
                    attrMap.put(strKey, Optional.of(new TestAttr(strKey, boolAttrs.get(strKey))));
                }
            if (colorAttrs != null)
                for (String strKey : colorAttrs.keySet()) {
                    attrMap.put(strKey, Optional.of(new TestAttr(strKey, colorAttrs.get(strKey))));
                }
            if (enumAttrs != null)
                for (String strKey : enumAttrs.keySet()) {
                    attrMap.put(strKey, Optional.of(new TestAttr(strKey, enumAttrs.get(strKey))));
                }
            if (elementAttrs != null)
                for (String strKey : elementAttrs.keySet()) {
                    attrMap.put(strKey, Optional.of(new TestAttr(strKey, elementAttrs.get(strKey))));
                }
        }

        @Override
        public Optional<String> getStyle() {
            return Optional.empty();
        }

        @Override
        public int getLength() {
            return 0;
        }

        @Override
        public Optional<Attr> getAttr(int i) {
            return Optional.empty();
        }

        @Override
        public Optional<Attr> getAttr(String s) {
            if (attrMap.containsKey(s))
                return attrMap.get(s);
            return Optional.empty();
        }
    }

    private static class TestAttr implements Attr {

        private final String name;
        private String stringValue = null;
        private int dimensionValue = 0;
        private boolean boolValue = false;
        private Color colorValue = null;
        private Element elementValue = null;

        public TestAttr(String name) {
            this.name = name;
        }

        public TestAttr(String name, String value) {
            this(name);
            this.stringValue = value;
        }

        public TestAttr(String name, boolean value) {
            this(name);
            this.boolValue = value;
        }
        public TestAttr(String name, Color value) {
            this(name);
            this.colorValue = value;
        }
        public TestAttr(String name, Element value) {
            this(name);
            this.elementValue = value;
        }

        public static TestAttr createDimensionAttr(String name, String dimensionValue) {
            TestAttr testAttr = new TestAttr(name);
            try {
                if (dimensionValue.endsWith("fp") || dimensionValue.endsWith("vp") || dimensionValue.endsWith("px")) {
                    String integerPortionOfString = dimensionValue.split("[^0-9]")[0];
                    testAttr.dimensionValue = Integer.parseInt(integerPortionOfString);
                    return testAttr;
                }
                else {
                    return null;
                }
            } catch (IllegalArgumentException | IndexOutOfBoundsException ex) {
                return null;
            }
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getStringValue() {
            return stringValue;
        }

        @Override
        public int getIntegerValue() {
            return 0;
        }

        @Override
        public boolean getBoolValue() {
            return boolValue;
        }

        @Override
        public float getFloatValue() {
            return 0;
        }

        @Override
        public long getLongValue() {
            return 0;
        }

        @Override
        public Element getElement() {
            return elementValue;
        }

        @Override
        public int getDimensionValue() {
            return dimensionValue;
        }

        @Override
        public Color getColorValue() {
            return colorValue;
        }

        @Override
        public Context getContext() {
            return null;
        }
    }
}
