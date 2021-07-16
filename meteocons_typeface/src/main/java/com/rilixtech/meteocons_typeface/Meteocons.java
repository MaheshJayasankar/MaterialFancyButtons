package com.rilixtech.meteocons_typeface;

import com.rilixtech.materialfancybutton.typeface.IIcon;
import com.rilixtech.materialfancybutton.typeface.ITypeface;
import ohos.agp.text.Font;
import ohos.app.AbilityContext;
import ohos.global.resource.RawFileDescriptor;
import ohos.global.resource.RawFileEntry;
import ohos.global.resource.Resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public class Meteocons implements ITypeface {
    private static final String TTF_FILE = "meteocons.ttf";
    private static final String MAPPING_FONT_PREFIX = "METI";

    private static Font typeface = null;

    private static HashMap<String, Character> mChars;

    @Override
    public IIcon getIcon(String key) {
        return Icon.valueOf(key);
    }

    @Override
    public HashMap<String, Character> getCharacters() {
        if (mChars == null) {
            HashMap<String, Character> aChars = new HashMap<>();
            for (Icon v : Icon.values()) {
                aChars.put(v.name(), v.character);
            }
            mChars = aChars;
        }

        return mChars;
    }

    @Override
    public String getMappingPrefix() {
        return MAPPING_FONT_PREFIX;
    }

    @Override
    public String getFontName() {
        return "Meteocons";
    }

    @Override
    public String getVersion() {
        return "1.1.1";
    }

    @Override
    public int getIconCount() {
        return mChars.size();
    }

    @Override
    public Collection<String> getIcons() {
        Collection<String> icons = new LinkedList<>();

        for (Icon value : Icon.values()) {
            icons.add(value.name());
        }

        return icons;
    }

    @Override
    public String getAuthor() {
        return "Alessio Atzeni";
    }

    @Override
    public String getUrl() {
        return "http://www.alessioatzeni.com/meteocons/";
    }

    @Override
    public String getDescription() {
        return "Meteocons is a set of weather icons, it containing 40+ icons available in PSD, CSH, EPS, SVG, Desktop font and Web font. All icon and updates are free and always will be.";
    }

    @Override
    public String getLicense() {
        return "Meteocons are free for use in both personal and commercial projects.\n"
                + "No attribution or backlinks are required, but any form of spreading the word is always appreciated!\n"
                + "You must not resell any icons or distribute them in any other way.\n"
                + "The Icons as such are the property of the author.";
    }

    @Override
    public String getLicenseUrl() {
        return "";
    }

    @Override
    public Font getTypeface(AbilityContext context) {
        if (typeface == null) {
            RawFileEntry rawFileEntry = context.getResourceManager()
                    .getRawFileEntry("resources/rawfile/" + TTF_FILE);
            try {
                File file = getFileFromRawFile(context, rawFileEntry, "file_" + TTF_FILE);
                Font.Builder newTypeface = new Font.Builder(file);
                Font builtFont = newTypeface.build();
                typeface = builtFont;
                return builtFont;
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return  typeface;
    }

    private File getFileFromRawFile(AbilityContext ctx, RawFileEntry rawFileEntry, String filename) {
        byte[] buf;
        try (Resource resource = rawFileEntry.openRawFile();
             RawFileDescriptor rawFileDescriptor = rawFileEntry.openRawFileDescriptor()) {
            File file = new File(ctx.getCacheDir(), filename);

            buf = new byte[(int) rawFileDescriptor.getFileSize()];
            int bytesRead = resource.read(buf);
            if (bytesRead != buf.length) {
                throw new IOException("Asset read failed");
            }
            FileOutputStream output = new FileOutputStream(file);
            output.write(buf, 0, bytesRead);
            output.close();
            return file;
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public enum Icon implements IIcon {
        METI_WINDY_RAIN_INV('\ue800'),
        METI_SNOW_INV('\ue801'),
        METI_SNOW_HEAVY_INV('\ue802'),
        METI_HAIL_INV('\ue803'),
        METI_CLOUDS_INV('\ue804'),
        METI_CLOUDS_FLASH_INV('\ue805'),
        METI_TEMPERATURE('\ue806'),
        METI_COMPASS('\ue807'),
        METI_NA('\ue808'),
        METI_CELCIUS('\ue809'),
        METI_FAHRENHEIT('\ue80a'),
        METI_CLOUDS_FLASH_ALT('\ue80b'),
        METI_SUN_INV('\ue80c'),
        METI_MOON_INV('\ue80d'),
        METI_CLOUD_SUN_INV('\ue80e'),
        METI_CLOUD_MOON_INV('\ue80f'),
        METI_CLOUD_INV('\ue810'),
        METI_CLOUD_FLASH_INV('\ue811'),
        METI_DRIZZLE_INV('\ue812'),
        METI_RAIN_INV('\ue813'),
        METI_WINDY_INV('\ue814'),
        METI_SUNRISE('\ue815'),
        METI_SUN('\ue816'),
        METI_MOON('\ue817'),
        METI_ECLIPSE('\ue818'),
        METI_MIST('\ue819'),
        METI_WIND('\ue81a'),
        METI_SNOWFLAKE('\ue81b'),
        METI_CLOUD_SUN('\ue81c'),
        METI_CLOUD_MOON('\ue81d'),
        METI_FOG_SUN('\ue81e'),
        METI_FOG_MOON('\ue81f'),
        METI_FOG_CLOUD('\ue820'),
        METI_FOG('\ue821'),
        METI_CLOUD('\ue822'),
        METI_CLOUD_FLASH('\ue823'),
        METI_CLOUD_FLASH_ALT('\ue824'),
        METI_DRIZZLE('\ue825'),
        METI_RAIN('\ue826'),
        METI_WINDY('\ue827'),
        METI_WINDY_RAIN('\ue828'),
        METI_SNOW('\ue829'),
        METI_SNOW_ALT('\ue82a'),
        METI_SNOW_HEAVY('\ue82b'),
        METI_HAIL('\ue82c'),
        METI_CLOUDS('\ue82d'),
        METI_CLOUDS_FLASH('\ue82e');

        char character;

        Icon(char character) {
            this.character = character;
        }

        public String getFormattedName() {
            return "{" + name() + "}";
        }

        public char getCharacter() {
            return character;
        }

        public String getName() {
            return name();
        }

        // remember the typeface so we can use it later
        private static ITypeface typeface;

        public ITypeface getTypeface() {
            if (typeface == null) {
                typeface = new Meteocons();
            }
            return typeface;
        }
    }
}
