/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.task.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.jinmibao.biz.common.enums.PendingDeletedDomainDealStatusEnum;
import com.jinmibao.biz.dataobject.domain.DomainDeletedDO;
import com.jinmibao.biz.service.domain.DomainDeletedService;
import com.jinmibao.task.service.ResolvePendingHandler;
import com.jinmibao.task.service.deleteddomain.ResolvePendingDeletedDomain;

/**
 * 类ResolvePendingHandlerImpl.java的实现描述：TODO 类实现描述
 * 
 * @author chongan.wangca 2012-4-20 下午10:09:32
 */
public class ResolvePendingHandlerImpl implements ResolvePendingHandler {

    private List<ResolvePendingDeletedDomain> resolvePendingDeletedDomainList;

    @Resource
    private DomainDeletedService              domainDeletedService;

    /*
     * (non-Javadoc)
     * @see com.jinmibao.task.service.ResolvePendingHandler#deal(com.jinmibao.biz.dataobject.domain.DomainDeletedDO)
     */
    @Override
    public void deal(DomainDeletedDO domainDeletedDO) {
        for (ResolvePendingDeletedDomain resolvePendingDeletedDomain : resolvePendingDeletedDomainList) {
            resolvePendingDeletedDomain.deal(domainDeletedDO);
        }
        domainDeletedDO.setDealStatus(PendingDeletedDomainDealStatusEnum.FINISH.getKey());
        domainDeletedService.update(domainDeletedDO);
    }

    public void setResolvePendingDeletedDomainList(List<ResolvePendingDeletedDomain> resolvePendingDeletedDomainList) {
        this.resolvePendingDeletedDomainList = resolvePendingDeletedDomainList;
    }

}
