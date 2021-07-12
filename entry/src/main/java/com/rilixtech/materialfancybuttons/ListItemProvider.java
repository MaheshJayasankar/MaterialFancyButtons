package com.rilixtech.materialfancybuttons;

import com.rilixtech.materialfancybuttons.slice.MainAbilitySlice;
import ohos.aafwk.ability.AbilitySlice;
import ohos.agp.components.*;

import java.util.ArrayList;
import java.util.List;

public class ListItemProvider extends BaseItemProvider {

    private final List<String> itemNames;
    private final int ITEM_COUNT;



    private final List<MainAbilitySlice.NameLayout> list;
    private final AbilitySlice slice;

    public ListItemProvider(List<MainAbilitySlice.NameLayout> list, AbilitySlice slice) {
        this.list = list;
        this.slice = slice;

        if (slice instanceof MainAbilitySlice){
            List<String> nameLayoutNames = new ArrayList<>();
            for (MainAbilitySlice.NameLayout nameLayoutObj : list) {
                String name = nameLayoutObj.getName();
                nameLayoutNames.add(name);
            }
            itemNames = nameLayoutNames;
        }
        else{
            itemNames = new ArrayList<>();
        }
        ITEM_COUNT = itemNames.size();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        if (position < ITEM_COUNT)
            return list.get(position);
        else
            return list.get(list.size() - 1);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Component getComponent(int position, Component convertComponent, ComponentContainer componentContainer) {
        final Component cpt;
        if (convertComponent == null) {
            cpt = LayoutScatter.getInstance(slice).parse(ResourceTable.Layout_list_item, null, false);
        } else {
            cpt = convertComponent;
        }
        Text text = (Text) cpt.findComponentById(ResourceTable.Id_tv_item);
        text.setText(itemNames.get(position));
        return cpt;
    }
}
