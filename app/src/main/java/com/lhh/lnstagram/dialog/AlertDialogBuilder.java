package com.lhh.lnstagram.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lhh.lnstagram.R;
import com.lhh.lnstagram.mvvm.util.ShapeUtil;
import com.lhh.lnstagram.view.PriceEditText;


public class AlertDialogBuilder {
    private Context mContext;
    private CharSequence mTitle, mContent, mOk, mCancel, mCurrencyType, mTip, mMoney, mBgColor;
    private ImageView imageView;
    private int imgId;

    public OnItemClickerListener listener;
    private View.OnClickListener onClickListener;
    private TextView tvTitle, tvContent, cancel, ok, tvCurrencyType, tip, tvMoney, tvUrl;
    private LinearLayout ll_pay;
    private Dialog dialog;
    private boolean cancelable = true;
    private boolean canceledOnTouchOutside = true;

    public interface OnItemClickerListener {
        void onOkClick(AlertDialogBuilder dialog);

        void onCancelClick(AlertDialogBuilder dialog);
    }

    public AlertDialogBuilder(Context context) {
        mContext = context;
    }


    public AlertDialogBuilder setImageID(int imageID) {
        this.imgId = imageID;
        return this;
    }

    public AlertDialogBuilder setTitle(String title) {
        this.mTitle = title;
        return this;
    }

    public AlertDialogBuilder setCurrencyType(String currencyType) {
        this.mCurrencyType = currencyType;
        return this;
    }

    public AlertDialogBuilder setCurrencyTypeBgColor(int bgColor) {
        this.mBgColor = String.valueOf(bgColor);
        return this;
    }

    public AlertDialogBuilder setTip(String mTip) {
        this.mTip = mTip;
        return this;
    }

    public AlertDialogBuilder setMoney(String money) {
        this.mMoney = money;
        return this;
    }

    public AlertDialogBuilder setContent(CharSequence content) {
        this.mContent = content;
        return this;
    }

    public AlertDialogBuilder setOk(String okContent) {
        this.mOk = okContent;
        return this;
    }

    public AlertDialogBuilder setCancel(String cancelContent) {
        this.mCancel = cancelContent;
        return this;
    }

    public AlertDialogBuilder setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
        return this;
    }

    public AlertDialogBuilder setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        this.canceledOnTouchOutside = canceledOnTouchOutside;
        return this;
    }

    public TextView getTvContent() {
        return tvContent;
    }

    public TextView getTvCurrencyType() {
        return tvCurrencyType;
    }

    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public void show() {
        if (dialog != null) {
            dialog.show();
        }
    }

    public AlertDialogBuilder setOnItemClickListener(OnItemClickerListener lisClicker) {
        this.listener = lisClicker;
        return this;
    }

    public AlertDialogBuilder setContentOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }





    public void setPayError(String payError, String desc) {
        tvTitle.setText(payError);
        tvContent.setText(desc);
        tvTitle.setVisibility(View.VISIBLE);
        tvContent.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.VISIBLE);
    }

    /**
     * 设置评分列表显示
     */
    public RecyclerView setRatingListView() {
        tvTitle.setVisibility(View.VISIBLE);
        tvContent.setVisibility(View.VISIBLE);
        RecyclerView rv_list = dialog.findViewById(R.id.rv_list);
        rv_list.setVisibility(View.VISIBLE);
        return rv_list;
    }





    public Dialog create() {
        dialog = new Dialog(mContext, R.style.DefaultDialog);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_alert);

        Window window = dialog.getWindow();

        Display display = window.getWindowManager().getDefaultDisplay();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (int) (display.getWidth() * 0.8);
        lp.gravity = Gravity.CENTER;
        window.setAttributes(lp);

        imageView = dialog.findViewById(R.id.iv_img);
        ll_pay = dialog.findViewById(R.id.ll_pay);
        tvTitle = dialog.findViewById(R.id.tv_title);
        tvContent = dialog.findViewById(R.id.tv_content);
        cancel = dialog.findViewById(R.id.tv_cancle);
        tvCurrencyType = dialog.findViewById(R.id.tv_currency_type);
        tip = dialog.findViewById(R.id.tv_pay_sus_tip);
        tvUrl = dialog.findViewById(R.id.tv_url);
        ok = dialog.findViewById(R.id.tv_ok);
        dialog.setCancelable(cancelable);
        dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);

        tvContent.setMovementMethod(new LinkMovementMethod());
        if (onClickListener != null) {
            tvContent.setOnClickListener(onClickListener);
        }

        View.OnClickListener clickListener = new View.OnClickListener() {
            public void onClick(View v) {
                if (listener != null) {
                    if (v == cancel) {
                        listener.onCancelClick(AlertDialogBuilder.this);
                    } else if (v == ok) {
                        listener.onOkClick(AlertDialogBuilder.this);
                    }
                }
            }
        };

        cancel.setOnClickListener(clickListener);
        ok.setOnClickListener(clickListener);
        if (imgId > 0) {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(imgId);
        }
        if (!TextUtils.isEmpty(mTitle)) {
            tvTitle.setText(mTitle);
            tvTitle.setVisibility(View.VISIBLE);
        }
        if (!TextUtils.isEmpty(mContent)) {
            tvContent.setText(mContent);
            tvContent.setVisibility(View.VISIBLE);
        }
        if (mOk != null) {
            ok.setText(mOk);
        }
        if (!TextUtils.isEmpty(mCancel)) {
            cancel.setText(mCancel);
            cancel.setVisibility(View.VISIBLE);
        }
        if (!TextUtils.isEmpty(mCurrencyType)) {
            tvCurrencyType.setText(mCurrencyType);
            ll_pay.setVisibility(View.VISIBLE);
        }

        if (!TextUtils.isEmpty(mBgColor)) {
            tvCurrencyType.setBackground(ShapeUtil.getCornerShape(mContext, mBgColor.toString(), 10));
        }

        if (!TextUtils.isEmpty(mMoney)) {
            tvMoney.setText(mMoney);
        }

        if (!TextUtils.isEmpty(mTip)) {
            tip.setText(mTip);
        }
        return dialog;

    }

    public void setUrlTips(String url, View.OnClickListener onClickListener) {
        tvContent.setGravity(Gravity.CENTER);
        tvUrl.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tvUrl.getPaint().setAntiAlias(true);//抗锯齿
        tvUrl.setOnClickListener(onClickListener);
        tvUrl.setVisibility(View.VISIBLE);
        tvUrl.setText(url);
    }
}
