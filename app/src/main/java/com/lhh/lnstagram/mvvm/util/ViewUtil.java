package com.lhh.lnstagram.mvvm.util;

import android.text.InputFilter;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import guide.util.Utils;

/**
 * Created by XieWenJun on 2018/11/28.
 */
public class ViewUtil {

    public static int dp2px(final float dpValue) {
        final float scale = Utils.getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp(final float pxValue) {
        final float scale = Utils.getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static boolean isSmallPhone() {
        DisplayMetrics displayMetrics = Utils.getContext().getResources().getDisplayMetrics();
        final int heightPixels = displayMetrics.heightPixels;
        final float scale = displayMetrics.density;
        int heightDp = (int) (heightPixels / scale + 0.5f);
        if (heightDp < 600) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isSuperSmallPhone() {
        DisplayMetrics displayMetrics = Utils.getContext().getResources().getDisplayMetrics();
        final int heightPixels = displayMetrics.heightPixels;
        final float scale = displayMetrics.density;
        int heightDp = (int) (heightPixels / scale + 0.5f);
        if (heightDp < 500) {
            return true;
        } else {
            return false;
        }
    }

    public static int getPhoneHeight() {
        DisplayMetrics displayMetrics = Utils.getContext().getResources().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

    public static int getPhoneWidth() {
        DisplayMetrics displayMetrics = Utils.getContext().getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    /**
     * 禁止EditText输入空格和换行符
     *
     * @param editText EditText输入框
     */
    public static void setEditTextInhibitInputSpace(EditText editText) {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (source.equals(" ") || source.toString().contentEquals("\n")) {
                    return "";
                } else {
                    return null;
                }
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }

    /**
     * 禁止EditText输入特殊字符
     *
     * @param editText
     */
    public static void setEditTextInhibitInputSpeChat(EditText editText) {

        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String speChat = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
                Pattern pattern = Pattern.compile(speChat);
                Matcher matcher = pattern.matcher(source.toString());
                if (matcher.find()) return "";
                else return null;
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }

    /**
     * 设置EditText输入上限
     *
     * @param editText EditText输入框
     */
    public static void setEditTextLengthLimit(EditText editText, int length) {
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(length)});
    }

    /**
     * 禁止EditText输入特殊字符（只能输入汉字；英文；数字）
     *
     * @param editText
     */
    public static void setEditTextInhibitInputSpeChat(EditText editText, int maxLength) {
        InputFilter inputFilter = new InputFilter() {
            Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\u4E00-\\u9FA5_]");

            @Override
            public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
                Matcher matcher = pattern.matcher(charSequence);
                if (!matcher.find()) {
                    return null;
                } else {
                    return "";
                }

            }
        };
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength), inputFilter});
    }


}
