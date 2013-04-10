/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.task.service.deleteddomain;

import com.jinmibao.biz.dataobject.domain.DomainDeletedDO;

/**
 * 类ResolvePendingDeletedDomain.java的实现描述：TODO 类实现描述
 * 
 * @author chongan.wangca 2012-4-20 下午6:48:21
 */
public interface ResolvePendingDeletedDomain {

    /**
     * 处理删除订单
     * 
     * @param domainDeletedDO
     */
    public void deal(DomainDeletedDO domainDeletedDO);

}
