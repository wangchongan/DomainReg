/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.web.entity;

import java.util.Date;

import com.jinmibao.common.util.ArrayUtil;
import com.jinmibao.common.util.DateUtil;
import com.jinmibao.common.util.StringUtil;

/**
 * @author chongan.wangca 2012-4-21 下午2:52:15
 */
public class PendingDeletedSearch {

    private String   keywords;
    private String   keywordPlace;
    // 域名后缀
    private String[] domainType;
    private Long     lengthStart;
    private Long     lengthEnd;
    // 域名结构
    private String[] includeType;
    // 特殊结构
    private String[] sepcType;
    private String   pinyinType;
    private String   deletedDateStart;
    private String   deletedDateEnd;
    private String   line;
    private Long     lineCount;

    // 域名后缀字符串
    private String   domainTypeStr;
    // 域名结构字符串
    private String   includeTypeStr;
    // 特殊结构字符串
    private String   sepcTypeStr;

    // 排序
    private String   rankByType;

    // 排除的关键字
    private String   excludeKeywords;

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        if (!StringUtil.isBlank(keywords)) {
            // 消除掉空格,变为小写，所有只当成一个来看待
            keywords = StringUtil.removeBlank(keywords.toLowerCase());
        }
        this.keywords = keywords;
    }

    public String getKeywordPlace() {
        return keywordPlace;
    }

    public void setKeywordPlace(String keywordPlace) {
        this.keywordPlace = keywordPlace;
    }

    public String[] getDomainType() {
        return domainType;
    }

    public void setDomainType(String[] domainType) {
        this.domainType = domainType;
    }

    public Long getLengthStart() {
        return lengthStart;
    }

    public void setLengthStart(Long lengthStart) {
        this.lengthStart = lengthStart;
    }

    public Long getLengthEnd() {
        if (this.lengthEnd == null) {
            return 10L;
        }
        return lengthEnd;
    }

    public void setLengthEnd(Long lengthEnd) {
        this.lengthEnd = lengthEnd;
    }

    public String[] getIncludeType() {
        return includeType;
    }

    public void setIncludeType(String[] includeType) {
        this.includeType = includeType;
    }

    public String[] getSepcType() {
        return sepcType;
    }

    public void setSepcType(String[] sepcType) {
        this.sepcType = sepcType;
    }

    public String getPinyinType() {
        return pinyinType;
    }

    public void setPinyinType(String pinyinType) {
        this.pinyinType = pinyinType;
    }

    // 如果为空，则采用默认值，默认为昨天
    public String getDeletedDateStart() {
        if (StringUtil.isBlank(this.deletedDateStart)) {
            Date startDate = DateUtil.increaseDate(new Date(), -1);
            return DateUtil.date2String(startDate, DateUtil.DAY_DATE_FORMAT);
        }
        return deletedDateStart;
    }

    public void setDeletedDateStart(String deletedDateStart) {
        this.deletedDateStart = deletedDateStart;
    }

    // 如果为空，则采用默认值，默认为今天
    public String getDeletedDateEnd() {
        if (StringUtil.isBlank(this.deletedDateStart)) {
            return DateUtil.date2String(new Date(), DateUtil.DAY_DATE_FORMAT);
        }
        return deletedDateEnd;
    }

    public void setDeletedDateEnd(String deletedDateEnd) {
        this.deletedDateEnd = deletedDateEnd;
    }

    public Long getLineCount() {
        return lineCount;
    }

    public void setLineCount(Long lineCount) {
        this.lineCount = lineCount;
    }

    public String getDomainTypeStr() {
        domainTypeStr = ArrayUtil.joinArray2Str(this.domainType, ",");
        return domainTypeStr;
    }

    public void setDomainTypeStr(String domainTypeStr) {
        this.domainTypeStr = domainTypeStr;
    }

    public String getIncludeTypeStr() {
        includeTypeStr = ArrayUtil.joinArray2Str(this.includeType, ",");
        return includeTypeStr;

    }

    public void setIncludeTypeStr(String includeTypeStr) {
        this.includeTypeStr = includeTypeStr;
    }

    public String getSepcTypeStr() {
        sepcTypeStr = ArrayUtil.joinArray2Str(this.sepcType, ",");
        return sepcTypeStr;
    }

    public void setSepcTypeStr(String sepcTypeStr) {
        this.sepcTypeStr = sepcTypeStr;
    }

    public String getRankByType() {
        if (StringUtil.isBlank(this.rankByType)) {
            return "0";
        }
        return rankByType;
    }

    public void setRankByType(String rankByType) {
        this.rankByType = rankByType;
    }

    public String getExcludeKeywords() {
        return excludeKeywords;
    }

    public void setExcludeKeywords(String excludeKeywords) {
        if (!StringUtil.isBlank(excludeKeywords)) {
            // 消除掉空格,变为小写，所有只当成一个来看待
            excludeKeywords = StringUtil.removeBlank(excludeKeywords.toLowerCase());
        }
        this.excludeKeywords = excludeKeywords;
    }

}
