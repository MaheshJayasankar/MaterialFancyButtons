package com.rilixtech.materialfancybuttons;

import com.rilixtech.materialfancybutton.MaterialFancyButton;
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
        MaterialFancyButton mfb = new MaterialFancyButton(context, attrSet);

        assertEquals(colorAttrs.get("mfb_defaultColor").getValue(), mfb.getBackgroundColor());
        assertEquals(colorAttrs.get("mfb_focusColor").getValue(), mfb.getFocusBackgroundColor());
        assertEquals(colorAttrs.get("mfb_disabledColor").getValue(), mfb.getDisableBackgroundColor());
        assertEquals(colorAttrs.get("mfb_disabledTextColor").getValue(), mfb.getDisableTextColor());
        assertEquals(colorAttrs.get("mfb_disabledBorderColor").getValue(), mfb.getDisableBorderColor());
        assertEquals(colorAttrs.get("mfb_textColor").getValue(), mfb.getTextColor());
        assertEquals(colorAttrs.get("mfb_iconColor").getValue(), mfb.getIconColor());
        assertEquals(colorAttrs.get("mfb_borderColor").getValue(), mfb.getBorderColor());

    }

    private static class TestAttrSet implements AttrSet {

        Map<String, Optional<Attr>> attrMap= new HashMap<>();

        public TestAttrSet() {
            super();
        }

        public TestAttrSet(Map<String, String> stringAttrs,
                                                         Map<String, Integer> integerAttrs,
                                                         Map<String, Boolean> boolAttrs,
                                                         Map<String, Color> colorAttrs,
                                                         Map<String, String> enumAttrs,
                                                         Map<String, Element> elementAttrs) {
            super();
            if (stringAttrs != null)
                for (String strKey : stringAttrs.keySet()) {
                    attrMap.put(strKey, Optional.of(new TestAttr(strKey, stringAttrs.get(strKey))));
                }
            if (integerAttrs != null)
                for (String strKey : integerAttrs.keySet()) {
                    attrMap.put(strKey, Optional.of(new TestAttr(strKey, integerAttrs.get(strKey))));
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
        private int integerValue = 0;
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
        public TestAttr(String name, int value) {
            this(name);
            this.integerValue = value;
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
            return integerValue;
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
            return 0;
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
