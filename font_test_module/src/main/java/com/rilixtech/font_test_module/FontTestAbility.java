package com.rilixtech.font_test_module;

import com.rilixtech.font_test_module.slice.FontTestAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class FontTestAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(FontTestAbilitySlice.class.getName());
    }
}
