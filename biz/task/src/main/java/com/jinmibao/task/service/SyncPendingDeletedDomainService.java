/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.task.service;

import java.util.Date;
import java.util.List;

/**
 * 同步删除即将过期域名
 * 
 * @author chongan.wangca 2012-4-19 上午12:17:38
 */
public interface SyncPendingDeletedDomainService {

    /**
     * 同步主入口
     * 
     * @param syncDateList
     */
    public void syncMain(List<Date> syncDateList);

}
