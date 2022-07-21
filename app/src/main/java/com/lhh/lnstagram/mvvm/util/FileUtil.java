package com.lhh.lnstagram.mvvm.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.text.format.Formatter;

import com.lhh.lnstagram.R;
import com.lhh.lnstagram.base.BaseApplication;
import com.lhh.lnstagram.db.DBHelper;
import com.lhh.lnstagram.sp.CookiesSP;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


/**
 * 文件操作类
 *
 * @author XieWenjun
 * @version 1.0, 2014-9-16 下午4:50:07
 */
public class FileUtil {

    /**
     * chat根目录
     *
     * @return /Sasai
     */
    public static final File getRootFile() {
        if (isSDCardExist()) {
            return BaseApplication.getInstance().getExternalCacheDir();
        }
        String path = getSDCardPath() + "/" + getRootFileName();
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return folder;
    }

    /**
     * 缓存等其他琐碎文件
     *
     * @return /Sasai/Cache
     */
    public static final File getCacheFile() {
        if (isSDCardExist()) {
            return BaseApplication.getInstance().getExternalCacheDir();
        } else {
            String path = getSDCardPath() + "/" + getRootFileName() + "/Cache";
            File folder = new File(path);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            return folder;
        }
    }


    /**
     * 日志文件
     *
     * @return /Sasai/Log
     */
    public static final File getLogFile() {
        if (isSDCardExist()) {
            return BaseApplication.getInstance().getExternalFilesDir("Log");
        } else {
            String path = getSDCardPath() + "/" + getRootFileName() + "/Log";
            File folder = new File(path);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            return folder;
        }
    }


    /**
     * 聊天背景图片
     *
     * @return /Sasai/Wallpaper
     */
    public static final File getWallpaperFile() {
        if (isSDCardExist()) {
            return BaseApplication.getInstance().getExternalFilesDir("Wallpaper");
        } else {
            String path = getSDCardPath() + "/" + getRootFileName() + "/Wallpaper";
            File folder = new File(path);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            return folder;
        }
    }

    /**
     * 备份的文件夹
     *
     * @return /Sasai/Backup
     */
    public static final File getBackupFile() {
        if (isSDCardExist()) {
            return BaseApplication.getInstance().getExternalFilesDir("Backup");
        } else {
            String path = getSDCardPath() + "/" + getRootFileName() + "/Backup";
            File folder = new File(path);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            return folder;
        }


    }

    /**
     * 备份的数据库文件夹
     *
     * @return /Sasai/Database
     */
    public static final File getDatabaseFile() {
        if (isSDCardExist()) {
            return BaseApplication.getInstance().getExternalFilesDir("Database");
        } else {
            String path = getSDCardPath() + "/" + getRootFileName() + "/Database";
            File folder = new File(path);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            return folder;
        }

    }

//    public static final File getInnerAppFile() {
//        return BaseApplication.getInstance().getFilesDir();
//    }


    /*-- START 默认保存位置（不做备份）--*/
    public static final File getImageChatFile() {
        return getChatFile("image");
    }

    public static final File getAudioChatFile() {
        return getChatFile("audio");
    }

    public static final File getVideoChatFile() {
        return getChatFile("video");
    }

    public static final File getFileChatFile() {
        return getChatFile("file");
    }

    public static final File getDownloadFile() {
        return getChatFile("download");
    }
    /*-- END 默认保存位置（不做备份）--*/


    /*-- START 手动保存位置（做备份）--*/
    public static final File getMedialImageFile() {
        return getMedialFile("Sasai Image");
    }

    public static final File getMedialAudioFile() {
        return getMedialFile("Sasai Audio");
    }

    public static final File getMedialVideoFile() {
        return getMedialFile("Sasai Video");
    }

    public static final File getMedialFileFile() {
        return getMedialFile("Sasai File");
    }

    public static final File getMedialProfileFile() {
        return getMedialFile("Sasai Profile Photos");
    }

    public static final File getMedialQRCodeFile() {
        return getMedialFile("Sasai QR Code");
    }

    public static final File getMedialQRCodeFileNew() {
        return getSDCardDownloadsPath();
    }

    /*-- END 手动保存位置（做备份）--*/


    /**
     * Medial的子目录
     *
     * @param typeName 子目录名称 /Sasai Image /Sasai Audio /Sasai Video /Sasai Profile Phtots ...
     * @return /Sasai/Media/{typeName}
     */
    private static final File getMedialFile(String typeName) {
        StringBuilder builder = new StringBuilder();
        if (isSDCardExist()) {
            builder.append("Media/")
                    .append(CookiesSP.getCookies().getUserId())
                    .append("/")
                    .append(typeName);
            return BaseApplication.getInstance().getExternalFilesDir(builder.toString());
        }
        builder.append(getSDCardPath())
                .append("/")
                .append(getRootFileName())
                .append("/Media/")
                .append(CookiesSP.getCookies().getUserId())
                .append("/")
                .append(typeName);
        File folder = new File(builder.toString());
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return folder;
    }

    /**
     * Medial 根目录
     *
     * @return /Sasai/Media
     */
    public static final File getMedialFile() {
        StringBuilder builder = new StringBuilder();
        if (isSDCardExist()) {
            builder.append("/Media/")
                    .append(CookiesSP.getCookies().getUserId());
            return BaseApplication.getInstance().getExternalFilesDir(builder.toString());
        }
        builder.append(getSDCardPath())
                .append("/")
                .append(getRootFileName())
                .append("/Media/")
                .append(CookiesSP.getCookies().getUserId());
        File folder = new File(builder.toString());
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return folder;
    }

    /**
     * chat的某个用户的根目录
     *
     * @return /Sasai/Chat/{userId}
     */
    public static final File getChatFile() {
        StringBuilder builder = new StringBuilder();
        if (isSDCardExist()) {
            builder.append("/Media/")
                    .append(CookiesSP.getCookies().getUserId())
                    .append("/Chat");
            return BaseApplication.getInstance().getExternalFilesDir(builder.toString());
        } else {
            builder.append(getSDCardPath())
                    .append("/")
                    .append(getRootFileName())
                    .append("/Chat/")
                    .append(CookiesSP.getCookies().getUserId());
            File folder = new File(builder.toString());
            if (!folder.exists()) {
                folder.mkdirs();
            }
            return folder;
        }
    }

    /**
     * chat根目录下的文件夹
     *
     * @param typeName image、audio、video、file、download
     * @return /Sasai/Chat/{userId}/{typeName}
     */
    private static final File getChatFile(String typeName) {
        StringBuilder builder = new StringBuilder();
        if (isSDCardExist()) {
            builder.append("/Chat/")
                    .append(CookiesSP.getCookies().getUserId())
                    .append("/")
                    .append(typeName);
            return BaseApplication.getInstance().getExternalFilesDir(typeName);
        } else {
            builder.append(getSDCardPath())
                    .append("/")
                    .append(getRootFileName())
                    .append("/Chat/")
                    .append(CookiesSP.getCookies().getUserId())
                    .append("/")
                    .append(typeName);
            File folder = new File(builder.toString());
            if (!folder.exists()) {
                folder.mkdirs();
            }
            return folder;
        }
    }

    public static long getChatFileSizeOldVersion(String typeName) {
        if (isSDCardExist()) {
            return getFolderSize(BaseApplication.getInstance().getExternalFilesDir(typeName));
        } else {
            return 0;
        }
    }

    public static File getChatFileOldVersion(String typeName) {
        if (isSDCardExist()) {
            return BaseApplication.getInstance().getExternalFilesDir(typeName);
        } else {
            return null;
        }
    }

    /**
     * 获取包名
     */
    private static String getRootFileName() {
        return "Sasai";
    }

    /**
     * 获取SD卡路径
     */
    public static String getSDCardPath() {
        File storageDirectory = null;
        if (isSDCardExist()) {
            storageDirectory = Environment.getExternalStorageDirectory();
        }
        if (storageDirectory == null) {
            storageDirectory = BaseApplication.getInstance().getFilesDir();
        }
        return storageDirectory.toString();
    }

    /**
     * SD卡是否存在
     *
     * @return
     * @author XieWenjun
     */
    public static boolean isSDCardExist() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取SD卡 DOWNLOADS路径
     */
    public static File getSDCardDownloadsPath() {
        File storageDirectory = null;
        if (isSDCardExist()) {
            storageDirectory = new File(getSDCardPath() + "/" + Environment.DIRECTORY_DCIM + "/" + "Camera");
        }

        return storageDirectory;
    }


    /**
     * 删除图片-根据路径
     *
     * @param path
     */
    public static void deleteTempFile(String path) {
        File file = new File(path);
        deleteTempFile(file);
    }

    /**
     * 删除图片-根据文件
     */
    public static void deleteTempFile(File file) {
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 创建文件
     */
    public static File newFile(Context context, String path, String name) {
        return new File(newFileDir(context, path), name);
    }

    /**
     * 创建文件夹
     */
    private static File newFileDir(Context context, String path) {
        File dir = null;
        try {
            dir = new File(FileUtil.getRootFile(), path);
        } catch (Exception e) {
          //  LogUtil.e("创建文件夹失败");
        }
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    /**
     * 由URI得到文件路径
     */
    public static String getFilePath4Uri(Context context, Uri uri) {
        String filePath = null;
        ContentResolver cr = context.getContentResolver();
        Cursor cursor = cr.query(uri, null, null, null, null);// 根据URI从数据库中找
        if (cursor != null) {
            cursor.moveToFirst();
            filePath = cursor.getString(cursor.getColumnIndex("_data"));
        }
        return filePath;
    }


    /**
     * 将RES的GIF保存到SD卡
     *
     * @param resId
     * @param file
     * @return path
     */
    public static String saveGif2SDCard(Context context, int resId, File file) {
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        FileOutputStream fos = null;
        try {
            is = context.getResources().openRawResource(resId);
            byte[] b = new byte[1024];
            baos = new ByteArrayOutputStream();
            int len = -1;
            while ((len = is.read(b)) != -1) {
                baos.write(b, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                baos.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        byte[] result = baos.toByteArray();
        try {
            fos = new FileOutputStream(file);
            fos.write(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return file.getAbsolutePath();
    }

    /**
     * 读取Asset文件
     */
    public static String readAssetFile(Context context, String fileName) {
        InputStream is = null;
        String content = null;
        try {
            is = context.getAssets().open(fileName);
            if (is != null) {

                byte[] buffer = new byte[1024];
                ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    int readLength = is.read(buffer);
                    if (readLength == -1)
                        break;
                    arrayOutputStream.write(buffer, 0, readLength);
                }
                is.close();
                arrayOutputStream.close();
                content = new String(arrayOutputStream.toByteArray());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            content = null;
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return content;
    }


    /**
     * 读取SD文件
     */
    public static String readSDFile(File file) {
        FileInputStream is = null;
        String content = null;
        try {
            is = new FileInputStream(file);
            if (is != null) {
                byte[] buffer = new byte[1024];
                ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    int readLength = is.read(buffer);
                    if (readLength == -1)
                        break;
                    arrayOutputStream.write(buffer, 0, readLength);
                }
                is.close();
                arrayOutputStream.close();
                content = new String(arrayOutputStream.toByteArray());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            content = null;
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return content;
    }


    public static String getFormatFileSize(long size) {
        if (size <= 0) return "0 KB";
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    public static void copyFile(String oldPath, String newPath) throws Exception {
        int bytesum = 0;
        int byteread = 0;
        File oldfile = new File(oldPath);
        if (oldfile.exists()) { //文件存在时
            InputStream inStream = new FileInputStream(oldPath); //读入原文件
            FileOutputStream fs = new FileOutputStream(newPath);
            byte[] buffer = new byte[1024];
            int length;
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread; //字节数 文件大小
                // System.out.println(bytesum);
                fs.write(buffer, 0, byteread);
            }
            inStream.close();
        }
    }

    public static String getBackUpSize(Context context) {
        File backupFile = getMedialFile();
        long size = getFolderSize(backupFile)
                + getFolderSize(BaseApplication.getInstance().getDatabasePath(DBHelper.getDBName4UserId(CookiesSP.getCookies().getUserId()) + ".db"));
        return Formatter.formatFileSize(context, size);
    }

    /**
     * 获取文件夹大小
     *
     * @param file File实例
     * @return long
     */
    public static long getFolderSize(File file) {
        long size = 0;
        try {
            if (file.isFile()) {
                size = file.length();
            }

            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 复制文件
     *
     * @param source 输入文件
     * @param target 输出文件
     */
    public void copy(File source, File target) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(source);
            fileOutputStream = new FileOutputStream(target);
            byte[] buffer = new byte[1024];
            while (fileInputStream.read(buffer) > 0) {
                fileOutputStream.write(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static String readFile(File file) {
        if (file == null) {
            return "";
        }
        StringBuffer buffer = new StringBuffer();

        FileInputStream fis = null;
        InputStreamReader isr = null;
        Reader in = null;
        try {
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis, "UTF-8");
            in = new BufferedReader(isr);
            int i;
            while ((i = in.read()) > -1) {
                buffer.append((char) i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (isr != null) {
                    isr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return buffer.toString();
        }
    }

    public static void writeFile(File file, String content) {
        if (file == null || content == null) {
            return;
        }
        FileOutputStream fos = null;
        Writer out = null;
        try {
            fos = new FileOutputStream(file);
            out = new OutputStreamWriter(fos, "UTF-8");
            out.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean fileIsExists(String strFile) {
        try {
            File f = new File(strFile);
            if (!f.exists()) {
                return false;
            }

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * 获取缓存目录
     */
    public static String getCacheDir() {
        String path = "";
        File cacheDir = BaseApplication.getInstance().getCacheDir();
        if (cacheDir != null) {
            path = cacheDir.getPath();
        }
        if (TextUtils.isEmpty(path)) {
            path = FileUtil.getCacheFile().getPath();
        }
        return path;
    }

    /**
     * Watch视频下载目录
     *
     * @return /Sasai/WatchVideo/userId/
     */
    public static final File getWatchDownloadFileDir() {
        if (isSDCardExist()) {
            StringBuilder builder = new StringBuilder();
            builder.append(CookiesSP.getCookies().getUserId())
                    .append("/")
                    .append("WatchVideo");
            return BaseApplication.getInstance().getExternalFilesDir(builder.toString());
        } else {
            StringBuilder builder = new StringBuilder();
            builder.append(getSDCardPath())
                    .append("/")
                    .append(getRootFileName())
                    .append("/WatchVideo/")
                    .append(CookiesSP.getCookies().getUserId());
            File folder = new File(builder.toString());
            if (!folder.exists()) {
                folder.mkdirs();
            }
            return folder;
        }
    }

    /**
     * 获取文件后缀名
     */
    public static String getFileExtensionName(String fileName) {
        if (TextUtils.isEmpty(fileName)) {
            return "";
        }
        int endP = fileName.lastIndexOf(".");
        return endP > -1 ? fileName.substring(endP + 1, fileName.length()) : "";
    }

    /**
     * 获取文件MD5码
     */
    public static String getFileMD5(File updateFile) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
//            L.e(TAG, "Exception while getting digest", e);
            return null;
        }

        InputStream is;
        try {
            is = new FileInputStream(updateFile);
        } catch (FileNotFoundException e) {
//            L.e(TAG, "Exception while getting FileInputStream", e);
            return null;
        }

        byte[] buffer = new byte[8192];
        int read;
        try {
            while ((read = is.read(buffer)) > 0) {
                digest.update(buffer, 0, read);
            }
            byte[] md5sum = digest.digest();
            BigInteger bigInt = new BigInteger(1, md5sum);
            String output = bigInt.toString(16);
            // Fill to 32 chars
            output = String.format("%32s", output).replace(' ', '0');
            return output;
        } catch (IOException e) {
            throw new RuntimeException("Unable to process file for MD5", e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
//                L.e(TAG, "Exception on closing MD5 input stream", e);
            }
        }
    }

    //移除文件，获取文件时间与当前时间对比，删除五天前的文件
    public static void removeFileByTime(String dirPath, int logMaxKeepTime) {
        //获取目录下所有文件
        List<File> allFile = getDirAllFile(new File(dirPath));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //获取当前时间
        Date end = new Date(System.currentTimeMillis());
        try {
            end = dateFormat.parse(dateFormat.format(new Date(System.currentTimeMillis())));
        } catch (Exception e) {
          //  LogUtil.d("dataformat exeption e " + e.toString());
        }
        //LogUtil.d("getNeedRemoveFile  dirPath = " + dirPath);
        for (File file : allFile) {//ComDef
            try {
                //文件时间减去当前时间
                Date start = dateFormat.parse(dateFormat.format(new Date(file.lastModified())));
                long diff = end.getTime() - start.getTime();//这样得到的差值是微秒级别
                long days = diff / (1000 * 60 * 60 * 24);
//                if (logMaxKeepTime <= days) {
//                    deleteFile(file);
//                }
                deleteFile(file);
            } catch (Exception e) {
               // LogUtil.d("dataformat exeption e " + e.toString());
            }
        }
    }

    //删除文件夹及文件夹下所有文件
    public static void deleteFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                File f = files[i];
                deleteFile(f);
            }
            file.delete();
        } else if (file.exists()) {
            file.delete();
        }
    }

    //获取指定目录下一级文件
    public static List<File> getDirAllFile(File file) {
        List<File> fileList = new ArrayList<>();
        File[] fileArray = file.listFiles();
        if (fileArray == null)
            return fileList;
        for (File f : fileArray) {
            fileList.add(f);
        }
        fileSortByTime(fileList);
        return fileList;
    }

    //对文件进行时间排序
    public static void fileSortByTime(List<File> fileList) {
        Collections.sort(fileList, new Comparator<File>() {
            public int compare(File p1, File p2) {
                if (p1.lastModified() < p2.lastModified()) {
                    return -1;
                }
                return 1;
            }
        });
    }

    /**
     * 下载的音频文件(放在公共sd目录)
     *
     * @return
     */
    public static File getAudioFileDir() {
        if (isSDCardExist()) {
            return BaseApplication.getInstance().getExternalFilesDir("song");
        } else {
            File file = new File(getSDCardPath() + "/" + getRootFileName() + "/song");
            if (!file.exists()) {
                file.mkdirs();
            }
            return file;
        }
    }

    /**
     * PodCast音频目录
     *
     * @return /Sasai
     */
    public static File getPodCastAudioEditFile() {
        String path = "";
        File cacheDir = BaseApplication.getInstance().getCacheDir();
        if (cacheDir != null) {
            path = cacheDir.getPath();
        }
        if (TextUtils.isEmpty(path)) {
            path = FileUtil.getCacheFile().getPath();
        }
        File folder = new File(path + File.separator + "PodCast");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return folder;
    }

}
