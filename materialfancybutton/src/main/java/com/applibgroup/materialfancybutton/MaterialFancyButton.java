package com.applibgroup.materialfancybutton;

import com.applibgroup.materialfancybutton.typeface.IIcon;
import com.applibgroup.materialfancybutton.typeface.ITypeface;
import com.applibgroup.materialfancybutton.utils.FontUtil;
import com.applibgroup.materialfancybutton.utils.TextUtils;
import ohos.agp.colors.RgbColor;
import ohos.agp.components.*;
import ohos.agp.components.element.Element;
import ohos.agp.components.element.ElementContainer;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.components.element.StateElement;
import ohos.agp.text.Font;
import ohos.agp.text.Layout;
import ohos.agp.utils.Color;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.utils.TextAlignment;
import ohos.app.Context;

import java.util.ArrayList;
import java.util.List;

public class MaterialFancyButton extends DirectionalLayout {

  public static final String TAG = MaterialFancyButton.class.getSimpleName();

  // # Background Attributes
  private int mDefaultBackgroundColor = Color.BLACK.getValue();
  private int mFocusBackgroundColor = 0;
  private int mDisabledBackgroundColor = Color.getIntColor("#f6f7f9");
  private int mDisabledTextColor = Color.getIntColor("#bec2c9");
  private int mDisabledBorderColor = Color.getIntColor("#dddfe2");

  // # Text Attributes
  private int mDefaultTextColor = Color.WHITE.getValue();
  private int mDefaultIconColor = Color.WHITE.getValue();
  //private int mTextPosition = 1;
  private int mTextSize = FontUtil.spToPx(getContext(), 15);
  private int mTextGravity; // Gravity.CENTER
  private String mText = null;
  private int mTextStyle;

  // # Icon Attributes
  private Element mIconResource = null;
  private int mFontIconSize = FontUtil.spToPx(getContext(), 15);
  private String mFontIcon = null;
  private int mIconPosition = 1;
  private String mIcon = null;

  private int mIconPaddingLeft = 10;
  private int mIconPaddingRight = 10;
  private int mIconPaddingTop = 0;
  private int mIconPaddingBottom = 0;

  private int mBorderColor;
  private int mBorderWidth = 0;

  private int mRadius = 0;
  private int mRadiusTopLeft = 0;
  private int mRadiusTopRight = 0;
  private int mRadiusBottomLeft = 0;
  private int mRadiusBottomRight = 0;

  private boolean mEnabled;
  private boolean mTextAllCaps;

  private Font mTextTypeFace = null;
  private Font mIconTypeFace = null;

  /**
   * Tags to identify icon position
   */
  public static final int POSITION_LEFT = 1;
  public static final int POSITION_RIGHT = 2;
  public static final int POSITION_TOP = 3;
  public static final int POSITION_BOTTOM = 4;

  private Image mIconView;
  private Text mFontIconView;
  private Text mTextView;

  private boolean mGhost = false; // Default is a solid button !

  public MaterialFancyButton(Context context) {
    super(context);
    initializeMaterialFancyButton();
  }

  public MaterialFancyButton(Context context, AttrSet attrs) {
    super(context, attrs);

    // TODO Replace with custom attribute set parsing logic
    TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.MaterialFancyButton, 0, 0);
    initAttributesArray(arr);
    arr.recycle();
    initializeMaterialFancyButton();
  }

  /**
   * Initialize Button dependencies
   * - Initialize Button Container : The LinearLayout
   * - Initialize Button TextView
   * - Initialize Button Icon
   * - Initialize Button Font Icon
   */
  private void initializeMaterialFancyButton() {
    initializeButtonContainer();
    setupTextView();
    setupFontIconView();
    setupIconView();
    if (mIcon != null) setIcon(mIcon);

    //this.removeAllViews();
    setupBackground();

    List<Component> views = new ArrayList<>();

    if (mIconPosition == POSITION_LEFT || mIconPosition == POSITION_TOP) {
      if (mIconView != null) views.add(mIconView);
      if (mFontIconView != null) views.add(mFontIconView);
      if (mTextView != null) views.add(mTextView);
    } else {
      if (mTextView != null) views.add(mTextView);
      if (mIconView != null) views.add(mIconView);
      if (mFontIconView != null) views.add(mFontIconView);
    }

    for (Component view : views) {
      addComponent(view);
    }
  }

  /**
   * Setup Text View
   *
   * @return : TextView
   */
  private void setupTextView() {
    if (mText == null) mText = "BUTTON";
    if (mTextView == null) mTextView = new Text(getContext());

    mTextView.setText(mText);
    mTextView.setTextAlignment(mTextGravity);
    mTextView.setTextColor(new Color(mEnabled ? mDefaultTextColor : mDisabledTextColor));
    mTextView.setTextSize(FontUtil.pxToSp(getContext(), mTextSize));
    mTextView.setLayoutConfig(new LayoutConfig(LayoutConfig.MATCH_CONTENT, LayoutConfig.MATCH_CONTENT));
    // TODO How to set font style?
    mTextView.setFont(mTextView.getFont());
  }

  /**
   * Setup Font Icon View
   */
  private void setupFontIconView() {
    // Log.d("setupFontIconView", "mFontIcon = " + mFontIcon);
    if (mFontIcon == null) return;
    if(mFontIconView == null) mFontIconView = new Text(getContext());
    mFontIconView.setTextColor(new Color(mEnabled ? mDefaultIconColor : mDisabledTextColor));
    LayoutConfig params = new LayoutConfig(LayoutConfig.MATCH_CONTENT, LayoutConfig.MATCH_CONTENT);
    params.setMarginRight(mIconPaddingRight);
    params.setMarginLeft(mIconPaddingLeft);
    params.setMarginTop(mIconPaddingTop);
    params.setMarginBottom(mIconPaddingBottom);

    if (mTextView == null) {
      params.alignment = LayoutAlignment.CENTER;
      mFontIconView.setTextAlignment(TextAlignment.VERTICAL_CENTER);
    } else {
      if (mIconPosition == POSITION_TOP || mIconPosition == POSITION_BOTTOM) {
        params.alignment = LayoutAlignment.CENTER;
        mFontIconView.setTextAlignment(TextAlignment.CENTER);
      } else {
        mFontIconView.setTextAlignment(TextAlignment.VERTICAL_CENTER);
        params.alignment = LayoutAlignment.VERTICAL_CENTER;
      }
    }

    mFontIconView.setLayoutConfig(params);
    mFontIconView.setTextSize(FontUtil.pxToSp(getContext(), mFontIconSize));
    mFontIconView.setText(mFontIcon);
    mFontIconView.setFont(mIconTypeFace);
    //Log.d("setupFontIconView", "mIconTypeFace  =  " + mIconTypeFace.toString());
  }

  /**
   * Text Icon resource view
   *
   * @return : ImageView
   */
  private void setupIconView() {
    if (mIconView == null) mIconView = new Image(getContext());
    mIconView.setPadding(mIconPaddingLeft, mIconPaddingTop, mIconPaddingRight, mIconPaddingBottom);
    LayoutConfig params = new LayoutConfig(LayoutConfig.MATCH_CONTENT, LayoutConfig.MATCH_CONTENT);
    if (TextUtils.isEmpty(mText)) {
      params.alignment = LayoutAlignment.VERTICAL_CENTER;
    } else {
      if (mIconPosition == POSITION_TOP || mIconPosition == POSITION_BOTTOM) {
        params.alignment = LayoutAlignment.CENTER;
      } else {
        params.alignment = LayoutAlignment.START;
      }
    }
    mIconView.setLayoutConfig(params);

    if (mIconResource == null) {
      //Log.d(TAG, "mIconResource is null");
    } else {
      mIconView.setImageElement(mIconResource);
      //Log.d(TAG, "mIconResource is not null");
    }
  }

  /**
   * Initialize Attributes arrays
   *
   * @param attrs : Attributes array
   */
  private void initAttributesArray(TypedArray attrs) {
    mDefaultBackgroundColor = attrs.getColor(R.styleable.MaterialFancyButton_mfb_defaultColor, mDefaultBackgroundColor);
    mFocusBackgroundColor = attrs.getColor(R.styleable.MaterialFancyButton_mfb_focusColor, mFocusBackgroundColor);
    mDisabledBackgroundColor = attrs.getColor(R.styleable.MaterialFancyButton_mfb_disabledColor, mDisabledBackgroundColor);

    mEnabled = attrs.getBoolean(R.styleable.MaterialFancyButton_android_enabled, true);

    mDisabledTextColor = attrs.getColor(R.styleable.MaterialFancyButton_mfb_disabledTextColor, mDisabledTextColor);
    mDisabledBorderColor = attrs.getColor(R.styleable.MaterialFancyButton_mfb_disabledBorderColor, mDisabledBorderColor);
    mDefaultTextColor = attrs.getColor(R.styleable.MaterialFancyButton_mfb_textColor, mDefaultTextColor);
    // if default color is set then the icon's color is the same (the default for icon's color)
    mDefaultIconColor = attrs.getColor(R.styleable.MaterialFancyButton_mfb_iconColor, mDefaultTextColor);

    mTextSize = (int) attrs.getDimension(R.styleable.MaterialFancyButton_mfb_textSize, mTextSize);
    mTextSize = (int) attrs.getDimension(R.styleable.MaterialFancyButton_android_textSize, mTextSize);
    mTextStyle = attrs.getInt(R.styleable.MaterialFancyButton_android_textStyle, Typeface.NORMAL);

    mTextGravity = attrs.getInt(R.styleable.MaterialFancyButton_mfb_textGravity, Gravity.CENTER);

    mBorderColor = attrs.getColor(R.styleable.MaterialFancyButton_mfb_borderColor, Color.TRANSPARENT);
    mBorderWidth = (int) attrs.getDimension(R.styleable.MaterialFancyButton_mfb_borderWidth, mBorderWidth);

    // Handle radius for button.
    mRadius = (int) attrs.getDimension(R.styleable.MaterialFancyButton_mfb_radius, mRadius);

    mRadiusTopLeft = (int) attrs.getDimension(R.styleable.MaterialFancyButton_mfb_radiusTopLeft, mRadius);
    mRadiusTopRight = (int) attrs.getDimension(R.styleable.MaterialFancyButton_mfb_radiusTopRight, mRadius);
    mRadiusBottomLeft = (int) attrs.getDimension(R.styleable.MaterialFancyButton_mfb_radiusBottomLeft, mRadius);
    mRadiusBottomRight = (int) attrs.getDimension(R.styleable.MaterialFancyButton_mfb_radiusBottomRight, mRadius);

    mFontIconSize = (int) attrs.getDimension(R.styleable.MaterialFancyButton_mfb_fontIconSize, mFontIconSize);

    mIconPaddingLeft = (int) attrs.getDimension(R.styleable.MaterialFancyButton_mfb_iconPaddingLeft, mIconPaddingLeft);
    mIconPaddingRight = (int) attrs.getDimension(R.styleable.MaterialFancyButton_mfb_iconPaddingRight, mIconPaddingRight);
    mIconPaddingTop = (int) attrs.getDimension(R.styleable.MaterialFancyButton_mfb_iconPaddingTop, mIconPaddingTop);
    mIconPaddingBottom = (int) attrs.getDimension(R.styleable.MaterialFancyButton_mfb_iconPaddingBottom, mIconPaddingBottom);

    mTextAllCaps = attrs.getBoolean(R.styleable.MaterialFancyButton_mfb_textAllCaps, false);
    mTextAllCaps = attrs.getBoolean(R.styleable.MaterialFancyButton_android_textAllCaps, false);

    mGhost = attrs.getBoolean(R.styleable.MaterialFancyButton_mfb_ghost, mGhost);
    //mUseSystemFont = attrsArray.getBoolean(R.styleable.MaterialFancyButtonAttrs_fb_useSystemFont,
    //    mUseSystemFont);

    String text = attrs.getString(R.styleable.MaterialFancyButton_mfb_text);

    //no mfb_text attribute
    if (text == null) text = attrs.getString(R.styleable.MaterialFancyButton_android_text);

    mIconPosition = attrs.getInt(R.styleable.MaterialFancyButton_mfb_iconPosition, mIconPosition);

    String fontIcon = attrs.getString(R.styleable.MaterialFancyButton_mfb_fontIconResource);

    String iconFontFamily = attrs.getString(R.styleable.MaterialFancyButton_mfb_iconFont);
    String textFontFamily = attrs.getString(R.styleable.MaterialFancyButton_mfb_textFont);

    try {
      mIconResource = attrs.getDrawable(R.styleable.MaterialFancyButton_mfb_iconResource);
    } catch (Exception e) {
      mIconResource = null;
    }

    mIcon = attrs.getString(R.styleable.MaterialFancyButton_mfb_icon);

    Log.d(TAG, "mIcon = " + mIcon);
    if (fontIcon != null) mFontIcon = fontIcon;

    if (text != null) mText = mTextAllCaps ? text.toUpperCase() : text;

    if (mIcon == null) {
      if (iconFontFamily == null) {
        mIconTypeFace = FontUtil.findFont(getContext(), null, null);
      } else {
        mIconTypeFace = FontUtil.findFont(getContext(), iconFontFamily, null);
      }

      if (textFontFamily == null) {
        mTextTypeFace = FontUtil.findFont(getContext(), null, null);
      } else {
        mTextTypeFace = FontUtil.findFont(getContext(), textFontFamily, null);
      }
    }
  }

  private void setupBackground() {
    ShapeElement defaultDrawable = new ShapeElement();
    defaultDrawable.setCornerRadius(mRadius);
    defaultDrawable.setCornerRadiiArray(new float[] { mRadiusTopLeft, mRadiusTopLeft,
        mRadiusTopRight, mRadiusTopRight,
        mRadiusBottomRight, mRadiusBottomRight,
        mRadiusBottomLeft, mRadiusBottomLeft
    });

    if (mGhost) {
      // Hollow Background
      defaultDrawable.setRgbColor(new RgbColor(Color.TRANSPARENT.getValue()));

    } else {
      defaultDrawable.setRgbColor(new RgbColor(mDefaultBackgroundColor));
    }

    ShapeElement focusDrawable = new ShapeElement();

    focusDrawable.setCornerRadius(mRadius);
    focusDrawable.setCornerRadiiArray(new float[] {
        mRadiusTopLeft, mRadiusTopLeft, mRadiusTopRight, mRadiusTopRight, mRadiusBottomRight, mRadiusBottomRight,
        mRadiusBottomLeft, mRadiusBottomLeft
    });
    focusDrawable.setRgbColor(new RgbColor(mFocusBackgroundColor));

    // Disabled Drawable
    //if(disabledDrawable == null) {
    ShapeElement disabledDrawable = new ShapeElement();
    //}
    disabledDrawable.setCornerRadius(mRadius);
    focusDrawable.setCornerRadiiArray(new float[] {
        mRadiusTopLeft, mRadiusTopLeft, mRadiusTopRight, mRadiusTopRight, mRadiusBottomRight, mRadiusBottomRight,
        mRadiusBottomLeft, mRadiusBottomLeft
    });
    disabledDrawable.setRgbColor(new RgbColor(mDisabledBackgroundColor));
    disabledDrawable.setStroke(mBorderWidth, new RgbColor(mDisabledBorderColor));

    // Handle Border
    if (mBorderColor != 0) defaultDrawable.setStroke(mBorderWidth, new RgbColor(mBorderColor));

    // Handle disabled border color
    if (!mEnabled) {
      defaultDrawable.setStroke(mBorderWidth, new RgbColor(mDisabledBorderColor));
      if (mGhost) {
          disabledDrawable.setRgbColor(new RgbColor(Color.TRANSPARENT.getValue()));
      }
    }

    setupRippleEffect(defaultDrawable, focusDrawable, disabledDrawable);
  }

  private void setupRippleEffect(ShapeElement defaultDrawable, ShapeElement focusDrawable,
                                 ShapeElement disabledDrawable) {
    //private boolean mUseSystemFont = false; // Default is using robotoregular.ttf
    boolean mUseRippleEffect = false;
    StateElement states = new StateElement();
    ShapeElement drawable2 = new ShapeElement();
    drawable2.setCornerRadius(mRadius);
    focusDrawable.setCornerRadiiArray(new float[] {
        mRadiusTopLeft, mRadiusTopLeft, mRadiusTopRight, mRadiusTopRight, mRadiusBottomRight, mRadiusBottomRight,
        mRadiusBottomLeft, mRadiusBottomLeft
    });
    if (mGhost) {
      drawable2.setRgbColor(new RgbColor(Color.TRANSPARENT.getValue())); // No focus color
    } else {
      drawable2.setRgbColor(new RgbColor(mFocusBackgroundColor));
    }

    // Handle Button Border
    if (mBorderColor != 0) {
      if (mGhost) {
        drawable2.setStroke(mBorderWidth, new RgbColor(mFocusBackgroundColor)); // Border is the main part of button now
      } else {
        drawable2.setStroke(mBorderWidth, new RgbColor(mBorderColor));
      }
    }

    if (!mEnabled) {
      drawable2.setStroke(mBorderWidth, new RgbColor(mDisabledBorderColor));
    }

    if (mFocusBackgroundColor != 0) {
      states.addState(new int[] { ComponentState.COMPONENT_STATE_PRESSED }, drawable2);
      states.addState(new int[] { ComponentState.COMPONENT_STATE_FOCUSED }, drawable2);
      states.addState(new int[] { ComponentState.COMPONENT_STATE_DISABLED }, disabledDrawable);
    }

    states.addState(new int[] {}, defaultDrawable);
    setBackground(states);
  }

  private void initializeButtonContainer() {
    if (mIconPosition == POSITION_TOP || mIconPosition == POSITION_BOTTOM) {
      setOrientation(DirectionalLayout.VERTICAL);
    } else {
      setOrientation(DirectionalLayout.HORIZONTAL);
    }

    if (getLayoutConfig() == null) {
      LayoutConfig params = new LayoutConfig(LayoutConfig.MATCH_CONTENT, LayoutConfig.MATCH_CONTENT);
      setLayoutConfig(params);
    }

    setAlignment(LayoutAlignment.CENTER);
    setClickable(true);
    setFocusable(FOCUS_ENABLE);
    if (mIconResource == null
        && mFontIcon == null
        && getPaddingLeft() == 0
        && getPaddingRight() == 0
        && getPaddingTop() == 0
        && getPaddingBottom() == 0) {

      //fix for all version of androids and screens
      setPadding(20, 0, 20, 0);
    }
  }

  /**
   * Set Text of the button
   *
   * @param text : Text
   */
  public void setText(String text) {
    text = mTextAllCaps ? text.toUpperCase() : text;
    mText = text;
    if(mTextView == null) setupView();
    mTextView.setText(text);
  }

  /**
   * Set text of the button by string resource id
   *
   * @param resId Resource id for the string
   */
  @SuppressWarnings("unused") public void setText(int resId) {
    String text = getContext().getString(resId);
    text = mTextAllCaps ? text.toUpperCase() : text;
    mText = text;
    if (mTextView == null) setupTextView();
    mTextView.setText(text);
  }

  /**
   * Set the capitalization of text
   *
   * @param textAllCaps : is text to be capitalized
   */
  @SuppressWarnings("unused") public void setTextAllCaps(boolean textAllCaps) {
    mTextAllCaps = textAllCaps;
    setText(mText);
  }

  /**
   * Set the color of text
   *
   * @param color : Color
   * use Color.parse('#code')
   */
  @SuppressWarnings("unused") public void setTextColor(int color) {
    mDefaultTextColor = color;
    if (mTextView == null) setupTextView();
    mTextView.setTextColor(new Color(color));
  }

  public void setTextStyle(/*@Typeface.Style*/ int style) {
    mTextSize = style;
    if(mTextView == null) setupTextView();
    // TODO: How to add style to font?
    mTextView.setFont(mTextView.getFont());
  }

  /**
   * Setting the icon's color independent of the text color
   *
   * @param color : Color
   */
  @SuppressWarnings("unused") public void setIconColor(int color) {
    if (mFontIconView != null) mFontIconView.setTextColor(new Color(color));
  }

  /**
   * Set Background color of the button
   *
   * @param color : use Color.parse('#code')
   */
  public void setBackgroundColor(int color) {
    mDefaultBackgroundColor = color;
    setupView();
  }

  /**
   * Set Focus color of the button
   *
   * @param color : use Color.parse('#code')
   */
  public void setFocusBackgroundColor(int color) {
    mFocusBackgroundColor = color;
    setupView();
  }

  /**
   * Set Disabled state color of the button
   *
   * @param color : use Color.parse('#code')
   */
  @SuppressWarnings("unused") public void setDisableBackgroundColor(int color) {
    mDisabledBackgroundColor = color;
    setupView();
  }

  /**
   * Set Disabled state color of the button text
   *
   * @param color : use Color.parse('#code')
   */
  @SuppressWarnings("unused") public void setDisableTextColor(int color) {
    mDisabledTextColor = color;
    setupTextView();
    if (!mEnabled) mTextView.setTextColor(new Color(color));
  }

  /**
   * Set Disabled state color of the button border
   *
   * @param color : use Color.parse('#code')
   */
  @SuppressWarnings("unused") public void setDisableBorderColor(int color) {
    mDisabledBorderColor = color;
    if (mIconView != null || mFontIconView != null || mTextView != null) {
      setupBackground();
    }
  }

  /**
   * Set the size of Text in sp
   *
   * @param textSize : Text Size
   */
  public void setTextSize(int textSize) {
    mTextSize = FontUtil.spToPx(getContext(), textSize);
    if (mTextView != null) mTextView.setTextSize(textSize);
  }

  /**
   * Set the gravity of Text
   *
   * @param gravity : Text Gravity
   */
  @SuppressWarnings("unused") public void setTextGravity(int gravity) {
    mTextGravity = gravity;
    if (mTextView != null) mTextView.setTextAlignment(gravity);
  }

  /**
   * Set Padding for mIconView and mFontIconSize
   *
   * @param paddingLeft : Padding Left
   * @param paddingTop : Padding Top
   * @param paddingRight : Padding Right
   * @param paddingBottom : Padding Bottom
   */
  public void setIconPadding(int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
    this.mIconPaddingLeft = paddingLeft;
    this.mIconPaddingTop = paddingTop;
    this.mIconPaddingRight = paddingRight;
    this.mIconPaddingBottom = paddingBottom;
    if (mIconView != null) {
      mIconView.setPadding(this.mIconPaddingLeft, this.mIconPaddingTop, this.mIconPaddingRight,
          this.mIconPaddingBottom);
    }
    if (mFontIconView != null) {
      mFontIconView.setPadding(mIconPaddingLeft, mIconPaddingTop, mIconPaddingRight, mIconPaddingBottom);
    }
  }

  /**
   * Set a drawable to the button
   *
   * @param drawable : Drawable resource
   */
  @SuppressWarnings("unused") public void setIconResource(Element drawable) {
    mIconResource = drawable;
    if (mIconView == null) setupIconView();
    if(mFontIconView == null) setupFontIconView();
    mIconView.setImageElement(mIconResource);
  }

  /**
   * Set a font icon to the button (eg FFontAwesome or Entypo...)
   *
   * @param icon : Icon value eg : \uf082
   */
  public void setIconResource(String icon) {
    mIcon = icon;
    mFontIcon = icon;
    setupView();
  }

  public void setIcon(Character icon) {
    setIcon(icon.toString());
  }

  public void setIcon(String icon) {
    try {
      //Log.d(TAG, "icon.substring(0, 3) = " + icon.substring(0, 3));
      ITypeface font =
          CoreIcon.findFont(getContext().getApplicationContext(), icon.substring(0, CoreIcon.FONT_MAPPING_PREFIX));
      //Log.d(TAG, "Font characters = " + font.getCharacters().size());
      icon = icon.replace("-", "_");
      setIcon(font.getIcon(icon));
      //Log.d(TAG, font.getIcon(icon).getTypeface().getDescription());
    } catch (Exception ex) {
      // Log.e(TAG, "Wrong icon name: " + icon);
    }
  }

  public void setIcon(IIcon icon) {
    ITypeface typeface = icon.getTypeface();
    // Log.d(TAG, "Typeface = " + icon.getTypeface().getFontName());
    mIconTypeFace = typeface.getTypeface(getContext().getApplicationContext());
    setIconResource(String.valueOf(icon.getCharacter()));
  }

  /**
   * Set Icon size of the button (for only font icons) in sp
   *
   * @param iconSize : Icon Size
   */
  public void setFontIconSize(int iconSize) {
    mFontIconSize = FontUtil.spToPx(getContext(), iconSize);
    if (mFontIconView != null) mFontIconView.setTextSize(iconSize);
  }

  /**
   * Set Icon Position
   * Use the global variables (FancyButton.POSITION_LEFT, FancyButton.POSITION_RIGHT,
   * FancyButton.POSITION_TOP, FancyButton.POSITION_BOTTOM)
   *
   * @param position : Position
   */
  public void setIconPosition(int position) {
    if (position > 0 && position < 5) {
      mIconPosition = position;
    } else {
      mIconPosition = POSITION_LEFT;
    }
    setupView();
  }

  /**
   * Set color of the button border
   *
   * @param color : Color
   * use Color.parse('#code')
   */
  @SuppressWarnings("unused") public void setBorderColor(int color) {
    mBorderColor = color;
    setupView();
  }

  /**
   * Set Width of the button
   *
   * @param width : Width
   */
  @SuppressWarnings("unused") public void setBorderWidth(int width) {
    mBorderWidth = width;
    setupView();
  }

  private void setupView() {
    setupFontIconView();
    setupTextView();
    setupBackground();
  }
  /**
   * Set Border Radius of the button
   *
   * @param radius : Radius
   */
  public void setRadius(int radius) {
    mRadius = radius;
    mRadiusTopLeft = radius;
    mRadiusTopRight = radius;
    mRadiusBottomLeft = radius;
    mRadiusBottomRight = radius;

    setupView();
    // Log.d(TAG, "setRadius is called");
  }

  public void setRadius(int radiusTopLeft, int radiusTopRight, int radiusBottomLeft, int radiusBottomRight) {
    mRadiusTopLeft = radiusTopLeft;
    mRadiusTopRight = radiusTopRight;
    mRadiusBottomLeft = radiusBottomLeft;
    mRadiusBottomRight = radiusBottomRight;
    setupView();
  }

  /**
   * Set border radius top left of the button
   *
   * @param radiusTopLeft radius top left of the button
   */
  public void setRadiusTopLeft(int radiusTopLeft) {
    mRadiusTopLeft = radiusTopLeft;
    setupView();
  }

  /**
   * Set border radius top right of the button
   *
   * @param radiusTopRight radius top right of the button
   */
  public void setRadiusTopRight(int radiusTopRight) {
    mRadiusTopRight = radiusTopRight;
    setupView();
  }

  /**
   * Set border radius bottom left of the button
   *
   * @param radiusBottomLeft radius bottom left of the button
   */
  public void setRadiusBottomLeft(int radiusBottomLeft) {
    mRadiusBottomLeft = radiusBottomLeft;
    setupView();
  }

  /**
   * Set border radius bottom right of the button
   *
   * @param radiusBottomRight radius bottom right of the button
   */
  public void setRadiusBottomRight(int radiusBottomRight) {
    mRadiusBottomRight = radiusBottomRight;
    setupView();
  }

  /**
   * Set custom font for button Text
   *
   * @param fontName : Font Name
   * Place your text fonts in assets
   */
  public void setCustomTextFont(String fontName) {
    mTextTypeFace = FontUtil.findFont(getContext(), fontName, null);
    setupTextView();
    mTextView.setFont(mTextTypeFace);
  }

  /**
   * Set Custom font for button icon
   *
   * @param fontName : Font Name
   * Place your icon fonts in assets
   */
  @SuppressWarnings("unused") public void setIconFont(String fontName) {
    mIconTypeFace = FontUtil.findFont(getContext(), fontName, null);
    setupFontIconView();
    mFontIconView.setFont(mIconTypeFace);
  }

  /**
   * Override setEnabled and rebuild the fancybutton view
   * To redraw the button according to the state : enabled or disabled
   */
  @Override public void setEnabled(boolean value) {
    super.setEnabled(value);
    mEnabled = value;
    setupView();
  }

  /**
   * Setting the button to have hollow or solid shape
   */
  @SuppressWarnings("unused") public void setGhost(boolean ghost) {
    mGhost = ghost;
    setupView();
  }

  /**
   * Return Text of the button
   *
   * @return Text
   */
  @SuppressWarnings("unused") public CharSequence getText() {
    return mTextView != null ? mTextView.getText() : "";
  }

  /**
   * Return TextView Object of the FancyButton
   *
   * @return TextView Object
   */
  @SuppressWarnings("unused") public Text getTextViewObject() {
    return mTextView;
  }

  /**
   * Return Icon Font of the FancyButton
   *
   * @return TextView Object
   */
  @SuppressWarnings("unused") public Text getIconFontObject() {
    return mFontIconView;
  }

  /**
   * Return Icon of the FancyButton
   *
   * @return ImageView Object
   */
  @SuppressWarnings("unused") public Image getIconImageObject() {
    return mIconView;
  }
}

//protected void applyStyle(Context context, AttributeSet attrs, int defStyleAttr,
//    int defStyleRes) {
//  getRippleManager().onCreate(this, context, attrs, defStyleAttr, defStyleRes, mCornerRadii);
//}

//protected RippleManager getRippleManager() {
//  if (mRippleManager == null) {
//    synchronized (RippleManager.class) {
//      if (mRippleManager == null) mRippleManager = new RippleManager();
//    }
//  }
//
//  return mRippleManager;
//}

//@Override protected void onDetachedFromWindow() {
//  super.onDetachedFromWindow();
//  RippleManager.cancelRipple(this);
//}

//@Override public void setOnClickListener(OnClickListener l) {
//  RippleManager rippleManager = getRippleManager();
//  if (l == rippleManager) {
//    super.setOnClickListener(l);
//  } else {
//    rippleManager.setOnClickListener(l);
//    setOnClickListener(rippleManager);
//  }
//}

//@Override public boolean onTouchEvent(MotionEvent event) {
//  boolean result = super.onTouchEvent(event);
//  return getRippleManager().onTouchEvent(this, event) || result;
//}
//}
