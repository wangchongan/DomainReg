/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.web.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.jinmibao.task.service.ResolveAllPendingHandler;
import com.jinmibao.task.service.SyncPendingDeletedDomainService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 类SyncPendingDeletedDomainAction.java的实现描述：TODO 类实现描述
 * 
 * @author chongan.wangca 2012-4-19 上午1:34:14
 */
public class SyncPendingDeletedDomainAction extends ActionSupport {

    /**
     * 
     */
    private static final long               serialVersionUID = -5153839172612629181L;

    @Resource
    private SyncPendingDeletedDomainService syncPendingDeletedDomainService;

    @Resource
    private ResolveAllPendingHandler        resolveAllPendingHandler;

    public String sync() {
        List<Date> dateList = new ArrayList<Date>();
        dateList.add(new Date());
        syncPendingDeletedDomainService.syncMain(dateList);
        return SUCCESS;
    }

    public String dealDomain() {
        resolveAllPendingHandler.dealAll();
        return SUCCESS;
    }

}
