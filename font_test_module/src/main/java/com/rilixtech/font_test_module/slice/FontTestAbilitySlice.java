package com.rilixtech.font_test_module.slice;

import com.rilixtech.community_material_typeface.CommunityMaterial;
import com.rilixtech.font_test_module.ResourceTable;
import com.rilixtech.google_material_typeface.GoogleMaterial;
import com.rilixtech.ionicons_typeface.Ionicons;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.TextField;
import ohos.agp.text.Font;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class FontTestAbilitySlice extends AbilitySlice {

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_font_test);

        TextField tf1 = (TextField) findComponentById(ResourceTable.Id_textField1);
        Font loadedFont = new Ionicons().getTypeface(this);
        tf1.setFont(loadedFont);
        tf1.setText(String.valueOf(Ionicons.Icon.ioni_alert.getCharacter()));

    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

}
