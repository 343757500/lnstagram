package com.lhh.lnstagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lhh.lnstagram.aroute.RoutePath;
import com.lhh.lnstagram.base.BaseActivity;
import com.lhh.lnstagram.base.BaseApplication;
import com.lhh.lnstagram.mvvm.base.AbsLifecycleActivity;
import com.lhh.lnstagram.mvvm.util.TUtil;
import com.lhh.lnstagram.mvvm.view.MainFragment;
import com.lhh.lnstagram.mvvm.vm.MainViewModel;

import retrofit2.http.PATCH;

@Route(path = RoutePath.MAIN_INDEX)
public class MainActivity extends AbsLifecycleActivity<MainViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setWhiteTheme();

        // UI
        showMainFragment();
        mViewModel.getShow();

    }


    private void showMainFragment() {
        MainFragment mainFragment = new MainFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragmentLL, mainFragment);
        try {
            transaction.commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}