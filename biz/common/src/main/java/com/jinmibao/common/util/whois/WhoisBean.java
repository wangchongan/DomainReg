/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.common.util.whois;

import java.util.Date;

/**
 * 类WhoisBean.java的实现描述：TODO 类实现描述
 * 
 * @author chongan.wangca 2012-4-22 下午10:58:39
 */
public class WhoisBean {

    // WHOIS完全信息
    private String   whoisMessage;

    // 注册时间
    private Date     creationDate;

    // 过期时间
    private Date     expirationDate;

    // 注册时间串
    private String   creationDateStr;

    // 过期时间串
    private String   expirationDateStr;

    // 注册商
    private String   registrar;

    // DNS
    private String   nameServer;

    // 记录域名状态的 分为注册/未注册/禁止查询
    private String   domainStatus;

    // 域名的WHOIS的状态，分为禁止转移/禁止删除等等
    private String   whoisStatus;

    private String   queryStatus;

    private String   domain;

    private String[] domainArray;

    public String getWhoisMessage() {
        return whoisMessage;
    }

    public void setWhoisMessage(String whoisMessage) {
        this.whoisMessage = whoisMessage;
    }

    public String getCreationDateStr() {
        return creationDateStr;
    }

    public void setCreationDateStr(String creationDateStr) {
        this.creationDateStr = creationDateStr;
    }

    public String getExpirationDateStr() {
        return expirationDateStr;
    }

    public void setExpirationDateStr(String expirationDateStr) {
        this.expirationDateStr = expirationDateStr;
    }

    public String getRegistrar() {
        return registrar;
    }

    public void setRegistrar(String registrar) {
        this.registrar = registrar;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getDomainStatus() {
        return domainStatus;
    }

    public void setDomainStatus(String domainStatus) {
        this.domainStatus = domainStatus;
    }

    public String getQueryStatus() {
        return queryStatus;
    }

    public void setQueryStatus(String queryStatus) {
        this.queryStatus = queryStatus;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String[] getDomainArray() {
        return domainArray;
    }

    public void setDomainArray(String[] domainArray) {
        this.domainArray = domainArray;
    }

    public String getNameServer() {
        return nameServer;
    }

    public void setNameServer(String nameServer) {
        this.nameServer = nameServer;
    }

    public String getWhoisStatus() {
        return whoisStatus;
    }

    public void setWhoisStatus(String whoisStatus) {
        this.whoisStatus = whoisStatus;
    }

}
