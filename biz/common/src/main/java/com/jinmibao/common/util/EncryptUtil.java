package com.jinmibao.common.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author Administrator
 */
public class EncryptUtil {

    // 默认的盐值
    private static final String saltFigure_1 = "taobaiji1234@#$%";

    /**
     * MD5加密
     * 
     * @param str
     * @return
     */
    public static String encrypt(String str) {
        if (str != null) {
            return DigestUtils.md5Hex(str);
        } else {
            return null;
        }
    }

    /**
     * 使用MD5加密，并且将原字符串拼上盐值 如果传进来盐值，则使用用户传进的盐值 否则使用系统默认的盐值
     * 
     * @param str
     * @param saltFigure
     * @return
     */
    public static String encrypt(String str, String saltFigure) {
        if (str != null) {
            if (saltFigure != null) {
                return encrypt(str + saltFigure);
            } else {
                return encrypt(str + saltFigure_1);
            }
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(encrypt("2012-03-16 16:29:08王崇安1331886548732"));
    }
}
