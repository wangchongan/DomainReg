/**
 * Project: taobaiji File Created at 2012-3-11 $Id$ Copyright 1999-2100 Alibaba.com Corporation Limited. All rights
 * reserved. This software is the confidential and proprietary information of Alibaba Company.
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.biz.common.enums;

/**
 * 域名处理类型
 * 
 * @author chongan.wangca
 */
public enum PendingDeletedDomainDealStatusEnum {

    PENDING("PENDING", "等待处理"),

    FINISH("FINISH", "已处理");

    private String key;
    private String name;

    private PendingDeletedDomainDealStatusEnum(String key, String name){
        this.key = key;
        this.name = name;
    }

    static public String getName(String key) {
        for (PendingDeletedDomainDealStatusEnum obj : PendingDeletedDomainDealStatusEnum.values()) {
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
