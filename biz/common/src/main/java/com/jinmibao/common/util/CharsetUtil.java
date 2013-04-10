/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.common.util;

import java.io.UnsupportedEncodingException;

/**
 * 类CharsetUtil.java的实现描述：TODO 类实现描述
 * 
 * @author chongan.wangca 2012-4-30 下午10:33:44
 */
public class CharsetUtil {

    /**
     * 　　* 字符串编码转换的实现方法 　　* @param str 待转换编码的字符串 　　* @param newCharset 目标编码来源：考试大的美女编辑们 　　* @return 　　* @throws
     * UnsupportedEncodingException 　　
     * 
     * @throws Exception
     */
    public static String changeCharset(String str, String newCharset) {
        if (str != null) {
            byte[] bs = str.getBytes();
            try {
                return new String(bs, newCharset);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
