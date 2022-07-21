package com.lhh.lnstagram.base;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.jaeger.library.StatusBarUtil;
import com.jeremyliao.liveeventbus.core.Observable;
import com.lhh.lnstagram.R;
import com.lhh.lnstagram.aroute.MyRoute;
import com.lhh.lnstagram.dialog.DialogUtil;
import com.lhh.lnstagram.https.BaseReq;
import com.lhh.lnstagram.https.BaseResp;
import com.lhh.lnstagram.https.HttpHelper;
import com.lhh.lnstagram.manager.ActivityManager;
import com.lhh.lnstagram.mvvm.base.BaseConfig;
import com.lhh.lnstagram.mvvm.event.LiveBus;
import com.lhh.lnstagram.mvvm.event.LiveBusKey;
import com.lhh.lnstagram.mvvm.util.AVEmuType;
import com.lhh.lnstagram.mvvm.util.DeviceUtil;
import com.lhh.lnstagram.mvvm.util.TimeUtil;
import com.lhh.lnstagram.receiver.SMSBroadcastReceiver;
import com.lhh.lnstagram.sp.AppLockSP;
import com.lhh.lnstagram.sp.CookiesSP;
import com.lhh.lnstagram.view.LifeCycleListener;
import com.lhh.lnstagram.view.TitleBar;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import guide.util.StringUtil;
import skinlibrary.base.SkinBaseActivity;


/**
 * 总父类
 */
public abstract class BaseActivity extends SkinBaseActivity implements View.OnClickListener {

    private boolean destroyed = false;

    public Context mContext;
    public AppCompatActivity mActivity;
    public TitleBar titleBar;
    protected int clickId;
    private final String TAG = "BaseActivity";
    protected SMSBroadcastReceiver smsBroadcastReceiver;
    protected CopyOnWriteArrayList<LifeCycleListener> mLifeCycleListeners;
    private Observer<String> touristsModeObserver;//游客模式回调监听

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O && isTranslucentOrFloating()) {
            boolean result = fixOrientation();
            Log.e(TAG, "onCreate: " + "onCreate fixOrientation when Oreo, result = " + result);
        }
        super.onCreate(savedInstanceState);
        Log.e("当前的Activity路径=", "" + getClass().getName());
        mLifeCycleListeners = new CopyOnWriteArrayList<>();
        ActivityManager.getInstance().put(this);
        mContext = this;
        mActivity = this;
        // 初始化状态栏
        initStatusBar();
        if (mLifeCycleListeners != null) {
            for (LifeCycleListener listener : mLifeCycleListeners) {
                listener.onCreate();
            }
        }

        if (touristsModeObserver == null) {
            touristsModeObserver = data -> touristsModeTip(1, StringUtil.isNotEmpty(data) ? data : StringUtil.getString(R.string.text_tourist_mode_prompts));
        }
    }

    @SuppressLint("WrongConstant")
    private boolean fixOrientation() {
        try {
            Field field = Activity.class.getDeclaredField("mActivityInfo");
            field.setAccessible(true);
            ActivityInfo o = (ActivityInfo) field.get(this);
            o.screenOrientation = -1;
            field.setAccessible(false);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O && isTranslucentOrFloating()) {
            Log.e(TAG, "setRequestedOrientation: " + "avoid calling setRequestedOrientation when Oreo.");
            return;
        }
        super.setRequestedOrientation(requestedOrientation);
    }

    private boolean isTranslucentOrFloating() {
        boolean isTranslucentOrFloating = false;
        try {
            int[] styleableRes = (int[]) Class.forName("com.android.internal.R$styleable").getField("Window").get(null);
            final TypedArray ta = obtainStyledAttributes(styleableRes);
            Method m = ActivityInfo.class.getMethod("isTranslucentOrFloating", TypedArray.class);
            m.setAccessible(true);
            isTranslucentOrFloating = (boolean) m.invoke(null, ta);
            m.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isTranslucentOrFloating;
    }

    @Override
    protected void onResume() {
        LiveBus.getDefault().subscribe(LiveBusKey.MAIN_TO_LOGIN_VISITOR, String.class).observeForever(touristsModeObserver);
        super.onResume();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
//        getWindow().getDecorView().setFilterTouchesWhenObscured(true);
        // 手动点忘记密码的，需要跳去自动设置页
        if (AppLockSP.getInstance().getToLockSetting()) {
            AppLockSP.getInstance().setToLockSetting(false);
            MyRoute.launchAppLockSetting();
        }
        if (mLifeCycleListeners != null) {
            for (LifeCycleListener listener : mLifeCycleListeners) {
                listener.onResume();
            }
        }
    }

    @Override
    protected void onPause() {
        LiveBus.getDefault().subscribe(LiveBusKey.MAIN_TO_LOGIN_VISITOR, String.class).removeObserver(touristsModeObserver);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        super.onPause();
        if (mLifeCycleListeners != null) {
            for (LifeCycleListener listener : mLifeCycleListeners) {
                listener.onPause();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (smsBroadcastReceiver != null) {
            try {
                unregisterReceiver(smsBroadcastReceiver);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        if (mLifeCycleListeners != null) {
            for (LifeCycleListener listener : mLifeCycleListeners) {
                listener.onStop();
            }
        }
    }

    protected void registerSmsObserver(EditText editText, String smsType) {
        try {
            Task task = SmsRetriever.getClient(this).startSmsRetriever();
            task.addOnSuccessListener(o -> {
                if (smsBroadcastReceiver == null) {
                    smsBroadcastReceiver = new SMSBroadcastReceiver();
                }
                smsBroadcastReceiver.initOtpReceiver(opt -> {
                    if (!TextUtils.isEmpty(opt) && editText != null) {
                        editText.setText(opt);
                    }
                }, smsType);
                try {
                    registerReceiver(smsBroadcastReceiver, new IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            task.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    e.printStackTrace();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 初始化状态栏
     */
    protected void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
        }
    }

    public void setWhiteTheme() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            StatusBarUtil.setColor(this, getResources().getColor(R.color.white), 0);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setTheme(R.style.whiteTheme);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            StatusBarUtil.setLightMode(BaseActivity.this);
        }
    }

    public boolean isDestroyedCompatible() {
        if (Build.VERSION.SDK_INT >= 17) {
            return isDestroyedCompatible17();
        } else {
            return destroyed || super.isFinishing();
        }
    }

    @TargetApi(17)
    private boolean isDestroyedCompatible17() {
        return super.isDestroyed();
    }

    @Override
    protected void onDestroy() {
        if (mLifeCycleListeners != null) {
            for (LifeCycleListener listener : mLifeCycleListeners) {
                listener.onDestroy();
            }
            mLifeCycleListeners.clear();
            mLifeCycleListeners = null;
        }
        super.onDestroy();
        destroyed = true;

        // 移除监听
        if (observerMap != null && observerMap.size() > 0) {
            Iterator<Map.Entry<Observable, Observer>> it = observerMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Observable, Observer> entry = it.next();
                Observable key = entry.getKey();
                Observer value = entry.getValue();
                key.removeObserver(value);
                it.remove();
            }
        }
    }

    Map<Observable, Observer> observerMap;

    protected void registerObserveForever(LiveBusKey liveBusKey, Observer observer) {
        if (observerMap == null) {
            observerMap = new HashMap<>();
        }
        Observable observable = LiveBus.getDefault().subscribe(liveBusKey, Object.class);
        observerMap.put(observable, observer);
        observable.observeForever(observer);
    }

    protected void registerCloseObserver(LiveBusKey liveBusKey) {
        registerObserveForever(liveBusKey, closeObserver);
    }

    protected Observer closeObserver = new Observer<Object>() {
        @Override
        public void onChanged(@Nullable Object object) {
            if (object != null) {
                finish();
            }
        }
    };


    @Override
    public void finish() {
        super.finish();
//        this.overridePendingTransition(R.anim.activity_slide_left_in, R.anim.activity_slide_right_out);
    }

    @Override
    public void onClick(View v) {
        // 隐藏键盘
        hideKeyboard();
        // 点击返回
        clickId = v.getId();
        if (clickId == R.id.titleLeftTv) {
            // 不弹窗的话就关闭
            if (!toastEditNotCompleteTips()) {
                finish();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
                showKeyboard(false);
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN
                && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (toastEditNotCompleteTips()) {
                // 拦截
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    public void hideKeyboard() {
        showKeyboard(false);
    }

    // start of 放弃编辑
    protected String editOriginalData;

    protected void setEditOriginalData(String editOriginalData) {
        this.editOriginalData = editOriginalData;
    }

    protected String getEditCurrentData() {
        return "";
    }

    protected String getEditNotCompleteTips() {
        return StringUtil.getString(R.string.common_tip_give_up_editor);
    }

    public boolean toastEditNotCompleteTips() {
        if (StringUtil.isNotEmpty(editOriginalData) && StringUtil.isNotEmpty(getEditCurrentData())
                && !TextUtils.equals(editOriginalData, getEditCurrentData())) {
            DialogUtil.showConfirmDialog(mContext, getEditNotCompleteTips(), new DialogUtil.OnConfirmClickListener() {
                @Override
                public void onOkClick() {
                    finish();
                }

                @Override
                public void onCancelClick() {

                }
            });
            return true;
        } else {
            return false;
        }
    }
    // end of 放弃编辑

    /**
     * 延时弹出键盘
     *
     * @param focus 键盘的焦点项
     */
    public void showKeyboardDelayed(View focus) {
        final View viewToFocus = focus;
        if (focus != null) {
            focus.requestFocus();
        }

        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (viewToFocus == null || viewToFocus.isFocused()) {
                    showKeyboard(true);
                }
            }
        }, 200);
    }

    public void showKeyboard(boolean isShow) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (isShow) {
            if (getCurrentFocus() == null) {
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            } else {
                imm.showSoftInput(getCurrentFocus(), 0);
            }
        } else {
            if (getCurrentFocus() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    public void hideSoftInput(EditText editText) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public void setTitle() {
        setTitle("");
    }

    public void setTitle(int titleId) {
        setTitle(StringUtil.getString(titleId));
    }

    public void setTitle(String titleName) {
        // 没有titleBar的时候，view命名成titleLeft/titleRightTv也同样有返回时间方便使用
        if (findViewById(R.id.titleLeftTv) != null) {
            findViewById(R.id.titleLeftTv).setOnClickListener(this);
        }
        if (findViewById(R.id.titleRightTv) != null) {
            findViewById(R.id.titleRightTv).setOnClickListener(this);
        }
        // 页面layout
        if (titleBar == null) {
            titleBar = findViewById(R.id.titleBar);
        }
        if (titleBar != null) {
            titleBar.setTitle(titleName);
            titleBar.setTitleLeft("").setOnClickListener(this);
        }
    }

    public void startActivity(Class<?> clazz) {
        startActivity(clazz, null);
    }

    public void startActivity(Class<?> clazz, Bundle bundle) {
        if (clazz == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 更新版本弹窗
     *
     * @param autoCheck
     */
    public void checkUpdate(boolean autoCheck) {
        // HTTP
        if (!autoCheck) {
            DialogUtil.showProgressHUD(mContext, StringUtil.getString(R.string.common_tip_on_request));
        }
        updatedVersionOne(autoCheck);
        if (CookiesSP.isLogin())
            updatedVersionTwo(autoCheck);
    }

    /**
     * 游客提示弹窗
     *
     * @param content
     */
    private Dialog dialog;
    public void touristsModeTip(int status, String content) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        dialog = DialogUtil.showDialog(mContext, 0, StringUtil.getString(R.string.login_txt_sign_up), content,
                StringUtil.getString(R.string.login_txt_sign_up_now), status == 2 ? null : StringUtil.getString(R.string.code_reset_cancel), new DialogUtil.OnConfirmClickListener() {
                    @Override
                    public void onOkClick() {
                        MyRoute.launchRegister();
                    }

                    @Override
                    public void onCancelClick() {
                    }
                });
        if (status == 2) {
            dialog.setOnKeyListener((dialogInterface, i, keyEvent) -> true);
            dialog.setCanceledOnTouchOutside(false);//点击外部不消失
        }
    }

    /**
     * 更新版本-方式一
     *
     * @param autoCheck
     */
    private void updatedVersionOne(boolean autoCheck) {
        BaseReq req = BaseReq.api("/common/update")
                .setSafeType(BaseReq.SAFE_NONE)
                .put("versionCode", BaseConfig.VERSION_CODE + "")
                .put("versionName", BaseConfig.VERSION_NAME)
                .put("deviceType", "2")
                .put("timestamp", TimeUtil.getCurrentTimeStr4Local())
                .put("deviceVersion", "Android-" + Build.VERSION.SDK_INT)
                .put("deviceModel", "");
        HttpHelper.getInstance().reqHttp(req, new Observer<BaseResp>() {
            @Override
            public void onChanged(@Nullable BaseResp baseResp) {
                DialogUtil.closeProgressDialog();
                if (baseResp.isOk()) {
                }
            }
        });
    }

    /**
     * 更新版本-方式二
     *
     * @param autoCheck
     */
    private void updatedVersionTwo(boolean autoCheck) {
        HttpHelper.getInstance().reqHttp(BaseReq.api("/discovery/new/pageList/popupInfo"), baseResp -> {
            DialogUtil.closeProgressDialog();
            if (baseResp.isOk()) {

            }
        });
    }





    //是否在视频当中----
    private ServiceConnection misCallConnection = null;



    private GetIsCallRelyHandler mGetIsCallRelyHandler = new GetIsCallRelyHandler();

    private Messenger mIsCallRelyMessenger = new Messenger(mGetIsCallRelyHandler);

    public class GetIsCallRelyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            long serviceMsg = bundle.getLong("service", 0L);
            AVEmuType.ulMeetingID = serviceMsg;

            unBindAvIsCallService();
        }

    }

    public void unBindAvIsCallService() {
        if (null != misCallConnection) {
            this.unbindService(misCallConnection);
            misCallConnection = null;
        }
    }


    // ------ 第三方授权 -------



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
    }






    //  ------ end of 第三方授权 ------

    @Override
    protected void onStart() {
        super.onStart();
        if (mLifeCycleListeners != null) {
            for (LifeCycleListener listener : mLifeCycleListeners) {
                listener.onStart();
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (mLifeCycleListeners != null) {
            for (LifeCycleListener listener : mLifeCycleListeners) {
                listener.onReStart();
            }
        }
    }

    public void addLifeCycleListener(LifeCycleListener listener) {
        if (mLifeCycleListeners != null && listener != null) {
            mLifeCycleListeners.add(listener);
        }
    }

    public void addAllLifeCycleListener(List<LifeCycleListener> listeners) {
        if (mLifeCycleListeners != null && listeners != null) {
            mLifeCycleListeners.addAll(listeners);
        }
    }

    public void removeLifeCycleListener(LifeCycleListener listener) {
        if (mLifeCycleListeners != null) {
            mLifeCycleListeners.remove(listener);
        }
    }
}

