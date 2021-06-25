package com.applibgroup.materialfancybutton.utils;

import ohos.agp.components.AttrHelper;
import ohos.agp.text.Font;
import ohos.app.Context;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FontUtil {

  private static final String TAG = FontUtil.class.getSimpleName();
  private static final int DOMAIN = 0xD000100;
  private static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, DOMAIN, TAG);

  private static Map<String, Font> cachedFontMap = new HashMap<>();

  public static int pxToSp(final Context context, final float px) {
    // TODO: potential mapping AttrHelper.getFontRatio? Check with Android
    // return Math.round(px / context.getResources().getDisplayMetrics().scaledDensity);
    return Math.round(px / AttrHelper.getFontRatio(context));
  }

  public static int spToPx(final Context context, final float sp) {
    // TODO: potential mapping AttrHelper.getFontRatio? Check with Android
    // return Math.round(sp * context.getResources().getDisplayMetrics().scaledDensity);
    return Math.round(sp * AttrHelper.getFontRatio(context));
  }

  public static Font findFont(Context context, String fontPath, String defaultFontPath) {

    if (fontPath == null) {
      return Font.DEFAULT;
    }

    String fontName = new File(fontPath).getName();
    String defaultFontName = "";
    if (!TextUtils.isEmpty(defaultFontPath)) {
      defaultFontName = new File(defaultFontPath).getName();
    }

    if (cachedFontMap.containsKey(fontName)) {
      return cachedFontMap.get(fontName);
    } else {
      try {
        // TODO Find mapping for AssetManager
        AssetManager assets = context.getResources().getAssets();

        if (Arrays.asList(assets.list("")).contains(fontPath)) {
          Font typeface = Font.createFromAsset(context.getAssets(), fontName);
          cachedFontMap.put(fontName, typeface);
          return typeface;
        } else if (Arrays.asList(assets.list("fonts")).contains(fontName)) {
          Font typeface =
                  Font.createFromAsset(context.getAssets(), String.format("fonts/%s", fontName));
          cachedFontMap.put(fontName, typeface);
          return typeface;
        } else if (Arrays.asList(assets.list("iconfonts")).contains(fontName)) {
          Font typeface = Font.createFromAsset(context.getAssets(),
              String.format("iconfonts/%s", fontName));
          cachedFontMap.put(fontName, typeface);
          return typeface;
        } else if (!TextUtils.isEmpty(defaultFontPath) && Arrays.asList(assets.list(""))
            .contains(defaultFontPath)) {
          Font typeface = Font.createFromAsset(context.getAssets(), defaultFontPath);
          cachedFontMap.put(defaultFontName, typeface);
          return typeface;
        } else {
          throw new Exception("Font not Found");
        }
      } catch (Exception e) {
        HiLog.error(LABEL,
            "Unable to find %{public}s font. Using Typeface.DEFAULT instead.", fontName);
        cachedFontMap.put(fontName, Font.DEFAULT);
        return Font.DEFAULT;
      }
    }
  }

  }

}