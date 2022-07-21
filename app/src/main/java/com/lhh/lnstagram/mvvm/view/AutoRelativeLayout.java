package com.lhh.lnstagram.mvvm.view;

import static android.os.Build.VERSION_CODES.R;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.WindowInsets;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;

import com.lhh.lnstagram.R;
import com.lhh.lnstagram.base.BaseApplication;
import com.lhh.lnstagram.mvvm.util.StatusBarUtils;

public class AutoRelativeLayout extends RelativeLayout {
    public AutoRelativeLayout(Context context) {
        super(context);
    }

    public AutoRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected boolean fitSystemWindows(Rect insets) {
        if (insets != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                insets.left = 0;
                insets.top = 0;
                insets.right = 0;
                super.fitSystemWindows(insets);
            }
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    @Override
    public WindowInsets onApplyWindowInsets(WindowInsets insets) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int fakeStatusBarHeight = BaseApplication.getInstance().getResources().getDimensionPixelSize(com.lhh.lnstagram.R.dimen.statusbar_view_height);
            int statusHeight = StatusBarUtils.getStatusBarHeight(BaseApplication.getInstance()) + fakeStatusBarHeight;
            if (insets != null) {
                int systemWindowInsetBottom = insets.getSystemWindowInsetBottom();
                systemWindowInsetBottom = systemWindowInsetBottom - statusHeight;
                if (systemWindowInsetBottom < 0) {
                    systemWindowInsetBottom = insets.getSystemWindowInsetBottom();
                }
                return super.onApplyWindowInsets(insets.replaceSystemWindowInsets(insets.getSystemWindowInsetLeft(), insets.getSystemWindowInsetTop(), insets.getSystemWindowInsetRight(), systemWindowInsetBottom));
            }
        }
        return insets;
    }
}
