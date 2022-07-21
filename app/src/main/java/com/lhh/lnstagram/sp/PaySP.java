package com.lhh.lnstagram.sp;

import android.content.Context;


import com.lhh.lnstagram.base.BaseApplication;
import com.lhh.lnstagram.mvvm.util.SharePreferencesUtil;

public class PaySP extends SharePreferencesUtil {
    private static PaySP instance;

    private static final String KEY_ACCESS_TOKEN = "pay_access_token";
    private static final String KEY_OPEN_ID = "pay_open_id";

    private static final String KEY_HIDDEN_BALANCE = "pay_hidden_balance";
    private static final String KEY_SERVICE_COUNTRY_NAME = "pay_service_country_name";//默认国家名字
    private static final String KEY_SERVICE_COUNTRY_ISO = "pay_service_country_iso";//默认国家代码

    private static final String KEY_SECURITY_MOBILE = "pay_security_mobile";//安全手机号
    private static final String KEY_SECURITY_MOBILE_CODE = "pay_security_mobile_code";//安全手机号 国家号
    private static final String KEY_TOKENS_AMOUNT = "pay_tokens_amount";//
    private static final String KEY_REMIT_TOKEN = "pay_remit_token";


    private PaySP(Context context) {
        super(context, "pay_sp");
    }

    public static final PaySP getInstance() {
        if (instance == null) {
            synchronized (PaySP.class) {
                if (instance == null) {
                    instance = new PaySP(BaseApplication.getInstance());
                }
            }
        }
        return instance;
    }

    public void setAccessToken(String token) {
        setValue(KEY_ACCESS_TOKEN + CookiesSP.getCookies().getUserId(), token);
    }

    public String getAccessToken() {
        return getValue(KEY_ACCESS_TOKEN + CookiesSP.getCookies().getUserId(), "");
    }

    public void setOpenId(String openId) {
        setValue(KEY_OPEN_ID + CookiesSP.getCookies().getUserId(), openId);
    }

    public String getOpenId() {
        return getValue(KEY_OPEN_ID + CookiesSP.getCookies().getUserId(), "");
    }

    public void setKeyHiddenBalance(int status) {//0 显示  1 隐藏
        setValue(KEY_HIDDEN_BALANCE + CookiesSP.getCookies().getUserId(), status);
    }

    public int getKeyHiddenBalance() {
        return getValue(KEY_HIDDEN_BALANCE + CookiesSP.getCookies().getUserId(), 0);
    }

    public String getCountryName() {
        return getValue(KEY_SERVICE_COUNTRY_NAME + CookiesSP.getCookies().getUserId(), "");
    }

    public void setCountryName(String countryName) {
        setValue(KEY_SERVICE_COUNTRY_NAME + CookiesSP.getCookies().getUserId(), countryName);
    }


    public String getCountryIso() {
        return getValue(KEY_SERVICE_COUNTRY_ISO + CookiesSP.getCookies().getUserId(), "");
    }

    public void setCountryIso(String countryIso) {
        setValue(KEY_SERVICE_COUNTRY_ISO + CookiesSP.getCookies().getUserId(), countryIso);
    }

    public String getSecurityMobile() {
        return getValue(KEY_SECURITY_MOBILE + CookiesSP.getCookies().getUserId(), "");
    }

    public void setSecurityMobile(String mobile) {
        setValue(KEY_SECURITY_MOBILE + CookiesSP.getCookies().getUserId(), mobile);
    }

    public String getSecurityMobileCode() {
        return getValue(KEY_SECURITY_MOBILE_CODE + CookiesSP.getCookies().getUserId(), "");
    }

    public void setSecurityMobileCode(String mobileCode) {
        setValue(KEY_SECURITY_MOBILE_CODE + CookiesSP.getCookies().getUserId(), mobileCode);
    }

    public void setKeyTokensAmount(String tokensAmount) {
        setValue(KEY_TOKENS_AMOUNT + CookiesSP.getCookies().getUserId(), tokensAmount);
    }

    public String getTokensAmount() {
        return getValue(KEY_TOKENS_AMOUNT + CookiesSP.getCookies().getUserId(),"");
    }
}
