package com.lhh.lnstagram.mvvm.view.fragment;

import android.app.Application;


import androidx.annotation.NonNull;

import com.lhh.lnstagram.base.DiscoveryApiRepository;

import com.lhh.lnstagram.mvvm.base.AbsViewModel;

/**
 * 朋友圈列表ViewModel
 */
public class PostListViewModel extends AbsViewModel<DiscoveryApiRepository> {

    public PostListViewModel(@NonNull Application application) {
        super(application);
    }

}