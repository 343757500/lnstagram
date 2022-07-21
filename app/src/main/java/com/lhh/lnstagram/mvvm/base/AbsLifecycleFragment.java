package com.lhh.lnstagram.mvvm.base;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.lhh.lnstagram.R;
import com.lhh.lnstagram.base.BaseFragment;
import com.lhh.lnstagram.dialog.DialogUtil;
import com.lhh.lnstagram.mvvm.util.TUtil;
import com.lhh.lnstagram.mvvm.util.ToastUtil;

import guide.util.StringUtil;


public abstract class AbsLifecycleFragment<T extends AbsViewModel> extends BaseFragment {

    protected T mViewModel;



    @Override
    public void onCreateViewAfter(Bundle arguments) {
        super.onCreateViewAfter(arguments);
        initViewModel();
    }

    public void initViewModel() {
        mViewModel = VMProviders(this, (Class<T>) TUtil.getInstance(this, 0));
        if (null != mViewModel && autoDataObserver()) {
            dataObserver();
        }
    }

    /**
     * create ViewModelProviders
     *
     * @return ViewModel
     */
    protected <T extends ViewModel> T VMProviders(BaseFragment fragment, @NonNull Class<T> modelClass) {
        return ViewModelProviders.of(fragment).get(modelClass);

    }

    protected boolean autoDataObserver() {
        return true;
    }

    protected void dataObserver() {
        mViewModel.getAlertInfo().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (!TextUtils.isEmpty(s)) {
                    ToastUtil.show(s); DialogUtil.showAlertDialog(mContext, s);
                }
            }
        });


        mViewModel.getProgressShow().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                String progressInfo = null;
                if (mViewModel.getProgressInfoResId().getValue() != null) {
                    progressInfo = StringUtil.getString((int)mViewModel.getProgressInfoResId().getValue()) ;
                }
                if (aBoolean) {
                    DialogUtil.showProgressHUD(mContext,
                            TextUtils.isEmpty(progressInfo) ? StringUtil.getString(R.string.common_tip_on_request) : progressInfo);
                } else {
                    DialogUtil.closeProgressDialog();
                }
            }
        });


        mViewModel.getToastInfo().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (!TextUtils.isEmpty(s)) {
                    ToastUtil.show(s);
                }
            }
        });
        mViewModel.getToastSuccess().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (!TextUtils.isEmpty(s)) {
                    ToastUtil.show(s);
                }
            }
        });
        mViewModel.getToastError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (!TextUtils.isEmpty(s)) {
                    ToastUtil.show(s);
                }
            }
        });
    }

}
