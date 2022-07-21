package com.lhh.lnstagram.mvvm.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lhh.lnstagram.R;
import com.lhh.lnstagram.adapter.MomentItemCallback;
import com.lhh.lnstagram.adapter.MomentPicIndicatorAdapter;
import com.lhh.lnstagram.adapter.PostMomentsPicItemAdapter;
import com.lhh.lnstagram.bean.MomentBean;
import com.lhh.lnstagram.bean.PostArticleInfoBean;
import com.lhh.lnstagram.mvvm.util.CalculationPicUtil;
import com.lhh.lnstagram.mvvm.view.indicator.CircleIndicator2;

import java.util.List;

/**
 * 朋友圈列表图片查看器
 */
public class PicScrollView extends LinearLayout {
    private PagerSnapHelper snapHelper;
    private int mLastPosition = -1;
    private MomentPicIndicatorAdapter indicatorAdapter;
    private RecyclerView rv_user_pic;
    private TextView tv_indicator;
    private TextView tv_indicator_bottom;//底部数字
    //    private RecyclerView rv_indicator_pic;
    private CircleIndicator2 indicator;
    private PostMomentsPicItemAdapter picItemAdapter;
    //    private List<PostArticleInfoBean> files;
    private MomentBean mMomentBean;
    private MomentItemCallback momentItemCallback;

    //    private boolean isShowIndicator;
    public PicScrollView(Context context) {
        this(context, null);
    }

    public PicScrollView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public PicScrollView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.view_pic_scroll, this, true);

        rv_user_pic = inflate.findViewById(R.id.rv_user_pic);
//        rv_indicator_pic = inflate.findViewById(R.id.rv_indicator_pic);
        indicator = inflate.findViewById(R.id.indicator);
        tv_indicator = inflate.findViewById(R.id.tv_indicator);
        tv_indicator_bottom = inflate.findViewById(R.id.tv_indicator_bottom);
        LinearLayoutManager manager = new LinearLayoutManager(context) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_user_pic.setLayoutManager(manager);
        LinearLayoutManager managerIndicator = new LinearLayoutManager(context) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        managerIndicator.setOrientation(LinearLayoutManager.HORIZONTAL);
//        rv_indicator_pic.setLayoutManager(managerIndicator);
//        rv_user_pic.setOnFlingListener(null);

        snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rv_user_pic);
        picItemAdapter = new PostMomentsPicItemAdapter();
        myPicItemOnClickListener = new MyPicItemOnClickListener();
        picItemAdapter.setOnItemClickListener(myPicItemOnClickListener);
//        rv_user_pic.setRecycledViewPool(PostRecyclerViewPoolManager.getInstance().getPostPicItemRecycledViewPool());
        rv_user_pic.setAdapter(picItemAdapter);

        myScrollListener = new MyScrollListener();
        rv_user_pic.clearOnScrollListeners();
        rv_user_pic.addOnScrollListener(myScrollListener);


        indicatorAdapter = new MomentPicIndicatorAdapter();
//        rv_indicator_pic.setRecycledViewPool(PostRecyclerViewPoolManager.getInstance().getPostPicIndicatorItemRecycledViewPool());
//        rv_indicator_pic.setAdapter(indicatorAdapter);
//        rv_indicator_pic.setItemAnimator(null);
        if (rv_user_pic.getOnFlingListener() != null) {
            rv_user_pic.setOnFlingListener(null);
        }
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rv_user_pic);
        indicator.attachToRecyclerView(rv_user_pic, snapHelper);
        picItemAdapter.registerAdapterDataObserver(indicator.getAdapterDataObserver());

        myPicIndicatorItemOnClickListener = new MyPicIndicatorItemOnClickListener();
        indicatorAdapter.setOnItemClickListener(myPicIndicatorItemOnClickListener);
    }

    public void setData(MomentBean momentBean, int previewHeight, int previewWidth, MomentItemCallback callback) {
        this.mMomentBean = momentBean;
        this.momentItemCallback = callback;
        if (momentBean != null && momentBean.getArticleInfo() != null && momentBean.getArticleInfo().size() > 0) {
            previewHeight = CalculationPicUtil.picCount(momentBean.getArticleInfo(), previewWidth, previewHeight);
            Log.i("TAG", "内容handleMomentImageType: " + momentBean.getArticleContent());
            Log.i("TAG", "计算高度handleMomentImageType: " + previewHeight);
            Log.i("TAG", "宽度handleMomentImageType: " + previewWidth);

            List<PostArticleInfoBean> files = mMomentBean.getArticleInfo();
            int size = files.size();
            if (size > 0) {
                files.get(mMomentBean.index).setSelected(true);

                rv_user_pic.setVisibility(View.VISIBLE);
                //图片适配器
                picItemAdapter.setPreviewHeight(previewHeight);
                picItemAdapter.setPreviewWidth(previewWidth);
                picItemAdapter.setArticleId(mMomentBean.getArticleId());
                picItemAdapter.setNewData(files);

                setTvIndicator(size, momentBean.getDataType());

                if (size == 1) {
//                    rv_indicator_pic.setVisibility(View.GONE);
                    indicator.setVisibility(View.GONE);
                } else {
//                    rv_indicator_pic.setVisibility(View.VISIBLE);
                    if (momentBean.getDataType() == 1) {
                        //广告图片隐藏指示器
                        indicator.setVisibility(View.GONE);
                    } else {
                        indicator.setVisibility(View.VISIBLE);
                    }
                    indicatorAdapter.setArticleId(mMomentBean.getArticleId());
                    indicatorAdapter.setNewData(files);
//                    rv_indicator_pic.scrollToPosition(mMomentBean.index);
                }
                rv_user_pic.scrollToPosition(mMomentBean.index);

            }
        } else {
            tv_indicator.setVisibility(View.GONE);
            rv_user_pic.setVisibility(View.GONE);
//            rv_indicator_pic.setVisibility(View.GONE);
            indicator.setVisibility(View.GONE);
            tv_indicator_bottom.setVisibility(GONE);
        }

    }

    private void setTvIndicator(int size, int dataType) {
        if (size > 1) {
            if (dataType == 1) {
                tv_indicator.setVisibility(View.GONE);
                tv_indicator_bottom.setVisibility(View.VISIBLE);
                tv_indicator_bottom.setText((mMomentBean.index + 1) + "/" + size);
            } else {
                tv_indicator_bottom.setVisibility(View.GONE);
                tv_indicator.setVisibility(View.VISIBLE);
                tv_indicator.setText((mMomentBean.index + 1) + "/" + size);
            }

        } else {
            tv_indicator.setVisibility(View.GONE);
            tv_indicator_bottom.setVisibility(View.GONE);
        }
    }

    private MyScrollListener myScrollListener;

    private MyPicItemOnClickListener myPicItemOnClickListener;

    private MyPicIndicatorItemOnClickListener myPicIndicatorItemOnClickListener;

    private class MyPicIndicatorItemOnClickListener implements BaseQuickAdapter.OnItemClickListener {

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            if (rv_user_pic == null) {
                return;
            }
            if (mMomentBean == null) {
                return;
            }
            if (position == RecyclerView.NO_POSITION) {
                return;
            }
            if (mLastPosition == position) {
                return;
            }
            mMomentBean.index = position;
            rv_user_pic.scrollToPosition(mMomentBean.index);
        }
    }


    private class MyPicItemOnClickListener implements BaseQuickAdapter.OnItemClickListener {


        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            if (momentItemCallback != null && mMomentBean != null) {
                momentItemCallback.onPicClick(mMomentBean, position);
            }
        }
    }


    private class MyScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int position = getSnapPosition(recyclerView.getLayoutManager(), snapHelper);
            if (position == RecyclerView.NO_POSITION) {
                return;
            }
            if (mLastPosition == position) {
                return;
            }
            if (mMomentBean == null || mMomentBean.getArticleInfo() == null) {
                return;
            }
            int size = mMomentBean.getArticleInfo().size();
            List<PostArticleInfoBean> files = mMomentBean.getArticleInfo();
            if (mLastPosition > -1 && mLastPosition < size) {
                files.get(mLastPosition).setSelected(false);
            }
            mMomentBean.index = position;
            files.get(position).setSelected(true);
            indicatorAdapter.notifyItemChanged(mLastPosition);
            indicatorAdapter.notifyItemChanged(mMomentBean.index);
//            rv_indicator_pic.smoothScrollToPosition(position);
            if (size > 1) {
                String indicatorStr = (mMomentBean.index + 1) + "/" + size;
                tv_indicator.setText(indicatorStr);
                if (mMomentBean.getDataType() == 1) {
                    tv_indicator_bottom.setText(indicatorStr);
                }
            }
            mLastPosition = position;
        }
    }

    private int getSnapPosition(@Nullable RecyclerView.LayoutManager layoutManager, PagerSnapHelper mSnapHelper) {
        if (layoutManager == null) {
            return RecyclerView.NO_POSITION;
        }
        View snapView = mSnapHelper.findSnapView(layoutManager);
        if (snapView == null) {
            return RecyclerView.NO_POSITION;
        }
        return layoutManager.getPosition(snapView);
    }
}
