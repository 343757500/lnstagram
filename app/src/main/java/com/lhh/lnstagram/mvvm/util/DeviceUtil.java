package com.lhh.lnstagram.mvvm.util;

import static android.content.Context.AUDIO_SERVICE;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.lhh.lnstagram.R;
import com.lhh.lnstagram.base.BaseApplication;
import com.lhh.lnstagram.manager.BaseHandlerManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;

import guide.util.StringUtil;
import guide.util.Utils;

/**
 * 信息工具类
 *
 * @author XieWenjun
 * @version 1.0, 2014-9-17 上午9:14:27
 */
public class DeviceUtil {

    /**
     * 获取设备名
     */
    public static String getDeviceName() {
        try {
            return Build.BRAND + " " + Build.MODEL;
        } catch (Exception e) {
            return "Unknown";
        }
    }

    private static String fileDeviceId;

    public static String getDeviceId() {
        if (StringUtil.isEmpty(fileDeviceId)) {
            fileDeviceId = readFileDeviceId();  // 读文件
        }

        if (StringUtil.isEmpty(fileDeviceId)) {
            fileDeviceId = createDeviceId();
            BaseHandlerManager.getInstance().getWorkHandler().post(() -> {
                // 写入文件
                writeFileDeviceId(fileDeviceId);
            });
        }

        return fileDeviceId;
    }

    private static String readFileDeviceId() {
        try {
            File file = FileUtil.newFile(Utils.getContext(), "", "Sasai");
            return FileUtil.readFile(file);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static void writeFileDeviceId(String deviceId) {
        try {
            File file = FileUtil.newFile(Utils.getContext(), "", "Sasai");
            FileUtil.writeFile(file, deviceId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取设备唯一值
     */
    private static String createDeviceId() {
        String tmDevice = getDevice();
        String tmSerial = getSERIAL();
        String androidId = getAndroidId(Utils.getContext());
        try {
            return new UUID(androidId.hashCode(),
                    ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode())
                    .toString().replace("-", "");
        } catch (Exception e) {
            // 保证DeviceId不为空
            return UUID.randomUUID().toString().replace("-", "");
        }
    }

    /**
     * 获取当前时区
     */
    public static String getDeviceCurrentTimeZone() {
        try {
            TimeZone tz = TimeZone.getDefault();
            return tz.getDisplayName(false, TimeZone.SHORT);
        } catch (Exception e) {
            return "";
        }

    }


    /**
     * 获取当前系统国家和语言格式
     */
    public static String[] getDeviceCountryAndLanguage() {
        try {
            Locale locale = Utils.getContext().getResources().getConfiguration().locale;
            String country = locale.getCountry();
            String language = locale.getLanguage();
            return new String[]{country, language};
        } catch (Exception e) {
            return new String[]{"", ""};
        }
    }


    /**
     * 获得设备的AndroidId（如：df176fbb152ddce）无需权限,极个别设备获取不到数据或得到错误数据
     */
    private static String getAndroidId(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * 获得设备序列号（如：WTK7N16923005607）个别设备无法获取
     */
    private static String getSERIAL() {
        try {
            return Build.SERIAL + "";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * 使用硬件信息得出一个字符串
     */
    private static String getDevice() {
        try {
            String dev = "3883756" +
                    (Build.BOARD + "").length() % 10 +
                    (Build.BRAND + "").length() % 10 +
                    (Build.DEVICE + "").length() % 10 +
                    (Build.HARDWARE + "").length() % 10 +
                    (Build.ID + "").length() % 10 +
                    (Build.MODEL + "").length() % 10 +
                    (Build.PRODUCT + "").length() % 10 +
                    getSERIAL().length() % 10;
            return dev;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    /**
     * 时候支持指纹TouchID
     */
    public static int isSupportFingerprint(Context context, boolean isShowToast) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return -1;
        FingerprintManager fingerprintManager = (FingerprintManager) context.getSystemService(Context.FINGERPRINT_SERVICE);
        if (fingerprintManager == null)
            return -1;
        if (!fingerprintManager.isHardwareDetected()) {
            if (isShowToast)
                ToastUtil.show(R.string.common_tip_no_support_fingerprint);
            return -1;
        }
        if (!fingerprintManager.hasEnrolledFingerprints()) {
            if (isShowToast)
                ToastUtil.show(R.string.common_tip_no_fingerprint);
            return 0;
        }
        return 1;
    }

    public static final int getNetWorkStates(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return 0;//没网
        }

        int type = activeNetworkInfo.getType();
        switch (type) {
            case ConnectivityManager.TYPE_MOBILE:
                return 2;//移动数据
            case ConnectivityManager.TYPE_WIFI:
                return 1;//WIFI
            default:
                break;
        }
        return 0;
    }


    public static boolean isAppForeground(Context context) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        if (!pm.isScreenOn()) {
            return false;
        }

        ActivityManager activityManager =
                (ActivityManager) context.getSystemService(Service.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfoList =
                activityManager.getRunningAppProcesses();
        if (runningAppProcessInfoList == null) {
            return false;
        }

        for (ActivityManager.RunningAppProcessInfo processInfo : runningAppProcessInfoList) {
            if (processInfo.processName.equals(context.getPackageName())
                    && (processInfo.importance ==
                    ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND)) {
                return true;
            }
        }
        return false;
    }

    public static boolean muteAudio(Context context, boolean bMute) {
        boolean isSuccess = false;
        AudioManager am = (AudioManager) context.getSystemService(AUDIO_SERVICE);
        if (bMute) {
            int result = am.requestAudioFocus(null,
                    AudioManager.STREAM_MUSIC,
                    AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
            isSuccess = (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED);
        } else {
            int result = am.abandonAudioFocus(null);
            isSuccess = (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED);
        }

       // LogUtil.d("pauseMusic bMute=" + bMute + " result=" + isSuccess);
        return isSuccess;
    }

    public static int getLanguageFlag() {
        String str = BaseApplication.getInstance().getResources().getConfiguration().locale.getLanguage();
        switch (str) {
            case "zh":
                return 1;//中文
            default:
                return 2;//英文
        }
    }

    public static String getLocaleLanguageCode() {
        return BaseApplication.getInstance().getResources().getConfiguration().locale.getLanguage();
    }

    public static void openApp(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(packageName);
        context.startActivity(intent);
    }

    public static boolean checkApkExist(Context context, String packageName) {
        if (TextUtils.isEmpty(packageName))
            return false;
        try {
            context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static void openUrl(Context context, String url) {
        try {
            if (TextUtils.isEmpty(url)) return;
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri uri = Uri.parse(url);
            intent.setData(uri);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getAppList(Context context) {
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> packages = pm.getInstalledPackages(0);
        ArrayList<String> packageList = new ArrayList<>();
        for (PackageInfo packageInfo : packages) {
            // 判断系统/非系统应用
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) { // 非系统应用
                packageList.add(packageInfo.packageName);
            }
        }
        return packageList;
    }


    /**
     * [获取应用程序版本名称信息]
     *
     * @param context
     * @return 当前应用的版本名称
     */
    public static synchronized String getPackageName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.packageName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDeviceInfo() {
        String handSetInfo = "Android:"
                + "SDK版本" + Build.VERSION.SDK_INT + ","
                + "系统版本:" + Build.VERSION.RELEASE + ","
                + "手机厂商:" + Build.BRAND + ","
                + "手机型号:" + Build.MODEL + ",";
        return handSetInfo;
    }

    public static int getAppVersionCode(Context context) {
        int versioncode = 0;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            // versionName = pi.versionName;
            versioncode = pi.versionCode;
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versioncode;
    }

    public static boolean checkDeviceIsRoot() {
        Process process = null;
        try {
            //   /system/xbin/which 或者  /system/bin/which
            process = Runtime.getRuntime().exec(new String[]{"which", "su"});
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            if (in.readLine() != null) return true;
            return false;
        } catch (Throwable t) {
            return false;
        } finally {
            if (process != null) process.destroy();
        }
    }


    /**
     * 手机是否开启位置服务，如果没有开启那么所有app将不能使用定位功能
     */
    public static boolean isLocServiceEnable(Context context) {
        int locationMode = 0;
        String locationProviders;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }
            return locationMode != Settings.Secure.LOCATION_MODE_OFF;

        } else {
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }

    }

    /**
     * 直接跳转至位置信息设置界面
     */
    public static void openLocation(Context context) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        context.startActivity(intent);
    }


    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            //versioncode = pi.versionCode;
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }

    public static String getAppName(Context context) {
        PackageManager pm = context.getPackageManager();
        //获取包信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            //获取应用 信息
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            //获取albelRes
            int labelRes = applicationInfo.labelRes;
            //返回App的名称
            return context.getResources().getString(labelRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
