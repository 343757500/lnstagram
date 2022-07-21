package com.lhh.lnstagram.mvvm.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author sunling
 * @date 2019/5/7
 */
public class MD5Util {

    public static String md5(String data) {
        return encryptionMD5(data).toLowerCase();
    }

    public static String MD5(String data) {
        return encryptionMD5(data).toUpperCase();
    }

    public static String encryptionMD5(String data) {
        return encryptionMD5(data.getBytes());
    }

    /**
     * MD5加密
     *
     * @param byteStr 需要加密的内容
     * @return 返回 byteStr的md5值
     */
    public static String encryptionMD5(byte[] byteStr) {
        MessageDigest messageDigest = null;
        StringBuffer md5StrBuff = new StringBuffer();
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(byteStr);
            byte[] byteArray = messageDigest.digest();
            for (int i = 0; i < byteArray.length; i++) {
                if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                    md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
                } else {
                    md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5StrBuff.toString();
    }

/*
    public static void main(String[] args){
        System.out.print(encryptionMD5("123456"));
    }
*/

}
