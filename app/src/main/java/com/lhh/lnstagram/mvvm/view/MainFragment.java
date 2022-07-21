package com.lhh.lnstagram.mvvm.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.android.material.tabs.TabLayout;
import com.lhh.lnstagram.MainTab;
import com.lhh.lnstagram.R;
import com.lhh.lnstagram.aroute.MyRoute;
import com.lhh.lnstagram.aroute.RoutePath;
import com.lhh.lnstagram.base.BaseFragment;

import com.lhh.lnstagram.mvvm.view.fragment.V3DiscoveryFragment;
import com.lhh.lnstagram.mvvm.view.fragment.V4DiscoveryFragment;
import com.lhh.lnstagram.mvvm.view.lazyviewpager.LazyViewPager;

import guide.util.StringUtil;

public class MainFragment extends BaseFragment {

    public LazyViewPager viewPager;
    private MainTabAdapter mainTabAdapter;

    private int[] mainTabTitleList = {
            R.string.main_tab_wallet,
            R.string.main_tab_chat
    };

    private int[] mainTabIconResId = {
            R.drawable.icon_tab_wallet,
            R.drawable.icon_tab_chat,
            R.drawable.icon_tab_discovery,
            R.drawable.icon_tab_post,
            R.drawable.icon_tab_me
    };

    private BaseFragment[] baseFragmentList;

    @Override
    public int setLayoutResId() {
        return R.layout.fragment_main;
    }

    @Override
    public void onCreateViewAfter(Bundle arguments) {
        super.onCreateViewAfter(arguments);
        initView();
    }

    private void initView() {
        viewPager = findViewById(R.id.viewPager);

        baseFragmentList = new BaseFragment[mainTabTitleList.length];
        mainTabAdapter = new MainTabAdapter(getChildFragmentManager());
        viewPager.setAdapter(mainTabAdapter);



        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < mainTabTitleList.length; i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(initTabView(i));
            }
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }


    private View initTabView(int pos) {
        View viewTab = View.inflate(mContext, R.layout.activity_main_tablayout_item, null);
        ImageView ivTabItem = viewTab.findViewById(R.id.tabIv);
        TextView tvTabItem = viewTab.findViewById(R.id.tabTv);
        ivTabItem.setImageResource(mainTabIconResId[pos]);
        tvTabItem.setText(StringUtil.getString(mainTabTitleList[pos]));
        return viewTab;
    }

    // fragment的适配器类
    class MainTabAdapter extends FragmentPagerAdapter {

        // 标题数组
        public MainTabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return getFragmentByPosition(position);
        }

        @Override
        public int getCount() {
            return mainTabTitleList.length;
        }
    }


    private BaseFragment getFragmentByPosition(int position) {
        switch (position) {
            case 0:
                baseFragmentList[position] = (BaseFragment) MyRoute.getInstance().build(RoutePath.Discovery.DISCOVERY_INDEX_FRAGMENT_V4).navigation();
                break;
            case 1:
                baseFragmentList[position] = (BaseFragment) MyRoute.getInstance().build(RoutePath.Discovery.DISCOVERY_INDEX_FRAGMENT_V3).navigation();
                break;

        }
        return baseFragmentList[position];
    }

}
