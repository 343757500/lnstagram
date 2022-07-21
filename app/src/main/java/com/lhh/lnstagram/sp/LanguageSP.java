package com.lhh.lnstagram.sp;

import android.content.Context;

import com.lhh.lnstagram.base.BaseApplication;
import com.lhh.lnstagram.mvvm.base.BaseConfig;
import com.lhh.lnstagram.mvvm.util.SharePreferencesUtil;

public class LanguageSP extends SharePreferencesUtil {

    private static LanguageSP instance;

    private LanguageSP(Context context) {
        super(context, "language_sp");
    }

    public static final LanguageSP getInstance(Context context) {
        if (instance == null) {
            synchronized (LanguageSP.class) {
                if (instance == null) {
                    instance = new LanguageSP(context);
                }
            }
        }
        return instance;
    }

    public static final LanguageSP getInstance() {
        return getInstance(BaseApplication.getInstance());
    }

    // 当前选择的国家语言
    public String getCurrentLanguageCode() {
        return getValue("common_current_language_code", BaseConfig.isInner ? "zh" : "en");
    }
    // 当前选择的国家语言
    public void setCurrentLanguage(String languageCode) {
        setValue("common_current_language_code", languageCode);
    }

    // 当前选择的国家语言版本号
    public int getLanguageVersion(String languageCode) {
        return getValue("common_language_version_" + languageCode, 0);
    }
    // 当前选择的国家语言版本号
    public void setLanguageVersion(String languageCode, int version) {
        setValue("common_language_version_" +  languageCode, version);
    }

}
