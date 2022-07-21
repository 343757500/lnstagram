package com.lhh.lnstagram.mvvm.util;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.StringRes;

import com.lhh.lnstagram.R;
import com.lhh.lnstagram.base.BaseApplication;
import com.lhh.lnstagram.mvvm.util.toasty.Toasty;

import java.util.ArrayList;
import java.util.List;

import guide.util.StringUtil;

/**
 * Created by XieWenJun on 2018/11/2.
 */
public class ToastUtil {

    public static Context context;
    private static Toast toast;
    private static List<Toast> toastList = new ArrayList<>();

    public static Context getContext() {
        if (context == null) {
            context = BaseApplication.getInstance();
        }
        return context;
    }

    /**
     * 单例模式
     *
     * @param content  内容
     * @param duration 显示的时间
     */
    private static void show(String content, int duration) {
//        if (toast != null) {
//            toast.cancel();
//        }
//        toast = Toast.makeText(getContext(), "", duration);
//        toast.setText(content);
//        toast.setDuration(duration);
//        toast.show();
        if (!TextUtils.isEmpty(content)) {
            Toasty.normal(getContext(), content).show();
        }
    }


    /**
     * 单例模式
     *
     * @param content 内容
     */
    public static void show(String content) {
        show(content, Toast.LENGTH_SHORT);
    }

    public static void show(@StringRes int strId) {
        show(StringUtil.getString(strId), Toast.LENGTH_SHORT);
    }

    /**
     * 重置Toast对象(主要是用于前一个Toast位置变化之后)
     */
    public static void resetToast() {
        toast = Toast.makeText(getContext(), "", Toast.LENGTH_LONG);
    }

    /**
     * 取消最近创建的一个Toast
     */
    public static void cancel() {
        if (toast != null) {
            toast.cancel();
        }
    }

    /**
     * 取消所有的Toast任务
     */
    public static void cancelAll() {
        for (int i = 0; i < toastList.size(); i++) {
            if (toastList.get(i) != null) {
                toastList.get(i).cancel();
            }
        }
    }

    //带时间的toast
    public static void showDuration(String content, int duration) {
        Toasty.normal(getContext(), content, duration).show();
    }


    public static void showTopIconToast(Context context, int strResId, int imgResId) {
        View mView = LayoutInflater.from(context).inflate(R.layout.toast_top_icon, null);
        TextView text = mView.findViewById(R.id.tv_content);
        ImageView imageView = mView.findViewById(R.id.iv_img);
        if (imgResId > 0) {
            imageView.setImageResource(imgResId);
        } else {
            imageView.setVisibility(View.GONE);
        }
        text.setText(StringUtil.getString(strResId));
        Toast toast = new Toast(context);
        toast.setView(mView);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }


}
