/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.web.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 类PermCalcResult.java的实现描述：TODO 类实现描述
 * 
 * @author chongan.wangca 2012-5-1 下午1:45:18
 */
public class PermCalcResult {

    private String       domain;

    private String       domainChinese;

    private Integer      wordLength;

    private Integer      domainLength;

    private List<String> everyWordList = new ArrayList<String>();

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDomainChinese() {
        return domainChinese;
    }

    public void setDomainChinese(String domainChinese) {
        this.domainChinese = domainChinese;
    }

    public Integer getWordLength() {
        return wordLength;
    }

    public void setWordLength(Integer wordLength) {
        this.wordLength = wordLength;
    }

    public List<String> getEveryWordList() {
        return everyWordList;
    }

    public void setEveryWordList(List<String> everyWordList) {
        this.everyWordList = everyWordList;
    }

    public Integer getDomainLength() {
        return domainLength;
    }

    public void setDomainLength(Integer domainLength) {
        this.domainLength = domainLength;
    }

}
