package com.lhh.lnstagram.mvvm.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.lhh.lnstagram.aroute.MyRoute;
import com.lhh.lnstagram.aroute.RoutePath;
import com.lhh.lnstagram.bean.MomentBean;
import com.lhh.lnstagram.mvvm.base.AbsViewModel;

import java.util.ArrayList;
import java.util.List;

public class StartViewModel extends AbsViewModel {


    private MutableLiveData<Boolean> goLiveData = new MutableLiveData<>();

    public MutableLiveData<Boolean> goMain() {
        if (goLiveData == null) {
            goLiveData = new MediatorLiveData<Boolean>();
        }
        return goLiveData;
    }


    public StartViewModel(@NonNull Application application) {
        super(application);
    }


    public void forTime(){
        getProgressShow().postValue(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                getProgressShow().postValue(false);
                goLiveData.postValue(true);
            }
        }).start();

        //bannerLiveData.postValue(momentBeanList);
    }





}
