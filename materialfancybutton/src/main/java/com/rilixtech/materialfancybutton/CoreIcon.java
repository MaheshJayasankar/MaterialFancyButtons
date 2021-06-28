package com.rilixtech.materialfancybutton;

import com.rilixtech.materialfancybutton.typeface.IIcon;
import com.rilixtech.materialfancybutton.typeface.ITypeface;
import com.rilixtech.materialfancybutton.utils.GenericsUtil;
import ohos.app.Context;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.util.Collection;
import java.util.HashMap;

public final class CoreIcon {

  private static final String TAG = MaterialFancyButton.class.getSimpleName();
  private static final int DOMAIN = 0xD000100;
  private static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, DOMAIN, TAG);

  private static boolean INIT_DONE = false;
  private static HashMap<String, ITypeface> FONTS = new HashMap<>();



  public static final int FONT_MAPPING_PREFIX = 4;

  /**
   * initializes the FONTS. This also tries to find all founds automatically via their font file
   */
  public static void init(Context ctx) {
    if (!INIT_DONE) {
      String[] fonts = GenericsUtil.getFields(ctx);
      for (String fontsClassPath : fonts) {
        try {
          ITypeface typeface = (ITypeface) Class.forName(fontsClassPath).newInstance();
          validateFont(typeface);
          FONTS.put(typeface.getMappingPrefix(), typeface);
          HiLog.debug(LABEL, "Typeface = %{public}s", typeface.getAuthor());
        }
        catch (ClassNotFoundException e){
          HiLog.debug(LABEL, "%{public}s not found.", fontsClassPath);
        }
        catch (Exception e) {
          HiLog.debug(LABEL, "Can't init: %{public}s", fontsClassPath);
        }
      }
      HiLog.debug(LABEL, "Total font = %{public}d", FONTS.size());
      INIT_DONE = true;
    }
  }

  /**
   * this makes sure the FONTS are initialized. If the given fonts Map is empty we set the
   * initialized FONTS on it
   */
  private static HashMap<String, ITypeface> init(Context ctx, HashMap<String, ITypeface> fonts) {
    init(ctx);
    if (fonts == null || fonts.size() == 0) {
      fonts = FONTS;
    }
    return fonts;
  }

  /**
   * Test if the icon exists in the currently loaded fonts
   *
   * @param context A context to access application resources
   * @param icon The icon to verify
   * @return true if the icon is available
   */
  public static boolean iconExists(Context context, String icon) {
    try {
      ITypeface font = findFont(context, icon.substring(0, FONT_MAPPING_PREFIX));
      icon = icon.replace("-", "_");
      font.getIcon(icon);
      return true;
    } catch (Exception ignore) {
    }
    return false;
  }

  /**
   * Registers a fonts into the FONTS array for performance
   */
  public static boolean registerFont(ITypeface font) {
    validateFont(font);

    FONTS.put(font.getMappingPrefix(), font);
    return true;
  }

  /**
   * Perform a basic sanity check for a font.
   */
  private static void validateFont(ITypeface font) {
    if (font.getMappingPrefix().length() != FONT_MAPPING_PREFIX) {
      throw new IllegalArgumentException(
          "The mapping prefix of a font must be four characters long.");
    }
  }

  /**
   * return all registered FONTS
   */
  public static Collection<ITypeface> getRegisteredFonts(Context ctx) {
    init(ctx);
    return FONTS.values();
  }

  /**
   * tries to find a font by its key in all registered FONTS
   */
  public static ITypeface findFont(Context ctx, String key) {
    init(ctx);
    return FONTS.get(key);
  }

  /**
   * fetches the font from the Typeface of an IIcon
   */
  public static ITypeface findFont(IIcon icon) {
    return icon.getTypeface();
  }

  private CoreIcon() {
    // Prevent instantiation
  }
}
