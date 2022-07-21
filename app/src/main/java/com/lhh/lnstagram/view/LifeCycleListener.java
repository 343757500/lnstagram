package com.lhh.lnstagram.view;

/**
 * author LiangDangWei
 * date 2020-11-25 10:09
 * description 监控生命周期接口
 */

public interface LifeCycleListener {

    void onCreate();

    void onStart();

    void onReStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();
}
