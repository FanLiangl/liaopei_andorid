package com.zm.utils;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES工具类
 */
public class AESUtils {
    /**
     * aes 加密
     * @param str 传输的值
     * @return  Strint
     */
    public static String aesEncryptToString(String str,String key) {
        return new String(aesEncrypt(str,key));
    }

    /**
     * @param str 传输的值
     * @return byte[]
     */
    public static byte[] aesEncrypt(String str,String key){
        if (str == null) {
            return null;
        }
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
            byte[] bytes = cipher.doFinal(str.getBytes("utf-8"));
            return Base64.encode(bytes, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * aes解密
     * @param str 传输的值
     * @return  String
     */
    public static String aesDecrypt(String str,String key){
        if (str == null) {
            return null;
        }
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
            byte[] bytes = Base64.decode(str,Base64.DEFAULT);
            bytes = cipher.doFinal(bytes);
            return new String(bytes, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
