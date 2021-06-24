package com.applibgroup.materialfancybutton.utils;

import ohos.agp.text.Font;
import ohos.app.Context;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FontUtil {

  private static final String TAG = FontUtil.class.getSimpleName();

  private static Map<String, Font> cachedFontMap = new HashMap<>();

  public static int pxToSp(final Context context, final float px) {
    // TODO: potential mapping AttrHelper.getFontRatio? Check with Android
    return Math.round(px / context.getResources().getDisplayMetrics().scaledDensity);
  }

  public static int spToPx(final Context context, final float sp) {
    return Math.round(sp * context.getResources().getDisplayMetrics().scaledDensity);
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
        Log.e(TAG,
            String.format("Unable to find %s font. Using Typeface.DEFAULT instead.", fontName));
        cachedFontMap.put(fontName, Font.DEFAULT);
        return Font.DEFAULT;
      }
    }
  }
}