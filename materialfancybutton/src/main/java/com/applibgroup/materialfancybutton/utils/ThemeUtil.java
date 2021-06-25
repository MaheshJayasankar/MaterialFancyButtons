package com.applibgroup.materialfancybutton.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.TypedValue;
import com.rilixtech.materialfancybutton.R;

public class ThemeUtil {

  private static TypedValue value;

  public static int dpToPx(Context context, int dp) {
    return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
        context.getResources().getDisplayMetrics()) + 0.5f);
  }

  public static int spToPx(Context context, int sp) {
    return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
        context.getResources().getDisplayMetrics()) + 0.5f);
  }

  private static int getColor(Context context, int id, int defaultValue) {
    if (value == null) value = new TypedValue();

    try {
      Theme theme = context.getTheme();
      if (theme != null && theme.resolveAttribute(id, value, true)) {
        if (value.type >= TypedValue.TYPE_FIRST_INT && value.type <= TypedValue.TYPE_LAST_INT) {
          return value.data;
        } else if (value.type == TypedValue.TYPE_STRING) {
          return context.getResources().getColor(value.resourceId);
        }
      }
    } catch (Exception ex) {
    }

    return defaultValue;
  }

  public static int windowBackground(Context context, int defaultValue) {
    return getColor(context, android.R.attr.windowBackground, defaultValue);
  }

  public static int textColorPrimary(Context context, int defaultValue) {
    return getColor(context, android.R.attr.textColorPrimary, defaultValue);
  }

  public static int textColorSecondary(Context context, int defaultValue) {
    return getColor(context, android.R.attr.textColorSecondary, defaultValue);
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public static int colorControlHighlight(Context context, int defaultValue) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      return getColor(context, android.R.attr.colorControlHighlight, defaultValue);
    }

    //TODO: Can we use this?
    //return getColor(context, R.attr.colorControlHighlight, defaultValue);
    return getColor(context, android.R.attr.colorControlHighlight, defaultValue);
  }

  public static int getType(TypedArray array, int index) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      return array.getType(index);
    } else {
      TypedValue value = array.peekValue(index);
      return value == null ? TypedValue.TYPE_NULL : value.type;
    }
  }

  public static CharSequence getString(TypedArray array, int index, CharSequence defaultValue) {
    String result = array.getString(index);
    return result == null ? defaultValue : result;
  }
}
