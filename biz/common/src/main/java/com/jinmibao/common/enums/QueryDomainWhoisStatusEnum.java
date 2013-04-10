/**
 * Project: taobaiji File Created at 2012-3-11 $Id$ Copyright 1999-2100 Alibaba.com Corporation Limited. All rights
 * reserved. This software is the confidential and proprietary information of Alibaba Company.
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.common.enums;

/**
 * 店铺状态类型
 * 
 * @author chongan.wangca
 */
public enum QueryDomainWhoisStatusEnum {

    ILLEGAL_DOMAIN("ILLEGAL_DOMAIN", "不合法的域名"),

    QUERY_ERROR("QUERY_ERROR", "查询失败"), QUERY_SUCCESS("QUERY_SUCCESS", "查询成功"),

    UNSUPPORT_DOMAIN("UNSUPPORT_DOMAIN", "不支持的域名后缀类型");

    private String key;
    private String name;

    private QueryDomainWhoisStatusEnum(String key, String name){
        this.key = key;
        this.name = name;
    }

    static public String getName(String key) {
        for (QueryDomainWhoisStatusEnum obj : QueryDomainWhoisStatusEnum.values()) {
            if (obj.getKey().equals(key)) {
                return obj.getName();
            }
        }
        return null;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }
}
