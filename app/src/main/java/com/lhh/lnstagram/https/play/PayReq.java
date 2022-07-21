package com.lhh.lnstagram.https.play;


import com.google.gson.Gson;
import com.lhh.lnstagram.base.BaseApplication;
import com.lhh.lnstagram.https.gson.GsonUtil;
import com.lhh.lnstagram.mvvm.base.BaseConfig;
import com.lhh.lnstagram.mvvm.util.DeviceUtil;
import com.lhh.lnstagram.mvvm.util.MD5Util;
import com.lhh.lnstagram.sp.CookiesSP;
import com.lhh.lnstagram.sp.PaySP;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import guide.util.StringUtil;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by XieWenJun on 17/4/27.
 */

public class PayReq {

    public static final int SAFE_NONE = 0;
    public static final int SAFE_AES = 1; // 默认值
    public static final int SAFE_GET_TOKEN = 3;

    private Gson gson;
    private boolean showCache;      // 是否先返回缓存
    private boolean isGetMethod;    // 是否用Get请求
    private String mUrl;
    private String mPath;
    private int timeOut = 0;        // 超时时间
    private String[] customCacheKeys;  // 自定义CacheKey，(Url+defaultCacheKey -> Url+customCacheKey)
    private boolean showHttpError = true;  // 是否显示HttpException
    private int safeType = SAFE_AES;
    private String safeKey;

    // 参数-jsonObj
    private Object mParamObj;
    // 参数-Map
    private Map<String, Object> mParams = new HashMap<>();
    //头部
    private Map<String, String> mHeader = new HashMap<>();

    /**
     * 初始化(被with调用)
     */
    public PayReq(String mUrl, String mPath) {
        this.mUrl = mUrl;
        this.mPath = mPath;
        this.gson = GsonUtil.getGson();
    }

    // 初始化
    public static PayReq payApi(String path) {
        return new PayReq(BaseConfig.PAY_HOST, path);
    }

    public PayReq post() {
        this.isGetMethod = false;
        return this;
    }

    public PayReq get() {
        this.isGetMethod = true;
        return this;
    }

    public PayReq put(String key, Object value) {
        this.mParams.put(key, value);
        return this;
    }

    public PayReq putHeader(String key, String value) {
        this.mHeader.put(key, value);
        return this;
    }

    public PayReq putAll(HashMap<String, Object> params) {
        if (params != null) {
            this.mParams.putAll(params);
        }
        return this;
    }

    public PayReq setTimeOut(int timeOut) {
        this.timeOut = timeOut;
        return this;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public boolean isShowHttpError() {
        return showHttpError;
    }

    public PayReq setSafeType(int safeType) {
        this.safeType = safeType;
        if (safeType == SAFE_GET_TOKEN) {
            this.safeKey = MD5Util.md5(System.currentTimeMillis() + "000");
        }
        return this;
    }

    public int getSafeType() {
        return safeType;
    }

    public String getSafeKey() {
        return safeKey;
    }

    public PayReq setShowHttpError(boolean showHttpError) {
        this.showHttpError = showHttpError;
        return this;
    }

    public PayReq setShowCache(boolean showCache) {
        this.showCache = showCache;
        return this;
    }

    // 如果打算和参数无法，随便传一个字符串
    // 如果打算自定义参数，可以传"token"+CookiesSP.getCookies().getToken()
    public PayReq setCustomCacheKey(String... customCacheKeys) {
        if (customCacheKeys == null || (customCacheKeys.length == 1 && StringUtil.isEmpty(customCacheKeys[0]))) {
            return this;
        }
        this.customCacheKeys = customCacheKeys;
        return this;
    }

    public PayReq putObj(Object obj) {
        this.mParamObj = obj;
        return this;
    }

    public String getUrl() {
        String param = String.format(BaseConfig.PAY_HOST_PARAM, PaySP.getInstance().getAccessToken(), "1.1", DeviceUtil.getDeviceId());
        if (getSafeType() == SAFE_GET_TOKEN) {
            param = String.format(BaseConfig.PAY_HOST_PARAM, "", "1.1", DeviceUtil.getDeviceId());
        }
        return mUrl + mPath + param;
    }

    public String getPath() {
        return mPath;
    }

    public boolean isShowCache() {
        return showCache;
    }

    public boolean isGetMethod() {
        return isGetMethod;
    }

    public Map<String, String> getHeader() {
        mHeader.put("h_open_uid", CookiesSP.getCookies().getUserId());
        mHeader.put("appName", DeviceUtil.getAppName(BaseApplication.getInstance()));
        mHeader.put("appPlatform", "Android");
        mHeader.put("appVersion", BaseConfig.VERSION_NAME);
        mHeader.put("appType", BaseConfig.APP_TYPE);
        return mHeader;
    }

    public String getCacheKey() {
        return getUrl() + "@" + getCustomCacheKey();
    }

    private String getCustomCacheKey() {
        StringBuffer sb = new StringBuffer();
        // 区分用户
        sb.append("userId").append(CookiesSP.getCookies().getUserId() + ",");
        if (customCacheKeys != null && customCacheKeys.length > 0) {
            // 如果自定义了key
            for (String key : customCacheKeys) {
                sb.append(key + ",");
            }
        } else {
            // 没有则使用参数方式拼
            for (Map.Entry<String, Object> item : mParams.entrySet()) {
                if (StringUtil.isNotEmpty(item.getKey())
                        && !"userId".equals(item.getKey()) && !"token".equals(item.getKey())
                        && item.getValue() != null && item.getValue() instanceof String) {
                    sb.append(item.getKey()).append(item.getValue() + ",");
                }
            }
        }
        return MD5Util.md5(sb.toString());
    }

    // get-格式参数
    public Map<String, String> getStrBodyMap() {
        Map<String, String> strMap = new HashMap<>();
        for (Map.Entry<String, Object> item : mParams.entrySet()) {
            if (StringUtil.isNotEmpty(item.getKey()) && item.getValue() != null && item.getValue() instanceof String) {
                strMap.put(item.getKey(), (String) item.getValue());
            }
        }
        return strMap;
    }

    // post-json格式参数
    public RequestBody getBody() {
        String content = null;
        if (getSafeType() == SAFE_AES) {
            // AES
          //  content = getJsonContent4AES();
        } else if (getSafeType() == SAFE_GET_TOKEN) {
            // GetToken
          //  content = getJsonContent4GetToken();
        } else {
            // 旧方式
            content = getJsonContent(false);
        }
        return RequestBody.create(MediaType.parse(BaseConfig.HTTP_MEDIA_TYPE_JSON), content);
    }

    // post-json格式参数
    public String getJsonContent(boolean addImToken) {
        if (mParamObj != null) {
            // 如果有请求对象，则用对象，忽略Map
            if (mParamObj instanceof JSONObject) {
                return ((JSONObject) mParamObj).toString();
            } else {
                return gson.toJson(mParamObj);
            }
        } else {
            // 如果没有mParamObj，则用mParams
            if (addImToken) {
                mParams.put("userId", CookiesSP.getCookies().getUserId());
                mParams.put("token", CookiesSP.getCookies().getToken());
            }
            return gson.toJson(mParams);
        }
    }


}
