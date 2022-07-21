package skinlibrary.loader;

import android.util.LruCache;
import android.util.Xml;

import com.lhh.lnstagram.mvvm.util.FileUtil;
import com.lhh.lnstagram.mvvm.util.LanguageUtil;
import com.lhh.lnstagram.sp.LanguageSP;

import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import guide.util.StringUtil;

public class StringLoaderB {

    private static LruCache<String, String> lruCache;

    static  {
        int cacheSize = 100 * 1024;
        lruCache = new LruCache<String, String>(cacheSize) {
            @Override
            protected int sizeOf(String key, String value) {
                return value.getBytes().length;
            }
        };
    }

    /**
     * 获取文案
     * @param key eg: common_txt_welcome
     */
    public static String getString(String key) {
        return parserXml(key);
    }

    /**
     * 清空缓存，切换语言的时候
     */
    public static void clear() {
        lruCache.evictAll();
    }

    // 把String对象加入到缓存中
    private static void addStringToMemory(String key, String value) {
        if (getStringFromMemory(key) == null) {
            lruCache.put(key, value);
        }
    }

    // 从缓存中得到String
    private static String getStringFromMemory(String key) {
        return lruCache.get(key);
    }

    //解析xml方法
    private static String parserXml(String key) {
        String cache = getStringFromMemory(key);
        if (StringUtil.isNotEmpty(cache)) {
            return cache;
        }

        String languageCode = LanguageSP.getInstance().getCurrentLanguageCode();
        String fileName = LanguageUtil.getLanguageFileName(languageCode);

        // 获取assets中的xml文件
        FileInputStream fop = null;
        InputStreamReader inputStream = null;
        try {
            File file = new File(FileUtil.getCacheFile(), fileName);
            if (file == null) {
                return "";
            }
            fop = new FileInputStream(file);
            inputStream = new InputStreamReader(fop, "utf-8");
        } catch (Exception e) {
            return "";
        }
        String value = null;
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(inputStream);

            // 得到当前事件类型
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT && null == value) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        /**
                         * 通过getName判断读到哪个标签, 然后通过nextText获取文本节点值，
                         * 或者通过getAttributeValue(i)获取属性节点值
                         */
                        String name = parser.getName();
                        if ("string".equals(name)) {
                            if (key.equals(parser.getAttributeValue(null, "name"))) {
                                try {
                                    value = parser.nextText();
                                    addStringToMemory(key, value);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return value;
    }


}