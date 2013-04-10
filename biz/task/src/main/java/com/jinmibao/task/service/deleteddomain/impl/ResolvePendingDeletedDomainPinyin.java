/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.task.service.deleteddomain.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jinmibao.biz.dataobject.domain.DomainDeletedDO;
import com.jinmibao.biz.dataobject.domain.PinyinDO;
import com.jinmibao.biz.query.domain.PinyinQuery;
import com.jinmibao.biz.service.domain.PinyinService;
import com.jinmibao.common.constants.CommonConstant;
import com.jinmibao.task.service.deleteddomain.ResolvePendingDeletedDomain;

/**
 * 处理域名是否是拼音
 * 
 * @author chongan.wangca 2012-4-20 下午7:09:03
 */
public class ResolvePendingDeletedDomainPinyin implements ResolvePendingDeletedDomain {

    private final static Logger  logger       = LoggerFactory.getLogger(ResolvePendingDeletedDomainPinyin.class);

    public static List<PinyinDO> pinyinDOList = null;

    @Resource
    private PinyinService        pinyinService;

    /*
     * (non-Javadoc)
     * @see
     * com.jinmibao.task.service.ResolvePendingDeletedDomain#deal(com.jinmibao.biz.dataobject.domain.DomainDeletedDO)
     */
    @Override
    public void deal(DomainDeletedDO domainDeletedDO) {
        String domainName = domainDeletedDO.getDomainName();

        PinyinQuery pinyinQuery = new PinyinQuery();
        pinyinQuery.setPinyin(domainName);

        pinyinQuery.setNum(1L);
        // 单拼域名
        PinyinDO danPinyinDO = pinyinService.find(pinyinQuery);
        if (danPinyinDO != null) {
            domainDeletedDO.setIsAllPinyin(CommonConstant.YES);
            domainDeletedDO.setIsAllPinyinOne(CommonConstant.YES);
            domainDeletedDO.setIsAllPinyinTwo(CommonConstant.NO);
            domainDeletedDO.setIsAllPinyinThree(CommonConstant.NO);
            // 是否含拼音
            domainDeletedDO.setIsHasPinyin(CommonConstant.NO);
            domainDeletedDO.setIsHasPinyinOne(CommonConstant.NO);
            domainDeletedDO.setIsHasPinyinTwo(CommonConstant.NO);
            domainDeletedDO.setIsHasPinyinThree(CommonConstant.NO);
            domainDeletedDO.setPinyinChinese(danPinyinDO.getChinese());
            logger.info("IS DanPin = " + domainDeletedDO.getDomainName());
            return;
        }

        pinyinQuery.setNum(2L);
        // 双拼域名
        PinyinDO shuangPinyinDO = pinyinService.find(pinyinQuery);
        if (shuangPinyinDO != null) {
            domainDeletedDO.setIsAllPinyin(CommonConstant.YES);
            domainDeletedDO.setIsAllPinyinOne(CommonConstant.NO);
            domainDeletedDO.setIsAllPinyinTwo(CommonConstant.YES);
            domainDeletedDO.setIsAllPinyinThree(CommonConstant.NO);
            // 是否含拼音
            domainDeletedDO.setIsHasPinyin(CommonConstant.NO);
            domainDeletedDO.setIsHasPinyinOne(CommonConstant.NO);
            domainDeletedDO.setIsHasPinyinTwo(CommonConstant.NO);
            domainDeletedDO.setIsHasPinyinThree(CommonConstant.NO);
            String firstChinese = pinyinService.findById(shuangPinyinDO.getFirst()).getChinese();
            String secondChinese = pinyinService.findById(shuangPinyinDO.getSecond()).getChinese();
            domainDeletedDO.setPinyinChinese(firstChinese + "," + secondChinese);
            logger.info("IS ShuangPin = " + domainDeletedDO.getDomainName());
            return;
        }

        // 处理判断是否匹配三拼
        pinyinQuery.setNum(1L);
        pinyinQuery.setPinyin(null);

        if (pinyinDOList == null) {
            pinyinDOList = pinyinService.findList(pinyinQuery);
        }

        for (PinyinDO pinyinDO : pinyinDOList) {
            if (domainName.startsWith(pinyinDO.getPinyin())) {
                String domainStart = pinyinDO.getPinyin();
                String withoutStartDomain = domainName.replaceFirst(domainStart, "");

                pinyinQuery.setNum(2L);
                pinyinQuery.setPinyin(withoutStartDomain);
                PinyinDO secondPinyinDO = pinyinService.find(pinyinQuery);

                if (secondPinyinDO != null) {
                    domainDeletedDO.setIsAllPinyin(CommonConstant.YES);
                    domainDeletedDO.setIsAllPinyinOne(CommonConstant.NO);
                    domainDeletedDO.setIsAllPinyinTwo(CommonConstant.NO);
                    domainDeletedDO.setIsAllPinyinThree(CommonConstant.YES);
                    // 是否含拼音
                    domainDeletedDO.setIsHasPinyin(CommonConstant.NO);
                    domainDeletedDO.setIsHasPinyinOne(CommonConstant.NO);
                    domainDeletedDO.setIsHasPinyinTwo(CommonConstant.NO);
                    domainDeletedDO.setIsHasPinyinThree(CommonConstant.NO);

                    // 设置三拼汉字
                    String firstChinese = pinyinService.findById(secondPinyinDO.getFirst()).getChinese();
                    String secondChinese = pinyinService.findById(secondPinyinDO.getSecond()).getChinese();
                    domainDeletedDO.setPinyinChinese(pinyinDO.getChinese() + "," + firstChinese + "," + secondChinese);
                    logger.info("IS SanPin = " + domainDeletedDO.getDomainName());
                    return;
                }
            }
        }

        domainDeletedDO.setIsAllPinyin(CommonConstant.NO);
        domainDeletedDO.setIsAllPinyinOne(CommonConstant.NO);
        domainDeletedDO.setIsAllPinyinTwo(CommonConstant.NO);
        domainDeletedDO.setIsAllPinyinThree(CommonConstant.NO);
        // 是否含拼音
        domainDeletedDO.setIsHasPinyin(CommonConstant.NO);
        domainDeletedDO.setIsHasPinyinOne(CommonConstant.NO);
        domainDeletedDO.setIsHasPinyinTwo(CommonConstant.NO);
        domainDeletedDO.setIsHasPinyinThree(CommonConstant.NO);

    }
}
