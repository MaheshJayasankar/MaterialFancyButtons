package com.rilixtech.materialfancybutton.utils;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LogHelperTest {
    @Test
    public void testSetEnabled() {
        LogHelper logHelper = new LogHelper(0, "TEST");
        logHelper.setEnabled(true);
        assertTrue(logHelper.isEnabled());
    }
    @Test
    public void testIsEnabled() {
        LogHelper logHelper = new LogHelper(0, "TEST");
        logHelper.setEnabled(false);
        logHelper.logDebug("Test Debug Log. This will not be logged as the logger is disabled.");
        logHelper.logError("Test Error Log. This will not be logged as the logger is disabled.");
        assertFalse(logHelper.isEnabled());
    }
}
