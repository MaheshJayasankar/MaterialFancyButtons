package com.rilixtech.materialfancybutton.utils;


import ohos.app.Context;

public class GenericsUtil {

  public static final String DEFINE_FONT_PREFIX = "define_mfb_font_";
  public static final String MODULE_PREFIX = "com.example.%s";

  //TODO This code logic may have to be completely re written.
  // Inferred usage: Dynamically loads all ITypefaces from respective classes if the corresponding define_mfb_font_ is present
  // in the module directory

  /**
   * Find the full class names for each of the ITypeface classes defined in each typeface module
   */
  public static String[] getFields(Context ctx) {
    ctx.getBundleName();
    return null;
  }
}
