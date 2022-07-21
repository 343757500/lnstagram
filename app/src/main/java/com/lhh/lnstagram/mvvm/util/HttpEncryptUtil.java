package com.lhh.lnstagram.mvvm.util;


import com.google.gson.Gson;
import com.lhh.lnstagram.mvvm.base.BaseConfig;

import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;


/**
 * Http加密方式
 */
public class HttpEncryptUtil {

	/**
	 * 请求RSA加密，返回用AES解密
	 */
	public static String reqEncryptByRSA(String str) {
		try {
			return encryptByPublicKey(str, getPublicKey(BaseConfig.KEY_HTTP_RSA_ENCRYPT_PUBLIC_KEY));
		} catch (Exception e) {
			e.printStackTrace();
			return str;
		}
	}

	/**
	 * 返回用AES解密
	 */
	public static String respDecodeByAES(String data, String key) {
		return AESUtil.get().decrypt7Padding(data, key, BaseConfig.KEY_HTTP_RSA_DECODE_AES_IV);
	}

	/**
	 * 请求用AES加密，返回不需要解密
	 */
	public static String reqEncryptByAES(String data, String key) {
		return AESUtil.get().encrypt7Padding(data, key, BaseConfig.KEY_HTTP_AES_ENCRYPT_IV);
	}

	public static String reqSign(String data) {
		return MD5Util.encryptionMD5(data + "/" + BaseConfig.KEY_HTTP_AES_ENCRYPT_SIGN).toLowerCase();
	}


	/**
	 * RSA加密
	 * @param data 待加密数据
	 * @param publicKey 公钥
	 * @return
	 */
	public static String encryptByPublicKey(String data, PublicKey publicKey) throws Exception {
		// RSA最大加密明文大小
		int MAX_ENCRYPT_BLOCK = 117;

		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		int inputLen = data.getBytes().length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offset = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offset > 0) {
			if (inputLen - offset > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data.getBytes(), offset, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data.getBytes(), offset, inputLen - offset);
			}
			out.write(cache, 0, cache.length);
			i++;
			offset = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		// 获取加密内容使用base64进行编码,并以UTF-8为标准转化成字符串
		// 加密后的字符串
		return Base64Util.encode(encryptedData);
	}

	/**
	 * 获取公钥
	 */
	public static PublicKey getPublicKey(String publicKey) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		byte[] decodedKey = Base64Util.decode(publicKey);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
		return keyFactory.generatePublic(keySpec);
	}

	private static String[] fileHeader;


}