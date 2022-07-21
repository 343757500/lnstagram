package com.lhh.lnstagram.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Selection;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lhh.lnstagram.R;


/**
 * 群匠的ItemLayout4Edit
 */
public class ItemLayout4Edit extends LinearLayout {

	public View view;
	public ImageView iconIv;
	public ImageView arrowIv;
	public ImageView clearIv;
	public TextView labelTv;
	public EditText valueTv;
	public View lineLL;
	public RelativeLayout mBaseItemLayout;

	public ItemLayout4Edit(Context context) {
		this(context, null);
	}

	public ItemLayout4Edit(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ItemLayout4Edit(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		LayoutInflater.from(context).inflate(R.layout.view_custom_itemlayout_edit, this, true);
		mBaseItemLayout = (RelativeLayout) this.findViewById(R.id.baseItemLayout);
		iconIv = (ImageView) this.findViewById(R.id.iconIv);
		labelTv = (TextView) this.findViewById(R.id.labelTv);
		valueTv = (EditText) this.findViewById(R.id.valueTv);
		arrowIv = (ImageView) this.findViewById(R.id.arrowIv);
		clearIv = (ImageView) this.findViewById(R.id.clearIv);
		lineLL = this.findViewById(R.id.lineLL);

		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ItemLayout);
		Drawable iconRes = a.getDrawable(R.styleable.ItemLayout_itemIcon);
		String labelStr = a.getString(R.styleable.ItemLayout_itemLabel);
		String valueStr = a.getString(R.styleable.ItemLayout_itemHint);
		boolean isIcon = a.getBoolean(R.styleable.ItemLayout_isIcon, false);
		boolean isLabel = a.getBoolean(R.styleable.ItemLayout_isLabel, true);
		boolean isLine = a.getBoolean(R.styleable.ItemLayout_isLine, true);
		boolean isArrow = a.getBoolean(R.styleable.ItemLayout_isArrow, false);
		a.recycle();

		// 显示或隐藏
		setIconVisibility(isIcon);
		setLabelVisibility(isLabel);
		setLineVisibility(isLine);
		setArrowVisibility(isArrow);

		// 初始化值
		setIcon(iconRes);
		setLabelText(labelStr);
		setHint(valueStr);

		allowClearClearShow();
	}




	public void setIconVisibility(boolean isIcon) {
		iconIv.setVisibility(isIcon ? View.VISIBLE : View.GONE);
	}
	public void setLabelVisibility(boolean isLabel) {
		labelTv.setVisibility(isLabel ? View.VISIBLE : View.GONE);
	}
	public void setLineVisibility(boolean isLine) {
		lineLL.setVisibility(isLine ? View.VISIBLE : View.GONE);
	}
	public void setArrowVisibility(boolean isArrow) {
		arrowIv.setVisibility(isArrow ? View.VISIBLE : View.GONE);
	}
	public void setIcon(Drawable iconRes) {
		iconIv.setImageDrawable(iconRes);
	}
	public void setLabelText(String labelStr) {
		labelTv.setText(labelStr);
	}
	public void setHint(String hintStr) {
		valueTv.setHint(hintStr);
	}
	public void setValue(String valueStr) {
		valueTv.setText(valueStr);
	}
	public void setRedTips() {
		valueTv.setHintTextColor(getResources().getColor(R.color.color_red));
	}
	public String getText() {
		return valueTv.getText().toString().trim();
	}

	/**
	 * 限制手机号码
	 */
	public void setPhoneModel() {
		setNumberModel(11);
	}

	/**
	 * 限制数字
	 * @param max
	 */
	public void setNumberModel(int max) {
		if (valueTv != null) {
			int inputType = InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL;
			valueTv.setRawInputType(inputType);
			valueTv.setKeyListener(DigitsKeyListener.getInstance("1234567890"));
		}
		setMaxLength(max);
	}

	/**
	 * 限制长度
	 * @param max
	 */
	public void setMaxLength(int max) {
		if (valueTv != null) {
			valueTv.setFilters(new InputFilter[]{new InputFilter.LengthFilter(max)});
		}
	}

	/**
	 * 限制小数点数字模式
	 */
	public void setDecimalNumberModel() {
		setNumberModel(10);
	}
	/**
	 * 限制小数点数字模式
	 */
	public void setDecimalNumberModel(int max) {
		if (valueTv != null) {
			int inputType = InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL;
			valueTv.setRawInputType(inputType);
			if (max > 0) {
				valueTv.setFilters(new InputFilter[]{new InputFilter.LengthFilter(max)});
			}
		}
	}


	public void setPasswordModel() {
		setPasswordModel(false);
	}
	public void setPasswordModel(boolean isShow) {
		if (valueTv != null) {
			if (isShow) {
				int inputType = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
				valueTv.setInputType(inputType);
				Selection.setSelection(valueTv.getText(), valueTv.getText().length());
//				valueTv.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
			} else {
				int inputType = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
				valueTv.setInputType(inputType);
				Selection.setSelection(valueTv.getText(), valueTv.getText().length());
//				valueTv.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
			}
		}
	}

	/**
	 * 禁止空格和换行
	 */
	private InputFilter spaceFilter = new InputFilter() {
		@Override
		public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
			if( source.equals(" ") || source.toString().contentEquals("\n") ) {
				return "";
			} else {
				return null;
			}
		}
	};

	boolean isAllowClearShow = false; // 没有焦点或选择模式或相似模式
	public void allowClearClearShow() {
		valueTv.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (valueTv.getText().toString().trim().length() > 0 && isAllowClearShow) {
					clearIv.setVisibility(View.VISIBLE);
				} else {
					clearIv.setVisibility(View.GONE);
				}
			}
		});
		valueTv.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				isAllowClearShow = hasFocus;
				clearIv.setVisibility(hasFocus ? View.VISIBLE : View.GONE);
			}
		});
		clearIv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setValue("");
			}
		});
	}

	public void setSelectModel() {
		isAllowClearShow = false;
		setArrowVisibility(true);
		clearIv.setVisibility(View.GONE);
		valueTv.setCursorVisible(false);//光标隐藏
		valueTv.setFocusable(false);//丢失焦点
		valueTv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				ItemLayout4Edit.this.callOnClick();
			}
		});
	}

	public void setShowModel() {
		isAllowClearShow = false;
		clearIv.setVisibility(View.GONE);
		valueTv.setTextColor(getResources().getColor(R.color.text_color_gray_light));
		valueTv.setEnabled(false);
		valueTv.setKeyListener(null);//重点
		valueTv.setCursorVisible(false);//光标隐藏
		valueTv.setFocusable(false);//丢失焦点
		valueTv.setOnClickListener(null);
	}

}
