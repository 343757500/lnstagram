package com.lhh.lnstagram.db;

import android.content.Context;


import com.lhh.lnstagram.mvvm.base.BaseConfig;
import com.lhh.lnstagram.sp.CommonSP;
import com.lhh.lnstagram.sp.CookiesSP;


import net.sqlcipher.database.SQLiteDatabase;


import org.litepal.LitePal;
import org.litepal.LitePalDB;

import java.io.File;



/**
 * Created by XieWenJun on 2018/12/25.
 * 版本55之后
 */
public class DBHelper {


    public static String getDBName4UserId(String userId) {
        String prefix = "U";
        switch (BaseConfig.ENV_TYPE) {
            case 1:
                prefix = "Dev";
                break;
            case 2:
                prefix = "Test";
                break;
            case 3:
                prefix = "U";
                break;
            case 4:
                prefix = "Beta";
                break;
        }
        return prefix + userId;
    }

    // 初始化 (App启动的时候)
    public static void initDefaultDB(Context context) {
        //LogUtil.d("DB", "initDefaultDB");
        // 在APP中进行加载加密的数据库所需要的so库文件
        SQLiteDatabase.loadLibs(context);
        // 初始化LitePal
        LitePal.initialize(context);

        // 如果已经登录，切换到个人数据库
        if (CookiesSP.isLogin()) {
            switchDb(CookiesSP.getCookies().getUserId());
        }

    }

    // 开启连接 （登录成功后，备份还原后）
    public static void switchDb(String userId) {
      //  LogUtil.d("DB", "switchDb=" + getDBName4UserId(userId));

        // 个人数据库
        LitePalDB litePalDB = LitePalDB.fromDefault(getDBName4UserId(userId));
        LitePal.use(litePalDB);


        // start of 升级逻辑
        int lastLoginVersion = CommonSP.getInstance().getLastLogin4AppBuildVersion(userId);
        if (lastLoginVersion > 0 && lastLoginVersion < 19101601) {
            // 19101601 升级了上传通讯录（旧版本的用户，默认同意上传通讯录）
            CommonSP.getInstance().setUploadMobileContactPermissionType(1);
        }

        // 更新最后登录版本号
        CommonSP.getInstance().setLastLogin4AppBuildVersion(userId);
        // end


//        // Bugly添加用户信息
//        try {
//            CrashReport.setUserId(BaseApplication.getInstance(),
//                    userId +
//                            "#" + CookiesSP.getCookies().getMobileCode() +
//                            "#" + CookiesSP.getCookies().getNickname() +
//                            "(" + BaseConfig.ENV_TYPE + ")");
//            if (BaseConfig.ENV_TYPE != 3) {
//                CrashReport.setUserId(BaseApplication.getInstance(),
//                        BaseConfig.VERSION_CODE + "#" + BaseConfig.ENV_TYPE + "#" + userId);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    // 关闭连接（备份还原前）
    public static void closeDb() {
        if (LitePal.getDatabase().isOpen()) {
            LitePal.getDatabase().close();
        }
    }

    public static long getDBSize(Context mContext) {
        long folderSize;
        String dbName4UserId = getDBName4UserId(CookiesSP.getCookies().getUserId());
        String path = mContext.getDatabasePath(dbName4UserId).getPath() + ".db";
        File file = new File(path);
        folderSize = file.length();
        return folderSize;
    }

}
