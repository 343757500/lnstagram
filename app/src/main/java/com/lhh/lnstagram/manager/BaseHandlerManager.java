package com.lhh.lnstagram.manager;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

/**
 * 信息提供者
 * 1.读缓存
 * 2.读数据库/文件
 * 3.网络获取
 */
public class BaseHandlerManager {

    protected static BaseHandlerManager mInstance;
    protected Handler mMainHandler;
    protected Handler mWorkHandler;
    protected HandlerThread mWorkThread;

    // 获取实例
    public static BaseHandlerManager getInstance() {
        if (mInstance == null) {
            synchronized (BaseHandlerManager.class) {
                if (mInstance == null) {
                    mInstance = new BaseHandlerManager();
                }
            }
        }
        return mInstance;
    }

    // 构造函数
    public BaseHandlerManager() {
        initHandler();
    }

    private void initHandler() {
        // 主线程
        mMainHandler = new Handler(Looper.getMainLooper());
        // 查询线程
        if (mWorkThread == null) {
            mWorkThread = new HandlerThread("BaseWorkThread");
            mWorkThread.start();
        }
        if (mWorkThread != null && !mWorkThread.isAlive()) {
            mWorkThread.start();
        }
        mWorkHandler = new Handler(mWorkThread.getLooper());
    }

    // 获取主线程Handler
    public Handler getMainHandler() {
        if (mMainHandler == null) {
            synchronized (BaseHandlerManager.class) {
                if (mMainHandler == null) {
                    initHandler();
                }
            }
        }
        return mMainHandler;
    }

    // 获取后台Handler
    public Handler getWorkHandler() {
        if (mWorkHandler == null) {
            synchronized (BaseHandlerManager.class) {
                if (mWorkHandler == null) {
                    initHandler();
                }
            }
        }
        return mWorkHandler;
    }

}
