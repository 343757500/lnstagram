package com.lhh.lnstagram.aroute;

import android.os.Bundle;


import com.alibaba.android.arouter.launcher.ARouter;

import com.lhh.lnstagram.base.BaseApplication;


/**
 * Created by XieWenJun on 2018/10/30.
 */
public class MyRoute {

    private volatile static ARouter aRouterInstance = null;

    public static void initARouter() {
        //  if (BaseApplication.isDebug()) {
        ARouter.openLog();
        ARouter.openDebug();
        // }
        ARouter.init(BaseApplication.getInstance());
    }

    public static ARouter getInstance() {
        if (aRouterInstance == null) {
            synchronized (MyRoute.class) {
                if (aRouterInstance == null) {
                    initARouter();
                    aRouterInstance = ARouter.getInstance();
                }
            }
        }
        return aRouterInstance;
    }


    public static void start(String routePath) {
        start(routePath, null);
    }

    public static void start(String routePath, Bundle bundle) {
        MyRoute.getInstance().build(routePath).with(bundle).navigation();
    }

    public static void launchAppLockSetting() {
        MyRoute.getInstance().build("")
                .withBoolean("app_lock", true)
                .navigation();
    }

    public static void launchRegister() {

    }
}