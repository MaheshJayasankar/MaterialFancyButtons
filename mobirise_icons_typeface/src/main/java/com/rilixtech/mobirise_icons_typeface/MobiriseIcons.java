package com.rilixtech.mobirise_icons_typeface;

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

public class MobiriseIcons implements ITypeface {
    private static final String TTF_FILE = "mobirise-icons-v1.0.0.ttf";
    private static final String MAPPING_FONT_PREFIX = "MBRI";

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
        return "Mobirise Icons";
    }

    @Override public String getVersion() {
        return "1.0.0";
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
        return "Free Website Builder, https://mobirise.com/";
    }

    @Override public String getUrl() {
        return "https://mobirise.com/";
    }

    @Override public String getDescription() {
        return "A free, open source set of 150 elegant, pixel-perfect vector icons from Free Website Builder.";
    }

    @Override public String getLicense() {
        return "CC BY 4.0.";
    }

    @Override public String getLicenseUrl() {
        return "https://creativecommons.org/licenses/by/4.0/";
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
        MBRI_ADD_SUBMENU('\ue900'),
        MBRI_ALERT('\ue901'),
        MBRI_ALIGN_CENTER('\ue902'),
        MBRI_ALIGN_JUSTIFY('\ue903'),
        MBRI_ALIGN_LEFT('\ue904'),
        MBRI_ALIGN_RIGHT('\ue905'),
        MBRI_ANDROID('\ue906'),
        MBRI_APPLE('\ue907'),
        MBRI_ARROW_DOWN('\ue908'),
        MBRI_ARROW_NEXT('\ue909'),
        MBRI_ARROW_PREV('\ue90a'),
        MBRI_ARROW_UP('\ue90b'),
        MBRI_BOLD('\ue90c'),
        MBRI_BOOKMARK('\ue90d'),
        MBRI_BOOTSTRAP('\ue90e'),
        MBRI_BRIEFCASE('\ue90f'),
        MBRI_BROWSE('\ue910'),
        MBRI_BULLETED_LIST('\ue911'),
        MBRI_CALENDAR('\ue912'),
        MBRI_CAMERA('\ue913'),
        MBRI_CART_ADD('\ue914'),
        MBRI_CART_FULL('\ue915'),
        MBRI_CASH('\ue916'),
        MBRI_CHANGE_STYLE('\ue917'),
        MBRI_CHAT('\ue918'),
        MBRI_CLOCK('\ue919'),
        MBRI_CLOSE('\ue91a'),
        MBRI_CLOUD('\ue91b'),
        MBRI_CODE('\ue91c'),
        MBRI_CONTACT_FORM('\ue91d'),
        MBRI_CREDIT_CARD('\ue91e'),
        MBRI_CURSOR_CLICK('\ue91f'),
        MBRI_CUST_FEEDBACK('\ue920'),
        MBRI_DATABASE('\ue921'),
        MBRI_DELIVERY('\ue922'),
        MBRI_DESKTOP('\ue923'),
        MBRI_DEVICES('\ue924'),
        MBRI_DOWN('\ue925'),
        MBRI_DOWNLOAD('\ue926'),
        MBRI_DRAG_N_DROP('\ue927'),
        MBRI_DRAG_N_DROP2('\ue928'),
        MBRI_EDIT('\ue929'),
        MBRI_EDIT2('\ue92a'),
        MBRI_ERROR('\ue92b'),
        MBRI_EXTENSION('\ue92c'),
        MBRI_FEATURES('\ue92d'),
        MBRI_FILE('\ue92e'),
        MBRI_FLAG('\ue92f'),
        MBRI_FOLDER('\ue930'),
        MBRI_GIFT('\ue931'),
        MBRI_GITHUB('\ue932'),
        MBRI_GLOBE_2('\ue933'),
        MBRI_GLOBE('\ue934'),
        MBRI_GROWING_CHART('\ue935'),
        MBRI_HEARTH('\ue936'),
        MBRI_HELP('\ue937'),
        MBRI_HOME('\ue938'),
        MBRI_HOT_CUP('\ue939'),
        MBRI_IDEA('\ue93a'),
        MBRI_IMAGE_GALLERY('\ue93b'),
        MBRI_IMAGE_SLIDER('\ue93c'),
        MBRI_INFO('\ue93d'),
        MBRI_ITALIC('\ue93e'),
        MBRI_KEY('\ue93f'),
        MBRI_LAPTOP('\ue940'),
        MBRI_LAYERS('\ue941'),
        MBRI_LEFT_RIGHT('\ue942'),
        MBRI_LEFT('\ue943'),
        MBRI_LETTER('\ue944'),
        MBRI_LIKE('\ue945'),
        MBRI_LINK('\ue946'),
        MBRI_LOCK('\ue947'),
        MBRI_LOGIN('\ue948'),
        MBRI_LOGOUT('\ue949'),
        MBRI_MAGIC_STICK('\ue94a'),
        MBRI_MAP_PIN('\ue94b'),
        MBRI_MENU('\ue94c'),
        MBRI_MOBILE('\ue94d'),
        MBRI_MOBILE2('\ue94e'),
        MBRI_MOBIRISE('\ue94f'),
        MBRI_MORE_HORIZONTAL('\ue950'),
        MBRI_MORE_VERTICAL('\ue951'),
        MBRI_MUSIC('\ue952'),
        MBRI_NEW_FILE('\ue953'),
        MBRI_NUMBERED_LIST('\ue954'),
        MBRI_OPENED_FOLDER('\ue955'),
        MBRI_PAGES('\ue956'),
        MBRI_PAPER_PLANE('\ue957'),
        MBRI_PAPERCLIP('\ue958'),
        MBRI_PHOTO('\ue959'),
        MBRI_PHOTOS('\ue95a'),
        MBRI_PIN('\ue95b'),
        MBRI_PLAY('\ue95c'),
        MBRI_PLUS('\ue95d'),
        MBRI_PREVIEW('\ue95e'),
        MBRI_PRINT('\ue95f'),
        MBRI_PROTECT('\ue960'),
        MBRI_QUESTION('\ue961'),
        MBRI_QUOTE_LEFT('\ue962'),
        MBRI_QUOTE_RIGHT('\ue963'),
        MBRI_REDO('\ue964'),
        MBRI_REFRESH('\ue965'),
        MBRI_RESPONSIVE('\ue966'),
        MBRI_RIGHT('\ue967'),
        MBRI_ROCKET('\ue968'),
        MBRI_SAD_FACE('\ue969'),
        MBRI_SALE('\ue96a'),
        MBRI_SAVE('\ue96b'),
        MBRI_SEARCH('\ue96c'),
        MBRI_SETTING('\ue96d'),
        MBRI_SETTING2('\ue96e'),
        MBRI_SETTING3('\ue96f'),
        MBRI_SHARE('\ue970'),
        MBRI_SHOPPING_BAG('\ue971'),
        MBRI_SHOPPING_BASKET('\ue972'),
        MBRI_SHOPPING_CART('\ue973'),
        MBRI_SITES('\ue974'),
        MBRI_SMILE_FACE('\ue975'),
        MBRI_SPEED('\ue976'),
        MBRI_STAR('\ue977'),
        MBRI_SUCCESS('\ue978'),
        MBRI_SUN('\ue979'),
        MBRI_SUN2('\ue97a'),
        MBRI_TABLET_VERTICAL('\ue97b'),
        MBRI_TABLET('\ue97c'),
        MBRI_TARGET('\ue97d'),
        MBRI_TIMER('\ue97e'),
        MBRI_TO_FTP('\ue97f'),
        MBRI_TO_LOCAL_DRIVE('\ue980'),
        MBRI_TOUCH_SWIPE('\ue981'),
        MBRI_TOUCH('\ue982'),
        MBRI_TRASH('\ue983'),
        MBRI_UNDERLINE('\ue984'),
        MBRI_UNDO('\ue985'),
        MBRI_UNLINK('\ue986'),
        MBRI_UNLOCK('\ue987'),
        MBRI_UP_DOWN('\ue988'),
        MBRI_UP('\ue989'),
        MBRI_UPDATE('\ue98a'),
        MBRI_UPLOAD('\ue98b'),
        MBRI_USER('\ue98c'),
        MBRI_USER2('\ue98d'),
        MBRI_USERS('\ue98e'),
        MBRI_VIDEO_PLAY('\ue98f'),
        MBRI_VIDEO('\ue990'),
        MBRI_WATCH('\ue991'),
        MBRI_WEBSITE_THEME('\ue992'),
        MBRI_WIFI('\ue993'),
        MBRI_WINDOWS('\ue994'),
        MBRI_ZOOM_OUT('\ue995');

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
                typeface = new MobiriseIcons();
            }
            return typeface;
        }
    }
}
