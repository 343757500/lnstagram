package com.lhh.lnstagram.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.util.TypedValue;

import androidx.appcompat.widget.AppCompatEditText;

import com.lhh.lnstagram.mvvm.util.ViewUtil;

import java.text.DecimalFormat;

/**
 * @author sunling
 * @date 2019/3/28
 */
public class PriceEditText extends AppCompatEditText {
    private DecimalFormat nf = new DecimalFormat("#,###.##");
    private final int DEFAULT_LENGTH = 8;
    private String lastStr = "";
    private float oriSize;
    private int inputIndex;
    private int intMax = DEFAULT_LENGTH;

    public PriceEditText(Context context) {
        super(context);
        initView();
    }

    public PriceEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PriceEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    public void setOriSize(float oriSize) {
        this.oriSize = ViewUtil.px2dp(oriSize);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, this.oriSize);
    }

    /**
     * 设置整数限制多少个
     *
     * @param intMax
     */
    public void setIntMax(int intMax) {
        if (intMax <= 0) {
            intMax = DEFAULT_LENGTH;
        } else {
            this.intMax = intMax;
        }
    }

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            inputIndex = start;
        }

        @Override
        public void afterTextChanged(Editable edt) {
            if (isEnabled()) {//可以编辑
                String temp = edt.toString();
                int posDot = temp.indexOf(".");
                if (!isCanInsert(edt)) {
                    updateText();
                    return;
                }
                if (posDot >= 0) {
                    if (posDot == 0) { // 不能以 点开始
                        edt.delete(0, 1);
                        return;
                    }
                    int fractionalPartCount = temp.length() - posDot - 1; //计算当前有多少位小数
                    //只能有一个小数点
                    if (fractionalPartCount >= 1) {
                        String lastContent = temp.substring(posDot + 1);
                        if (lastContent.contains(".")) {
                            updateText();
                            return;
                        }
                    }
                    if (fractionalPartCount > 2) {// 只能两们小数
                        edt.delete(posDot + 3, posDot + 4);
                        return;
                    }
                } else {
                    if (temp.length() >= 2 && temp.startsWith("0")) {
                        edt.delete(0, 1);
                        return;
                    }
                }
                //新增限制整数位个数的逻辑
                int index = temp.indexOf(".");
                if (index != -1) {//有小数点
                    if (index > intMax) {
                        edt.delete(intMax, index);
                        return;
                    }
                } else {
                    if (temp.length() > intMax) {
                        edt.delete(intMax, temp.length());
                        return;
                    }
                }
                lastStr = edt.toString();
                updateText();
            } else {//不可以编辑
                try {
                    double num = Double.parseDouble(edt.toString().trim());
                    lastStr = nf.format(num);
                    int posDot = lastStr.indexOf(".");
                    if (posDot <= 0) {
                        lastStr = lastStr + ".00";
                    } else if (posDot == lastStr.length() - 2) {
                        lastStr = lastStr + "0";
                    }
                    updateText();
                } catch (Exception ignore) {
                }
            }
        }
    };

    /**
     * 更新文本
     */
    private void updateText() {
        removeTextChangedListener(watcher);
        setText(lastStr);
        setSelection(lastStr.length());
        addTextChangedListener(watcher);
    }


    private void initView() {
        oriSize = ViewUtil.px2dp(getTextSize());
        if (isEnabled()) {
            setKeyListener(DigitsKeyListener.getInstance("0123456789."));
        } else {
            setKeyListener(DigitsKeyListener.getInstance("0123456789.,"));
        }
        addTextChangedListener(watcher);
    }

    private boolean isCanInsert(Editable edt) {
        boolean isCanInsert = true;
        String priceText = edt.toString();
        //最多输入这么多字符
        if (lastStr.length() == intMax + 3 && !TextUtils.isEmpty(priceText) && priceText.length() == intMax + 3) {//小数点+2个小数
            isCanInsert = false;
        }
        //如果原来已经有小数点，如果再加小数点则删除
        if (lastStr.contains(".")) {
            if (!TextUtils.isEmpty(priceText)) {
                int indexOf = priceText.indexOf(".");
                int lastIndexOf = priceText.lastIndexOf(".");
                if (indexOf != lastIndexOf) {
                    isCanInsert = false;
                }
            }
        } else {
            if (lastStr.length() == intMax) {//最大整数的
                if (!TextUtils.isEmpty(priceText) && priceText.contains(".")) {//添加小数
                    isCanInsert = true;
                } else if (!TextUtils.isEmpty(priceText) && priceText.length() < lastStr.length()) {//删除操作
                    isCanInsert = true;
                } else {
                    isCanInsert = false;
                }
            }
        }

        return isCanInsert;
    }

    private void resetSize(int count) {
        if (count > 10) {
            setTextSize(oriSize / 3 * 2);
        } else {
            setTextSize(oriSize);
        }
    }

    public String getPriceText() {
        return getText().toString().trim().replace(",", "").replace(" ", "");
    }


}
