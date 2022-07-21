package com.lhh.lnstagram.mvvm.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.lhh.lnstagram.bean.MomentBean;
import com.lhh.lnstagram.mvvm.util.TUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * MV-VM的VM (V=Activity/Fragment/xml, VM=ViewModel, M-Repository)
 * <p>
 * User <-> View<-> 【VieModel】 <-> Model
 * <p>
 * 1、View传递过来的数据，进行加工判断
 * 2、不持有任何UI控件，使用LiveData更新数据，驱动UI变化
 * <p>
 * 3、从Model中获取数据，不关注数据是Local还是Remote
 */
public class AbsViewModel<T extends AbsRepository> extends AndroidViewModel {

    private T mRepository;

    public AbsViewModel(@NonNull Application application) {
        super(application);
        mRepository = TUtil.getNewInstance(this, 0);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (mRepository != null) {
            mRepository.unDisposable();
            mRepository = null;
        }
    }

    public T getRepository() {
        if (mRepository != null) {
            return mRepository;
        } else {
            mRepository = TUtil.getNewInstance(this, 0);
            if (mRepository != null) {
                return mRepository;
            } else {
                return mRepository = TUtil.getInstance(this, 0);
            }
        }
    }

    // View请求ViewModel的4个生命周期(ViewMode->Repository通过CallBack回调)
    // Integer为了方便一个页面有多个请求，多个请求才需要在订阅上进行区分
    // onReqStart/onReqComplete/onReqError
    // Activity一般不需要监听这3个请求周期，因为有具体的progress、alert和toast等具体时间
    // onReqSuccess 这个请求周期
    // 一般处理请求成功的下一步
    protected MutableLiveData<Integer> onReqStart = new MediatorLiveData<>(); // 请求开始
    protected MutableLiveData<Integer> onReqComplete = new MediatorLiveData<>(); // 请求完成
    protected MutableLiveData<Integer> onReqError = new MediatorLiveData<>(); // 请求失败
    protected MutableLiveData<Integer> onReqSuccess = new MediatorLiveData<>(); // 请求成功

    // 请求过程中的加载框之一，之后可能会添加EmptyLayout其二
    protected MutableLiveData<String> progressInfo; // 加载框内容
    protected MutableLiveData<Integer> progressInfoResId; // 加载框内容
    protected MutableLiveData<Boolean> progressShow; // 加载框是否显示

    // 本地判断或请求过程中，普通、正确或错误的提示
    // 因为alert和toast基本都是展示数据不用做回调处理，所以统一交给由AbsLifecycleActivity做默认处理
    // 如果像showConfirmDialog的onOkClick和onCancel的具体处理还是交给具体Activity处理
    // 例如：在具体ViewModel定义showDelConfirm，showApplyFriend
    // alertDialog如果需要多做点事，也是和confirmDialog自己在具体ViewModel定义
    protected MutableLiveData<Integer> alertIconResId; // 提示框图标
    protected MutableLiveData<String> alertInfo; // 提示框

    protected MutableLiveData<String> toastInfo; // 普通提示
    protected MutableLiveData<String> toastSuccess; // 成功提示
    protected MutableLiveData<String> toastError; // 错误提示
    protected MutableLiveData<Integer> RemitTokeOut; // remitToke过时回调

    public MutableLiveData<Integer> getOnReqStart() {
        if (onReqStart == null) {
            onReqStart = new MediatorLiveData<Integer>();
        }
        return onReqStart;
    }

    public MutableLiveData<Integer> getOnReqComplete() {
        if (onReqComplete == null) {
            onReqComplete = new MediatorLiveData<Integer>();
        }
        return onReqComplete;
    }

    public MutableLiveData<Integer> getOnReqError() {
        if (onReqError == null) {
            onReqError = new MediatorLiveData<Integer>();
        }
        return onReqError;
    }

    public MutableLiveData<Integer> getOnReqSuccess() {
        if (onReqSuccess == null) {
            onReqSuccess = new MediatorLiveData<Integer>();
        }
        return onReqSuccess;
    }

    public MutableLiveData<Integer> getProgressInfoResId() {
        if (progressInfoResId == null) {
            progressInfoResId = new MediatorLiveData<Integer>();
        }
        return progressInfoResId;
    }

    public MutableLiveData<String> getProgressInfo() {
        if (progressInfo == null) {
            progressInfo = new MediatorLiveData<String>();
        }
        return progressInfo;
    }

    public MutableLiveData<Boolean> getProgressShow() {
        if (progressShow == null) {
            progressShow = new MediatorLiveData<Boolean>();
        }
        return progressShow;
    }


    public MutableLiveData<String> getToastInfo() {
        if (toastInfo == null) {
            toastInfo = new MediatorLiveData<String>();
        }
        return toastInfo;
    }

    public MutableLiveData<String> getToastSuccess() {
        if (toastSuccess == null) {
            toastSuccess = new MediatorLiveData<String>();
        }
        return toastSuccess;
    }

    public MutableLiveData<String> getToastError() {
        if (toastError == null) {
            toastError = new MediatorLiveData<String>();
        }
        return toastError;
    }

    public MutableLiveData<Integer> getRemitTokeOut() {
        if (RemitTokeOut == null) {
            RemitTokeOut = new MediatorLiveData<Integer>();
        }
        return RemitTokeOut;
    }


    public MutableLiveData<String> getAlertInfo() {
        if (alertInfo == null) {
            alertInfo = new MediatorLiveData<String>();
        }
        return alertInfo;
    }

    public MutableLiveData<Integer> getAlertIconResId() {
        if (alertIconResId == null) {
            alertIconResId = new MediatorLiveData<Integer>();
        }
        return alertIconResId;
    }
}
