package com.rilixtech.dripicons_typeface;

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

public class Dripicons implements ITypeface {
    private static final String TTF_FILE = "dripicons-v2.ttf";
    private static final String MAPPING_FONT_PREFIX = "DRPI";

    private static Font typeface = null;

    private static HashMap<String, Character> mChars;

    @Override public IIcon getIcon(String key) {
        return Icon.valueOf(key);
    }

    @Override public HashMap<String, Character> getCharacters() {
        if (mChars == null) {
            HashMap<String, Character> aChars = new HashMap<>();
            for (Icon v : Icon.values()) {
                aChars.put(v.name(), v.character);
            }
            mChars = aChars;
        }

        return mChars;
    }

    @Override public String getMappingPrefix() {
        return MAPPING_FONT_PREFIX;
    }

    @Override public String getFontName() {
        return "Dripicons";
    }

    @Override public String getVersion() {
        return "2.0";
    }

    @Override public int getIconCount() {
        return mChars.size();
    }

    @Override public Collection<String> getIcons() {
        Collection<String> icons = new LinkedList<>();

        for (Icon value : Icon.values()) {
            icons.add(value.name());
        }

        return icons;
    }

    @Override public String getAuthor() {
        return "Amit Jakhu";
    }

    @Override public String getUrl() {
        return "https://github.com/amitjakhu/dripicons";
    }

    @Override public String getDescription() {
        return "A completely free vector line iconset by Amit Jakhu.";
    }

    @Override public String getLicense() {
        return "SIL Open Font License";
    }

    @Override public String getLicenseUrl() {
        return "http://scripts.sil.org/cms/scripts/page.php?site_id=nrsi&id=OFL";
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

    private static final char a = 0x0027;
    private static final char back_slash = 0x005C;

    public enum Icon implements IIcon {
        DRPI_ALARM('\u0061'),
        DRPI_ALIGN_CENTER('\u0062'),
        DRPI_ALIGN_JUSTIFY('\u0063'),
        DRPI_ALIGN_LEFT('\u0064'),
        DRPI_ALIGN_RIGHT('\u0065'),
        DRPI_ANCHOR('\u0066'),
        DRPI_ARCHIVE('\u0067'),
        DRPI_ARROW_DOWN('\u0068'),
        DRPI_ARROW_LEFT('\u0069'),
        DRPI_ARROW_RIGHT('\u006a'),
        DRPI_ARROW_THIN_DOWN('\u006b'),
        DRPI_ARROW_THIN_LEFT('\u006c'),
        DRPI_ARROW_THIN_RIGHT('\u006d'),
        DRPI_ARROW_THIN_UP('\u006e'),
        DRPI_ARROW_UP('\u006f'),
        DRPI_ARTICLE('\u0070'),
        DRPI_BACKSPACE('\u0071'),
        DRPI_BASKET('\u0072'),
        DRPI_BASKETBALL('\u0073'),
        DRPI_BATTERY_EMPTY('\u0074'),
        DRPI_BATTERY_FULL('\u0075'),
        DRPI_BATTERY_LOW('\u0076'),
        DRPI_BATTERY_MEDIUM('\u0077'),
        DRPI_BELL('\u0078'),
        DRPI_BLOG('\u0079'),
        DRPI_BLUETOOTH('\u007a'),
        DRPI_BOLD('\u0041'),
        DRPI_BOOKMARK('\u0042'),
        DRPI_BOOKMARKS('\u0043'),
        DRPI_BOX('\u0044'),
        DRPI_BRIEFCASE('\u0045'),
        DRPI_BRIGHTNESS_LOW('\u0046'),
        DRPI_BRIGHTNESS_MAX('\u0047'),
        DRPI_BRIGHTNESS_MEDIUM('\u0048'),
        DRPI_BROADCAST('\u0049'),
        DRPI_BROWSER('\u004a'),
        DRPI_BROWSER_UPLOAD('\u004b'),
        DRPI_BRUSH('\u004c'),
        DRPI_CALENDAR('\u004d'),
        DRPI_CAMCORDER('\u004e'),
        DRPI_CAMERA('\u004f'),
        DRPI_CARD('\u0050'),
        DRPI_CART('\u0051'),
        DRPI_CHECKLIST('\u0052'),
        DRPI_CHECKMARK('\u0053'),
        DRPI_CHEVRON_DOWN('\u0054'),
        DRPI_CHEVRON_LEFT('\u0055'),
        DRPI_CHEVRON_RIGHT('\u0056'),
        DRPI_CHEVRON_UP('\u0057'),
        DRPI_CLIPBOARD('\u0058'),
        DRPI_CLOCK('\u0059'),
        DRPI_CLOCKWISE('\u005a'),
        DRPI_CLOUD('\u0030'),
        DRPI_CLOUD_DOWNLOAD('\u0031'),
        DRPI_CLOUD_UPLOAD('\u0032'),
        DRPI_CODE('\u0033'),
        DRPI_CONTRACT('\u0034'),
        DRPI_CONTRACT_2('\u0035'),
        DRPI_CONVERSATION('\u0036'),
        DRPI_COPY('\u0037'),
        DRPI_CROP('\u0038'),
        DRPI_CROSS('\u0039'),
        DRPI_CROSSHAIR('\u0021'),
        DRPI_CUTLERY('\u0022'),
        DRPI_DEVICE_DESKTOP('\u0023'),
        DRPI_DEVICE_MOBILE('\u0024'),
        DRPI_DEVICE_TABLET('\u0025'),
        DRPI_DIRECTION('\u0026'),
        drpi_disc(a),
        DRPI_DOCUMENT('\u0028'),
        DRPI_DOCUMENT_DELETE('\u0029'),
        DRPI_DOCUMENT_EDIT('\u002a'),
        DRPI_DOCUMENT_NEW('\u002b'),
        DRPI_DOCUMENT_REMOVE('\u002c'),
        DRPI_DOT('\u002d'),
        DRPI_DOTS_2('\u002e'),
        DRPI_DOTS_3('\u002f'),
        DRPI_DOWNLOAD('\u003a'),
        DRPI_DUPLICATE('\u003b'),
        DRPI_ENTER('\u003c'),
        DRPI_EXIT('\u003d'),
        DRPI_EXPAND('\u003e'),
        DRPI_EXPAND_2('\u003f'),
        DRPI_EXPERIMENT('\u0040'),
        DRPI_EXPORT('\u005b'),
        DRPI_FEED('\u005d'),
        DRPI_FLAG('\u005e'),
        DRPI_FLASHLIGHT('\u005f'),
        DRPI_FOLDER('\u0060'),
        DRPI_FOLDER_OPEN('\u007b'),
        DRPI_FORWARD('\u007c'),
        DRPI_GAMING('\u007d'),
        DRPI_GEAR('\u007e'),
        drpi_graduation(back_slash),
        DRPI_GRAPH_BAR('\ue000'),
        DRPI_GRAPH_LINE('\ue001'),
        DRPI_GRAPH_PIE('\ue002'),
        DRPI_HEADSET('\ue003'),
        DRPI_HEART('\ue004'),
        DRPI_HELP('\ue005'),
        DRPI_HOME('\ue006'),
        DRPI_HOURGLASS('\ue007'),
        DRPI_INBOX('\ue008'),
        DRPI_INFORMATION('\ue009'),
        DRPI_ITALIC('\ue00a'),
        DRPI_JEWEL('\ue00b'),
        DRPI_LIFTING('\ue00c'),
        DRPI_LIGHTBULB('\ue00d'),
        DRPI_LINK('\ue00e'),
        DRPI_LINK_BROKEN('\ue00f'),
        DRPI_LIST('\ue010'),
        DRPI_LOADING('\ue011'),
        DRPI_LOCATION('\ue012'),
        DRPI_LOCK('\ue013'),
        DRPI_LOCK_OPEN('\ue014'),
        DRPI_MAIL('\ue015'),
        DRPI_MAP('\ue016'),
        DRPI_MEDIA_LOOP('\ue017'),
        DRPI_MEDIA_NEXT('\ue018'),
        DRPI_MEDIA_PAUSE('\ue019'),
        DRPI_MEDIA_PLAY('\ue01a'),
        DRPI_MEDIA_PREVIOUS('\ue01b'),
        DRPI_MEDIA_RECORD('\ue01c'),
        DRPI_MEDIA_SHUFFLE('\ue01d'),
        DRPI_MEDIA_STOP('\ue01e'),
        DRPI_MEDICAL('\ue01f'),
        DRPI_MENU('\ue020'),
        DRPI_MESSAGE('\ue021'),
        DRPI_METER('\ue022'),
        DRPI_MICROPHONE('\ue023'),
        DRPI_MINUS('\ue024'),
        DRPI_MONITOR('\ue025'),
        DRPI_MOVE('\ue026'),
        DRPI_MUSIC('\ue027'),
        DRPI_NETWORK_1('\ue028'),
        DRPI_NETWORK_2('\ue029'),
        DRPI_NETWORK_3('\ue02a'),
        DRPI_NETWORK_4('\ue02b'),
        DRPI_NETWORK_5('\ue02c'),
        DRPI_PAMPHLET('\ue02d'),
        DRPI_PAPERCLIP('\ue02e'),
        DRPI_PENCIL('\ue02f'),
        DRPI_PHONE('\ue030'),
        DRPI_PHOTO('\ue031'),
        DRPI_PHOTO_GROUP('\ue032'),
        DRPI_PILL('\ue033'),
        DRPI_PIN('\ue034'),
        DRPI_PLUS('\ue035'),
        DRPI_POWER('\ue036'),
        DRPI_PREVIEW('\ue037'),
        DRPI_PRINT('\ue038'),
        DRPI_PULSE('\ue039'),
        DRPI_QUESTION('\ue03a'),
        DRPI_REPLY('\ue03b'),
        DRPI_REPLY_ALL('\ue03c'),
        DRPI_RETURN('\ue03d'),
        DRPI_RETWEET('\ue03e'),
        DRPI_ROCKET('\ue03f'),
        DRPI_SCALE('\ue040'),
        DRPI_SEARCH('\ue041'),
        DRPI_SHOPPING_BAG('\ue042'),
        DRPI_SKIP('\ue043'),
        DRPI_STACK('\ue044'),
        DRPI_STAR('\ue045'),
        DRPI_STOPWATCH('\ue046'),
        DRPI_STORE('\ue047'),
        DRPI_SUITCASE('\ue048'),
        DRPI_SWAP('\ue049'),
        DRPI_TAG('\ue04a'),
        DRPI_TAG_DELETE('\ue04b'),
        DRPI_TAGS('\ue04c'),
        DRPI_THUMBS_DOWN('\ue04d'),
        DRPI_THUMBS_UP('\ue04e'),
        DRPI_TICKET('\ue04f'),
        DRPI_TIME_REVERSE('\ue050'),
        DRPI_TO_DO('\ue051'),
        DRPI_TOGGLES('\ue052'),
        DRPI_TRASH('\ue053'),
        DRPI_TROPHY('\ue054'),
        DRPI_UPLOAD('\ue055'),
        DRPI_USER('\ue056'),
        DRPI_USER_GROUP('\ue057'),
        DRPI_USER_ID('\ue058'),
        DRPI_VIBRATE('\ue059'),
        DRPI_VIEW_APPS('\ue05a'),
        DRPI_VIEW_LIST('\ue05b'),
        DRPI_VIEW_LIST_LARGE('\ue05c'),
        DRPI_VIEW_THUMB('\ue05d'),
        DRPI_VOLUME_FULL('\ue05e'),
        DRPI_VOLUME_LOW('\ue05f'),
        DRPI_VOLUME_MEDIUM('\ue060'),
        DRPI_VOLUME_OFF('\ue061'),
        DRPI_WALLET('\ue062'),
        DRPI_WARNING('\ue063'),
        DRPI_WEB('\ue064'),
        DRPI_WEIGHT('\ue065'),
        DRPI_WIFI('\ue066'),
        DRPI_WRONG('\ue067'),
        DRPI_ZOOM_IN('\ue068'),
        DRPI_ZOOM_OUT('\ue069');

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
                typeface = new Dripicons();
            }
            return typeface;
        }
    }
}
