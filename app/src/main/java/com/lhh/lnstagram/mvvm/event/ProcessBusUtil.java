package com.lhh.lnstagram.mvvm.event;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;


import com.jeremyliao.liveeventbus.LiveEventBus;
import com.jeremyliao.liveeventbus.core.Observable;
import com.lhh.lnstagram.base.BaseApplication;
import com.lhh.lnstagram.mvvm.util.ToastUtil;

import java.util.concurrent.ConcurrentHashMap;

//import cn.leo.messenger.MagicMessenger;
//import cn.leo.messenger.MessageCallback;

public class ProcessBusUtil {

    // 修改成false，使用LiveBus
    private static boolean useMagicMessenger = false;
    private static ConcurrentHashMap<String, Observer> observers = new ConcurrentHashMap();

    public static void init(Application application) {
        if (useMagicMessenger) {
            try {
                //MagicMessenger.init(application);
            } catch (Exception e) {
                // 使用LiveBus
                useMagicMessenger = false;

                //if (BaseApplication.isDebug()) {
                    ToastUtil.show("MagicMessenger init failed, use LiveBus");
               // }
            }
        }
    }

    public static void subscribe(@NonNull String key, @NonNull ProcessCallback callback) {
        if (useMagicMessenger) {
            //MagicMessenger.subscribe(key, callback);
        } else {
            Observable observable = LiveBus.getDefault().registerBroadcast(key);
            Observer observer = obj -> {
                if (callback != null && obj != null && obj instanceof Bundle) {
                    callback.onMsgCallBack((Bundle) obj);
                }
            };
            observers.put(key, observer);
            observable.observeForever(observer);
        }
    }

    public static void unsubscribe(@NonNull String key) {
        if (useMagicMessenger) {
            //MagicMessenger.unsubscribe(key);
        } else {
            Observer observer = observers.get(key);
            if (observer != null) {
                LiveEventBus.get(key).removeObserver(observer);
            }
        }
    }

    public static void post(@NonNull String key, Bundle data) {
        if (useMagicMessenger) {
            //MagicMessenger.post(key, data);
        } else {
            LiveBus.getDefault().postBroadcast(key, data);
        }
    }

}
