package com.lhh.lnstagram.mvvm.vm;

import android.app.Application;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;


import com.lhh.lnstagram.base.DiscoveryApiRepository;
import com.lhh.lnstagram.bean.MomentBean;
import com.lhh.lnstagram.mvvm.base.AbsViewModel;

import java.util.ArrayList;
import java.util.List;


public class DiscoveryViewModel extends AbsViewModel<DiscoveryApiRepository> {
    public DiscoveryViewModel(@NonNull Application application) {
        super(application);
    }

    private MutableLiveData<List<MomentBean>> bannerLiveData = new MutableLiveData<>();

    public MutableLiveData<List<MomentBean>> getMomentBean() {
        if (bannerLiveData == null) {
            bannerLiveData = new MediatorLiveData<List<MomentBean>>();
        }
        return bannerLiveData;
    }



    public void forTime(){
//        getProgressShow().postValue(true);
//        List<MomentBean> momentBeanList = new ArrayList<>();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(4000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                for (int i = 0; i < 4; i++) {
//                    MomentBean momentBean = new MomentBean();
//                    momentBeanList.add(momentBean);
//                    getProgressShow().postValue(false);
//                }
//            }
//        }).start();

        //bannerLiveData.postValue(momentBeanList);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
