package com.lhh.lnstagram.adapter;

import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.lhh.lnstagram.R;
import com.lhh.lnstagram.bean.PostArticleInfoBean;
import com.lhh.lnstagram.mvvm.util.CalculationPicUtil;

import java.util.List;

/**
 * 朋友圈列表图片适配器
 */
public class PostMomentsPicItemAdapter extends BaseQuickAdapter<PostArticleInfoBean, BaseViewHolder> {
    private int previewHeight;
    private int previewWidth;
    private String articleId;

    /**
     * @param data          数据
     * @param previewHeight 预览图高度
     */
    public PostMomentsPicItemAdapter(List<PostArticleInfoBean> data, int previewHeight, int previewWidth, String articleId) {
        super(R.layout.activity_post_moment_img_type_item_pic, data);
        this.previewHeight = previewHeight;
        this.previewWidth = previewWidth;
        this.articleId = articleId;
    }

    public PostMomentsPicItemAdapter() {
        super(R.layout.activity_post_moment_img_type_item_pic);
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    @Override
    protected void convert(BaseViewHolder helper, PostArticleInfoBean item) {
        LinearLayout ll_root = helper.getView(R.id.ll_root);
        ll_root.getLayoutParams().height = previewHeight;
        ll_root.getLayoutParams().width = previewWidth;

        ImageView imageView = helper.getView(R.id.iv_pic);
//        imageView.getLayoutParams().width = previewWidth;
//        imageView.getLayoutParams().height = previewHeight;
//        if (imageView.getLayoutParams() != null) {
//            int width = imageView.getLayoutParams().width;
//            int height = imageView.getLayoutParams().height;
//            if (width != previewWidth && height != previewHeight) {
//                updateView(imageView);
//            }
//        }
        if (item.getWidth() != 0 && item.getHeight() != 0) {
            imageView.getLayoutParams().width = previewWidth;
            double div = CalculationPicUtil.div(item.getWidth(), item.getHeight(), 1);
            if (div < 0.8) {
                imageView.getLayoutParams().height = previewHeight;
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } else {
                imageView.getLayoutParams().height = CalculationPicUtil.getImageHeight(previewWidth, item.getWidth(), item.getHeight());
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        } else {
            imageView.getLayoutParams().width = previewWidth;
            imageView.getLayoutParams().height = previewHeight;
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        //PostGlideUtil.loadPostImage(imageView, item.getAzureUrl(), item.getUrl(), articleId);

        Glide.with(mContext).load(R.drawable.splash).into(imageView);
    }

    private void updateView(ImageView imageView) {
        imageView.getLayoutParams().height = previewHeight;
        imageView.getLayoutParams().width = previewWidth;
    }


    public int getPreviewHeight() {
        return previewHeight;
    }

    public void setPreviewHeight(int previewHeight) {
        this.previewHeight = previewHeight;
    }

    public int getPreviewWidth() {
        return previewWidth;
    }

    public void setPreviewWidth(int previewWidth) {
        this.previewWidth = previewWidth;
    }
}