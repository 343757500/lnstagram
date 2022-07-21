package com.lhh.lnstagram.sp;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import com.google.gson.Gson;
import com.lhh.lnstagram.base.BaseApplication;
import com.lhh.lnstagram.bean.UserInfo;
import com.lhh.lnstagram.mvvm.util.SharePreferencesUtil;

import guide.util.StringUtil;

/**
 * 用户缓存
 *
 * @author XieWenjun
 * @time 2014-11-13
 */
public class CookiesSP extends SharePreferencesUtil {

    private UserInfo accountInfo;
    private static CookiesSP instance;
    private final String KEY_USER = "user_cookies";

    private CookiesSP(Context context) {
        super(context, "Cookies");
    }

    public static final CookiesSP getInstance() {
        if (instance == null) {
            synchronized (CookiesSP.class) {
                if (instance == null) {
                    instance = new CookiesSP(BaseApplication.getInstance());
                }
            }
        }
        return instance;
    }

    /**
     * 先从内存拿数据，再去从缓存拿数据
     *
     * @return
     */
    private UserInfo get() {
        if (accountInfo == null) {
            accountInfo = new Gson().fromJson(getValue(KEY_USER, "{}"), UserInfo.class);
        }
        return accountInfo;
    }


    /**
     * 保存用户所有字段到内存和缓存中 // 2018.10.31 只有这个是非静态方法？？
     *
     * @param cookies
     */
    public void save(UserInfo cookies) {
        this.accountInfo = cookies; // 内存
        setValue(KEY_USER, new Gson().toJson(cookies)); // sp
        //CookiesDao.get().saveCookie(cookies);
    }

    /**
     * 获取用户信息
     */
    public static UserInfo getCookies() {
        return getInstance().get();
    }

    /**
     * 是否登录
     */
    public static boolean isLogin() {
        UserInfo cookies = getInstance().get();
        if (cookies == null) {
            return false;
        }
        if (StringUtil.isEmpty(cookies.getUserId())
                || StringUtil.isEmpty(cookies.getToken())) {
            return false;
        }
        return true;
    }

    /**
     * 退出，清空Token
     */
    public static void exitToken() {
        // 清空Token
        UserInfo newCookies = getInstance().get();
        newCookies.setToken("");
        newCookies.setTcpToken("");
        newCookies.setEncryption("");
        newCookies.setKyc("");
        getInstance().save(newCookies);

        // 清空WebView的登录Cookies
        // LoginManager.loginOut(context);
        CookieSyncManager.createInstance(BaseApplication.getInstance());
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        CookieSyncManager.getInstance().sync();

    }

    /**
     * 清空用户数据但保留手机号码，方便登录
     */
    @Deprecated
    public static void clear() {
        UserInfo newCookies = new UserInfo();
        UserInfo oldCookies = getInstance().get();
        if (oldCookies != null && !TextUtils.isEmpty(oldCookies.getMobile())) {
            newCookies.setMobile(oldCookies.getMobile());
        }
        getInstance().save(newCookies);
    }

    /**
     * 更新用户信息的所有字段，不会清空登录状态
     *
     * @param newUserInfo
     */
    public static void update(UserInfo newUserInfo) {
        // 旧数据中拿到登录状态
        UserInfo cookies = CookiesSP.getCookies();
        newUserInfo.setToken(cookies.getToken());
        newUserInfo.setTcpToken(cookies.getTcpToken());
        newUserInfo.setEncryption(cookies.getEncryption());
        newUserInfo.setKyc(cookies.getKyc());
        // 保存新数据
        getInstance().save(newUserInfo);

    }


    /**
     * 保存用户所有字段到内存和缓存中 // 2018.10.31 只有这个是非静态方法？？
     *
     * @param cookies
     */
    public void saveAv(UserInfo cookies) {
        this.accountInfo = cookies; // 内存
        setValue(KEY_USER, new Gson().toJson(cookies)); // sp
        //CookiesDao.get().saveCookie(cookies);
    }

    /**
     * 更新用户信息的所有字段，不会清空登录状态
     */
    public static void updateKyc() {
        // 旧数据中拿到登录状态
        UserInfo cookies = CookiesSP.getCookies();
        if (StringUtil.isEmpty(cookies.getKyc())) {
            cookies.setKyc("1");
            // 保存新数据
            getInstance().save(cookies);
        }
    }
}
