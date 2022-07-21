package com.lhh.lnstagram.mvvm.view.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lhh.lnstagram.R;
import com.lhh.lnstagram.aroute.RoutePath;
import com.lhh.lnstagram.mvvm.base.AbsLifecycleFragment;
import com.lhh.lnstagram.mvvm.view.PostIndexTitleBar;
import com.lhh.lnstagram.mvvm.vm.DiscoveryViewModel;

@Route(path = RoutePath.Discovery.DISCOVERY_INDEX_FRAGMENT_V4)
public class V4DiscoveryFragment extends AbsLifecycleFragment<DiscoveryViewModel> {
    private PostIndexTitleBar title_bar_main;
    private View fake_status_bar;
    @Override
    public int setLayoutResId() {
        return R.layout.fragment_post_index;
    }

    @Override
    public void onCreateViewAfter(Bundle arguments) {
        super.onCreateViewAfter(arguments);
        initView();
        mViewModel.forTime();
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initView() {
        fake_status_bar = findViewById(R.id.fake_status_bar);
        initStatusBarWhite(mActivity, fake_status_bar);
        title_bar_main = findViewById(R.id.title_bar_main);
        title_bar_main.setTitleNameLeft("title").setOnClickListener(this);
        title_bar_main.setTitleRight(R.drawable.post_icon_title_message);
        title_bar_main.setTitleRight2(R.drawable.post_icon_title_search);
        title_bar_main.setTitleRight3(R.drawable.post_icon_title_contact);
        title_bar_main.setTitleRight4(R.drawable.post_icon_title_public_account);
        title_bar_main.setTitleRight5(R.drawable.post_icon_title_hot_list);
        title_bar_main.titleRightTv.setOnClickListener(this);
        title_bar_main.titleRightTv2.setOnClickListener(this);
        title_bar_main.titleRightTv3.setOnClickListener(this);

        FragmentManager fragmentManager = mActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.ll_fragment_container, new PostListFragment());
        fragmentTransaction.commit();

    }
}
