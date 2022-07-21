package com.lhh.lnstagram.mvvm.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferencesUtil {

    protected SharedPreferences sharedPreferences;

    protected SharePreferencesUtil(Context context, String spName) {
        sharedPreferences = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
    }

    /* get and put String value */
    protected void setValue(String key, String value) {
        sharedPreferences.edit().putString(key, value).commit();
    }

    protected String getValue(String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }

    /* get and put boolean value */
    protected void setValue(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).commit();
    }

    protected boolean getValue(String key, boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }

    /* get and put int value */
    protected void setValue(String key, int value) {
        sharedPreferences.edit().putInt(key, value).commit();
    }

    protected int getValue(String key, int defValue) {
        return sharedPreferences.getInt(key, defValue);
    }

    /* get and put int value */
    protected void setValue(String key, float value) {
        sharedPreferences.edit().putFloat(key, value).commit();
    }

    protected float getValue(String key, float defValue) {
        return sharedPreferences.getFloat(key, defValue);
    }

    /* get and put long value */
    protected void setValue(String key, long value) {
        sharedPreferences.edit().putLong(key, value).commit();
    }

    protected long getValue(String key, long defValue) {
        return sharedPreferences.getLong(key, defValue);
    }

    protected void spClearValue(String key) {
        sharedPreferences.edit().putString(key, "").commit();
    }

    /* get and put string set value */
//	protected void setValue(String key, Set<String> value) {
//		sharedPreferences.edit().putStringSet(key, value).commit();
//	}
//
//	protected Set<String> getValue(String key, Set<String> defValue) {
//		return sharedPreferences.getStringSet(key, defValue);
//	}

    /* 添加和移除sp改变的监听 */
    public void registerSpChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);
    }

    public void unregisterSpChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener);
    }
}
