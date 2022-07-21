package com.lhh.lnstagram.mvvm.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.lhh.lnstagram.base.BaseApplication;


//kecai
public class StatusBarUtils {

    public static void setWindowStatusBarColor(Activity activity, int colorResId) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//因为不是所有的系统都可以设置颜色的，在4.4以下就不可以。。有的说4.1，所以在设置的时候要检查一下系统版本是否是4.1以上
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(colorResId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setWindowStatusBarColor(Dialog dialog, int colorResId) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = dialog.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(dialog.getContext().getResources().getColor(colorResId));

                //底部导航栏
                //window.setNavigationBarColor(activity.getResources().getColor(colorResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static AudioRecord recorder =null;
//    //是否占用麦克风
//    @SuppressLint("MissingPermission")
//    public static boolean validateMicAvailability(){
//        if(!getAllPermision()){//没有权限就直接去相应界面开权限
//            return true;
//        }
//        Boolean available = true;
//        if(recorder==null){
//            recorder= new AudioRecord(MediaRecorder.AudioSource.MIC, 44100,
//                        AudioFormat.CHANNEL_IN_MONO,
//                        AudioFormat.ENCODING_DEFAULT, 44100);
//        }
//        if(recorder==null){
//            return true;
//        }
//        try{
//            if(recorder.getRecordingState() != AudioRecord.RECORDSTATE_STOPPED ){
//               // AVEmuType.wirteFileAvLog("validateMicAvailability","-----------validateMicAvailability--------"+AVEmuType.ulMeetingID);
//                available = true;
//
//            }
//
//            recorder.startRecording();
//            if(recorder.getRecordingState() != AudioRecord.RECORDSTATE_RECORDING){
//                recorder.stop();
//               // AVEmuType.wirteFileAvLog("validateMicAvailability","-----------validateMicAvailability--------"+AVEmuType.ulMeetingID);
//                available = true;
//
//            }
//            recorder.stop();
//        } finally{
//            recorder.release();
//            recorder = null;
//        }
//        return available;
//    }


    public static String turnToMeetingName(String name){
        if(!TextUtils.isEmpty(name)){
            if(name.contains(",")){
                return name.substring(0,name.lastIndexOf(",")-1);
            }else{
                return name;
            }
        }

        return "";
    }

    /**
     * 修改状态栏为全透明
     *
     * @param activity
     */
    @TargetApi(19)
    public static void transparencyBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //api21新增接口
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 获取状态栏高度,在页面还没有显示出来之前
     *
     * @param a
     * @return
     */
    public static int getStateBarHeight(Activity a) {
        int result = 0;
        int resourceId = a.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = a.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


//    protected static boolean getAllPermision(){
//        boolean hasPermission=false;
//        if (!XXPermissions.isHasPermission(BaseApplication.getInstance(), Permission.RECORD_AUDIO, Permission.CAMERA)) {
//            return hasPermission ;
//        }else {
//            return true;
//        }
//    }


    private static int statusBarHeight = 0;
    public static int getStatusBarHeight(Context context) {
        if (statusBarHeight == 0) {
            try {
                int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
                statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusBarHeight;
    }

}
