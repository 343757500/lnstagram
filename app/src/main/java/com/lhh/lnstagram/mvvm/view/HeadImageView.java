package com.lhh.lnstagram.mvvm.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lhh.lnstagram.R;

/**
 * 带认证logo的头像
 */
public class HeadImageView extends LinearLayout {
    private ImageView iv_public_account_certification;
    private CircleImageView iv_user_head;
    private static final int DEFAULT_BORDER_WIDTH = 0;
    private static final int DEFAULT_BORDER_COLOR = Color.BLACK;
    private int mBorderColor = DEFAULT_BORDER_COLOR;
    private int mBorderWidth = DEFAULT_BORDER_WIDTH;
    private int mHeadWidth = 0;
    private int mHeadHeight = 0;
    private int mIconWidth = 0;
    private int mIconHeight = 0;

    public HeadImageView(Context context) {
        super(context);
        initView(context);
    }

    public HeadImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        initView(context);
    }

    public CircleImageView getIvUserHead() {
        return iv_user_head;
    }

    public HeadImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HeadImageView);
        mBorderWidth = typedArray.getDimensionPixelSize(R.styleable.HeadImageView_head_border_width, DEFAULT_BORDER_WIDTH);
        mBorderColor = typedArray.getColor(R.styleable.HeadImageView_head_border_color, DEFAULT_BORDER_COLOR);
        mHeadWidth = typedArray.getDimensionPixelSize(R.styleable.HeadImageView_head_image_width, 0);
        mHeadHeight = typedArray.getDimensionPixelSize(R.styleable.HeadImageView_head_image_height, 0);
        mIconWidth = typedArray.getDimensionPixelSize(R.styleable.HeadImageView_head_icon_width, 0);
        mIconHeight = typedArray.getDimensionPixelSize(R.styleable.HeadImageView_head_icon_height, 0);
        typedArray.recycle();
        initView(context);
    }

    private void initView(Context context) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.view_post_head_view_certification, this, true);
        iv_user_head = inflate.findViewById(R.id.iv_user_head);
        iv_public_account_certification = inflate.findViewById(R.id.iv_public_account_certification);
        iv_user_head.setBorderColor(mBorderColor);
        iv_user_head.setBorderWidth(mBorderWidth);
        iv_user_head.getLayoutParams().width = mHeadWidth;
        iv_user_head.getLayoutParams().height = mHeadHeight;
        iv_public_account_certification.getLayoutParams().width = mIconWidth;
        iv_public_account_certification.getLayoutParams().height = mIconHeight;

        //测试
//        setCertificationCompany();
    }
}
