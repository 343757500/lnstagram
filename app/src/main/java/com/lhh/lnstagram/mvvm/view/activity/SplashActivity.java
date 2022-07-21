package com.lhh.lnstagram.mvvm.view.activity;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.lifecycle.Observer;

import com.lhh.lnstagram.R;
import com.lhh.lnstagram.aroute.MyRoute;
import com.lhh.lnstagram.aroute.RoutePath;
import com.lhh.lnstagram.mvvm.base.AbsLifecycleActivity;
import com.lhh.lnstagram.mvvm.vm.StartViewModel;

/**
 * 启动页
 */
public class SplashActivity extends AbsLifecycleActivity<StartViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // 设置为全屏模式
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setWhiteTheme();


        mViewModel.forTime();
    }

    @Override
    protected void dataObserver() {
        super.dataObserver();
        mViewModel.goMain().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                MyRoute.getInstance().build(RoutePath.MAIN_INDEX).navigation();
            }
        });
    }


}
