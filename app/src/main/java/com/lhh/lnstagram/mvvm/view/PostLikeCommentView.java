package com.lhh.lnstagram.mvvm.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;


import com.lhh.lnstagram.R;
import com.lhh.lnstagram.bean.MomentBean;

import java.util.List;

import guide.util.StringUtil;

/**
 * 朋友圈点赞评论View
 */
public class PostLikeCommentView extends LinearLayout {
    private Context mContext;
    private MomentBean momentBean;
    private TextView tv_like_ctrl;
    private TextView tv_comment_ctrl;
    private TextView tv_share_ctrl;
    private TextView tv_like_value;
    private TextView tv_comment_value;
    private LikeCommentCallBack callBack;
    private int position;//当前view所在列表下标
    private boolean isWhiteTheme;
    private static final String AND = " " + StringUtil.getString(R.string.post_ctrl_prompt_text_and) + " ";
    private static final String YOU = StringUtil.getString(R.string.post_ctrl_prompt_text_you);


    public void setCallBack(LikeCommentCallBack callBack) {
        this.callBack = callBack;
    }

    public PostLikeCommentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PostLikeCommentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void setWhiteTheme() {
        isWhiteTheme = true;
        tv_like_ctrl.setTextColor(getResources().getColor(R.color.text_color_white));
        tv_comment_ctrl.setTextColor(getResources().getColor(R.color.text_color_white));
        tv_share_ctrl.setTextColor(getResources().getColor(R.color.text_color_white));
        tv_like_value.setTextColor(getResources().getColor(R.color.text_color_white));
        tv_comment_value.setTextColor(getResources().getColor(R.color.text_color_white));


        Drawable drawableLike = ContextCompat.getDrawable(mContext, R.drawable.post_icon_like_nor_white);
        assert drawableLike != null;
        drawableLike.setBounds(0, 0, drawableLike.getMinimumWidth(), drawableLike.getMinimumHeight());
        tv_like_ctrl.setCompoundDrawables(drawableLike, null, null, null);


        Drawable drawableComment = ContextCompat.getDrawable(mContext, R.drawable.post_icon_comment_white);
        assert drawableComment != null;
        drawableComment.setBounds(0, 0, drawableComment.getMinimumWidth(), drawableComment.getMinimumHeight());
        tv_comment_ctrl.setCompoundDrawables(drawableComment, null, null, null);


        Drawable drawable = ContextCompat.getDrawable(mContext, R.drawable.post_icon_share_white);
        assert drawable != null;
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tv_share_ctrl.setCompoundDrawables(drawable, null, null, null);
    }

    private void initView(Context context) {
        this.mContext = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_post_item_like_comment, this, true);
        tv_like_ctrl = inflate.findViewById(R.id.tv_like_ctrl);
        tv_comment_ctrl = inflate.findViewById(R.id.tv_comment_ctrl);
        tv_share_ctrl = inflate.findViewById(R.id.tv_share_ctrl);
        tv_like_value = inflate.findViewById(R.id.tv_like_value);
        tv_comment_value = inflate.findViewById(R.id.tv_comment_value);
        tv_like_ctrl.setOnClickListener(v -> {
            if (callBack != null && momentBean != null) {
                callBack.onLikeClick(momentBean, position);
            }
        });

        tv_comment_ctrl.setOnClickListener(v -> {
            if (callBack != null && momentBean != null) {
                callBack.onCommentClick(momentBean);
            }
        });
    }

    public void setData(MomentBean momentBean) {
        this.momentBean = momentBean;
        updateView(momentBean);
    }

    public void setData(MomentBean momentBean, int position, LikeCommentCallBack callBack) {
        this.momentBean = momentBean;
        this.callBack = callBack;
        this.position = position;
        updateView(momentBean);
    }

    public void setData(MomentBean momentBean, LikeCommentCallBack callBack) {
        this.momentBean = momentBean;
        this.callBack = callBack;
        updateView(momentBean);
    }

    private void updateView(MomentBean momentBean) {


    }

    public interface LikeCommentCallBack {
        void onLikeClick(MomentBean momentBean, int position);

        void onLikeValueClick(MomentBean momentBean);

        void onShareClick(MomentBean momentBean, int position);

        void onCommentClick(MomentBean momentBean);

    }


    private String getLikes(int likeCount) {
        if (likeCount > 1) {
            return StringUtil.getString(R.string.post_ctrl_prompt_text_likes);
        } else {
            return StringUtil.getString(R.string.post_ctrl_prompt_text_for_likes);
        }
    }

    private String getNickName(String nickName) {
        if (TextUtils.isEmpty(nickName)) {
            return "";
        }
        if (nickName.length() > 8) {
            return nickName.substring(0, 8) + "...";
        }
        return nickName;
    }

}
