package com.lhh.lnstagram.mvvm.event;


import android.content.Context;


import com.jeremyliao.liveeventbus.LiveEventBus;
import com.jeremyliao.liveeventbus.core.Observable;
import com.lhh.lnstagram.sp.CookiesSP;

/**
 * 事件总线
 * <p>
 * <p>
 * 3.推送这个事件
 * LiveBus.getDefault().postEvent("LiveData","hi LiveData");
 * <p>
 * <p>
 * 1.获取对象LiveData, 2.在Activity/Fragment中订阅这个LiveData
 * LiveBus.getDefault().subscribe("LiveData").observe(Activity.this/Fragment.this, new Observer<Object>() {
 *
 * @Override public void onChanged(@Nullable Object o) {
 * LogUtil.e("onChanged",((String)o));
 * }
 * });
 * @author：tqzhang on 18/9/11 17:22
 */
public class LiveBus {

    private static class SingletonHolder {
        private static final LiveBus DEFAULT_BUS = new LiveBus();
    }

    public static LiveBus getDefault() {
        return SingletonHolder.DEFAULT_BUS;
    }


    public void initConfig(Context context) {
        com.jeremyliao.liveeventbus.LiveEventBus
                .config()
                .autoClear(true)
                .supportBroadcast(context)
                .lifecycleObserverAlwaysActive(false);
    }


    // 推送事件
    public void postEvent(LiveBusKey liveBusKey, Object value) {
        // 特殊处理
        if (liveBusKey == LiveBusKey.MAIN_TO_LOGIN_FROM_PUSH_LOGOUT || liveBusKey == LiveBusKey.MAIN_TO_LOGIN ) {
            CookiesSP.exitToken();
        }
        postEvent(liveBusKey.getEventKey(), value);
    }
    /**
     * 推送事件
     * @param key key
     * @param value 推送内容
     * @return LiveData<T>
     */
    public void postEvent(String key, Object value) {
        LiveEventBus.get(key).post(value);
    }


    // 获取LiveData
    public <T> Observable<T> subscribe(LiveBusKey liveBusKey, Class<T> tClass) {
        return subscribe(liveBusKey.getEventKey(), tClass);
    }


    /**
     * 获取LiveData
     * @param key 事件名
     * @param tClass 类型
     * @param <T>
     * @return 可订阅的LiveData
     */
    public <T> com.jeremyliao.liveeventbus.core.Observable<T> subscribe(String key, Class<T> tClass) {
        return LiveEventBus.get(key, tClass);
    }



    // 注册跨进程
    public <T> com.jeremyliao.liveeventbus.core.Observable<T> registerBroadcast(String key, Class<T> tClass) {
        return subscribe(key, tClass);
    }
    // 注册跨进程
    public com.jeremyliao.liveeventbus.core.Observable<Object> registerBroadcast(String key) {
        return LiveEventBus.get(key);
    }
    // 推送跨进程
    public void postBroadcast(String key, Object value) {
        LiveEventBus.get(key).broadcast(value);
    }

}
