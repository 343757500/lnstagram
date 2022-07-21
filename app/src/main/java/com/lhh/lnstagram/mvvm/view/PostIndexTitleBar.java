package com.lhh.lnstagram.mvvm.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lhh.lnstagram.R;

/**
 * 自定义TitleBar
 *
 * @author XieWenjun
 * @version 1.0, 2014-9-22 上午11:03:23
 */
public class PostIndexTitleBar extends LinearLayout {

    public View titleLL;
    public View bottomLine;
    //    public CircleImageView iv_user_logo;
    public HeadImageView header_img;
    public TextView tv_title_name_left;
    public TextView titleRightTv;
    public TextView titleRightTv2;
    public TextView titleRightTv3;
    public TextView titleRightTv4;
    public TextView titleRightTv5;

    public PostIndexTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PostIndexTitleBar(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_post_index_title_bar, this, true);
        header_img = findViewById(R.id.header_img);
        tv_title_name_left = findViewById(R.id.tv_title_name_left);
        titleRightTv = findViewById(R.id.titleRightTv);
        titleRightTv2 = findViewById(R.id.titleRightTv2);
        titleRightTv3 = findViewById(R.id.titleRightTv3);
        titleRightTv4 = findViewById(R.id.titleRightTv4);
        titleRightTv5 = findViewById(R.id.titleRightTv5);
        titleLL = findViewById(R.id.titleLL);
        bottomLine = findViewById(R.id.bottomLine);
        titleRightTv.setVisibility(View.INVISIBLE);
        titleRightTv2.setVisibility(View.GONE);
        titleRightTv3.setVisibility(View.GONE);
        titleRightTv4.setVisibility(View.GONE);
        titleRightTv5.setVisibility(View.GONE);
    }


    /**
     * 设置头像
     */
    public HeadImageView setImageLeftUserLogo() {
        header_img.setVisibility(View.VISIBLE);
        return header_img;
    }

    /**
     * 设置居左标题
     *
     * @param titleName
     */
    public TextView setTitleNameLeft(String titleName) {
        tv_title_name_left.setText(titleName);
        tv_title_name_left.setVisibility(View.VISIBLE);
        return tv_title_name_left;
    }


    /**
     * 设置菜单
     *
     * @param titleRightStr
     */
    public TextView setTitleRight(String titleRightStr) {
        titleRightTv.setText(titleRightStr);
        titleRightTv.setVisibility(View.VISIBLE);
        return titleRightTv;
    }

    /**
     * 设置菜单
     *
     * @param resId 图标
     */
    public TextView setTitleRight(int resId) {
        titleRightTv.setText("");
        titleRightTv.setCompoundDrawablesWithIntrinsicBounds(resId == 0 ? null : getResources().getDrawable(resId), null, null, null);
        titleRightTv.setVisibility(View.VISIBLE);
        return titleRightTv;
    }

    /**
     * 设置菜单
     *
     * @param resId 图标
     */
    public TextView setTitleRight2(int resId) {
        titleRightTv2.setText("");
        titleRightTv2.setCompoundDrawablesWithIntrinsicBounds(resId == 0 ? null : getResources().getDrawable(resId), null, null, null);
        titleRightTv2.setVisibility(View.VISIBLE);
        return titleRightTv2;
    }

    /**
     * 设置菜单
     *
     * @param resId 图标
     */
    public TextView setTitleRight3(int resId) {
        titleRightTv3.setText("");
        titleRightTv3.setCompoundDrawablesWithIntrinsicBounds(resId == 0 ? null : getResources().getDrawable(resId), null, null, null);
        titleRightTv3.setVisibility(View.VISIBLE);
        return titleRightTv3;
    }

    /**
     * 设置菜单
     *
     * @param resId 图标
     */
    public TextView setTitleRight4(int resId) {
        titleRightTv4.setText("");
        titleRightTv4.setCompoundDrawablesWithIntrinsicBounds(resId == 0 ? null : getResources().getDrawable(resId), null, null, null);
        titleRightTv4.setVisibility(View.VISIBLE);
        return titleRightTv4;
    }

    /**
     * 设置菜单
     *
     * @param resId 图标
     */
    public TextView setTitleRight5(int resId) {
        titleRightTv5.setText("");
        titleRightTv5.setCompoundDrawablesWithIntrinsicBounds(resId == 0 ? null : getResources().getDrawable(resId), null, null, null);
        titleRightTv5.setVisibility(View.VISIBLE);
        return titleRightTv5;
    }

    // 设置成深色模式
    public void setWhiteColorMode() {
        // 背景色
        titleLL.setBackgroundColor(getResources().getColor(R.color.bg_white));
        bottomLine.setVisibility(View.VISIBLE);
        // 文字颜色
        int textColor = getResources().getColor(R.color.text_color_gray_dark);
        titleRightTv.setTextColor(textColor);
        titleRightTv2.setTextColor(textColor);
    }

}