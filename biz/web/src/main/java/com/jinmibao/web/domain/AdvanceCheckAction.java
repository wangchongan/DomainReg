/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.web.domain;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jinmibao.biz.service.config.DomainConfig;
import com.jinmibao.biz.web.util.ActionUtil;
import com.jinmibao.common.util.ArrayUtil;
import com.jinmibao.common.util.ListUtil;
import com.jinmibao.common.util.StringUtil;
import com.jinmibao.web.entity.AdvanceCheckSearch;
import com.jinmibao.web.entity.AdvanceCheckTry;
import com.jinmibao.web.entity.DomainEntity;
import com.jinmibao.web.util.PlacePermutationUtil;
import com.jinmibao.web.vo.AdvanceCheckVO;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 类SyncPendingDeletedDomainAction.java的实现描述：TODO 类实现描述
 * 
 * @author chongan.wangca 2012-4-19 上午1:34:14
 */
public class AdvanceCheckAction extends ActionSupport {

    private final static Logger logger           = LoggerFactory.getLogger(AdvanceCheckAction.class);

    @Resource
    private ActionUtil          actionUtil;

    private AdvanceCheckSearch  acs;

    /**
     * 
     */
    private static final long   serialVersionUID = -5153839172612629181L;

    /**
     * 组合查询
     * 
     * @return
     */
    public String advanceCheck() {
        if (acs == null) {
            acs = new AdvanceCheckSearch();
            return SUCCESS;
        }
        return SUCCESS;
    }

    /**
     * 组合查询
     * 
     * @return
     */
    public void asyncAdvanceCheck() {
        if (acs == null) {
            acs = new AdvanceCheckSearch();
            return;
        }

        AdvanceCheckVO advanceCheckVO = new AdvanceCheckVO();

        // 事前校验
        boolean result = checkBeforeDeal(advanceCheckVO);

        if (result) {
            // 位置
            if (acs.getCheckType().longValue() == 0) {
                dealPlace(advanceCheckVO);
            }

            // 全排列
            if (acs.getCheckType().longValue() == 1) {
                dealAllWords(advanceCheckVO);
            }
        }

        JSONObject jsonObject = JSONObject.fromObject(advanceCheckVO);
        try {
            actionUtil.getResponse().getWriter().print(jsonObject.toString());
        } catch (IOException e) {
            logger.error("Response JSON Error", e);
        }

    }

    /**
     * 事前校验
     * 
     * @param advanceCheckVO
     */
    private boolean checkBeforeDeal(AdvanceCheckVO advanceCheckVO) {
        if (StringUtil.isBlank(acs.getDomainType())) {
            advanceCheckVO.setErrorFlag(true);
            advanceCheckVO.setMessage("亲！请至少选择一个域名后缀哦。");
            return false;
        }
        return true;
    }

    /**
     * 全组合处理
     * 
     * @param advanceCheckVO
     */
    private void dealAllWords(AdvanceCheckVO advanceCheckVO) {

        AdvanceCheckTry advanceCheckTry = new AdvanceCheckTry();
        advanceCheckTry.setTryType(1L);
        advanceCheckTry.setAllWordsList(ListUtil.str2StringList(acs.getAllWords(), "[|]"));
        advanceCheckTry.setDomainTypeList(ListUtil.str2StringList(acs.getDomainType(), "[|]"));
        advanceCheckTry.setLengthEnd(acs.getLengthEnd());
        advanceCheckTry.setLengthStart(acs.getLengthStart());
        advanceCheckTry.setCountStart(acs.getCountStart());
        advanceCheckTry.setCountEnd(acs.getCountEnd());
        advanceCheckTry.setAllWordsChineseList(ListUtil.str2StringList(acs.getAllWordsChinese(), "[|]"));
        // 计算
        try {
            advanceCheckTry.calcTryDomainPerm();
        } catch (Exception e) {
            advanceCheckVO.setErrorFlag(true);
            advanceCheckVO.setMessage(e.getMessage());
        }

        List<DomainEntity> domainEntityList = advanceCheckTry.getTryDomainList();
        // 如果存在计算得出的域名
        if (domainEntityList != null && domainEntityList.size() > 0) {
            // 是否超大了
            if (domainEntityList.size() > DomainConfig.ADVANCE_CHECK_MAX_COUNT_ALL) {
                advanceCheckVO.setDomainCount(Long.valueOf(domainEntityList.size()));
                advanceCheckVO.setErrorFlag(true);
                advanceCheckVO.setMessage("亲！组合得出" + domainEntityList.size() + "个域名，最大允许"
                                          + DomainConfig.ADVANCE_CHECK_MAX_COUNT_ALL + "个哦，请减少单词个数或缩小长度范围再重试。");
            } else {
                advanceCheckVO.setDomainList(domainEntityList);
                advanceCheckVO.setDomainCount(Long.valueOf(domainEntityList.size()));
            }
        }
    }

    /**
     * 按位置组合处理
     * 
     * @param advanceCheckVO
     */
    private void dealPlace(AdvanceCheckVO advanceCheckVO) {

        String[] wordStrArray = acs.getWordStr().split("[|]");
        String[] wordChineseStrArray = acs.getWordChineseStr().split("[|]");

        if (wordStrArray == null || wordStrArray.length < 1) {
            advanceCheckVO.setErrorFlag(true);
            advanceCheckVO.setMessage("亲！您并没有输入任何内容哦，请输入。");
            return;
        }

        List<String> placeWordList = ArrayUtil.array2List(ArrayUtil.removeBlank(wordStrArray));
        List<String> placeWordChineseList = ArrayUtil.array2List(ArrayUtil.removeBlank(wordChineseStrArray));

        if (placeWordList.size() > DomainConfig.ADVANCE_CHECK_PLACE_COUNT_MAX_COUNT
            || placeWordList.size() < DomainConfig.ADVANCE_CHECK_PLACE_COUNT_MIN_COUNT) {
            advanceCheckVO.setErrorFlag(true);
            advanceCheckVO.setMessage("亲！位置个数只能在" + DomainConfig.ADVANCE_CHECK_PLACE_COUNT_MIN_COUNT + "~"
                                      + DomainConfig.ADVANCE_CHECK_PLACE_COUNT_MAX_COUNT + "之间哦。");
            return;
        }

        if (placeWordChineseList != null && placeWordChineseList.size() > 1
            && placeWordList.size() != placeWordChineseList.size()) {
            advanceCheckVO.setErrorFlag(true);
            advanceCheckVO.setMessage("亲！单词位数" + placeWordList.size() + "个和对应的中文含义" + placeWordChineseList.size()
                                      + "个不一致，请确认是否漏填写了哦。");
            return;
        }

        List<List<String>> allPlaceWordList = ListUtil.list2List(placeWordList, " ");
        List<List<String>> allPlaceWordChineseList = ListUtil.list2List(placeWordChineseList, " ");

        int wordCount = ListUtil.listWordCount(allPlaceWordList);

        if (wordCount > DomainConfig.ADVANCE_CHECK_PLACE_WORD_MAX_COUNT) {
            advanceCheckVO.setErrorFlag(true);
            advanceCheckVO.setMessage("亲！所有单词总个数最多允许" + DomainConfig.ADVANCE_CHECK_PLACE_WORD_MAX_COUNT + "个，请至少删去"
                                      + (wordCount - DomainConfig.ADVANCE_CHECK_PLACE_WORD_MAX_COUNT) + "个哦。");
            return;
        }

        PlacePermutationUtil placePermutationUtil = new PlacePermutationUtil();
        placePermutationUtil.setPlaceCount(placeWordList.size());
        placePermutationUtil.setPlaceWordList(allPlaceWordList);
        placePermutationUtil.setPlaceWordChineseList(allPlaceWordChineseList);
        placePermutationUtil.setDomainTypeList(ListUtil.str2StringList(acs.getDomainType(), "[|]"));
        List<DomainEntity> domainEntityList = placePermutationUtil.getResult();

        // 如果存在计算得出的域名
        if (domainEntityList != null && domainEntityList.size() > 0) {
            // 是否超大了
            if (domainEntityList.size() > DomainConfig.ADVANCE_CHECK_MAX_COUNT_PLACE) {
                advanceCheckVO.setDomainCount(Long.valueOf(domainEntityList.size()));
                advanceCheckVO.setErrorFlag(true);
                advanceCheckVO.setMessage("亲！组合得出" + domainEntityList.size() + "个域名，最大允许"
                                          + DomainConfig.ADVANCE_CHECK_MAX_COUNT_PLACE + "个哦，请减少单词个数或缩小长度范围再重试。");
            } else {
                advanceCheckVO.setDomainList(domainEntityList);
                advanceCheckVO.setDomainCount(Long.valueOf(domainEntityList.size()));
            }
        }
    }

    public AdvanceCheckSearch getAcs() {
        return acs;
    }

    public void setAcs(AdvanceCheckSearch acs) {
        this.acs = acs;
    }

}
