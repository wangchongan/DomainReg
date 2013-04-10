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
import com.jinmibao.common.util.StringUtil;
import com.jinmibao.task.service.deleteddomain.ResolvePendingDeletedDomain;

/**
 * 处理域名是否是拼音
 * 
 * @author chongan.wangca 2012-4-20 下午7:09:03
 */
public class ResolvePendingDeletedDomainBase implements ResolvePendingDeletedDomain {

    private final static Logger logger = LoggerFactory.getLogger(ResolvePendingDeletedDomainBase.class);

    /*
     * (non-Javadoc)
     * @see
     * com.jinmibao.task.service.ResolvePendingDeletedDomain#deal(com.jinmibao.biz.dataobject.domain.DomainDeletedDO)
     */
    @Override
    public void deal(DomainDeletedDO domainDeletedDO) {
        String domainName = domainDeletedDO.getDomainName();
        domainDeletedDO.setAllLength(Long.valueOf(domainName.length()));
        // 判断是否含有横线
        if (domainName.indexOf("-") != -1) {
            domainDeletedDO.setIsHasLine(CommonConstant.YES);
            int count = StringUtil.containCount(domainName, "-");
            domainDeletedDO.setLineCount(Long.valueOf(count));
        } else {
            domainDeletedDO.setIsHasLine(CommonConstant.NO);
            domainDeletedDO.setLineCount(0L);
        }

        // 判断是否是中文域名
        if (domainName.matches("[\u4e00-\u9fa5]+")) {
            domainDeletedDO.setIsChinese(CommonConstant.YES);
        } else {
            domainDeletedDO.setIsChinese(CommonConstant.NO);
        }

        if (domainName.matches("[a-z]{" + domainName.length() + "}")) {
            domainDeletedDO.setIsAllLetter(CommonConstant.YES);
        } else {
            domainDeletedDO.setIsAllLetter(CommonConstant.NO);
        }
    }

}
