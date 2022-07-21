package com.lhh.lnstagram.sp;

import android.content.Context;

import com.lhh.lnstagram.base.BaseApplication;
import com.lhh.lnstagram.mvvm.util.SharePreferencesUtil;

public class AppLockSP extends SharePreferencesUtil {
    private static AppLockSP instance;

    private final String KEY_PATTERN = "app_lock_pattern";
    private final String KEY_LOCK_TYPE = "app_lock_type";
    private final String KEY_LOCK_FORGET_PROGRESS = "app_lock_forget_progress";
    private final String KEY_LOCK_TO_SETTING = "app_lock_to_setting";
    private final String KEY_LOCK_FROM_VERIFY_OR_FORGET = "app_lock_from_verify_or_forget";

    private AppLockSP(Context context) {
        super(context, "app_lock");
    }

    public static final AppLockSP getInstance() {
        if (instance == null) {
            synchronized (AppLockSP.class) {
                if (instance == null) {
                    instance = new AppLockSP(BaseApplication.getInstance());
                }
            }
        }
        return instance;
    }

    public void setPattern(String pattern) {
        setValue(KEY_PATTERN + CookiesSP.getCookies().getUserId(), pattern);
    }

    public String getPattern() {
        return getValue(KEY_PATTERN + CookiesSP.getCookies().getUserId(), "");
    }

    public void setLockType(int type) {
        setValue(KEY_LOCK_TYPE + CookiesSP.getCookies().getUserId(), type);
    }

    public int getLockType() {
        return getValue(KEY_LOCK_TYPE + CookiesSP.getCookies().getUserId(), 3);
    }

    public void setAppLockForgetProgress(boolean progress) {
        setValue(KEY_LOCK_FORGET_PROGRESS + CookiesSP.getCookies().getUserId(), progress);
    }
    public boolean getAppLockForgetProgress() {
        return getValue(KEY_LOCK_FORGET_PROGRESS + CookiesSP.getCookies().getUserId(), false);
    }

    public void setToLockSetting(boolean toSetting) {
        setValue(KEY_LOCK_TO_SETTING + CookiesSP.getCookies().getUserId(), toSetting);
    }
    public boolean getToLockSetting() {
        return getValue(KEY_LOCK_TO_SETTING + CookiesSP.getCookies().getUserId(), false);
    }

    public void setFromVerifyOrForget(boolean toSetting) {
        setValue(KEY_LOCK_FROM_VERIFY_OR_FORGET + CookiesSP.getCookies().getUserId(), toSetting);
    }
    public boolean getFromVerifyOrForget() {
        return getValue(KEY_LOCK_FROM_VERIFY_OR_FORGET + CookiesSP.getCookies().getUserId(), false);
    }

}
