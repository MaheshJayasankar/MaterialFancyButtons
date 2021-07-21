package com.rilixtech.materialfancybutton.typeface;

import ohos.agp.text.Font;
import ohos.app.AbilityContext;

import java.util.Collection;
import java.util.HashMap;

/**
 * Implementation of {@link ITypeface} that is used by the other modules.
 */
public abstract class BaseTypeface implements ITypeface {

    abstract Class<BaseIcon> getBaseIconClass();



    @Override
    public HashMap<String, Character> getCharacters() {
        return null;
    }

    @Override
    public int getIconCount() {
        return 0;
    }

    @Override
    public Collection<String> getIcons() {
        return null;
    }

    @Override
    public Font getTypeface(AbilityContext ctx) {
        return null;
    }

    public interface BaseIcon extends IIcon {
        String name();

        BaseIcon[] values();

        BaseIcon valueOf(String key);

        @Override
        default String getFormattedName() {
            return "{" + name() + "}";
        }

        @Override
        default String getName() {
            return name();
        }

    }
}
