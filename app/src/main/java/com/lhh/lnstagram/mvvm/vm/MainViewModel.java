package com.lhh.lnstagram.mvvm.vm;


import android.app.Application;
import androidx.annotation.NonNull;
import com.lhh.lnstagram.base.MainApiRepository;
import com.lhh.lnstagram.bean.MomentBean;
import com.lhh.lnstagram.mvvm.base.AbsViewModel;

import java.util.ArrayList;
import java.util.List;


public class MainViewModel extends AbsViewModel<MainApiRepository> {


    public MainViewModel(@NonNull Application application) {
        super(application);
    }


    public void getShow() {
            getProgressShow().postValue(true);
            List<MomentBean> momentBeanList = new ArrayList<>();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < 4; i++) {
                        MomentBean momentBean = new MomentBean();
                        momentBeanList.add(momentBean);
                        getProgressShow().postValue(false);
                    }
                }
            }).start();

            //bannerLiveData.postValue(momentBeanList);


    }
}