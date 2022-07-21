package com.lhh.lnstagram.adapter;

import android.view.View;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lhh.lnstagram.R;
import com.lhh.lnstagram.bean.PostArticleInfoBean;
import com.makeramen.roundedimageview.RoundedImageView;


import java.util.List;

/**
 * 图片列表指示器
 */
public class MomentPicIndicatorAdapter extends BaseQuickAdapter<PostArticleInfoBean, BaseViewHolder> {

    Callback callback;
    private String articleId;

    public MomentPicIndicatorAdapter(List<PostArticleInfoBean> locationModelList, Callback callback, String articleId) {
        super(R.layout.activity_post_pic_indicator_item, locationModelList);
        this.callback = callback;
        this.articleId = articleId;
    }


    public MomentPicIndicatorAdapter() {
        super(R.layout.activity_post_pic_indicator_item);
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    @Override
    protected void convert(BaseViewHolder helper, PostArticleInfoBean item) {
        RoundedImageView iv_indicator = helper.getView(R.id.iv_indicator);
        FrameLayout fl_indicator = helper.getView(R.id.fl_indicator);
        if (item.isSelected()) {
//            fl_indicator.setBackground(ContextCompat.getDrawable(mContext, R.drawable.post_bg_img_indicator));
            fl_indicator.setBackgroundResource(R.drawable.post_bg_img_indicator);
        }else{
            fl_indicator.setBackgroundResource(0);
        }

        Glide.with(mContext)
                .load(R.drawable.splash)
                .into(iv_indicator);
    }

    public interface Callback {
        void onClick(View v, int position);
    }
}
