package com.lhh.lnstagram.sp;

import android.content.Context;
import android.util.Log;
import com.lhh.lnstagram.base.BaseApplication;
import com.lhh.lnstagram.https.gson.GsonUtil;
import com.lhh.lnstagram.mvvm.base.BaseConfig;
import com.lhh.lnstagram.mvvm.util.DeviceUtil;
import com.lhh.lnstagram.mvvm.util.SharePreferencesUtil;


/**
 * 公共缓存
 *
 * @author XieWenjun
 * @time 2014-11-13
 */
public class CommonSP extends SharePreferencesUtil {

    private static final String SP_NAME = "Common";
    private static CommonSP instance;

    private final String KEY_CACHE = "key_cache";

    private CommonSP(Context context) {
        super(context, SP_NAME);
    }

    public static final CommonSP getInstance() {
        if (instance == null) {
            synchronized (CommonSP.class) {
                if (instance == null) {
                    instance = new CommonSP(BaseApplication.getInstance());
                }
            }
        }
        return instance;
    }


    /**
     * 缓存对象（根据方法名）
     *
     * @param src
     */
    public void setCache4Bean(Object src) {
        try {
            setValue(KEY_CACHE + "4bean" + src.getClass().getSimpleName() + CookiesSP.getCookies().getUserId(), GsonUtil.getGson().toJson(src));
        } catch (Exception e) {
            Log.e("【缓存失败】", src.getClass().getSimpleName());
        }
    }

    /**
     * 根据对象获取起缓存，可能返回为null（根据方法名）
     *
     * @param classOfT
     * @param <T>
     * @return
     */
    public <T> T getCache4Bean(Class<T> classOfT) {
        try {
            return GsonUtil.getGson().fromJson(getValue(KEY_CACHE + "4bean" + classOfT.getSimpleName() + CookiesSP.getCookies().getUserId(), ""), classOfT);
        } catch (Exception e) {
            Log.e("【获取缓存失败】", classOfT.getClass().getSimpleName());
            return null;
        }
    }


    public void setCache(String key, String string) {
        setValue(KEY_CACHE + key, string);
    }

    public String getCache(String key) {
        return getValue(KEY_CACHE + key, "");
    }

    public String getCache(String key, String defaultValue) {
        return getValue(KEY_CACHE + key, defaultValue);
    }

    public void setFloatWindowUrl(String url) {
        setValue("FloatWindowUrl", url);
    }

    public String getFloatWindowUrl() {
        return getValue("FloatWindowUrl", "");
    }


    public void setFirstKeyBoardHeight(int height) {
        setValue("first_keyboard_height", height);
    }

    public int getFirstKeyBoardHeight() {
        return getValue("first_keyboard_height", 0);
    }

    public void setLastLogin4AppBuildVersion(String userId) {
        setValue("last_login_build_version_" + userId, BaseConfig.VERSION_CODE);
    }

    public int getLastLogin4AppBuildVersion(String userId) {
        return getValue("last_login_build_version_" + userId, 0);
    }


    public void setLastPingSendTime(long time) {
        setValue("key_cache_ping_send_time", time);
    }

    public long getLastPingSendTime() {
        return getValue("key_cache_ping_send_time", 0L);
    }

    public void setLastPingAckTime(long time) {
        setValue("key_cache_ping_ack_time", time);
    }

    public long getLastPingAckTime() {
        return getValue("key_cache_ping_ack_time", 0L);
    }

    public void setClearImageCache() {
        setValue("key_clear_image_cache", DeviceUtil.getAppVersionCode(BaseApplication.getInstance()));
    }

    public int getClearImageCache() {
        return getValue("key_clear_image_cache", 0);
    }

    public void setSelectRegisterCountryId(String countryId) {
        setValue("select_register_country_id" + "0", countryId);
    }

    public String getSelectRegisterCountryId() {
        return getValue("select_register_country_id" + "0", "");
    }


    public void setMobileContactUploadMd5(int type, String md5) {
        setValue("mobile_contact_upload_md5_by_" + type + "_" + CookiesSP.getCookies().getUserId(), md5);
    }

    public void setUpdatePopoverTimes(int times, String androidVersion) {//更新弹窗弹窗的次数
        setValue("up_dialog_times_" + CookiesSP.getCookies().getUserId() + "_version_name_" + androidVersion, times);
    }

    public int getUpdatePopoverTimes(String androidVersion) {
        return getValue("up_dialog_times_" + CookiesSP.getCookies().getUserId() + "_version_name_" + androidVersion, 1);
    }

    public void setIsAppFirstInstallation(boolean value) {//保存App是否首次安装
        setValue("is_app_first_installation", value);
    }

    public boolean getIsAppFirstInstallation() {
        return getValue("is_app_first_installation", true);
    }

    public void setInviteBy(long value) {//保存深层链接中推荐码
        setValue("save_invite_code", value);
    }

    public long getInviteBy() {
        return getValue("save_invite_code", 0L);
    }

    /**
     * type == 2 上传后返回数据的md5
     * <p>
     * type == 1 当前通讯录数据的md5值
     *
     * @param type
     * @return
     */
    public String getMobileContactUploadMd5(int type) {
        return getValue("mobile_contact_upload_md5_by_" + type + "_" + CookiesSP.getCookies().getUserId(), "");
    }

    public void setUploadMobileContactPermissionType(int agreeType) {
        setValue("has_upload_mobile_contact_permission_" + CookiesSP.getCookies().getUserId(), agreeType);
    }

    public int getUploadMobileContactPermissionType() {
        return getValue("has_upload_mobile_contact_permission_" + CookiesSP.getCookies().getUserId(), 0);
    }

    public void setCacheClearTime(int time) {
        setValue("cache_clear_time", time);
    }

    public int getCacheClearTime() {
        return getValue("cache_clear_time", 5);
    }







    public long getLastAutoClearCacheTime() {
        return getValue("last_cache_clear_time", 0L);
    }
}
