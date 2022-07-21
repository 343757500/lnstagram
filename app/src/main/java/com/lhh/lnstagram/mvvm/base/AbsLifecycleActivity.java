package com.lhh.lnstagram.mvvm.base;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.lhh.lnstagram.R;
import com.lhh.lnstagram.base.BaseActivity;
import com.lhh.lnstagram.dialog.DialogUtil;
import com.lhh.lnstagram.mvvm.util.TUtil;
import com.lhh.lnstagram.mvvm.util.ToastUtil;

import guide.util.StringUtil;


/**
 * MV-VM的V (V=Activity/Fragment/xml, VM=ViewModel, M-Repository)
 *
 * User <-> 【View】<-> VieModel <-> Model
 *
 * 0、View只负责和用户交互（收集用户动作和展示数据给用户）
 *
 * 1、更新UI控件显示，由ViewModel 驱动(在dataObserver中检测viewModel)
 * 2、监听UI事件和声明周期，驱动ViewMode（在onclick中调用viewModel）
 *
 * 【强调】不直接处理任何业务逻辑，包括数据加工（这是ViewModel的工作）
 *
 */
public abstract class AbsLifecycleActivity<T extends AbsViewModel> extends BaseActivity {
    protected T mViewModel;

    public AbsLifecycleActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAbsViewModel();
    }

    public void initAbsViewModel() {
        mViewModel = VMProviders(this, (Class<T>) TUtil.getInstance(this, 0));
        if (autoDataObserver() && mViewModel != null) {
            dataObserver();
        }
    }

    protected <T extends ViewModel> T VMProviders(AppCompatActivity fragment, @NonNull Class modelClass) {
        try {
            return (T) ViewModelProviders.of(fragment).get(modelClass);
        } catch (Exception e) {
            return null;
        }
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
