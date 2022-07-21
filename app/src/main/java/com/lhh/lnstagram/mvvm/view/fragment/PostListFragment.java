package com.lhh.lnstagram.mvvm.view.fragment;

import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.chad.library.adapter.base.BaseQuickAdapter;

import com.google.gson.JsonSyntaxException;
import com.lhh.lnstagram.R;
import com.lhh.lnstagram.adapter.PostMomentsAdapter;
import com.lhh.lnstagram.bean.MomentBean;
import com.lhh.lnstagram.bean.PostArticleInfoBean;
import com.lhh.lnstagram.mvvm.base.AbsLifecycleFragment;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



/**
 * 朋友圈列表
 */
public class PostListFragment extends AbsLifecycleFragment<PostListViewModel> {


    private LinearLayout ll_root;
    private RecyclerView rv_moments;
    private PostMomentsAdapter userMomentsAdapter;
    private LinearLayoutManager mLayoutManager;
    private PostArticleInfoBean postArticleInfoBean;

    @Override
    public int setLayoutResId() {
        return R.layout.fragment_post_list;
    }

    @Override
    public void onCreateViewAfter(Bundle arguments) {
        super.onCreateViewAfter(arguments);
        Bundle bundle = getArguments();
        initView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initView() {
        ll_root = findViewById(R.id.ll_root);
        rv_moments = findViewById(R.id.rv_moments);

        List<MomentBean> momentBeans = new ArrayList<>();
        List<PostArticleInfoBean> articleInfo = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            postArticleInfoBean = new PostArticleInfoBean();
            articleInfo.add(postArticleInfoBean);
        }
        for (int i = 0; i < 100; i++) {
            MomentBean momentBean = new MomentBean();
            if (i% 2 == 0){
                momentBean.setArticleType(0);
            }else{
                momentBean.setArticleType(1);
            }
            momentBean.setArticleInfo(articleInfo);
            momentBeans.add(momentBean);
        }
        userMomentsAdapter = new PostMomentsAdapter(momentBeans);
        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rv_moments.setLayoutManager(mLayoutManager);
        rv_moments.setAdapter(userMomentsAdapter);
        userMomentsAdapter.setEnableLoadMore(false);
        userMomentsAdapter.setOnLoadMoreListener(null, rv_moments);
        userMomentsAdapter.setNewData(momentBeans);



    }
}

