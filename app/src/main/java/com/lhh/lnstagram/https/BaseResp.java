package com.lhh.lnstagram.https;


import android.util.Log;

import androidx.annotation.NonNull;


import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class BaseResp implements Serializable {

    private int code = HttpError.ERROR_LOCAL_EXCEPTION; // http和tcp共用的
    private String codeMsg; // http和tcp共用的
    private String respResult; // http返回的json数据

    private boolean isCache; // 是否缓存数据


    /**
     * 自定义错误
     *
     * @param code
     * @param codeMsg
     */
    public BaseResp(int code, String codeMsg) {
        this.code = code;
        this.codeMsg = codeMsg;
    }


    /**
     * Http返回(正常返回的时候)
     *
     * @param respResult
     */
    public BaseResp(String respResult) {
        this.respResult = respResult;
        parserJson();
    }


    public boolean isCache() {
        return isCache;
    }

    public void setCache(boolean cache) {
        isCache = cache;
    }


    // 解析Http返回的Json
    private void parserJson() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(respResult);
            code = jsonObject.optInt("code");
            boolean hasCodeMsg = jsonObject.has("codeMsg");
            if (hasCodeMsg) {
                this.codeMsg = jsonObject.optString("codeMsg");
            }
            boolean hasMsg = jsonObject.has("msg");
            if (hasMsg) {
                this.codeMsg = jsonObject.optString("msg");
            }
        } catch (Exception e) {
            code = HttpError.ERROR_LOCAL_EXCEPTION;
            codeMsg = "Parser Exception";
        }
    }

    // 是否成功
    public boolean isOk() {
        return code == HttpError.SUCCESS;
    }


    /**
     * 是否特殊错误码
     *
     * @param error
     * @return
     */
    public boolean isError(@NonNull int error) {
        return code == error;
    }

    public String getMsg() {
        return codeMsg;
    }

    public int getCode() {
        return code;
    }

    public String getRespResult() {
        return respResult;
    }

    public <T> T getData(Class<T> classOfT) {
        return getData("data", classOfT);
    }

    public <T> T getData(String name, Class<T> classOfT) {
        String jsonStr;
        try {
            JSONObject jsonObject = new JSONObject(respResult);
            jsonStr = jsonObject.optJSONObject(name).toString();
        } catch (Exception e) {
            jsonStr = "{}";
        }
        return new Gson().fromJson(jsonStr, classOfT);
    }

    public <T> T getDataByType(Type typeOfT) {
        String jsonStr;
        try {
            JSONObject jsonObject = new JSONObject(respResult);
            jsonStr = jsonObject.optJSONObject("data").toString();
        } catch (Exception e) {
            jsonStr = "{}";
        }
        return new Gson().fromJson(jsonStr, typeOfT);
    }

    //获取最外层的基本数据结构类型
    public Object getBasicValue(String name) {
        try {
            JSONObject jsonObject = new JSONObject(respResult);
            return jsonObject.opt(name);
        } catch (Exception e) {
            return null;
        }
    }

    //获取data 里的基本数据结构类型
    public String getDataBasicValue(String name) {
        try {
            JSONObject jsonObject = new JSONObject(respResult);
            JSONObject data = jsonObject.optJSONObject("data");
            return data.optString(name);
        } catch (Exception e) {
            return "";
        }
    }







}
