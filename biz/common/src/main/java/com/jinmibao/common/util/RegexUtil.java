/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.common.util;

import com.jinmibao.common.constants.RegExpConstant;

/**
 * 类RegexUtil.java的实现描述：TODO 类实现描述
 * 
 * @author chongan.wangca 2012-5-3 上午2:04:45
 */
public class RegexUtil {

    /**
     * 校验是否是合法邮箱地址
     * 
     * @param str
     * @return
     */
    public static boolean isEmail(String str) {
        if (StringUtil.isBlank(str)) {
            return Boolean.FALSE;
        }
        return str.matches(RegExpConstant.EMAIL);
    }
}
