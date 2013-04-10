/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.task.service;

import com.jinmibao.biz.dataobject.domain.DomainDeletedDO;

/**
 * 注入所有的处理过期域名的处理类
 * 
 * @author chongan.wangca 2012-4-20 下午10:06:43
 */
public interface ResolvePendingHandler {

    public void deal(DomainDeletedDO domainDeletedDO);

}
