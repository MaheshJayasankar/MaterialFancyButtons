package com.rilixtech.materialfancybutton.utils;

public class TextUtils {
    /**
     * Returns true if the string is null or 0-length
     * @param string - the string to be examined
     * @return true if the string is null or 0-length
     */
    public static boolean isEmpty(String string) {
        if (string == null)
            return true;
        return string.isEmpty();
    }
}
