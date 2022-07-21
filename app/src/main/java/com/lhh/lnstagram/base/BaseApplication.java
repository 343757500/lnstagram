package com.lhh.lnstagram.base;

import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;

import androidx.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.lhh.lnstagram.mvvm.event.LiveBus;

import java.lang.reflect.Field;


public class BaseApplication extends Application implements ComponentCallbacks2 {


    private static BaseApplication mInstance;

    public static BaseApplication getInstance() {
        return mInstance;
    }



    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(this).clearMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_UI_HIDDEN) {
            Glide.get(this).clearMemory();
        }
        Glide.get(this).trimMemory(level);
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // super.attachBaseContext(LanguageContextUtil.wrap(base));

        MultiDex.install(this);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        //setTypeFace();

        // 初始化
        LiveBus.getDefault().initConfig(this);

        // 异步初始化
        //AppLaunchTask.get().launch(this, true); // false同步
        ARouter.init(this);

    }


    // 这个音视频那边要用....
    private static Handler avMainHandler;

    public static Handler getHandler() {
        return avMainHandler;
    }

    public static void initAvMainHandler(Handler avMainHandler) {
        BaseApplication.avMainHandler = avMainHandler;
    }


    private void setTypeFace() {
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/Nunito-Regular.ttf");
        try {
            Field field = Typeface.class.getDeclaredField("MONOSPACE");
            field.setAccessible(true);
            field.set(null, typeFace);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //朋友圈视频流量播放权限
    private boolean postNeedShowWifiTip = true;
    private boolean postNeedShowWifiTipIsOpen = false;

    public boolean isPostNeedShowWifiTipIsOpen() {
        return postNeedShowWifiTipIsOpen;
    }

    public void setPostNeedShowWifiTipIsOpen(boolean postNeedShowWifiTipIsOpen) {
        this.postNeedShowWifiTipIsOpen = postNeedShowWifiTipIsOpen;
    }

    public boolean getPostNeedShowWifiTip() {
        return postNeedShowWifiTip;
    }

    public void setPostNeedShowWifiTip(boolean needShowWifiTip) {
        postNeedShowWifiTip = needShowWifiTip;
    }

}
