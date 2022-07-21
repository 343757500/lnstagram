package com.lhh.lnstagram.sp;

import android.content.Context;

import com.lhh.lnstagram.base.BaseApplication;
import com.lhh.lnstagram.mvvm.util.SharePreferencesUtil;


/**
 * Http缓存
 */
public class HttpCacheSP extends SharePreferencesUtil {

    private static final String SP_NAME = "HttpCache";
    private static HttpCacheSP instance;

    private HttpCacheSP(Context context) {
        super(context, SP_NAME);
    }

    public static final HttpCacheSP getInstance() {
        if (instance == null) {
            synchronized (HttpCacheSP.class) {
                if (instance == null) {
                    instance = new HttpCacheSP(BaseApplication.getInstance());
                }
            }
        }
        return instance;
    }

    public void setHttpCache(String key, String string) {
        setValue("key_http_cache" + key, string);
    }

    public String getHttpCache(String key) {
        return getValue("key_http_cache" + key, "");
    }

    public void setCountryCodeCacheTime(long time) {
        setValue("key_country_code_cache_time", time);
    }

    public long getCountryCodeCacheTime() {
        return getValue("key_country_code_cache_time", 0L);
    }

}
