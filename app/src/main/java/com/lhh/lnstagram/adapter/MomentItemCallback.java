package com.lhh.lnstagram.adapter;

import android.view.View;

import com.lhh.lnstagram.bean.MomentBean;

public interface MomentItemCallback {
    void onFocus(View root, View view, int position);

    void onLoseFocus(View view, int position);

    void onLikeClick(MomentBean momentBean, int position);

    void onLikeValueClick(MomentBean momentBean, int position);

    void onShareClick(MomentBean momentBean, int position);

    void onPicClick(MomentBean momentBean, int position);

    void onVideoClick(MomentBean momentBean, int position, long currentPosition);

    void onLiveClick(MomentBean momentBean, int position);

    void onAudioClick(MomentBean momentBean, int position);
}
