package com.lhh.lnstagram.https.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by XieWenJun on 2019-10-08.
 */
public class GsonUtil {

    private static Gson gson = null;

    public static Gson getGson() {
        if (gson == null) {
            synchronized (GsonUtil.class) {
                if (gson == null) {
                    gson = new GsonBuilder().disableHtmlEscaping().create();
                }
            }
        }
        return gson;
    }
}
