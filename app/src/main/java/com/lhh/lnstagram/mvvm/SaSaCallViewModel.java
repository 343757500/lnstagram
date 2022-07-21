package com.lhh.lnstagram.mvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.lhh.lnstagram.bean.UserInfo;
import com.lhh.lnstagram.mvvm.base.AbsViewModel;


/**
 * Created by XieWenJun on 2018/11/15.
 */
public class SaSaCallViewModel extends AbsViewModel<SaSaCallRepository> {

    public SaSaCallViewModel(@NonNull Application application) {
        super(application);
    }

    private MutableLiveData<UserInfo> userInfoLiveData;
    private MutableLiveData<Integer> finishLiveData = new MutableLiveData<>();

    public MutableLiveData<UserInfo> getUserInfoMutableLiveData() {
        if (userInfoLiveData == null) {
            userInfoLiveData = new MutableLiveData<>();
        }
        return userInfoLiveData;
    }

    public MutableLiveData<Integer> getFinishLiveData() {
        return finishLiveData;
    }


    private MutableLiveData<String> handleCompleteLiveData;

    public MutableLiveData<String> getHandleCompleteLiveData() {
        if (handleCompleteLiveData == null) {
            handleCompleteLiveData = new MutableLiveData<>();
        }
        return handleCompleteLiveData;
    }







}
