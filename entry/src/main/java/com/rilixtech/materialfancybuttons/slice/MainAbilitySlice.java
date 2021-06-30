package com.rilixtech.materialfancybuttons.slice;

import com.rilixtech.materialfancybuttons.ListItemProvider;
import com.rilixtech.materialfancybuttons.MainAbility;
import com.rilixtech.materialfancybuttons.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.ListContainer;
import ohos.agp.components.Text;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainAbilitySlice extends AbilitySlice {

    private List<NameLayout> nameLayouts;

    public static class NameLayout {
        String name;
        //Class clazz;
        int layoutId;

        NameLayout(String name, int layoutId) {
            this.name = name;
            //this.clazz = clazz;
            this.layoutId = layoutId;
        }
        public String getName(){
            return name;
        }
    }

    private List<NameLayout> getNameAndClasses() {
        List<NameLayout> list = new ArrayList<>();
        list.add(new NameLayout(getString(ResourceTable.String_title_xml_buttons), ResourceTable.Layout_ability_community_material_buttons));
        list.add(new NameLayout(getString(ResourceTable.String_title_program_buttons), ResourceTable.Layout_ability_program_buttons));
        list.add(new NameLayout(getString(ResourceTable.String_title_community_material_buttons), ResourceTable.Layout_ability_community_material_buttons));
        list.add(new NameLayout(getString(ResourceTable.String_title_devicon_buttons), ResourceTable.Layout_ability_community_material_buttons));
        list.add(new NameLayout(getString(ResourceTable.String_title_dripicons_buttons), ResourceTable.Layout_ability_community_material_buttons));
        list.add(new NameLayout(getString(ResourceTable.String_title_entypo), ResourceTable.Layout_ability_community_material_buttons));
        list.add(new NameLayout(getString(ResourceTable.String_title_fontawesome), ResourceTable.Layout_ability_community_material_buttons));
        list.add(new NameLayout(getString(ResourceTable.String_title_foundation_icons), ResourceTable.Layout_ability_community_material_buttons));
        list.add(new NameLayout(getString(ResourceTable.String_title_activity_glyphicons_halflings), ResourceTable.Layout_ability_community_material_buttons));
        list.add(new NameLayout(getString(ResourceTable.String_title_google_material), ResourceTable.Layout_ability_community_material_buttons));
        list.add(new NameLayout(getString(ResourceTable.String_title_ionicons), ResourceTable.Layout_ability_community_material_buttons));
        list.add(new NameLayout(getString(ResourceTable.String_title_mfglabs), ResourceTable.Layout_ability_community_material_buttons));
        list.add(new NameLayout(getString(ResourceTable.String_title_material_design_iconic), ResourceTable.Layout_ability_community_material_buttons));
        list.add(new NameLayout(getString(ResourceTable.String_title_meteocons), ResourceTable.Layout_ability_community_material_buttons));
        list.add(new NameLayout(getString(ResourceTable.String_title_mobiriseicons), ResourceTable.Layout_ability_community_material_buttons));
        list.add(new NameLayout(getString(ResourceTable.String_title_octicons), ResourceTable.Layout_ability_community_material_buttons));
        list.add(new NameLayout(getString(ResourceTable.String_title_openiconic), ResourceTable.Layout_ability_community_material_buttons));
        list.add(new NameLayout(getString(ResourceTable.String_title_picol), ResourceTable.Layout_ability_community_material_buttons));
        list.add(new NameLayout(getString(ResourceTable.String_title_pixeden7), ResourceTable.Layout_ability_community_material_buttons));
        list.add(new NameLayout(getString(ResourceTable.String_title_themify_icons), ResourceTable.Layout_ability_community_material_buttons));
        list.add(new NameLayout(getString(ResourceTable.String_title_typicons), ResourceTable.Layout_ability_community_material_buttons));
        list.add(new NameLayout(getString(ResourceTable.String_title_vaadin_icons), ResourceTable.Layout_ability_community_material_buttons));
        list.add(new NameLayout(getString(ResourceTable.String_title_weather_icons), ResourceTable.Layout_ability_community_material_buttons));
        return list;
    }

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        initViews();
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    private void initViews() {
        ListContainer listContainer = (ListContainer) findComponentById(ResourceTable.Id_list_container);
        nameLayouts = getNameAndClasses();
        ListItemProvider listItemProvider = new ListItemProvider(nameLayouts, this);
        listContainer.setItemProvider(listItemProvider);
        listContainer.setItemClickedListener((listContainer1, component, i, l) -> onListItemClick(i));

        Text titleTv = (Text) findComponentById(ResourceTable.Id_title_tv);
        titleTv.setText(
                "MaterialFancyButtons example with " + (nameLayouts.size() - 2) + " icon fonts");
    }

    private void onListItemClick(final int position) {
        if (nameLayouts.get(position).layoutId == ResourceTable.Layout_ability_program_buttons)
            present(new ProgramButtonsSlice(), new Intent());
        else{
            Intent buttonActivity = new Intent();
            buttonActivity.setParam(IconButtonsSlice.LAYOUT_ID_EXTRA, nameLayouts.get(position).layoutId);
            buttonActivity.setParam(IconButtonsSlice.TITLE_EXTRA, nameLayouts.get(position).name);
            present(new IconButtonsSlice(), buttonActivity);
        }
    }

}
