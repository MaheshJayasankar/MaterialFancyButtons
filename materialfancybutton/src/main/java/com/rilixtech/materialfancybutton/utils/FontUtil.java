package com.rilixtech.materialfancybutton.utils;

import ohos.agp.components.AttrHelper;
import ohos.agp.text.Font;
import ohos.app.AbilityContext;
import ohos.app.Context;
import ohos.global.resource.RawFileDescriptor;
import ohos.global.resource.RawFileEntry;
import ohos.global.resource.Resource;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FontUtil {

  private static final String TAG = FontUtil.class.getSimpleName();
  private static final int DOMAIN = 0xD000100;
  private static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, DOMAIN, TAG);

  private static Map<String, Font> cachedFontMap = new HashMap<>();

  public static int pxToSp(final Context context, final float px) {
    // TODO: Mapping not confirmed. Need to test
    // return Math.round(px / context.getResources().getDisplayMetrics().scaledDensity);
    return Math.round(px / AttrHelper.getFontRatio(context));
  }

  public static int spToPx(final Context context, final float sp) {
    // TODO: Mapping not confirmed. Need to test
    // return Math.round(sp * context.getResources().getDisplayMetrics().scaledDensity);
    return Math.round(sp * AttrHelper.getFontRatio(context));
  }

  // TODO Workaround to AssetManager. Put fonts in materialfancybutton/resources/rawfile. Test if working
  public static Font findFont(AbilityContext context, String fontName, String defaultFontName) {

    Font typeface = null;
    if (!TextUtils.isEmpty(fontName)) {
      // Try to load the font from the rawfile directory
      typeface = loadFontIfExists(context, fontName);
    }
    // If Font couldn't be loaded or wasn't specified, fallback to using the default font specified
    if (typeface == null) {
      if (!TextUtils.isEmpty(defaultFontName)) {
        // Try to load the default font form the rawfile directory
        typeface = loadFontIfExists(context, defaultFontName);
        if (typeface == null) {
          // If both font and default font are not able to be loaded, load the system Default font
          typeface = Font.DEFAULT;
          updateCacheIfNotEmpty(defaultFontName, typeface);
        }
        updateCacheIfNotEmpty(fontName, typeface);
      } else {
        // If Font couldn't be loaded, and default font wasn't specified, load the system Default font
        typeface = Font.DEFAULT;
        updateCacheIfNotEmpty(fontName, typeface);
      }
    }
    return  typeface;
  }

  private static void updateCacheIfNotEmpty(String key, Font font) {
    if (!TextUtils.isEmpty(key)) {
      cachedFontMap.put(key, font);
    }
  }

  private static Font loadFontIfExists(AbilityContext context, String fontName) {
    Font typeface;
    if (!cachedFontMap.containsKey(fontName)) {
      try {
        typeface = getFontFromRawFile(context, fontName);
        cachedFontMap.put(fontName, typeface);
      } catch (IllegalStateException e) {
        // File read failed. Return null
        typeface = null;
      }
    }
    else {
      typeface = cachedFontMap.get(fontName);
    }
    return typeface;
  }

  private static Font getFontFromRawFile(AbilityContext context, String fontName) throws IllegalStateException  {
    Font typeface;
    RawFileEntry rawFileEntry = context.getResourceManager()
            .getRawFileEntry("resources/rawfile/" + fontName);
    File file = getFileFromRawFile(context, rawFileEntry, "file_" + fontName);
    Font.Builder newTypeface = new Font.Builder(file);
    typeface = newTypeface.build();
    return typeface;
  }

  private static File getFileFromRawFile(AbilityContext ctx, RawFileEntry rawFileEntry, String filename) throws IllegalStateException {
    byte[] buf = null;
    try (Resource resource = rawFileEntry.openRawFile();
         RawFileDescriptor rawFileDescriptor = rawFileEntry.openRawFileDescriptor();) {
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
}