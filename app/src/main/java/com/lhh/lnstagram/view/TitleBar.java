package com.lhh.lnstagram.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lhh.lnstagram.R;

/**
 * 自定义TitleBar
 * @author XieWenjun
 * @version
 *    1.0, 2014-9-22 上午11:03:23
 */
public class TitleBar extends RelativeLayout {

    public View titleLL;
    public View bottomLine;
    public TextView titleLeftTv;
    public TextView titleNameTv;
    public TextView titleRightTv;
    public TextView titleRightTv2;
    public TextView titleRightTv3;
    public TextView titleRightTv4;

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TitleBar(Context context) {
        super(context);
        init(context);
    }

	private void init(Context context) {
		LayoutInflater.from(context).inflate(R.layout.view_custom_title_bar, this, true);
		titleNameTv = findViewById(R.id.titleNameTv);
		titleLeftTv = findViewById(R.id.titleLeftTv);
		titleRightTv = findViewById(R.id.titleRightTv);
		titleRightTv2 = findViewById(R.id.titleRightTv2);
		titleLL = findViewById(R.id.titleLL);
		bottomLine = findViewById(R.id.bottomLine);
		titleRightTv.setVisibility(View.INVISIBLE);
		titleRightTv2.setVisibility(View.GONE);
        titleRightTv3 = findViewById(R.id.titleRightTv3);
        titleRightTv3.setVisibility(View.GONE);
        titleRightTv4 = findViewById(R.id.titleRightTv4);
        titleRightTv4.setVisibility(View.GONE);
	}

    /**
     * 设置标题
     *
     * @param titleName
     */
    public TextView setTitle(String titleName) {
        titleNameTv.setText(titleName);
        titleNameTv.setVisibility(View.VISIBLE);
        return titleNameTv;
    }

    public TextView setTitleColor(int colorId) {
        titleNameTv.setTextColor(getResources().getColor(colorId));
        return titleNameTv;
    }

    /**
     * 设置返回
     *
     * @param titleLeftStr
     */
    public TextView setTitleLeft(String titleLeftStr) {
        titleLeftTv.setText(titleLeftStr);
        titleLeftTv.setVisibility(View.VISIBLE);
        return titleLeftTv;
    }

    /**
     * 设置返回
     *
     * @param resId 图标
     */
    public TextView setTitleLeft(int resId) {
        titleLeftTv.setText("");
        titleLeftTv.setCompoundDrawablesWithIntrinsicBounds(resId == 0 ? null : getResources().getDrawable(resId), null, null, null);
        titleLeftTv.setVisibility(View.VISIBLE);
        return titleLeftTv;
    }

    /**
     * 设置返回
     *
     * @param titleLeftStr
     */
    public TextView setTitleLeftTextAndIcon(String titleLeftStr, int resId) {
        titleLeftTv.setText(titleLeftStr);
        titleLeftTv.setCompoundDrawablesWithIntrinsicBounds(resId == 0 ? null : getResources().getDrawable(resId), null, null, null);
        titleLeftTv.setVisibility(View.VISIBLE);
        return titleLeftTv;
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

    public void setTitleNameGravityLeftMode() {
        titleNameTv.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
    }

	public void setBottomLineVisibility(int visibility){
		bottomLine.setVisibility(visibility);
	}

	// 设置成深色模式
	public void setWhiteColorMode() {
		// 背景色
		titleLL.setBackgroundColor(getResources().getColor(R.color.bg_white));
		bottomLine.setVisibility(View.VISIBLE);
		// 左边箭头
		titleLeftTv.setCompoundDrawablesWithIntrinsicBounds(
				getResources().getDrawable(R.drawable.common_icon_back_black), null, null, null);
		// 文字颜色
		int textColor = getResources().getColor(R.color.text_color_gray_dark);
		titleLeftTv.setTextColor(textColor);
		titleNameTv.setTextColor(textColor);
		titleRightTv.setTextColor(textColor);
		titleRightTv2.setTextColor(textColor);
	}

    public void setBlueColorMode() {
        // 背景色
        titleLL.setBackgroundColor(getResources().getColor(R.color.color_white));
        bottomLine.setVisibility(View.GONE);
        // 左边箭头
        titleLeftTv.setCompoundDrawablesWithIntrinsicBounds(
                getResources().getDrawable(R.drawable.common_icon_return), null, null, null);
        // 文字颜色
        int textColor = getResources().getColor(R.color.white);
        titleLeftTv.setTextColor(textColor);
        titleNameTv.setTextColor(textColor);
        titleRightTv.setTextColor(textColor);
        titleRightTv2.setTextColor(textColor);
    }

	public void setBgColor(int color){
		titleLL.setBackgroundColor(getResources().getColor(color));
	}

    public void setTitleBarVisible(boolean flags){
        if (flags){
            titleLL.setVisibility(View.VISIBLE);
        }else {
            titleLL.setVisibility(View.GONE);
        }
    }
}