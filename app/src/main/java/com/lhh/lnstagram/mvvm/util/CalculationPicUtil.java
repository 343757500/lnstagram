package com.lhh.lnstagram.mvvm.util;

import com.lhh.lnstagram.bean.PostArticleInfoBean;

import java.math.BigDecimal;
import java.util.List;

/**
 * author LiangDangWei
 * date 2021-04-20 10:56
 * description
 */
public class CalculationPicUtil {


    public static int picCount(List<PostArticleInfoBean> articleInfo, int previewWidth, int previewHeight) {
        int picHeight = previewHeight;
        if (articleInfo != null && articleInfo.size() > 0) {
            if (articleInfo.size() == 1) {
                int width = articleInfo.get(0).getWidth();
                int height = articleInfo.get(0).getHeight();
                if (width != 0 && height != 0) {
                    double div = div(width, height, 1);
                    if (div >= 0.8) { // 当宽高比例大于4:5时  显示挨图
                        if (div(width, height, 2) > 1.78) {   //  宽高比例 16/9
                            picHeight = previewWidth * 9 / 16;
                        } else {
                            if (height * previewWidth / width < previewHeight) {  // 当高度小于最大高度时候      才用最小   。大于最大高度 还是默认最大高度
                                picHeight = height * previewWidth / width; // 按照屏幕比例给高度
                            }
                        }
                    }
                }
            } else { // 多张图    //只要有一个图片比例  小于4/5就显示   大图（4/5）
                for (PostArticleInfoBean postInfoBean : articleInfo) {
                    if (postInfoBean.getWidth() != 0 && postInfoBean.getHeight() != 0) {
                        if (div(postInfoBean.getWidth(), postInfoBean.getHeight(), 1) < 0.8) {
                            return Math.max(picHeight, previewWidth * 9 / 16);
                        }
                    }
                }

                int dwarfHeight = 0;
                //所有图片 都大于4/5    就显示爱徒   （显示图片中的最大高度     同时不能超过屏幕宽度的 5/4）
                for (PostArticleInfoBean postInfoBean : articleInfo) {  // 遍历循环找出最大值
                    if (postInfoBean.getWidth() != 0 && postInfoBean.getHeight() != 0) {
                        if (div(postInfoBean.getWidth(), postInfoBean.getHeight(), 1) >= 0.8) {
                            if (postInfoBean.getHeight() * previewWidth / postInfoBean.getWidth() > dwarfHeight) {
                                dwarfHeight = Math.max(postInfoBean.getHeight() * previewWidth / postInfoBean.getWidth(), previewWidth * 9 / 16);
                            }
                        }
                    }
                }
                if (dwarfHeight >= previewHeight) { // 如果遍历后的最大高度  过大        返回默认比例高度
                    return picHeight;
                } else {
                    return dwarfHeight == 0 ? previewHeight : dwarfHeight;
                }
            }
        }
        return picHeight;
    }

    public static int getImageHeight(int previewWidth, int width, int height) {
        double div = div(width, height, 1);
        return (int) (previewWidth / div);
    }

    public static int picForwardCount(List<PostArticleInfoBean> articleInfo, int previewWidth, int previewHeight) {
        int picHeight = previewHeight;
        if (articleInfo != null && articleInfo.size() > 0) {
            int width = articleInfo.get(0).getWidth();
            int height = articleInfo.get(0).getHeight();
            if (width != 0 && height != 0) {
                double div = div(width, height, 1);
                if (div >= 0.8) { // 当宽高比例大于4:5时  显示挨图
                    if (div(width, height, 2) > 1.78) {   //  宽高比例 16/9
                        picHeight = previewWidth * 9 / 16;
                    } else {
                        if (height < previewHeight) {  // 当高度小于最大高度时候      才用最小   。大于最大高度 还是默认最大高度
                            picHeight = height * previewWidth / width; // 按照屏幕比例给高度
                        }
                    }
                }
            }
        }
        return picHeight;
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
