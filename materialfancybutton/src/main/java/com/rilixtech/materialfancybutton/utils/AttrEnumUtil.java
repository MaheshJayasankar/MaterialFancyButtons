package com.rilixtech.materialfancybutton.utils;

import ohos.agp.utils.TextAlignment;

import java.util.HashMap;
import java.util.Map;

public class AttrEnumUtil {

    private AttrEnumUtil() {}

    public enum MfbIconPosition{
        left(1),
        right(2),
        top(3),
        bottom(4);

        private static final Map<Integer, MfbIconPosition> BY_VALUE = new HashMap<>();

        static {
            for (MfbIconPosition e: values()) {
                BY_VALUE.put(e.value, e);
            }
        }

        public final int value;

        MfbIconPosition(int value){
            this.value = value;
        }

        public static MfbIconPosition labelOfValue(int value){
            return BY_VALUE.get(value);
        }
    }
    public enum MfbTextGravity{
        top(TextAlignment.TOP),
        bottom(TextAlignment.BOTTOM),
        left(TextAlignment.LEFT),
        right(TextAlignment.RIGHT),
        horizontal_center(TextAlignment.HORIZONTAL_CENTER),
        vertical_center(TextAlignment.VERTICAL_CENTER),
        start(TextAlignment.START),
        end(TextAlignment.END),
        center(TextAlignment.CENTER);

        private int value;

        MfbTextGravity(int value){
            this.setValue(value);
        }

        public int getValue() {
            return value;
        }

        private void setValue(int value) {
            this.value = value;
        }
    }
}
