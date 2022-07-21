package com.lhh.lnstagram.mvvm.base;


import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;

import com.lhh.lnstagram.https.BaseApiService;
import com.lhh.lnstagram.https.BaseReq;
import com.lhh.lnstagram.https.BaseResp;
import com.lhh.lnstagram.https.HttpHelper;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * MV-VM的V (V=Activity/Fragment/xml, VM=ViewModel, M-Repository)
 * <p>
 * User <-> View<-> VieModel <-> 【Model】-【Data】
 * <p>
 * 1、数据采集，来源有缓存、本地存储(DB、File、SharePreferences)和网络远程数据(Http、Socket)
 * 2、返回数据给ViewModel
 * <p>
 * 【问题】
 * 1、Model层如何与ViewModel通信
 * 2、Repository 如何整合各路数据，进行业务处理
 * 【方案】
 * LiveBus/EventBus/RxBus？
 * Transformations.switchMap?
 */
public abstract class AbsRepository {

    private CompositeDisposable mCompositeDisposable;
    private BaseApiService mBaseApiService;

    public AbsRepository() {
        // HTTP
        if (mBaseApiService == null) {
            mBaseApiService = HttpHelper.getInstance().getRetrofit().create(BaseApiService.class);
        }
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
    }


    // start of http
    protected void addDisposable(Disposable disposable) {
        if (disposable == null) {
            return;
        }
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    public void unDisposable() {
        if (mCompositeDisposable != null && mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.clear();
        }
        if (mBaseApiService != null) {
            mBaseApiService = null;
        }
    }

    public void reqHttp(BaseReq baseReq, @NonNull Observer<BaseResp> onChanged) {
        addDisposable( HttpHelper.getInstance().reqHttp(baseReq, onChanged) );
    }
    // end of http




}
