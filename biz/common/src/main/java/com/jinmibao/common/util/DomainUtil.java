/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.common.util;

/**
 * 类DomainUtil.java的实现描述：TODO 类实现描述
 * 
 * @author chongan.wangca 2012-4-19 上午1:03:33
 */
public class DomainUtil {

    /**
     * 把域名进行切分
     * 
     * @param domain
     * @return
     */
    public static String[] getDomainSplit(String domain) {
        if (isDomain(domain)) {
            if (StringUtil.containCount(domain.trim(), ".") == 1) {
                return domain.split("[.]");
            }
            if (StringUtil.containCount(domain.trim(), ".") == 2) {
                String[] domainArray = new String[2];
                domainArray[0] = domain.split("[.]")[0].trim();
                domainArray[1] = domain.split("[.]")[1].trim() + "." + domain.split("[.]")[2].trim();
                return domainArray;
            }
        }
        return null;
    }

    /**
     * 判断是否为合法域名
     * 
     * @param domain
     * @return
     */
    public static boolean isDomain(String domain) {
        if (domain == null) {
            return false;
        }
        if (StringUtil.containCount(domain, ".") == 0) {
            return false;
        }
        if (StringUtil.containCount(domain, ".") == 1) {
            String regex = "([a-zA-Z0-9\u4e00-\u9fa5]{1}[-a-zA-Z0-9\u4e00-\u9fa5]{0,62})(\\.{1}[a-zA-Z]{1,10})";
            return domain.trim().matches(regex);
        }
        if (StringUtil.containCount(domain, ".") == 2) {
            String regex = "([a-zA-Z0-9\u4e00-\u9fa5]{1}[-a-zA-Z0-9\u4e00-\u9fa5]{0,62})(\\.{1}[a-zA-Z]{1,10})(\\.{1}[a-zA-Z]{1,10})";
            return domain.trim().matches(regex);
        }
        return false;
    }

}
