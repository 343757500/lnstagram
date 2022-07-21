package com.lhh.lnstagram.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lhh.lnstagram.R;

import java.util.ArrayList;

import guide.util.StringUtil;

/**
 * 1.数据加载框
 * 2.确定弹出框
 * 3.底部弹出框
 * 4.选项弹出框
 */
public class DialogUtil {

    private static final String TAG = DialogUtil.class.getSimpleName();

    /**
     * 加载框
     */
    private static String progressContextStr;
    private static KProgressHUD progressHUD;

    public static KProgressHUD showProgressHUD(Context context, String message) {
        try {
            if (!context.toString().equals(progressContextStr)) {
                closeProgressDialog();
            }
            if (progressHUD == null) {
                progressContextStr = context.toString();
                progressHUD = KProgressHUD.create(context)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setAnimationSpeed(1)
                        .setLabel(message);
                progressHUD.show();
            } else {
                progressHUD.setLabel(message);
                if (!progressHUD.isShowing()) {
                    progressHUD.show();
                }
            }
            return progressHUD;
        } catch (Exception e) {
            closeProgressDialog();
            return null;
        }
    }

    /**
     * 关闭加载框
     */
    public static void closeProgressDialog() {
        try {
            if (progressHUD != null) {
                progressHUD.dismiss();
                progressHUD = null;
            }
        } catch (Exception e) {
            progressContextStr = "";
            progressHUD = null;
        }
    }


    /**
     * 确定弹出框接口
     */
    public interface OnConfirmClickListener {
        void onOkClick();

        void onCancelClick();
    }

    /**
     * 提示框
     *
     * @param content 内容
     */
    public static Dialog showAlertDialog(Context context, String content) {
        return showDialog(context, 0, "", content, StringUtil.getString(R.string.common_ctrl_confirm), "", null);
    }

    /**
     * 提示框
     *
     * @param content                内容
     * @param onConfirmClickListener 需要实现接口{@link OnConfirmClickListener}
     */
    public static Dialog showAlertDialog(Context context, String content, OnConfirmClickListener onConfirmClickListener) {
        return showDialog(context, 0, "", content, StringUtil.getString(R.string.common_ctrl_confirm), "", onConfirmClickListener);
    }

    /**
     * 二次确认框
     *
     * @param content                内容
     * @param onConfirmClickListener 需要实现接口{@link OnConfirmClickListener}
     */
    public static Dialog showConfirmDialog(Context context, String content, OnConfirmClickListener onConfirmClickListener) {
        return showDialog(context, 0, "", content, StringUtil.getString(R.string.common_ctrl_confirm), StringUtil.getString(R.string.common_ctrl_cancel), onConfirmClickListener);
    }

    /**
     * 提示框和二次确定框的父类（完整方法,私有）
     *
     * @param context                      上下文
     * @param title                        标题
     * @param content                      内容
     * @param cancelStr                    取消按钮文字
     * @param okStr                        确定按钮文字
     * @param onConfirmDialogClickListener 需要实现接口{@link OnConfirmClickListener}
     * @author XieWenjun
     */
    public static Dialog showDialog(final Context context, int iconResId, String title, String content,
                                    String okStr, String cancelStr,
                                    final OnConfirmClickListener onConfirmDialogClickListener) {
        Dialog dialog = getDialog(context, iconResId, title.replace("\\n", "\n"), content.replace("\\n", "\n"), okStr, cancelStr, onConfirmDialogClickListener);
        try {
            dialog.show();
        } catch (Exception e) {
           // LogUtil.e("");
        }
        return dialog;
    }

    public static Dialog getDialog(final Context context, int iconResId, String title, String content,
                            String okStr, String cancelStr,
                            final OnConfirmClickListener onConfirmDialogClickListener) {
        closeProgressDialog();

        AlertDialogBuilder builder = new AlertDialogBuilder(context);
        builder.setImageID(iconResId > 0 ? iconResId : 0);
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        if (!TextUtils.isEmpty(content)) {
            builder.setContent(content);
        }
        if (!TextUtils.isEmpty(cancelStr)) {
            builder.setCancel(cancelStr);
        }
        if (!TextUtils.isEmpty(okStr)) {
            builder.setOk(okStr);
        }
        builder.setOnItemClickListener(new AlertDialogBuilder.OnItemClickerListener() {
            @Override
            public void onOkClick(AlertDialogBuilder dialog) {
                if (onConfirmDialogClickListener != null) {
                    onConfirmDialogClickListener.onOkClick();
                }
                dialog.dismiss();
            }

            @Override
            public void onCancelClick(AlertDialogBuilder dialog) {
                if (onConfirmDialogClickListener != null) {
                    onConfirmDialogClickListener.onCancelClick();
                }
                dialog.dismiss();
            }
        });
        return builder.create();
    }



    /**
     * 共用的底部弹出框（可用于滑轮选择和特别的Toast）
     *
     * @param context
     * @param view    布局（逻辑处理）
     * @return dialog 弹出框对象（逻辑处理之后关闭弹出框）
     * @author XieWenjun
     */
    public static Dialog showBottomDialog(final Activity context, View view) {
        final Dialog dialog = new Dialog(context, R.style.ConfirmDialogStyle);
        dialog.setContentView(view);
        setDialogParams(dialog, context);
        dialog.setCanceledOnTouchOutside(true);
        // 逻辑处理通过View和返回的Dialog对象
        try {
            dialog.show();
        } catch (Exception e) {

        }
        return dialog;
    }

    /**
     * 底部设置参数
     */
    private static void setDialogParams(Dialog dialog, Context activityContext) {
        // 获取数据
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) activityContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // 获取屏幕数据
        Rect rect = new Rect();
        View view = ((Activity) activityContext).getWindow().getDecorView();
        view.getWindowVisibleDisplayFrame(rect);
        // 设置Dialog布局
        ViewGroup.LayoutParams layoutParams = dialog.getWindow().getAttributes();
//        layoutParams.height = displayMetrics.heightPixels;
        layoutParams.width = displayMetrics.widthPixels;
    }


    /**
     * 确定弹出框接口
     */
    public interface OnActionDialogListener {
        void onSelect(String[] items, int index);
    }

    public interface OnActionAllDialogListener extends OnActionDialogListener {
        void onCancel();
    }

    /**
     * 提示框
     *
     * @param content                内容
     * @param onConfirmClickListener 需要实现接口{@link OnConfirmClickListener}
     */
    public static Dialog showAlertDialog(Context context, String content,String sureText, OnConfirmClickListener onConfirmClickListener) {
        return showDialog(context, 0, "", content,sureText, "", onConfirmClickListener);
    }
}