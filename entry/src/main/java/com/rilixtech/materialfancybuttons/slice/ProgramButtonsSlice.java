package com.rilixtech.materialfancybuttons.slice;

import com.rilixtech.fontawesome_typeface.FontAwesome;
import com.rilixtech.materialfancybutton.MaterialFancyButton;
import com.rilixtech.materialfancybuttons.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.DirectionalLayout;

import static ohos.agp.components.ComponentContainer.LayoutConfig.MATCH_CONTENT;

public class ProgramButtonsSlice extends AbilitySlice {

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_program_buttons);

        MaterialFancyButton fontAwesomeBtn = new MaterialFancyButton(this);
        fontAwesomeBtn.setIcon(FontAwesome.Icon.FAWI_ADDRESS_BOOK);
        fontAwesomeBtn.setRadius(30);


        DirectionalLayout.LayoutConfig fabLayoutConfig =
                new DirectionalLayout.LayoutConfig(MATCH_CONTENT,
                        MATCH_CONTENT);
        DirectionalLayout container = (DirectionalLayout) findComponentById(ResourceTable.Id_programbuttons_container);
        container.addComponent(fontAwesomeBtn, fabLayoutConfig);
    }

    @Override
    protected void onActive() {
        super.onActive();
    }

    @Override
    protected void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
