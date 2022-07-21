package com.lhh.lnstagram.https;

import androidx.lifecycle.Observer;


import com.lhh.lnstagram.R;
import com.lhh.lnstagram.base.BaseApplication;
import com.lhh.lnstagram.https.rx.RxSubscriber;
import com.lhh.lnstagram.mvvm.event.LiveBus;
import com.lhh.lnstagram.mvvm.event.LiveBusKey;
import com.lhh.lnstagram.mvvm.util.HttpEncryptUtil;
import com.lhh.lnstagram.sp.HttpCacheSP;

import org.json.JSONArray;
import org.json.JSONObject;

import guide.util.StringUtil;
import okhttp3.ResponseBody;

public class BaseRxSubscriber extends RxSubscriber<ResponseBody> {

    BaseReq baseReq;
    Observer<BaseResp> onChanged;

    public BaseRxSubscriber(BaseReq baseReq, Observer<BaseResp> onChanged) {
        super(baseReq);
        this.baseReq = baseReq;
        this.onChanged = onChanged;
    }

    @Override
    public void onSuccess(ResponseBody responseBody) {
        try {
            String responseBodyStr = responseBody.string();

            JSONObject jsonObject;
            int code = 0;
            try {
                jsonObject = new JSONObject(responseBodyStr);
                code = jsonObject.optInt("code");
            } catch (Exception e) {
                jsonObject = new JSONObject("{}");
            }

            // 登录异常
            if (code == HttpError.USER_TOKEN_ERROR) {
                LiveBus.getDefault().postEvent(LiveBusKey.MAIN_TO_LOGIN, true);
                return;
            }
            // 游客模式状态
            if (code == HttpError.USER_ERROR_VISITOR) {
//                LiveBus.getDefault().postEvent(LiveBusKey.MAIN_TO_LOGIN_VISITOR, jsonObject.optString("codeMsg"));
               // DialogUtil.closeProgressDialog();
                BaseResp result = new BaseResp(HttpError.USER_ERROR_VISITOR, "");
                onChanged.onChanged(result);
                return;
            }

            // 默认的json
            String json = responseBodyStr;
            if (baseReq != null && baseReq.getSafeType() == BaseReq.SAFE_RSA && StringUtil.isNotEmpty(jsonObject.optString("data"))) {
                String data = HttpEncryptUtil.respDecodeByAES(jsonObject.optString("data"), baseReq.getSafeKey());
                if (StringUtil.isNotEmpty(data)) {
                    if (data.startsWith("[")) {
                        jsonObject.put("data", new JSONArray(data));
                    } else if (data.startsWith("{")) {
                        jsonObject.put("data", new JSONObject(data));
                    }
                    json = jsonObject.toString();
//                    if (BaseApplication.isDebug()) {
//                        LogUtil.e("Http", json);
//                    }
                }
            } else if (baseReq != null && baseReq.getSafeType() == BaseReq.SAFE_NO_TOKEN_OTP && StringUtil.isNotEmpty(jsonObject.optString("encryptResponse"))) {
                String data = HttpEncryptUtil.respDecodeByAES(jsonObject.optString("encryptResponse"), baseReq.getSafeKey());
                if (StringUtil.isNotEmpty(data)) {
                    json = data;
//                    if (BaseApplication.isDebug()) {
//                        LogUtil.e("Http", json);
//                    }
                }
            }

            // 缓存
            if (code == HttpError.SUCCESS && baseReq != null && baseReq.isShowCache()) {
                HttpCacheSP.getInstance().setHttpCache(baseReq.getCacheKey(), json);
                // 注册国家列表特殊处理一下
                if ("/common/countryCode".equals(baseReq.getPath())) {
                   // HttpCacheSP.getInstance().setCountryCodeCacheTime(TimeUtil.getCurrentTime4Local());
                }
            }

            // 解析
            BaseResp result = new BaseResp(json);
            // 响应(登录异常不响应)
            onChanged.onChanged(result);
        } catch (Exception e) {
            String error = "";
            if (baseReq != null && baseReq.isShowHttpError()) {
                error = e.getMessage();
            }
            BaseResp result = new BaseResp(HttpError.ERROR_LOCAL_EXCEPTION, error);
            onChanged.onChanged(result);
        }
    }

    @Override
    public void onFailure(String msg) {
        String error = "";
        if (baseReq != null && baseReq.isShowHttpError()) {
            error = msg;
        }
        BaseResp result = new BaseResp(HttpError.ERROR_LOCAL_EXCEPTION, error);
        onChanged.onChanged(result);
    }

    @Override
    protected void onNoNetWork() {
        super.onNoNetWork();
        String error = "";
        if (baseReq != null && baseReq.isShowHttpError()) {
            error = StringUtil.getString(R.string.common_txt_empty_network_not_good);
        }
        BaseResp result = new BaseResp(HttpError.ERROR_LOCAL_NO_NETWORK, error);
        onChanged.onChanged(result);
    }

}