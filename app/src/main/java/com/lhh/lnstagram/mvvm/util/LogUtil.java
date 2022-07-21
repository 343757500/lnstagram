//package com.lhh.lnstagram.mvvm.util;
//
//import android.text.TextUtils;
//
//import com.gigaiot.sasa.common.BaseConfig;
//import com.gigaiot.sasa.common.base.BaseApplication;
//import com.gigaiot.sasa.common.http.exception.ApiHttpException;
//import com.gigaiot.sasa.common.http.exception.ApiTooSlowException;
//import com.gigaiot.sasa.common.http.exception.LbsHttpException;
//import com.gigaiot.sasa.common.http.exception.MyException;
//import com.lhh.lnstagram.base.BaseApplication;
//import com.lhh.lnstagram.mvvm.base.BaseConfig;
//import com.orhanobut.logger.AndroidLogAdapter;
//import com.orhanobut.logger.FormatStrategy;
//import com.orhanobut.logger.Logger;
//import com.orhanobut.logger.PrettyFormatStrategy;
//import com.tencent.bugly.crashreport.CrashReport;
//
//public final class LogUtil {
//
//    public final static String TAG_DB = "DB";
//    public final static String TAG_TCP = "Tcp";
//    public final static String TAG_HTTP = "Http";
//    public final static String TAG_VIDEO= "Video";
//
//    public static void init(final boolean isLoggable) {
//        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
//                //.showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
//                //.methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
//                //.logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
//                .methodCount(3)         // (Optional) How many method line to show. Default 2
//                .tag("SaSaLog")         // (Optional) Global tag for every log. Default PRETTY_LOGGER
//                .build();
//        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
//            @Override
//            public boolean isLoggable(int priority, String tag) {
//                return isLoggable;
//            }
//        });
//
//        if (BaseConfig.localFileLog) {
//            FileLogUtil.getConfig().setLog2FileSwitch(true);
//        }
//    }
//
//
//    public static void d(String msg) {
//        if (!BaseApplication.isDebug() || TextUtils.isEmpty(msg)) return;
//        Logger.d(msg);
//        fileLog("", msg);
//    }
//
//    public static void e(String msg) {
//        if (!BaseApplication.isDebug() || TextUtils.isEmpty(msg)) return;
//        Logger.e(msg);
//        fileLog("", msg);
//    }
//
//    public static void d(String tag, String msg) {
//        if (!BaseApplication.isDebug() || TextUtils.isEmpty(tag) || TextUtils.isEmpty(msg)) return;
//        Logger.t(tag).d(msg);
//        fileLog(tag, msg);
//    }
//
//    public static void e(String tag, String msg) {
//        if (!BaseApplication.isDebug() || TextUtils.isEmpty(tag) || TextUtils.isEmpty(msg)) return;
//        Logger.t(tag).e(msg);
//        fileLog(tag, msg);
//    }
//
//    private static void fileLog(String tag, String msg) {
//        if (BaseConfig.localFileLog) {
//            FileLogUtil.file(tag, msg);
//        }
//    }
//
//    public static void postApiHttpException(String url, String msg, Throwable cause) {
//        CrashReport.postCatchedException(new ApiHttpException(url + "," + msg));
//    }
//
//    public static void postApiTooSlowException(String url, long tookMs) {
//        if (BaseConfig.ENV_TYPE != 3 && tookMs > (10 * 1000)) {
//            CrashReport.postCatchedException(new ApiTooSlowException(url + "," + tookMs + "ms"));
//        }
//    }
//
//    public static void postLbsHttpException(Throwable cause) {
//        CrashReport.postCatchedException(new LbsHttpException(cause));
//    }
//
//    public static void postMyCustomException(Throwable cause) {
//        CrashReport.postCatchedException(new MyException(cause));
//    }
//
//
//}