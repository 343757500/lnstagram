package com.lhh.lnstagram.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.jeremyliao.liveeventbus.core.Observable;
import com.lhh.lnstagram.R;
import com.lhh.lnstagram.mvvm.event.LiveBus;
import com.lhh.lnstagram.mvvm.util.AVEmuType;
import com.lhh.lnstagram.mvvm.util.StatusBarUtils;
import com.lhh.lnstagram.view.TitleBar;
import com.lhh.lnstagram.view.lazyviewpager.LazyFragmentPagerAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import guide.lifecycle.FragmentLifecycle;
import skinlibrary.base.SkinBaseFragment;


/**
 * 总父类（待完善）
 */
public abstract class BaseFragment extends SkinBaseFragment implements View.OnClickListener, LazyFragmentPagerAdapter.Laziable {

    public Context mContext;
    public BaseActivity mActivity;
    protected Bundle bundle;
    protected TitleBar titleBar;
    public View rootView;
    protected int clickId;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = getActivity();
        this.mActivity = (BaseActivity) context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Deprecated
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        if (rootView == null) {
            rootView = inflater.inflate(setLayoutResId(), container, false);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bundle = getArguments();
        onCreateViewAfter(bundle);
        isPrepared = true;
        preparedOrVisible();
    }


    /**
     * @return
     */
    public int setLayoutResId() {
        return 0;
    }


    public void onCreateViewAfter(Bundle arguments) {
        // 初始化控件？
    }

    public void onActivityCreatedAfter(Bundle arguments) {

    }

    public void onMockResume() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.mActivity = null;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        this.mActivity = null;
    }


    public void setTitle(String titleName) {
        // 没有titleBar的时候，view命名成titleLeft/titleRightTv也同样有返回时间方便使用
        if (findViewById(R.id.titleLeftTv) != null) {
            findViewById(R.id.titleLeftTv).setOnClickListener(this);
        }
        if (findViewById(R.id.titleRightTv) != null) {
            findViewById(R.id.titleRightTv).setOnClickListener(this);
        }
        if (titleBar == null) {
            titleBar = findViewById(R.id.titleBar);
        }
        if (titleBar != null) {
            titleBar.setTitle(titleName);
            titleBar.setTitleLeft("").setOnClickListener(this);
        }
    }

    public <T extends View> T findViewById(int id) {
        return (rootView != null ? rootView : getView()).findViewById(id);
    }

    @Override
    public void onClick(View v) {
        clickId = v.getId();
    }

    public void startActivity(Class<?> clazz) {
        mActivity.startActivity(clazz);
    }

    public void startActivity(Class<?> clazz, Bundle bundle) {
        mActivity.startActivity(clazz, bundle);
    }

    public int initStatusBar(Activity activity, View fakeStatusBar) {
        int statusHeight = 0;
        if (activity == null || fakeStatusBar == null) {
            return statusHeight;
        }
        fakeStatusBar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        try {
            statusHeight = StatusBarUtils.getStatusBarHeight(mContext);

            // 动态的设置隐藏布局的高度
            // 已经通过values-v19处理了，<API19的高度为0不需要用代码动态修改高度，只有全面屏需要动态配置高度
            // RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) fakeStatusBar.getLayoutParams();
            ViewGroup.LayoutParams params = fakeStatusBar.getLayoutParams();
            if (params.height > 0 && params.height != statusHeight) {
                params.height = statusHeight;
                fakeStatusBar.setLayoutParams(params);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    public void initStatusBarWhite(Activity activity, View fakeStatusBar) {
        if (activity == null || fakeStatusBar == null) {
            return;
        }
        fakeStatusBar.setBackgroundColor(getResources().getColor(R.color.bg_white));

        try {
            int statusHeight = StatusBarUtils.getStatusBarHeight(mContext);
            // 动态的设置隐藏布局的高度
            // 已经通过values-v19处理了，<API19的高度为0不需要用代码动态修改高度，只有全面屏需要动态配置高度
            // RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) fakeStatusBar.getLayoutParams();
            ViewGroup.LayoutParams params = fakeStatusBar.getLayoutParams();
            if (params.height > 0 && params.height != statusHeight) {
                params.height = statusHeight;
                fakeStatusBar.setLayoutParams(params);
            }
        } catch (Exception e) {

        }
    }

    FragmentLifecycle mFragmentLifecycle;

    public void setFragmentLifecycle(FragmentLifecycle lifecycle) {
        mFragmentLifecycle = lifecycle;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (mFragmentLifecycle != null) {
                mFragmentLifecycle.onResume();
            }
            isVisible = true;
            onVisible();
        } else {
            if (mFragmentLifecycle != null) {
                mFragmentLifecycle.onPause();
            }
            isVisible = false;
            onInvisible();
        }
    }

    // 标志已经初始化完成
    public boolean isPrepared;
    // 标识用户可见
    protected boolean isVisible;

    protected void onVisible() {
        preparedOrVisible();
    }

    protected void onInvisible() {

    }

    // 懒加载
    private void preparedOrVisible() {
        if (isPrepared && isVisible) {
            onLazyLoadData();
        }
    }

    public void onLazyLoadData() {
        isPrepared = false; // 保证只初始化一次

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
            //LogUtil.e("kecai99888222222", "音视频端的----：" + serviceMsg);
            unBindAvIsCallService();
        }
    }

    public void unBindAvIsCallService() {
        if (null != misCallConnection) {
            getActivity().unbindService(misCallConnection);
            misCallConnection = null;
        }
    }


    private final HashMap<Observable<Object>, Observer<Object>> foreverObserverHashMap = new HashMap<>();

    protected void registerForeverObserver(String key, Observer<Object> observer) {
        Observable<Object> observable = LiveBus.getDefault().subscribe(key, Object.class);
        foreverObserverHashMap.put(observable, observer);
        observable.observeForever(observer);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (foreverObserverHashMap.size() > 0) {
            Set<Map.Entry<Observable<Object>, Observer<Object>>> entries = foreverObserverHashMap.entrySet();
            for (Map.Entry<Observable<Object>, Observer<Object>> entry : entries) {
                entry.getKey().removeObserver(entry.getValue());
            }
        }
    }



}
