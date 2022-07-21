package com.lhh.lnstagram.mvvm.util;

import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {

    public static String ivParameter = "1122abcd3344abcd";
    public static String transformation = "AES/CBC/PKCS5Padding";
    public static String transformation_7padding = "AES/CBC/PKCS7Padding";



    private static class SingletonHolder {
        private static final AESUtil DEFAULT_BUS = new AESUtil();
    }

    public static AESUtil get() {
        return SingletonHolder.DEFAULT_BUS;
    }





    public String encrypt7Padding(String data, String key, String iv) {
        try {
            // 在构造中已初始化好
            // Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            // 字符串 -> bytes -> 加密 - > bytes -> base64
            byte[] bytes = data.getBytes();
            SecretKeySpec keySpec = getKey(key);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
            Cipher cipher = Cipher.getInstance(transformation_7padding);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec);
            return Base64Util.encode(cipher.doFinal(bytes));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String decrypt7Padding(String data, String key, String iv) {
        try {
            // 在构造中已初始化好
            // Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            // base64 -> bytes -> 解密 -> bytes -> 字符串
            byte[] bytes = Base64Util.decode(data);
            SecretKeySpec keySpec = getKey(key);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
            Cipher cipher = Cipher.getInstance(transformation_7padding);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);
            return new String(cipher.doFinal(bytes));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }





    /**
     * 说明 :将密钥转行成SecretKeySpec格式
     * @param aesPassword 16进制密钥
     * @return SecretKeySpec格式密钥
     */
    private static SecretKeySpec getKey(String aesPassword) throws UnsupportedEncodingException {
        int keyLength = 128;
        byte[] keyBytes = new byte[keyLength / 8];
        Arrays.fill(keyBytes, (byte) 0x0);
        byte[] passwordBytes = aesPassword.getBytes("UTF-8");
        int length = passwordBytes.length < keyBytes.length ? passwordBytes.length : keyBytes.length;
        System.arraycopy(passwordBytes, 0, keyBytes, 0, length);
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        return key;
    }


    public static void main(String[] args) throws Exception {

        String key = "1234567890123456";
        String data = "123456";

    }

}
