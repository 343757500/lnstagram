package guide.util;

import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;

import androidx.annotation.StringRes;



import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import skinlibrary.SkinConfig;
import skinlibrary.loader.SkinManager;


/**
 * Created by XieWenJun on 2018/11/28.
 */
public class StringUtil {

    //敏感词过滤
    private static String sensitiveRegex;
    private static Map<String, String> sensitiveMap = new HashMap<>();

    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }

    public static boolean isEmpty(String string) {
        if (TextUtils.isEmpty(string) || "null".equalsIgnoreCase(string)) {
            return true;
        }
        return false;
    }

    public static String getStringNoSkin(@StringRes int id) {
        return Utils.getContext().getResources().getString(id);
    }

    /**
     * 全局获取String的方法
     *
     * @param id 资源Id
     * @return String
     */
    public static String getString(@StringRes int id) {
        if (SkinConfig.isCanChangeLanguage()) {
            return SkinManager.getInstance().getString(id);
        }
        return Utils.getContext().getResources().getString(id);
    }

    /**
     * 全局获取String的方法
     *
     * @param id 资源Id
     * @return String
     */
    public static String getString(@StringRes int id, Object... formatArgs) {
        if (SkinConfig.isCanChangeLanguage()) {
            return SkinManager.getInstance().getString(id, formatArgs);
        }
        return Utils.getContext().getResources().getString(id, formatArgs);
    }

    // tcp调用多点
    public static String toHexString(long i) {
        try {
            return Long.toHexString(i);
        } catch (Exception e) {
            return i + "";
        }
    }



    public static boolean isLetterDigitOrChinese(String str) {
        //        String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";//其他需要，直接修改正则表达式就好
        String regex = "^[a-zA-Z\u4e00-\u9fa5\u2764\u0040\u2605\u2665]+$";//其他需要，直接修改正则表达式就好
        return str.matches(regex);
    }


    public static String showStrLastChar(String str) {
        if (str.length() < 5) {
            return "**** " + str;
        }
        return str.replaceAll("(\\d+)(\\d{4}$)", "**** $2");
    }

    public static String showStrLastCharTwo(String str) {
        if (str.length() < 5) {
            return "**** " + str;
        }
        return "**** " + str.substring(str.length() - 4);
    }

    public static String hideCenterChar(String str) {
        if (TextUtils.isEmpty(str) || str.length() < 7) {
            return str;
        }
        return str.replaceAll("(^\\d{3})(\\d+)(\\d{3}$)", "$1***$3");
    }

    public static String formatStrSpace(String str) {
        return str.replaceAll("(.{4})", "$1\t\t");
    }

    public static Spanned getHtmlText(String text, String key) {
        if (TextUtils.isEmpty(text)) {
            return Html.fromHtml("");
        }
        if (TextUtils.isEmpty(key)) {
            return Html.fromHtml(text);
        }
        try {
            return findSearch(text, key);
        } catch (Exception e) {
            return new SpannableString(text);
        }
    }

    /**
     * 关键字高亮变色
     *
     * @param text    文字
     * @param keyword 文字中的关键字
     * @return
     */
    public static SpannableString findSearch(String text, String keyword) {
        SpannableString s = new SpannableString(text);
        Matcher m = Pattern.compile(keyword.toLowerCase()).matcher(new SpannableString(text.toLowerCase()));
        while (m.find()) {
            int start = m.start();
            int end = m.end();
            s.setSpan(new ForegroundColorSpan(Color.parseColor("#15af9f")), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return s;
    }

    public static void copy(String text) {
        ClipboardManager clip = (ClipboardManager) Utils.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        clip.setText(text); // 复制
    }

    public static String getLowerString(String original) {
        try {
            return original.toLowerCase();
        } catch (Exception e) {
            return "";
        }
    }

    private static boolean isMath4Lower(String original, String key) {

        if (TextUtils.isEmpty(key)) {
            return false;
        }
        try {
            key = key.replaceAll(" ", "");
            original = original.replaceAll(" ", "");
//            return Pattern.compile("(?i/\\)" + key).matcher(original).find();
            return original.toLowerCase().contains(key.toLowerCase());
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isMath(String key, String... originals) {
        if (TextUtils.isEmpty(key)) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        for (String item : originals) {
            if (!TextUtils.isEmpty(item)) {
                sb.append(item).append(" ");
            }
        }
        return isMath4Lower(sb.toString(), key);
    }












    public static String getTimeFormat(int time) {
        //        time=time/1000;

       /* Date date = new Date(time*1000);//调用Date方法获值
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");//规定需要形式
        String TotalTime = simpleDateFormat.format(date);//转化为需要形式
        return TotalTime;*/

        int min = time / 60;
        int sec = time % 60;
        StringBuilder builder = new StringBuilder();
        if (min < 10) {
            builder.append("0");
        }
        builder.append(min);
        builder.append(":");
        if (sec < 10) {
            builder.append("0");
        }
        builder.append(sec);
        return builder.toString();
    }

    public static String replaceFirst0(String key) {
        if (TextUtils.isEmpty(key) || key.matches("^0*")) {
            return key;
        }
        // 以下2个方法都可以
        // return key.replaceFirst("^0*", "");
        return key.replaceAll("^(0+)", "");
    }

    public static String replaceMobileNumber(String target) {
        if (null == target) {
            return "";
        }
        return target.replaceAll("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……& amp;*（）——+|{}【】‘；：”“’。，、？|-]", "");
    }

    /**
     * 计数用K表示
     *
     * @param total
     * @return
     */
    public static String formatNum2K(int total) {
//        try {
//        String t = String.valueOf(total);
//            String result;
//            if (t.length() >= 4) {
//                if (t.length() >= 7) {
//                    result = t.substring(0, t.length() - 6);
//                    String decimal = t.substring(t.length() - 5, t.length() - 4);
//                    result = result + "." + t.substring(t.length() - 6, t.length() - 5) + "M";
//                } else {
//                    result = t.substring(0, t.length() - 3);
//                    result = result + "." + t.substring(t.length() - 3, t.length() - 2) + "K";
//                }
//            } else {
//                return t;
//            }
//            return result;
//        } catch (Exception e) {
//            return String.valueOf(total);
//        }
        String str;
        try {
            if (total <= 0) {
                str = "0";
            } else if (total < 1000) {
                str = total + "";
            } else {
                if (total >= 1000000) {
                    double num = (double) total / 1000000;//将数字转换成以百万为单位的数字
                    BigDecimal b = new BigDecimal(num);
                    double f1 = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();//2.转换后的数字四舍五入保留小数点后一位;
                    str = f1 + "M";
                } else {
                    double num = (double) total / 1000;//将数字转换成以千为单位的数字
                    BigDecimal b = new BigDecimal(num);
                    double f1 = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();//2.转换后的数字四舍五入保留小数点后一位;
                    str = f1 + "K";
                }
            }
        } catch (Exception e) {
            return String.valueOf(total);
        }
        return str;
    }

    public static String formatFloat(String f) {
        try {
            DecimalFormat nf = new DecimalFormat("0.00");
            return nf.format(Double.valueOf(f));
        } catch (Exception e) {
            return "0.00";
        }
    }

    public static String formatFloatDown(float f) {
        BigDecimal b = new BigDecimal(String.valueOf(f));
        String fStr = String.valueOf(b.setScale(2, BigDecimal.ROUND_DOWN).floatValue());
        return formatFloat(fStr);
    }

    public static String formatFloatHalfUp(float f) {
        BigDecimal b = new BigDecimal(String.valueOf(f));
        String fStr = String.valueOf(b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
        return formatFloat(fStr);
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
    public static double formatDivision(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 毫秒转秒保留一位小数
     */
    public static String formatMillisecondToSecond(double millisecond) {
        return formatDivision(millisecond, 1000, 1) + "s";
    }

    /**
     * 将每三个数字（或字符）加上逗号处理（通常使用金额方面的编辑）
     * 5000000.00 --> 5,000,000.00
     * 20000000 --> 20,000,000
     *
     * @param num 无逗号的数字
     * @return 加上逗号的数字
     */
    public static String strAddComma(int num) {
        String str = Integer.toString(num);
        if (str == null) {
            str = "";
        }
        String addCommaStr = ""; // 需要添加逗号的字符串（整数）
        String tmpCommaStr = ""; // 小数，等逗号添加完后，最后在末尾补上
        if (str.contains(".")) {
            addCommaStr = str.substring(0, str.indexOf("."));
            tmpCommaStr = str.substring(str.indexOf("."), str.length());
        } else {
            addCommaStr = str;
        }
        // 将传进数字反转
        String reverseStr = new StringBuilder(addCommaStr).reverse().toString();
        StringBuilder strTemp = new StringBuilder();
        for (int i = 0; i < reverseStr.length(); i++) {
            if (i * 3 + 3 > reverseStr.length()) {
                strTemp.append(reverseStr.substring(i * 3, reverseStr.length()));
                break;
            }
            strTemp.append(reverseStr.substring(i * 3, i * 3 + 3)).append(",");
        }
        // 将 "5,000,000," 中最后一个","去除
        if (strTemp.toString().endsWith(",")) {
            strTemp = new StringBuilder(strTemp.substring(0, strTemp.length() - 1));
        }
        // 将数字重新反转,并将小数拼接到末尾
        return new StringBuilder(strTemp.toString()).reverse().toString() + tmpCommaStr;
    }

    /**
     * 将加上逗号处理的数字（字符）的逗号去掉 （通常使用金额方面的编辑）
     * 5,000,000.00 --> 5000000.00
     * 20,000,000 --> 20000000
     *
     * @param str 加上逗号的数字（字符）
     * @return 无逗号的数字（字符）
     */
    public static String strRemoveComma(String str) {
        if (str == null) {
            str = "";
        }
        return str.replaceAll(",", "");
    }





    /**
     * 添加引号 兼容文件名包含空格
     */
    public static String getFFmpegPath(String str) {
        if (str == null) {
            str = "";
        }
        return "\"" + str + "\"";
    }

}
