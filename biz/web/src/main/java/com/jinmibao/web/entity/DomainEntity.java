/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.web.entity;

import com.jinmibao.common.util.StringUtil;

/**
 * 域名实体
 * 
 * @author chongan.wangca 2012-4-27 上午2:37:55
 */
public class DomainEntity {

    private String domainName;
    private String domainType;
    private int    wordLength;
    private int    domainLength;
    // 中文意思
    private String domainChinese;

    // 随机数，用于标识域名
    private String randStr;

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getDomainType() {
        return domainType;
    }

    public void setDomainType(String domainType) {
        this.domainType = domainType;
    }

    public int getWordLength() {
        return wordLength;
    }

    public void setWordLength(int wordLength) {
        this.wordLength = wordLength;
    }

    public int getAllLength() {
        if (this.domainName == null) {
            return 0;
        }
        return this.domainName.length();
    }

    public String getRandStr() {
        return StringUtil.getRandStr(10);
    }

    /**
     * 获取整个域名
     * 
     * @return
     */
    public String getDomain() {
        if (domainName == null) {
            return null;
        }
        return this.domainName + "." + this.domainType;
    }

    public int getDomainLength() {
        return domainLength;
    }

    public void setDomainLength(int domainLength) {
        this.domainLength = domainLength;
    }

    public String getDomainChinese() {
        return domainChinese;
    }

    public void setDomainChinese(String domainChinese) {
        this.domainChinese = domainChinese;
    }
}
