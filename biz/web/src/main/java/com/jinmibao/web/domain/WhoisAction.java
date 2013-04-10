/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.web.domain;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jinmibao.biz.web.util.ActionUtil;
import com.jinmibao.common.enums.QueryDomainWhoisStatusEnum;
import com.jinmibao.common.util.DomainUtil;
import com.jinmibao.common.util.whois.HichinaWhoisUtil;
import com.jinmibao.common.util.whois.WhoisBean;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 类SyncPendingDeletedDomainAction.java的实现描述：TODO 类实现描述
 * 
 * @author chongan.wangca 2012-4-19 上午1:34:14
 */
public class WhoisAction extends ActionSupport {

    private final static Logger logger           = LoggerFactory.getLogger(WhoisAction.class);

    @Resource
    private ActionUtil          actionUtil;

    private String              d;

    private String              t;

    /**
     * 
     */
    private static final long   serialVersionUID = -5153839172612629181L;

    // 查询域名whois
    public String whois() {

        if (d == null) {
            actionUtil.addRequestAttribute("whoisBean", null);
            return SUCCESS;
        }

        WhoisBean whoisBean = new WhoisBean();
        try {

            d = d.trim();

            if (d.indexOf(" ") != -1) {
                String[] dInfo = d.split(" ");
                this.d = dInfo[0].trim();
                this.t = dInfo[1].trim();
                whoisBean.setDomain(d + "." + t);
                whoisBean = HichinaWhoisUtil.queryWhois(d + "." + t);
            } else if (d.indexOf(".") != -1 && d.indexOf(" ") == -1) {
                whoisBean.setDomain(d);
                whoisBean = HichinaWhoisUtil.queryWhois(d);
                // 位置不能颠倒，否则会报空指针
                this.t = DomainUtil.getDomainSplit(d)[1];
                this.d = DomainUtil.getDomainSplit(d)[0];
            } else {
                whoisBean.setDomain(d + "." + t);
                whoisBean = HichinaWhoisUtil.queryWhois(d + "." + t);
            }
        } catch (Exception e) {
            logger.error("Request Whois Error.", e);
            whoisBean.setQueryStatus(QueryDomainWhoisStatusEnum.QUERY_ERROR.getKey());
        }
        actionUtil.addRequestAttribute("whoisBean", whoisBean);
        return SUCCESS;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

}
