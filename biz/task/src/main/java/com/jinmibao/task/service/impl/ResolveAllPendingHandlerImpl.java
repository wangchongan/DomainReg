/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.task.service.impl;

import javax.annotation.Resource;

import com.jinmibao.biz.common.enums.PendingDeletedDomainDealStatusEnum;
import com.jinmibao.biz.dataobject.domain.DomainDeletedDO;
import com.jinmibao.biz.query.domain.DomainDeletedQuery;
import com.jinmibao.biz.service.domain.DomainDeletedService;
import com.jinmibao.common.base.page.PageControler;
import com.jinmibao.task.service.ResolveAllPendingHandler;
import com.jinmibao.task.service.ResolvePendingHandler;

/**
 * 类ResolveAllPendingHandler.java的实现描述：TODO 类实现描述
 * 
 * @author chongan.wangca 2012-4-20 下午10:25:47
 */
public class ResolveAllPendingHandlerImpl implements ResolveAllPendingHandler {

    @Resource
    private DomainDeletedService  domainDeletedService;

    @Resource
    private ResolvePendingHandler resolvePendingHandler;

    /*
     * (non-Javadoc)
     * @see com.jinmibao.task.service.ResolveAllPendingHandler#dealALl()
     */
    @Override
    public void dealAll() {
        DomainDeletedQuery domainDeletedQuery = new DomainDeletedQuery();
        domainDeletedQuery.setDealStatus(PendingDeletedDomainDealStatusEnum.PENDING.getKey());

        while (true) {
            // 分页获取处理，直到没有数据
            PageControler<DomainDeletedDO> pageControler = domainDeletedService.findByPage(domainDeletedQuery, 1L, 500L);
            if (pageControler.getResultList() == null || pageControler.getResultList().size() < 1) {
                break;
            } else {
                for (DomainDeletedDO domainDeletedDO : pageControler.getResultList()) {
                    resolvePendingHandler.deal(domainDeletedDO);
                }
            }
        }
    }
}
