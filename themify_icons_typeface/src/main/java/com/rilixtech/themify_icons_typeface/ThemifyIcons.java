package com.rilixtech.themify_icons_typeface;

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

public class ThemifyIcons implements ITypeface {
    private static final String TTF_FILE = "themify-icons-v0.1.2.ttf";
    private static final String MAPPING_FONT_PREFIX = "THEI";

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
        return "Themify Icons";
    }

    @Override public String getVersion() {
        return "0.1.2";
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
        return "Lally Elias";
    }

    @Override public String getUrl() {
        return "http://themify.me/themify-icons";
    }

    @Override public String getDescription() {
        return "Themify Icons is a complete set of icons for use in web design and apps, consisting of 320+ pixel-perfect, hand-crafted icons that draw inspiration from Apple iOS 7.";
    }

    @Override public String getLicense() {
        return "SIL Open Font License (OFL)";
    }

    @Override public String getLicenseUrl() {
        return "http://scripts.sil.org/OFL";
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
        THEI_WAND('\ue600'),
        THEI_VOLUME('\ue601'),
        THEI_USER('\ue602'),
        THEI_UNLOCK('\ue603'),
        THEI_UNLINK('\ue604'),
        THEI_TRASH('\ue605'),
        THEI_THOUGHT('\ue606'),
        THEI_TARGET('\ue607'),
        THEI_TAG('\ue608'),
        THEI_TABLET('\ue609'),
        THEI_STAR('\ue60a'),
        THEI_SPRAY('\ue60b'),
        THEI_SIGNAL('\ue60c'),
        THEI_SHOPPING_CART('\ue60d'),
        THEI_SHOPPING_CART_FULL('\ue60e'),
        THEI_SETTINGS('\ue60f'),
        THEI_SEARCH('\ue610'),
        THEI_ZOOM_IN('\ue611'),
        THEI_ZOOM_OUT('\ue612'),
        THEI_CUT('\ue613'),
        THEI_RULER('\ue614'),
        THEI_RULER_PENCIL('\ue615'),
        THEI_RULER_ALT('\ue616'),
        THEI_BOOKMARK('\ue617'),
        THEI_BOOKMARK_ALT('\ue618'),
        THEI_RELOAD('\ue619'),
        THEI_PLUS('\ue61a'),
        THEI_PIN('\ue61b'),
        THEI_PENCIL('\ue61c'),
        THEI_PENCIL_ALT('\ue61d'),
        THEI_PAINT_ROLLER('\ue61e'),
        THEI_PAINT_BUCKET('\ue61f'),
        THEI_NA('\ue620'),
        THEI_MOBILE('\ue621'),
        THEI_MINUS('\ue622'),
        THEI_MEDALL('\ue623'),
        THEI_MEDALL_ALT('\ue624'),
        THEI_MARKER('\ue625'),
        THEI_MARKER_ALT('\ue626'),
        THEI_ARROW_UP('\ue627'),
        THEI_ARROW_RIGHT('\ue628'),
        THEI_ARROW_LEFT('\ue629'),
        THEI_ARROW_DOWN('\ue62a'),
        THEI_LOCK('\ue62b'),
        THEI_LOCATION_ARROW('\ue62c'),
        THEI_LINK('\ue62d'),
        THEI_LAYOUT('\ue62e'),
        THEI_LAYERS('\ue62f'),
        THEI_LAYERS_ALT('\ue630'),
        THEI_KEY('\ue631'),
        THEI_IMPORT('\ue632'),
        THEI_IMAGE('\ue633'),
        THEI_HEART('\ue634'),
        THEI_HEART_BROKEN('\ue635'),
        THEI_HAND_STOP('\ue636'),
        THEI_HAND_OPEN('\ue637'),
        THEI_HAND_DRAG('\ue638'),
        THEI_FOLDER('\ue639'),
        THEI_FLAG('\ue63a'),
        THEI_FLAG_ALT('\ue63b'),
        THEI_FLAG_ALT_2('\ue63c'),
        THEI_EYE('\ue63d'),
        THEI_EXPORT('\ue63e'),
        THEI_EXCHANGE_VERTICAL('\ue63f'),
        THEI_DESKTOP('\ue640'),
        THEI_CUP('\ue641'),
        THEI_CROWN('\ue642'),
        THEI_COMMENTS('\ue643'),
        THEI_COMMENT('\ue644'),
        THEI_COMMENT_ALT('\ue645'),
        THEI_CLOSE('\ue646'),
        THEI_CLIP('\ue647'),
        THEI_ANGLE_UP('\ue648'),
        THEI_ANGLE_RIGHT('\ue649'),
        THEI_ANGLE_LEFT('\ue64a'),
        THEI_ANGLE_DOWN('\ue64b'),
        THEI_CHECK('\ue64c'),
        THEI_CHECK_BOX('\ue64d'),
        THEI_CAMERA('\ue64e'),
        THEI_ANNOUNCEMENT('\ue64f'),
        THEI_BRUSH('\ue650'),
        THEI_BRIEFCASE('\ue651'),
        THEI_BOLT('\ue652'),
        THEI_BOLT_ALT('\ue653'),
        THEI_BLACKBOARD('\ue654'),
        THEI_BAG('\ue655'),
        THEI_MOVE('\ue656'),
        THEI_ARROWS_VERTICAL('\ue657'),
        THEI_ARROWS_HORIZONTAL('\ue658'),
        THEI_FULLSCREEN('\ue659'),
        THEI_ARROW_TOP_RIGHT('\ue65a'),
        THEI_ARROW_TOP_LEFT('\ue65b'),
        THEI_ARROW_CIRCLE_UP('\ue65c'),
        THEI_ARROW_CIRCLE_RIGHT('\ue65d'),
        THEI_ARROW_CIRCLE_LEFT('\ue65e'),
        THEI_ARROW_CIRCLE_DOWN('\ue65f'),
        THEI_ANGLE_DOUBLE_UP('\ue660'),
        THEI_ANGLE_DOUBLE_RIGHT('\ue661'),
        THEI_ANGLE_DOUBLE_LEFT('\ue662'),
        THEI_ANGLE_DOUBLE_DOWN('\ue663'),
        THEI_ZIP('\ue664'),
        THEI_WORLD('\ue665'),
        THEI_WHEELCHAIR('\ue666'),
        THEI_VIEW_LIST('\ue667'),
        THEI_VIEW_LIST_ALT('\ue668'),
        THEI_VIEW_GRID('\ue669'),
        THEI_UPPERCASE('\ue66a'),
        THEI_UPLOAD('\ue66b'),
        THEI_UNDERLINE('\ue66c'),
        THEI_TRUCK('\ue66d'),
        THEI_TIMER('\ue66e'),
        THEI_TICKET('\ue66f'),
        THEI_THUMB_UP('\ue670'),
        THEI_THUMB_DOWN('\ue671'),
        THEI_TEXT('\ue672'),
        THEI_STATS_UP('\ue673'),
        THEI_STATS_DOWN('\ue674'),
        THEI_SPLIT_V('\ue675'),
        THEI_SPLIT_H('\ue676'),
        THEI_SMALLCAP('\ue677'),
        THEI_SHINE('\ue678'),
        THEI_SHIFT_RIGHT('\ue679'),
        THEI_SHIFT_LEFT('\ue67a'),
        THEI_SHIELD('\ue67b'),
        THEI_NOTEPAD('\ue67c'),
        THEI_SERVER('\ue67d'),
        THEI_QUOTE_RIGHT('\ue67e'),
        THEI_QUOTE_LEFT('\ue67f'),
        THEI_PULSE('\ue680'),
        THEI_PRINTER('\ue681'),
        THEI_POWER_OFF('\ue682'),
        THEI_PLUG('\ue683'),
        THEI_PIE_CHART('\ue684'),
        THEI_PARAGRAPH('\ue685'),
        THEI_PANEL('\ue686'),
        THEI_PACKAGE('\ue687'),
        THEI_MUSIC('\ue688'),
        THEI_MUSIC_ALT('\ue689'),
        THEI_MOUSE('\ue68a'),
        THEI_MOUSE_ALT('\ue68b'),
        THEI_MONEY('\ue68c'),
        THEI_MICROPHONE('\ue68d'),
        THEI_MENU('\ue68e'),
        THEI_MENU_ALT('\ue68f'),
        THEI_MAP('\ue690'),
        THEI_MAP_ALT('\ue691'),
        THEI_LOOP('\ue692'),
        THEI_LOCATION_PIN('\ue693'),
        THEI_LIST('\ue694'),
        THEI_LIGHT_BULB('\ue695'),
        THEI_ITALIC('\ue696'),
        THEI_INFO('\ue697'),
        THEI_INFINITE('\ue698'),
        THEI_ID_BADGE('\ue699'),
        THEI_HUMMER('\ue69a'),
        THEI_HOME('\ue69b'),
        THEI_HELP('\ue69c'),
        THEI_HEADPHONE('\ue69d'),
        THEI_HARDDRIVES('\ue69e'),
        THEI_HARDDRIVE('\ue69f'),
        THEI_GIFT('\ue6a0'),
        THEI_GAME('\ue6a1'),
        THEI_FILTER('\ue6a2'),
        THEI_FILES('\ue6a3'),
        THEI_FILE('\ue6a4'),
        THEI_ERASER('\ue6a5'),
        THEI_ENVELOPE('\ue6a6'),
        THEI_DOWNLOAD('\ue6a7'),
        THEI_DIRECTION('\ue6a8'),
        THEI_DIRECTION_ALT('\ue6a9'),
        THEI_DASHBOARD('\ue6aa'),
        THEI_CONTROL_STOP('\ue6ab'),
        THEI_CONTROL_SHUFFLE('\ue6ac'),
        THEI_CONTROL_PLAY('\ue6ad'),
        THEI_CONTROL_PAUSE('\ue6ae'),
        THEI_CONTROL_FORWARD('\ue6af'),
        THEI_CONTROL_BACKWARD('\ue6b0'),
        THEI_CLOUD('\ue6b1'),
        THEI_CLOUD_UP('\ue6b2'),
        THEI_CLOUD_DOWN('\ue6b3'),
        THEI_CLIPBOARD('\ue6b4'),
        THEI_CAR('\ue6b5'),
        THEI_CALENDAR('\ue6b6'),
        THEI_BOOK('\ue6b7'),
        THEI_BELL('\ue6b8'),
        THEI_BASKETBALL('\ue6b9'),
        THEI_BAR_CHART('\ue6ba'),
        THEI_BAR_CHART_ALT('\ue6bb'),
        THEI_BACK_RIGHT('\ue6bc'),
        THEI_BACK_LEFT('\ue6bd'),
        THEI_ARROWS_CORNER('\ue6be'),
        THEI_ARCHIVE('\ue6bf'),
        THEI_ANCHOR('\ue6c0'),
        THEI_ALIGN_RIGHT('\ue6c1'),
        THEI_ALIGN_LEFT('\ue6c2'),
        THEI_ALIGN_JUSTIFY('\ue6c3'),
        THEI_ALIGN_CENTER('\ue6c4'),
        THEI_ALERT('\ue6c5'),
        THEI_ALARM_CLOCK('\ue6c6'),
        THEI_AGENDA('\ue6c7'),
        THEI_WRITE('\ue6c8'),
        THEI_WINDOW('\ue6c9'),
        THEI_WIDGETIZED('\ue6ca'),
        THEI_WIDGET('\ue6cb'),
        THEI_WIDGET_ALT('\ue6cc'),
        THEI_WALLET('\ue6cd'),
        THEI_VIDEO_CLAPPER('\ue6ce'),
        THEI_VIDEO_CAMERA('\ue6cf'),
        THEI_VECTOR('\ue6d0'),
        THEI_THEMIFY_LOGO('\ue6d1'),
        THEI_THEMIFY_FAVICON('\ue6d2'),
        THEI_THEMIFY_FAVICON_ALT('\ue6d3'),
        THEI_SUPPORT('\ue6d4'),
        THEI_STAMP('\ue6d5'),
        THEI_SPLIT_V_ALT('\ue6d6'),
        THEI_SLICE('\ue6d7'),
        THEI_SHORTCODE('\ue6d8'),
        THEI_SHIFT_RIGHT_ALT('\ue6d9'),
        THEI_SHIFT_LEFT_ALT('\ue6da'),
        THEI_RULER_ALT_2('\ue6db'),
        THEI_RECEIPT('\ue6dc'),
        THEI_PIN2('\ue6dd'),
        THEI_PIN_ALT('\ue6de'),
        THEI_PENCIL_ALT2('\ue6df'),
        THEI_PALETTE('\ue6e0'),
        THEI_MORE('\ue6e1'),
        THEI_MORE_ALT('\ue6e2'),
        THEI_MICROPHONE_ALT('\ue6e3'),
        THEI_MAGNET('\ue6e4'),
        THEI_LINE_DOUBLE('\ue6e5'),
        THEI_LINE_DOTTED('\ue6e6'),
        THEI_LINE_DASHED('\ue6e7'),
        THEI_LAYOUT_WIDTH_FULL('\ue6e8'),
        THEI_LAYOUT_WIDTH_DEFAULT('\ue6e9'),
        THEI_LAYOUT_WIDTH_DEFAULT_ALT('\ue6ea'),
        THEI_LAYOUT_TAB('\ue6eb'),
        THEI_LAYOUT_TAB_WINDOW('\ue6ec'),
        THEI_LAYOUT_TAB_V('\ue6ed'),
        THEI_LAYOUT_TAB_MIN('\ue6ee'),
        THEI_LAYOUT_SLIDER('\ue6ef'),
        THEI_LAYOUT_SLIDER_ALT('\ue6f0'),
        THEI_LAYOUT_SIDEBAR_RIGHT('\ue6f1'),
        THEI_LAYOUT_SIDEBAR_NONE('\ue6f2'),
        THEI_LAYOUT_SIDEBAR_LEFT('\ue6f3'),
        THEI_LAYOUT_PLACEHOLDER('\ue6f4'),
        THEI_LAYOUT_MENU('\ue6f5'),
        THEI_LAYOUT_MENU_V('\ue6f6'),
        THEI_LAYOUT_MENU_SEPARATED('\ue6f7'),
        THEI_LAYOUT_MENU_FULL('\ue6f8'),
        THEI_LAYOUT_MEDIA_RIGHT_ALT('\ue6f9'),
        THEI_LAYOUT_MEDIA_RIGHT('\ue6fa'),
        THEI_LAYOUT_MEDIA_OVERLAY('\ue6fb'),
        THEI_LAYOUT_MEDIA_OVERLAY_ALT('\ue6fc'),
        THEI_LAYOUT_MEDIA_OVERLAY_ALT_2('\ue6fd'),
        THEI_LAYOUT_MEDIA_LEFT_ALT('\ue6fe'),
        THEI_LAYOUT_MEDIA_LEFT('\ue6ff'),
        THEI_LAYOUT_MEDIA_CENTER_ALT('\ue700'),
        THEI_LAYOUT_MEDIA_CENTER('\ue701'),
        THEI_LAYOUT_LIST_THUMB('\ue702'),
        THEI_LAYOUT_LIST_THUMB_ALT('\ue703'),
        THEI_LAYOUT_LIST_POST('\ue704'),
        THEI_LAYOUT_LIST_LARGE_IMAGE('\ue705'),
        THEI_LAYOUT_LINE_SOLID('\ue706'),
        THEI_LAYOUT_GRID4('\ue707'),
        THEI_LAYOUT_GRID3('\ue708'),
        THEI_LAYOUT_GRID2('\ue709'),
        THEI_LAYOUT_GRID2_THUMB('\ue70a'),
        THEI_LAYOUT_CTA_RIGHT('\ue70b'),
        THEI_LAYOUT_CTA_LEFT('\ue70c'),
        THEI_LAYOUT_CTA_CENTER('\ue70d'),
        THEI_LAYOUT_CTA_BTN_RIGHT('\ue70e'),
        THEI_LAYOUT_CTA_BTN_LEFT('\ue70f'),
        THEI_LAYOUT_COLUMN4('\ue710'),
        THEI_LAYOUT_COLUMN3('\ue711'),
        THEI_LAYOUT_COLUMN2('\ue712'),
        THEI_LAYOUT_ACCORDION_SEPARATED('\ue713'),
        THEI_LAYOUT_ACCORDION_MERGED('\ue714'),
        THEI_LAYOUT_ACCORDION_LIST('\ue715'),
        THEI_INK_PEN('\ue716'),
        THEI_INFO_ALT('\ue717'),
        THEI_HELP_ALT('\ue718'),
        THEI_HEADPHONE_ALT('\ue719'),
        THEI_HAND_POINT_UP('\ue71a'),
        THEI_HAND_POINT_RIGHT('\ue71b'),
        THEI_HAND_POINT_LEFT('\ue71c'),
        THEI_HAND_POINT_DOWN('\ue71d'),
        THEI_GALLERY('\ue71e'),
        THEI_FACE_SMILE('\ue71f'),
        THEI_FACE_SAD('\ue720'),
        THEI_CREDIT_CARD('\ue721'),
        THEI_CONTROL_SKIP_FORWARD('\ue722'),
        THEI_CONTROL_SKIP_BACKWARD('\ue723'),
        THEI_CONTROL_RECORD('\ue724'),
        THEI_CONTROL_EJECT('\ue725'),
        THEI_COMMENTS_SMILEY('\ue726'),
        THEI_BRUSH_ALT('\ue727'),
        THEI_YOUTUBE('\ue728'),
        THEI_VIMEO('\ue729'),
        THEI_TWITTER('\ue72a'),
        THEI_TIME('\ue72b'),
        THEI_TUMBLR('\ue72c'),
        THEI_SKYPE('\ue72d'),
        THEI_SHARE('\ue72e'),
        THEI_SHARE_ALT('\ue72f'),
        THEI_ROCKET('\ue730'),
        THEI_PINTEREST('\ue731'),
        THEI_NEW_WINDOW('\ue732'),
        THEI_MICROSOFT('\ue733'),
        THEI_LIST_OL('\ue734'),
        THEI_LINKEDIN('\ue735'),
        THEI_LAYOUT_SIDEBAR_2('\ue736'),
        THEI_LAYOUT_GRID4_ALT('\ue737'),
        THEI_LAYOUT_GRID3_ALT('\ue738'),
        THEI_LAYOUT_GRID2_ALT('\ue739'),
        THEI_LAYOUT_COLUMN4_ALT('\ue73a'),
        THEI_LAYOUT_COLUMN3_ALT('\ue73b'),
        THEI_LAYOUT_COLUMN2_ALT('\ue73c'),
        THEI_INSTAGRAM('\ue73d'),
        THEI_GOOGLE('\ue73e'),
        THEI_GITHUB('\ue73f'),
        THEI_FLICKR('\ue740'),
        THEI_FACEBOOK('\ue741'),
        THEI_DROPBOX('\ue742'),
        THEI_DRIBBBLE('\ue743'),
        THEI_APPLE('\ue744'),
        THEI_ANDROID('\ue745'),
        THEI_SAVE('\ue746'),
        THEI_SAVE_ALT('\ue747'),
        THEI_YAHOO('\ue748'),
        THEI_WORDPRESS('\ue749'),
        THEI_VIMEO_ALT('\ue74a'),
        THEI_TWITTER_ALT('\ue74b'),
        THEI_TUMBLR_ALT('\ue74c'),
        THEI_TRELLO('\ue74d'),
        THEI_STACK_OVERFLOW('\ue74e'),
        THEI_SOUNDCLOUD('\ue74f'),
        THEI_SHARETHIS('\ue750'),
        THEI_SHARETHIS_ALT('\ue751'),
        THEI_REDDIT('\ue752'),
        THEI_PINTEREST_ALT('\ue753'),
        THEI_MICROSOFT_ALT('\ue754'),
        THEI_LINUX('\ue755'),
        THEI_JSFIDDLE('\ue756'),
        THEI_JOOMLA('\ue757'),
        THEI_HTML5('\ue758'),
        THEI_FLICKR_ALT('\ue759'),
        THEI_EMAIL('\ue75a'),
        THEI_DRUPAL('\ue75b'),
        THEI_DROPBOX_ALT('\ue75c'),
        THEI_CSS3('\ue75d'),
        THEI_RSS('\ue75e'),
        THEI_RSS_ALT('\ue75f');

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
                typeface = new ThemifyIcons();
            }
            return typeface;
        }
    }
}
