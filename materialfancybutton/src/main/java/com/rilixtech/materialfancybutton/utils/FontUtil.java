package com.rilixtech.materialfancybutton.utils;

import ohos.agp.components.AttrHelper;
import ohos.agp.text.Font;
import ohos.app.Context;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FontUtil {

  private static final String TAG = FontUtil.class.getSimpleName();
  private static final int DOMAIN = 0xD000100;
  private static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, DOMAIN, TAG);

  private static final String LIBRARY_NAME = "materialfancybutton";

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
  public static Font findFont(Context context, String fontPath, String defaultFontPath) {

    if (fontPath == null) {
      return Font.DEFAULT;
    }

    File fontPathFile = new File(fontPath);
    String fontName = fontPathFile.getName();
    String fontParentDir = fontPathFile.getParent();
    String defaultFontName = "";
    if (!TextUtils.isEmpty(defaultFontPath)) {
      defaultFontName = new File(defaultFontPath).getName();
    }

    if (cachedFontMap.containsKey(fontName)) {
      return cachedFontMap.get(fontName);
    } else {

      // default font directory for the font
      final String defaultParentDir = String.format("%s/resources/rawfile/", LIBRARY_NAME);
      try {
        Font loadedFont = getFont(context, fontName, fontParentDir);
        if (loadedFont != null) {
          cachedFontMap.put(fontName, loadedFont);
          return loadedFont;
        } else {
        loadedFont = getFont(context, fontName, defaultParentDir);
        if (loadedFont != null)
        {
          cachedFontMap.put(fontName, loadedFont);
          return loadedFont;
        } else {
        loadedFont = getFont(context, defaultFontName, defaultFontPath);
        if (loadedFont != null)
        {
          cachedFontMap.put(fontName, loadedFont);
          return loadedFont;
        }
        else {
          throw new FileNotFoundException();
        }}}
      } catch (Exception e) {
        HiLog.error(LABEL,
            "Unable to find %{public}s font. Using Font.DEFAULT instead.", fontName);
        HiLog.error(LABEL,
                "Searched in %{public}s, %{public}s, %{public}s", fontParentDir, defaultParentDir, defaultFontPath);

        cachedFontMap.put(fontName, Font.DEFAULT);
        return Font.DEFAULT;
      }
    }
  }

  private static Font getFont(Context context, String fontId, String parentDirectory) {
    final int BUFFER_LENGTH = 8192;
    final int DEFAULT_ERROR = -1;
    File fontFile = new File(parentDirectory, fontId);
    String path = fontFile.getPath();
    HiLog.debug(LABEL, "Font exist at %{public}s? %{public}s", path, fontFile.exists());
    File outputFile = new File(context.getDataDir(), fontId);
    try (OutputStream outputStream = new FileOutputStream(outputFile);
         InputStream inputStream = context.getResourceManager().getRawFileEntry(path).openRawFile()) {
      byte[] buffer = new byte[BUFFER_LENGTH];
      int bytesRead = inputStream.read(buffer, 0, BUFFER_LENGTH);
      while (bytesRead != DEFAULT_ERROR) {
        outputStream.write(buffer, 0, bytesRead);
        bytesRead = inputStream.read(buffer, 0, BUFFER_LENGTH);
      }
    } catch (IOException exception) {
      return null;
    }
    return Optional.of(new Font.Builder(outputFile).build()).get();
  }
}