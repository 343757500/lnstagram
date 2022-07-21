package com.lhh.lnstagram.mvvm.util;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

/**
 * Created by XieWenJun on 2019/12/24.
 */
public class LanguageUtil {

    public static String getLanguageFileName(String languageCode) {
        return "strings_" + languageCode + ".txt";
    }

    public static void setDefaultLanguage(Context context, String language) {
        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        config.locale = new Locale(language);
        context.getResources().updateConfiguration(config, resources.getDisplayMetrics());
    }

}
