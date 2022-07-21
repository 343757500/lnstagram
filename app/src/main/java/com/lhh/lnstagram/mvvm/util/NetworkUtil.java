package com.lhh.lnstagram.mvvm.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import guide.util.Utils;

public class NetworkUtil {

    /**
     * 网络是否可用
     */
    public static boolean isNetworkAvailable() {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) Utils.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
