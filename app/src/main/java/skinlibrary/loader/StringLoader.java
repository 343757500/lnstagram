package skinlibrary.loader;


import com.lhh.lnstagram.manager.BaseHandlerManager;
import com.lhh.lnstagram.mvvm.util.FileUtil;
import com.lhh.lnstagram.mvvm.util.LanguageUtil;
import com.lhh.lnstagram.sp.LanguageSP;

import org.json.JSONObject;

import java.io.File;

public class StringLoader {

    /**
     * 获取文案
     * @param key eg: common_txt_welcome
     */
    public static String getString(String key) {
        if (getJsonObject() == null) {
            return null;
        }
        return getJsonObject().optString(key);
    }

    /**
     * 清空缓存，切换语言的时候
     */
    public static void clear() {
        jsonObject = null;
        getJsonObject();
    }

    private static JSONObject jsonObject;
    public static JSONObject getJsonObject() {
        if (jsonObject == null) {
            synchronized (BaseHandlerManager.class) {
                if (jsonObject == null) {
                    try {
                        String languageCode = LanguageSP.getInstance().getCurrentLanguageCode();
                        String fileName = LanguageUtil.getLanguageFileName(languageCode);
                        File file = new File(FileUtil.getCacheFile(), fileName);
                        if (file.exists()) {
                            String jsonStr = FileUtil.readSDFile(new File(FileUtil.getCacheFile(), fileName));
//                            jsonStr = jsonStr.replace("&#10;", "\\n");
//                            jsonStr = jsonStr.replaceAll("\\\\","");
                            jsonObject = new JSONObject(jsonStr);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return jsonObject;
    }

}