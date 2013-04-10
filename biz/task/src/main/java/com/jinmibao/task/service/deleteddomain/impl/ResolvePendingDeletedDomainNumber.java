/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.task.service.deleteddomain.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jinmibao.biz.dataobject.domain.DomainDeletedDO;
import com.jinmibao.common.constants.CommonConstant;
import com.jinmibao.task.service.deleteddomain.ResolvePendingDeletedDomain;

/**
 * 处理是否全部是数字
 * 
 * @author chongan.wangca 2012-4-20 下午7:09:03
 */
public class ResolvePendingDeletedDomainNumber implements ResolvePendingDeletedDomain {

    private final static Logger logger       = LoggerFactory.getLogger(ResolvePendingDeletedDomainNumber.class);

    public final static String  NUMBER_REGEX = "^[0-9]+$";

    /*
     * (non-Javadoc)
     * @see
     * com.jinmibao.task.service.ResolvePendingDeletedDomain#deal(com.jinmibao.biz.dataobject.domain.DomainDeletedDO)
     */
    @Override
    public void deal(DomainDeletedDO domainDeletedDO) {

        String domainName = domainDeletedDO.getDomainName();
        if (domainName.matches(NUMBER_REGEX)) {
            domainDeletedDO.setIsAllNum(CommonConstant.YES);
            logger.info("IS NUM = " + domainDeletedDO.getDomainName());
        } else {
            domainDeletedDO.setIsAllNum(CommonConstant.NO);
        }
    }
}
