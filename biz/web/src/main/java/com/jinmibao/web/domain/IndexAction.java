/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.web.domain;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.jinmibao.biz.common.enums.PendingDeletedDomainDealStatusEnum;
import com.jinmibao.biz.dataobject.domain.DomainDeletedDO;
import com.jinmibao.biz.query.domain.DomainDeletedQuery;
import com.jinmibao.biz.service.domain.DomainDeletedService;
import com.jinmibao.biz.web.util.ActionUtil;
import com.jinmibao.common.base.page.PageControler;
import com.jinmibao.common.constants.CommonConstant;
import com.jinmibao.common.util.ArrayUtil;
import com.jinmibao.common.util.DateUtil;
import com.jinmibao.common.util.ListUtil;
import com.jinmibao.common.util.StringUtil;
import com.jinmibao.web.entity.PendingDeletedSearch;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 类SyncPendingDeletedDomainAction.java的实现描述：TODO 类实现描述
 * 
 * @author chongan.wangca 2012-4-19 上午1:34:14
 */
public class IndexAction extends ActionSupport {

    @Resource
    private ActionUtil           actionUtil;

    private PendingDeletedSearch pds;

    @Resource
    private DomainDeletedService domainDeletedService;

    // 查询的页码
    private Long                 currPage;

    /**
     * 
     */
    private static final long    serialVersionUID = -5153839172612629181L;

    public String forDelQuery() {
        String minDate = DateUtil.date2String(DateUtil.increaseDate(new Date(), -5), "yyyy-MM-dd");
        String maxDate = DateUtil.date2String(DateUtil.increaseDate(new Date(), +4), "yyyy-MM-dd");
        actionUtil.addRequestAttribute("minDate", minDate);
        actionUtil.addRequestAttribute("maxDate", maxDate);
        actionUtil.addRequestAttribute("pds", pds = new PendingDeletedSearch());
        return SUCCESS;
    }

    public String delQuery() {

        DomainDeletedQuery domainDeletedQuery = new DomainDeletedQuery();

        // 关键字
        if (!StringUtil.isBlank(pds.getKeywords())) {
            domainDeletedQuery.setKeywords(pds.getKeywords().trim());
        }

        // 关键字位置
        domainDeletedQuery.setKeywordPlace(pds.getKeywordPlace());

        // 排序
        domainDeletedQuery.setRankByType(pds.getRankByType());

        // 后缀
        if (pds.getDomainType().length == 1 && "0".equals(pds.getDomainType()[0])) {
            domainDeletedQuery.setDomainTypeArray(null);
        } else {
            domainDeletedQuery.setDomainTypeArray(pds.getDomainType());
        }

        // 排除的关键字，目前只接收一个
        domainDeletedQuery.setExcludeKeywords(pds.getExcludeKeywords());

        if (pds.getLengthStart() > 0) {
            domainDeletedQuery.setLengthStart(pds.getLengthStart());
        }
        if (pds.getLengthEnd() > 0) {
            domainDeletedQuery.setLengthEnd(pds.getLengthEnd());
        }

        // 域名结构
        if (pds.getIncludeType() != null) {
            List<String> includeTypeList = ArrayUtil.array2List(pds.getIncludeType());
            if (includeTypeList.size() == 1) {
                if (ListUtil.containStr("0", includeTypeList)) {
                    domainDeletedQuery.setIsAllLetter(CommonConstant.YES);
                }
                if (ListUtil.containStr("1", includeTypeList)) {
                    domainDeletedQuery.setIsAllNum(CommonConstant.YES);
                }
            }
        }

        // 中横线
        if ("0".equals(pds.getLine())) {
            domainDeletedQuery.setIsHasLine(CommonConstant.NO);
        } else if ("2".equals(pds.getLine())) {
            domainDeletedQuery.setIsHasLine(CommonConstant.YES);
            if (pds.getLineCount() > 0) {
                domainDeletedQuery.setLineCount(pds.getLineCount());
            }
        }

        // 特殊结构
        if (pds.getSepcType() != null) {
            for (String sepcType : pds.getSepcType()) {
                // 拼音
                if ("0".equals(sepcType)) {
                    String pinyinType = pds.getPinyinType();
                    if ("0".equals(pinyinType)) {
                        domainDeletedQuery.setIsAllPinyin(CommonConstant.YES);
                    } else if ("1".equals(pinyinType)) {
                        domainDeletedQuery.setIsAllPinyin(CommonConstant.YES);
                        domainDeletedQuery.setIsAllPinyinOne(CommonConstant.YES);
                    } else if ("2".equals(pinyinType)) {
                        domainDeletedQuery.setIsAllPinyin(CommonConstant.YES);
                        domainDeletedQuery.setIsAllPinyinTwo(CommonConstant.YES);
                    } else if ("3".equals(pinyinType)) {
                        domainDeletedQuery.setIsAllPinyin(CommonConstant.YES);
                        domainDeletedQuery.setIsAllPinyinThree(CommonConstant.YES);
                    }
                }
            }
        }

        if (pds.getDeletedDateStart() != null && pds.getDeletedDateEnd() != null
            && pds.getDeletedDateStart().equals(pds.getDeletedDateEnd())) {
            domainDeletedQuery.setDeletedDateStart(DateUtil.parseDate(pds.getDeletedDateStart() + " 00:00:00", null));
            domainDeletedQuery.setDeletedDateEnd(DateUtil.parseDate(pds.getDeletedDateEnd() + " 00:00:00", null));
        } else {
            if (pds.getDeletedDateStart() != null) {
                domainDeletedQuery.setDeletedDateStart(DateUtil.parseDate(pds.getDeletedDateStart() + " 00:00:00", null));
            }
            if (pds.getDeletedDateEnd() != null) {
                domainDeletedQuery.setDeletedDateEnd(DateUtil.parseDate(pds.getDeletedDateEnd() + " 00:00:00", null));
            }
        }

        domainDeletedQuery.setIsDeleted(CommonConstant.NO);
        domainDeletedQuery.setDealStatus(PendingDeletedDomainDealStatusEnum.FINISH.getKey());
        PageControler<DomainDeletedDO> domainDeletedPageControler = domainDeletedService.findByPage(domainDeletedQuery,

        getCurrPage(), 100L);

        String minDate = DateUtil.date2String(DateUtil.increaseDate(new Date(), -5), "yyyy-MM-dd");
        String maxDate = DateUtil.date2String(DateUtil.increaseDate(new Date(), +4), "yyyy-MM-dd");
        actionUtil.addRequestAttribute("minDate", minDate);
        actionUtil.addRequestAttribute("maxDate", maxDate);
        actionUtil.addRequestAttribute("pager", domainDeletedPageControler.getPager());
        actionUtil.addRequestAttribute("domainDeletedPageControler", domainDeletedPageControler);
        return SUCCESS;
    }

    public PendingDeletedSearch getPds() {
        return pds;
    }

    public void setPds(PendingDeletedSearch pds) {
        this.pds = pds;
    }

    public Long getCurrPage() {
        if (this.currPage == null || this.currPage == 0L) {
            return 1L;
        }
        return currPage;
    }

    public void setCurrPage(Long currPage) {
        this.currPage = currPage;
    }

}
