package com.lhh.lnstagram.mvvm.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

/**
 * @author sunling
 * @date 2019/4/8
 */
public class ShapeUtil {

    public static GradientDrawable getCornerShape(Context context, String bgColor, int corner) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        try {
            gradientDrawable.setColor(Color.parseColor(bgColor));
        } catch (Exception e) {
            gradientDrawable.setColor(Color.parseColor("#15af9f"));
        }
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setCornerRadius(ViewUtil.dp2px(corner));
        return gradientDrawable;
    }

}