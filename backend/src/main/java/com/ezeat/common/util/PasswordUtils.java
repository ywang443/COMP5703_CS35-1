package com.ezeat.common.util;

public class PasswordUtils {

    public static String encode(String password) {
        // 加密前端传过来的密码，用AES算法加密
        String key = "0123456789abcdef"; // 128 bit key
        String iv = "fedcba9876543210"; // 16 bytes IV
        // iv和key都要和php的一样
        String encryptPassword = AESCipherUtils.encrypt(key, iv, password);
        return encryptPassword;
    }
}
