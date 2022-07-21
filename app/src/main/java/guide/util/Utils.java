package guide.util;


import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.lhh.lnstagram.base.BaseApplication;


import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Utils初始化相关 </p>
 */
public class Utils {

    private static Context context;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        Utils.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context == null) {
            context = BaseApplication.getInstance();
        }
        return context;
    }

    /**
     * 判断App是否是Debug版本
     */
    public static boolean isAppDebug() {
        if (TextUtils.isEmpty(context.getPackageName())) {
            return false;
        }
        try {
            PackageManager pm = context.getPackageManager();
            ApplicationInfo ai = pm.getApplicationInfo(context.getPackageName(), 0);
            return ai != null && (ai.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static <T> T checkNotNull(T obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        return obj;
    }

    public static boolean isVideo(String path) {
        try {
            path = path.toUpperCase();
            if (path.contains(".MP4") || path.contains(".AVI") || path.contains(".MOV") || path.contains(".RMVB") || path.contains(".3GP"))
                return true;
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isPhoto(String path) {
        try {
            path = path.toUpperCase();
            if (path.contains(".JPG") || path.contains(".PNG") || path.contains(".JPEG") || path.contains(".BMP"))
                return true;
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static int getVideoDuration(String path) {
        try {
            MediaMetadataRetriever media = new MediaMetadataRetriever();
            media.setDataSource(path);
            String duration = media.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION); //
            return Integer.parseInt(duration);
        } catch (Exception e) {
        }
        return 0;
    }



    /**
     * 根据视频宽高自动裁剪缩略图为16:9或3:4获取Bitmap
     */
    public static void loadBitmapTarget(String url, CustomTarget<Bitmap> customTarget, int screenWide, int screenHigh) {
        int height;
        if (screenWide <= screenHigh) {
            height = screenWide * 4 / 3;
        } else {
            height = screenWide * 9 / 16;
        }
        RequestOptions requestOptions = new RequestOptions()
                .override(screenWide, height);
        Glide.with(BaseApplication.getInstance())
                .asBitmap()
                .centerCrop()
                .apply(requestOptions)
                .load(url)
                .into(customTarget);
    }


    public static String num2thousand00(String num) {
        String numStr = "";
        if (TextUtils.isEmpty(num)) {
            return numStr;
        }
        NumberFormat nf = NumberFormat.getInstance();
        try {
            DecimalFormat df = new DecimalFormat("#,##0.00");
            numStr = df.format(nf.parse(num));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return numStr;
    }


    /**
     * 获取一个随机的UUID
     *
     * @return
     */
    public static String getRandomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getImageFileType(String path) {
        try {
            path = path.toUpperCase();
//            if (path.contains(".JPG") || path.contains(".PNG") || path.contains(".JPEG") || path.contains(".BMP"))
            if (path.contains(".JPG") || path.contains(".JPEG")) {
                return "jpeg";
            } else if (path.contains(".PNG")) {
                return "png";
            } else if (path.contains(".GIF")) {
                return "gif";
            }
        } catch (Exception e) {
            return "jpeg";
        }
        return "jpeg";
    }

    public static boolean isHttpUrl(String urls) {
        String regex = "(((https|http)?://)?([a-z0-9]+[.])|(www.))"
                + "\\w+[.|\\/]([a-z0-9]{0,})?[[.]([a-z0-9]{0,})]+((/[\\S&&[^,;\u4E00-\u9FA5]]+)+)?([.][a-z0-9]{0,}+|/?)";//设置正则表达式
        Pattern pat = Pattern.compile(regex.trim());//对比
        Matcher mat = pat.matcher(urls.trim());
        return mat.matches();//判断是否匹配
    }

    private static boolean urlFlags = false;



    /**
     * 判断当前应用是否运行在后台
     *
     * @return
     */
    public static boolean isApplicationInBackground() {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskList = am.getRunningTasks(1);
        if (taskList != null && !taskList.isEmpty()) {
            ComponentName topActivity = taskList.get(0).topActivity;
            return topActivity != null && !topActivity.getPackageName().equals(context.getPackageName());
        }
        return false;
    }
}