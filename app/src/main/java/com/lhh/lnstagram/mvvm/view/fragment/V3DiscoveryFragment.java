package com.lhh.lnstagram.mvvm.view.fragment;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;
import com.lhh.lnstagram.R;
import com.lhh.lnstagram.aroute.RoutePath;
import com.lhh.lnstagram.bean.MomentBean;
import com.lhh.lnstagram.mvvm.base.AbsLifecycleFragment;
import com.lhh.lnstagram.mvvm.util.TUtil;
import com.lhh.lnstagram.mvvm.vm.DiscoveryViewModel;

import java.util.List;

@Route(path = RoutePath.Discovery.DISCOVERY_INDEX_FRAGMENT_V3)
public class V3DiscoveryFragment extends AbsLifecycleFragment<DiscoveryViewModel> {

    @Override
    public int setLayoutResId() {
        return R.layout.fragment_discovery_index_v3;
    }

    @Override
    public void onCreateViewAfter(Bundle arguments) {
        super.onCreateViewAfter(arguments);

        mViewModel.forTime();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void dataObserver() {
        super.dataObserver();

        mViewModel.getMomentBean().observe(this, new Observer<List<MomentBean>>() {
            @Override
            public void onChanged(List<MomentBean> momentBeans) {
            }
        });
    }
}
