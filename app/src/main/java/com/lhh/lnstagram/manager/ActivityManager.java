package com.lhh.lnstagram.manager;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mzz 2021/4/8  15:55
 */
public class ActivityManager {
    private static final ActivityManager ourInstance = new ActivityManager();

    private final List<Activity> mList = new ArrayList<>();

    public static ActivityManager getInstance() {
        return ourInstance;
    }

    public void put(Activity activity) {
        if (activity != null) {
            mList.add(activity);
        }
    }

    public void remove(Activity mActivity) {
        if (mActivity != null && mList.size() > 0) {
            for (Activity activity : mList) {
                if (activity != null && activity == mActivity) {
                    activity.finish();
                }
            }
            mList.remove(mActivity);
        }
    }

    /**
     * 关闭所有Activity
     */
    public void finishAll() {
        try {
            if (mList.size() > 0) {
                for (Activity activity : mList) {
                    if (activity != null) {
                        activity.finish();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
