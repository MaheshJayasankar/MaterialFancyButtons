package com.rilixtech.materialfancybuttons.slice;

import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;

public class IconButtonsSlice extends AbilitySlice {

    public static final String LAYOUT_ID_EXTRA = "layoutIdExtra";
    public static final String TITLE_EXTRA = "titleExtra";
    @Override protected void onStart(Intent intent) {
        super.onStart(intent);

        int layoutId = intent.getIntParam(LAYOUT_ID_EXTRA, 0);
        String title = intent.getStringParam(TITLE_EXTRA);

        super.setUIContent(layoutId);
    }
}
