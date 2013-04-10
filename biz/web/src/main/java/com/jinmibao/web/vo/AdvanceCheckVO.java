/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.web.vo;

import java.util.List;

import com.jinmibao.web.entity.DomainEntity;

/**
 * 域名搜索查询结果类
 * 
 * @author chongan.wangca 2012-4-28 下午3:28:31
 */
public class AdvanceCheckVO {

    // 错误标志
    private boolean            errorFlag = false;

    // 错误提示信息
    private String             message;

    // 域名总共个数
    private Long               domainCount;

    // 域名
    private List<DomainEntity> domainList;

    public boolean isErrorFlag() {
        return errorFlag;
    }

    public void setErrorFlag(boolean errorFlag) {
        this.errorFlag = errorFlag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getDomainCount() {
        return domainCount;
    }

    public void setDomainCount(Long domainCount) {
        this.domainCount = domainCount;
    }

    public List<DomainEntity> getDomainList() {
        return domainList;
    }

    public void setDomainList(List<DomainEntity> domainList) {
        this.domainList = domainList;
    }

}
