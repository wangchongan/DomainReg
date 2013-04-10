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
public enum DomainWhoisStatusEnum {

    /**
     * ok 正常状态 inactive 非激活状态(注册的时候没有填写域名服务器，不能进行解析) clientDeleteProhibited 禁止删除 serverDeleteProhibited 禁止删除
     * clientUpdateProhibited 禁止修改 serverUpdateProhibited 禁止修改 pendingDelete 正在删除过程中 pendingTransfer 正在转移过程中
     * clientTransferProhibited 禁止转移 serverTransferProhibited 禁止转移 clientRenewProhibited 禁止续费 serverRenewProhibited 禁止续费
     * clientHold 停止解析 serverHold 停止解析 pendingVerification 注册信息正在确认过程中
     */
    REG("REG", "已被注册"), NOT_REG("NOT_REG", "未被注册"), FORBID("FORBID", "禁止或受保护无法查询");

    private String key;
    private String name;

    private DomainWhoisStatusEnum(String key, String name){
        this.key = key;
        this.name = name;
    }

    static public String getName(String key) {
        for (DomainWhoisStatusEnum obj : DomainWhoisStatusEnum.values()) {
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
