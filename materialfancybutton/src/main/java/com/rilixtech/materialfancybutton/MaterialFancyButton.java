package com.rilixtech.materialfancybutton;

import com.rilixtech.materialfancybutton.typeface.IIcon;
import com.rilixtech.materialfancybutton.typeface.ITypeface;
import com.rilixtech.materialfancybutton.utils.AttrEnumUtil;
import com.rilixtech.materialfancybutton.utils.FontUtil;
import com.rilixtech.materialfancybutton.utils.TextUtils;
import ohos.aafwk.ability.Ability;
import ohos.agp.colors.RgbColor;
import ohos.agp.colors.RgbPalette;
import ohos.agp.components.*;
import ohos.agp.components.element.Element;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.components.element.StateElement;
import ohos.agp.text.Font;
import ohos.agp.utils.Color;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.utils.TextAlignment;
import ohos.app.AbilityContext;
import ohos.app.Context;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MaterialFancyButton extends DirectionalLayout {

  private static final String TAG = MaterialFancyButton.class.getSimpleName();
  private static final int DOMAIN = 0xD000100;
  private static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, DOMAIN, TAG);

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
  private int mTextSize = FontUtil.spToPx(getContext(), 40);
  private int mTextGravity; // Gravity.CENTER
  private String mText = null;
  private int mTextStyle;

  // # Icon Attributes
  private Element mIconResource = null;
  private int mFontIconSize = FontUtil.spToPx(getContext(), 40);
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

    initAttributes(attrs);
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

    buildComponentContainer();
  }

  private void buildComponentContainer() {
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

    addListOfComponents(views);
  }

  private void addListOfComponents(List<Component> views) {
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
    HiLog.debug(LABEL, "setupFontIconView mFontIcon = %{public}s", mFontIcon);
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
    HiLog.debug(LABEL, "setupFontIconView mIconTypeFace  =  %{public}s", mIconTypeFace.toString());
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
      HiLog.debug(LABEL, "mIconResource is null");
    } else {
      mIconView.setImageElement(mIconResource);
      HiLog.debug(LABEL, "mIconResource is not null");
    }
  }

  // TODO Workaround to initAttributesArray, test if this feature is working as intended
  /**
   * Initialize Attributes arrays
   *
   * @param attrSet : Attributes array
   */
  private void initAttributes(AttrSet attrSet){
    // COLOR ATTRIBUTES
    mDefaultBackgroundColor = getColorAttribute(attrSet, "mfb_defaultColor", mDefaultBackgroundColor);
    mFocusBackgroundColor = getColorAttribute(attrSet, "mfb_focusColor", mFocusBackgroundColor);
    mDisabledBackgroundColor = getColorAttribute(attrSet, "mfb_disabledColor", mDisabledBackgroundColor);
    mDisabledTextColor = getColorAttribute(attrSet, "mfb_disabledTextColor", mDisabledTextColor);
    mDisabledBorderColor = getColorAttribute(attrSet, "mfb_disabledBorderColor", mDisabledBorderColor);
    mDefaultTextColor = getColorAttribute(attrSet, "mfb_textColor", mDefaultTextColor);
    // if default color is set then the icon's color is the same (the default for icon's color)
    mDefaultIconColor = getColorAttribute(attrSet, "mfb_iconColor", mDefaultTextColor);
    mBorderColor = getColorAttribute(attrSet, "mfb_borderColor", mBorderColor);

    // BOOLEAN ATTRIBUTES
    mEnabled = getBoolAttribute(attrSet, "enabled", true);
    // // mTextAllCaps
    mTextAllCaps = getBoolAttribute(attrSet, "mfb_textAllCaps", false);
    mTextAllCaps = getBoolAttribute(attrSet, "textAllCaps", mTextAllCaps);
    // //
    mGhost = getBoolAttribute(attrSet, "mfb_ghost", mGhost);

    // DIMENSION ATTRIBUTES
    // // mTextSize
    mTextSize = getDimensionAttribute(attrSet, "mfb_textSize", mTextSize);
    mTextSize = getDimensionAttribute(attrSet, "textSize", mTextSize);
    // //
    mBorderWidth = getDimensionAttribute(attrSet, "mfb_borderWidth", mBorderWidth);
    mRadius = getDimensionAttribute(attrSet, "mfb_radius", mRadius);
    mRadiusTopLeft = getDimensionAttribute(attrSet, "mfb_radiusTopLeft", mRadius);
    mRadiusTopRight = getDimensionAttribute(attrSet, "mfb_radiusTopRight", mRadius);
    mRadiusBottomLeft = getDimensionAttribute(attrSet, "mfb_radiusBottomLeft", mRadius);
    mRadiusBottomRight = getDimensionAttribute(attrSet, "mfb_radiusBottomRight", mRadius);
    mFontIconSize = getDimensionAttribute(attrSet, "mfb_fontIconSize", mFontIconSize);
    mIconPaddingLeft = getDimensionAttribute(attrSet, "mfb_iconPaddingLeft", mIconPaddingLeft);
    mIconPaddingRight = getDimensionAttribute(attrSet, "mfb_iconPaddingRight", mIconPaddingRight);
    mIconPaddingTop = getDimensionAttribute(attrSet, "mfb_iconPaddingTop", mIconPaddingTop);
    mIconPaddingBottom = getDimensionAttribute(attrSet, "mfb_iconPaddingBottom", mIconPaddingBottom);

    // INTEGER ATTRIBUTES
    mTextStyle = getIntegerAttribute(attrSet, "mfb_textStyle", Font.REGULAR);

    // ENUM ATTRIBUTES
    mTextGravity = getEnumAttribute(
            attrSet, "mfb_textGravity", AttrEnumUtil.MfbTextGravity.class,
            AttrEnumUtil.MfbTextGravity.center).value;
    mIconPosition = getEnumAttribute(
            attrSet, "mfb_iconPosition", AttrEnumUtil.MfbIconPosition.class,
            AttrEnumUtil.MfbIconPosition.labelOfValue(mIconPosition)).value;

    // STRING ATTRIBUTES
    // // mText
    String text = getStringAttribute(attrSet, "mfb_text");
    if (text == null)
      text = getStringAttribute(attrSet, "text");
    if (text != null) mText = mTextAllCaps ? text.toUpperCase() : text;
    // //
    // // String Attribute Temporary Variables
    String fontIcon = getStringAttribute(attrSet, "mfb_fontIconResource");
    String iconFontFamily = getStringAttribute(attrSet, "mfb_iconFont");
    String textFontFamily = getStringAttribute(attrSet, "mfb_textFont");
    // //
    mIcon = getStringAttribute(attrSet, "mfb_icon");
    HiLog.debug(LABEL, "mIcon = %{public}s", mIcon);

    // DRAWABLE ATTRIBUTE
    Optional<Attr> iconResourceAttr = attrSet.getAttr("mfb_iconResource");
    iconResourceAttr.ifPresent(attr -> mIconResource = attr.getElement());

    // Resolve Temporary Attribute Variables
    if (fontIcon != null) mFontIcon = fontIcon;

    Context callerContext = getContext();
    if (mIcon == null && callerContext instanceof AbilityContext){
      AbilityContext abilityContext = (AbilityContext) callerContext;
      if (iconFontFamily == null) {
        mIconTypeFace = FontUtil.findFont(abilityContext, null, null);
      } else {
        mIconTypeFace = FontUtil.findFont(abilityContext, iconFontFamily, null);
      }

      if (textFontFamily == null) {
        mTextTypeFace = FontUtil.findFont(abilityContext, null, null);
      } else {
        mTextTypeFace = FontUtil.findFont(abilityContext, textFontFamily, null);
      }
    }

  }

  private int getColorAttribute(AttrSet attrSet, String attrName, int defaultValue){
    Optional<Attr> optionalAttribute = attrSet.getAttr(attrName);
    if (optionalAttribute.isPresent()){
      Color color = optionalAttribute.get().getColorValue();
      if (color != null)
        return color.getValue();
      else{
        return defaultValue;
      }
    }
    else{
      return defaultValue;
    }
  }

  private boolean getBoolAttribute(AttrSet attrSet, String attrName, boolean defaultValue){
    Optional<Attr> optionalAttribute = attrSet.getAttr(attrName);
    return optionalAttribute.map(Attr::getBoolValue).orElse(defaultValue);
  }

  private int getDimensionAttribute(AttrSet attrSet, String attrName, int defaultValue){
    Optional<Attr> optionalAttribute = attrSet.getAttr(attrName);
    return optionalAttribute.map(Attr::getDimensionValue).orElse(defaultValue);
  }

  private int getIntegerAttribute(AttrSet attrSet, String attrName, int defaultValue){
    Optional<Attr> optionalAttribute = attrSet.getAttr(attrName);
    return optionalAttribute.map(Attr::getIntegerValue).orElse(defaultValue);
  }

  private String getStringAttribute(AttrSet attrSet, String attrName){
    Optional<Attr> optionalAttribute = attrSet.getAttr(attrName);
    return optionalAttribute.map(Attr::getStringValue).orElse(null);
  }

  private <E extends java.lang.Enum<E>> E getEnumAttribute(AttrSet attrSet, String attrName, Class<E> enumType, E defaultValue){
    Optional<Attr> optionalAttribute = attrSet.getAttr(attrName);
    if (optionalAttribute.isPresent()){
          String stringValue = optionalAttribute.get().getStringValue();
          try{
              return E.valueOf(enumType, stringValue);
          }catch (IllegalArgumentException e){
              return defaultValue;
          }
      }
      else{
          return defaultValue;
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
      defaultDrawable.setRgbColor(RgbColor.fromArgbInt(Color.TRANSPARENT.getValue()));

    } else {

      defaultDrawable.setRgbColor(RgbColor.fromArgbInt(mDefaultBackgroundColor));
    }

    ShapeElement focusDrawable = new ShapeElement();

    focusDrawable.setCornerRadius(mRadius);
    focusDrawable.setCornerRadiiArray(new float[] {
        mRadiusTopLeft, mRadiusTopLeft, mRadiusTopRight, mRadiusTopRight, mRadiusBottomRight, mRadiusBottomRight,
        mRadiusBottomLeft, mRadiusBottomLeft
    });
    focusDrawable.setRgbColor(RgbColor.fromArgbInt(mFocusBackgroundColor));

    // Disabled Drawable
    //if(disabledDrawable == null) {
    ShapeElement disabledDrawable = new ShapeElement();
    //}
    disabledDrawable.setCornerRadius(mRadius);
    focusDrawable.setCornerRadiiArray(new float[] {
        mRadiusTopLeft, mRadiusTopLeft, mRadiusTopRight, mRadiusTopRight, mRadiusBottomRight, mRadiusBottomRight,
        mRadiusBottomLeft, mRadiusBottomLeft
    });
    disabledDrawable.setRgbColor(RgbColor.fromArgbInt(mDisabledBackgroundColor));
    disabledDrawable.setStroke(mBorderWidth, RgbColor.fromArgbInt(mDisabledBorderColor));

    // Handle Border
    if (mBorderColor != 0) defaultDrawable.setStroke(mBorderWidth, RgbColor.fromArgbInt(mBorderColor));

    // Handle disabled border color
    if (!mEnabled) {
      defaultDrawable.setStroke(mBorderWidth, RgbColor.fromArgbInt(mDisabledBorderColor));
      if (mGhost) {
          disabledDrawable.setRgbColor(RgbColor.fromArgbInt(Color.TRANSPARENT.getValue()));
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
      drawable2.setRgbColor(RgbColor.fromArgbInt(Color.TRANSPARENT.getValue())); // No focus color
    } else {
      drawable2.setRgbColor(RgbColor.fromArgbInt(mFocusBackgroundColor));
    }

    // Handle Button Border
    if (mBorderColor != 0) {
      if (mGhost) {
        drawable2.setStroke(mBorderWidth, RgbColor.fromArgbInt(mFocusBackgroundColor)); // Border is the main part of button now
      } else {
        drawable2.setStroke(mBorderWidth, RgbColor.fromArgbInt(mBorderColor));
      }
    }

    if (!mEnabled) {
      drawable2.setStroke(mBorderWidth, RgbColor.fromArgbInt(mDisabledBorderColor));
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
    // TODO: How to set font style?
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
      HiLog.error(LABEL, "Wrong icon name: %{public}s", icon);
    }
  }

  public void setIcon(IIcon icon) {
    ITypeface typeface = icon.getTypeface();
    HiLog.debug(LABEL, "Typeface = %{public}s", icon.getTypeface().getFontName());

    // TODO Change Context to AbilityContext
    Context callerContext = getContext();
    if (callerContext instanceof AbilityContext) {
      mIconTypeFace = typeface.getTypeface((AbilityContext) callerContext);
    }
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
    HiLog.debug(LABEL, "setRadius is called");
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
    Context callerContext = getContext();
    if (callerContext instanceof AbilityContext) {
      mTextTypeFace = FontUtil.findFont((AbilityContext)callerContext, fontName, null);
      setupTextView();
      mTextView.setFont(mTextTypeFace);
    }
  }

  /**
   * Set Custom font for button icon
   *
   * @param fontName : Font Name
   * Place your icon fonts in assets
   */
  @SuppressWarnings("unused") public void setIconFont(String fontName) {
    Context callerContext = getContext();
    if (callerContext instanceof AbilityContext) {
      mIconTypeFace = FontUtil.findFont((AbilityContext) callerContext, fontName, null);
      setupFontIconView();
      mFontIconView.setFont(mIconTypeFace);
    }
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
