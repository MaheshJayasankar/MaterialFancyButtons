package com.rilixtech.materialfancybutton.utils;


import ohos.agp.utils.Color;

public class ColorUtil {

	private static int getMiddleValue(int prev, int next, float factor){
		return Math.round(prev + (next - prev) * factor);
	}

	public static int getMiddleColor(int prevColor, int curColor, float factor){
		if(prevColor == curColor)
			return curColor;

		if(factor == 0f)
			return prevColor;
		else if(factor == 1f)
			return curColor;

		int a = getMiddleValue(Color.alpha(prevColor), Color.alpha(curColor), factor);
		int r = getMiddleValue(getRedComponent(prevColor), getRedComponent(curColor), factor);
		int g = getMiddleValue(getGreenComponent(prevColor), getGreenComponent(curColor), factor);
		int b = getMiddleValue(getBlueComponent(prevColor), getBlueComponent(curColor), factor);

		return Color.argb(a, r, g, b);
	}

	// TODO: Write tests for following 3 functions
	private static int getRedComponent(int color)
	{
		return (color >> 16) & 0xFF;
	}
	private static int getGreenComponent(int color)
	{
		return (color >> 8) & 0xFF;
	}
	private static int getBlueComponent(int color)
	{
		return color& 0xFF;
	}

	public static int getColor(int baseColor, float alphaPercent){
		int alpha = Math.round(Color.alpha(baseColor) * alphaPercent);

		return (baseColor & 0x00FFFFFF) | (alpha << 24);
	}
}
