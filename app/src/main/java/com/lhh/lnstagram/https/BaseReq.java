package com.lhh.lnstagram.https;

import android.text.TextUtils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lhh.lnstagram.base.BaseApplication;
import com.lhh.lnstagram.https.gson.GsonUtil;
import com.lhh.lnstagram.mvvm.base.BaseConfig;
import com.lhh.lnstagram.mvvm.util.DeviceUtil;
import com.lhh.lnstagram.mvvm.util.MD5Util;
import com.lhh.lnstagram.sp.CookiesSP;

import java.util.HashMap;
import java.util.Map;

import guide.util.StringUtil;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by XieWenJun on 17/4/27.
 */

public class BaseReq {

    public static final int SAFE_NONE = 0;
    public static final int SAFE_AES = 1; // 默认值
    public static final int SAFE_RSA = 2;
    public static final int SAFE_GET_POST_FILE_TOKEN = 3;
    public static final int SAFE_NO_TOKEN_OTP = 4;
    public static final int SAFE_PODCAST_NO = 5;

    private Gson gson;
    private boolean showCache;      // 是否先返回缓存
    private boolean isGetMethod;    // 是否用Get请求
    private String mUrl;
    private String mPath;
    private int timeOut = 0;        // 超时时间
    private String[] customCacheKeys;  // 自定义CacheKey，(Url+defaultCacheKey -> Url+customCacheKey)
    private boolean showHttpError = false;  // 是否显示HttpException
    private int safeType = SAFE_AES;
    private String safeKey;
    private String tempKey;

    public BaseReq setTempKey(String tempKey) {
        this.tempKey = tempKey;
        return this;
    }

    // 参数-jsonObj
    private Object mParamObj;
    // 参数-Map
    private Map<String, Object> mParams = new HashMap<>();
    //头部
    private Map<String, String> mHeader = new HashMap<>();

    /**
     * 初始化(被with调用)
     */
    public BaseReq(String mUrl, String mPath) {
        this.mUrl = mUrl;
        this.mPath = mPath;
        this.gson = GsonUtil.getGson();
    }

    // 初始化
    public static BaseReq api(String path) {
        return new BaseReq(BaseConfig.HTTP_HOST, path);
    }


    // podCast初始化
    public static BaseReq podCastApi(String path) {
        return new BaseReq(BaseConfig.HTTP_HOST_POD_CAST, path);
    }

    public static BaseReq liveApi(String path) {
        return new BaseReq(BaseConfig.HTTP_HOST_LIVE, path);
    }

    // 初始化
    public static BaseReq gameApi(String path) {
        return new BaseReq(BaseConfig.GAME_HTTP_HOST, path);
    }

    // 初始化 H5 授权
    public static BaseReq openApi(String path) {
        return new BaseReq(BaseConfig.ENV_HOST_OPEN, path);
    }

    // 初始化
    public static BaseReq merchantApi(String path) {
        return new BaseReq(BaseConfig.ENV_HOST_MERCHANT, path);
    }

    public static BaseReq fileApi(String path) {
        return new BaseReq(BaseConfig.FILE_HOST, path);
    }

    public static BaseReq postFileApi(String path) {
        return new BaseReq(BaseConfig.POST_FILE_HOST, path);
    }

    public static BaseReq watchFileApi(String path) {
        return new BaseReq(BaseConfig.ENV_HOST_FILE_WATCH, path);
    }

    public static BaseReq watchApi(String path) {
        return new BaseReq(BaseConfig.HTTP_HOST_WATCH, path);
    }


    public BaseReq post() {
        this.isGetMethod = false;
        return this;
    }

    public BaseReq get() {
        this.isGetMethod = true;
        return this;
    }

    public BaseReq put(String key, Object value) {
        this.mParams.put(key, value);
        return this;
    }

    public BaseReq putHeader(String key, String value) {
        this.mHeader.put(key, value);
        return this;
    }

    public BaseReq putAll(HashMap<String, Object> params) {
        if (params != null) {
            this.mParams.putAll(params);
        }
        return this;
    }

    public BaseReq setTimeOut(int timeOut) {
        this.timeOut = timeOut;
        return this;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public boolean isShowHttpError() {
        return showHttpError;
    }

    public BaseReq setSafeType(int safeType) {
        this.safeType = safeType;
        if (safeType == SAFE_RSA) {
            this.safeKey = System.currentTimeMillis() + "000";
        } else if (safeType == SAFE_GET_POST_FILE_TOKEN) {
            this.safeKey = System.currentTimeMillis() + "000";
            this.safeKey = "1234567890123456";
        } else if (safeType == SAFE_NO_TOKEN_OTP) {
            if (TextUtils.isEmpty(tempKey)) {
                tempKey = String.valueOf(System.currentTimeMillis());
            }
            safeKey = "abcdefgh" + tempKey.substring(0, 8);
            //LogUtil.e("OTP KEY=" + safeKey);
            putHeader("timestamp", tempKey);
        }

        return this;
    }

    public int getSafeType() {
        return safeType;
    }

    public void setSafeKey(String safeKey) {
        this.safeKey = safeKey;
    }

    public String getSafeKey() {
        return safeKey;
    }

    public BaseReq setShowHttpError(boolean showHttpError) {
        this.showHttpError = showHttpError;
        return this;
    }

    public BaseReq setShowCache(boolean showCache) {
        this.showCache = showCache;
        return this;
    }

    // 如果打算和参数无法，随便传一个字符串
    // 如果打算自定义参数，可以传"token"+CookiesSP.getCookies().getToken()
    public BaseReq setCustomCacheKey(String... customCacheKeys) {
        if (customCacheKeys == null || (customCacheKeys.length == 1 && StringUtil.isEmpty(customCacheKeys[0]))) {
            return this;
        }
        this.customCacheKeys = customCacheKeys;
        return this;
    }

    public BaseReq putObj(Object obj) {
        this.mParamObj = obj;
        return this;
    }

    public String getUrl() {
        return mUrl + mPath;
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
        return MD5Util.encryptionMD5(sb.toString());
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
        } else if (getSafeType() == SAFE_RSA) {
            // RSA
           // content = getJsonContent4RSA();
        } else if (getSafeType() == SAFE_GET_POST_FILE_TOKEN) {
            // 获取文件服务器token
          //  content = getJsonContent4PostFileToken();
        } else if (getSafeType() == SAFE_NO_TOKEN_OTP) {
          //  content = getNoTokenOTPContent();
        } else if (getSafeType() == SAFE_PODCAST_NO) {
            //podCast特殊使用
            content = getJsonContentPodCast();
        } else {
            // 旧方式
            content = getJsonContent(false);
        }
        return RequestBody.create(MediaType.parse(BaseConfig.HTTP_MEDIA_TYPE_JSON), content);
    }


    //PodCast特殊使用
    private String getJsonContentPodCast() {
        if (mParamObj != null) {
            // 如果有请求对象，则用对象，忽略Map
            return gson.toJson(mParamObj);
        } else {
            // 如果没有mParamObj，则用mParams
            mParams.put("userId", CookiesSP.getCookies().getUserId());
            return gson.toJson(mParams);
        }
    }

    // post-json格式参数
    private String getJsonContent(boolean addToken) {
        if (mParamObj != null) {
            // 如果有请求对象，则用对象，忽略Map
            return gson.toJson(mParamObj);
        } else {
            // 如果没有mParamObj，则用mParams
            mParams.put("userId", CookiesSP.getCookies().getUserId());
            if (addToken) {
                mParams.put("token", CookiesSP.getCookies().getToken());
            }
            return gson.toJson(mParams);
        }
    }







    private Object getSignContent() {
        StringBuilder stringBuilder = new StringBuilder();

        Object countryCode = mParams.get("countryCode");
        stringBuilder.append("countryCode").append("=").append(countryCode).append("&");

        Object type = mParams.get("type");
        stringBuilder.append("type").append("=").append(type).append("&");

        stringBuilder.append("monitorContent").append("=").append("").append("&");

        Object mobile = mParams.get("mobile");
        stringBuilder.append("mobile").append("=").append(mobile).append("&");

        Object deviceId = mParams.get("deviceId");
        stringBuilder.append("deviceId").append("=").append(deviceId == null ? "" : deviceId);

        stringBuilder.append("/").append("abcdefgh");
        return MD5Util.encryptionMD5(stringBuilder.toString());
    }



    //    // 初始化
    public static BaseReq testMeetingDataApi(String path) {
        return new BaseReq(BaseConfig.ENV_HOST_AV_COLLECT_URL, path);
        //return new BaseReq("https://videote.im.sasai.mobi:28443", path);
    }

    public void updateSafeKey(String timeStamp, int SafeType) {
        setTempKey(timeStamp);
        setSafeType(safeType);
    }

    @Override
    public String toString() {
        return "BaseReq{" +
                "mUrl='" + mUrl + '\'' +
                ", mPath='" + mPath + '\'' +
                '}';
    }
}
