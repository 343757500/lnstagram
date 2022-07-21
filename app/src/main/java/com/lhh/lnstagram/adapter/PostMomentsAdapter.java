package com.lhh.lnstagram.adapter;





import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lhh.lnstagram.R;
import com.lhh.lnstagram.bean.MomentBean;
import com.lhh.lnstagram.mvvm.view.PicScrollView;


import java.util.List;

/**
 * 用户朋友圈适配器
 */
public class PostMomentsAdapter extends BaseMultiItemQuickAdapter<MomentBean, BaseViewHolder> {

    private MomentItemCallback callback;
    /**

     */
    public PostMomentsAdapter(List<MomentBean> data) {
        super(data);
        addItemType(MomentBean.MULTI_TEXT, R.layout.activity_post_moment_list_item_text);
        addItemType(MomentBean.MULTI_IMG, R.layout.activity_post_moment_list_item_img);
    }


    @Override
    protected void convert(BaseViewHolder helper, MomentBean item) {
        switch (item.getItemType()){
            case MomentBean.MULTI_IMG:
                handleMomentImageType(helper, item);
                break;
        }

    }

    /**
     * 处理朋友圈类型：Image
     */
    private void handleMomentImageType(BaseViewHolder helper, MomentBean item) {
        PicScrollView pic_scroll_view = helper.getView(R.id.pic_scroll_view);
        pic_scroll_view.setData(item, 810, 1080, callback);
    }

}


