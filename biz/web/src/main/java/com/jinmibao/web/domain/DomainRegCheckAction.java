/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.web.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jinmibao.biz.web.util.ActionUtil;
import com.jinmibao.common.enums.DomainWhoisStatusEnum;
import com.jinmibao.common.enums.QueryDomainWhoisStatusEnum;
import com.jinmibao.common.util.StringUtil;
import com.jinmibao.common.util.whois.DomainRegCheckUtil;
import com.jinmibao.common.util.whois.WhoisBean;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 类SyncPendingDeletedDomainAction.java的实现描述：TODO 类实现描述
 * 
 * @author chongan.wangca 2012-4-19 上午1:34:14
 */
public class DomainRegCheckAction extends ActionSupport {

    private final static Logger logger           = LoggerFactory.getLogger(DomainRegCheckAction.class);

    @Resource
    private ActionUtil          actionUtil;

    private String              d;

    /**
     * 
     */
    private static final long   serialVersionUID = -5153839172612629181L;

    public void asyncRegCheck() {

        WhoisBean whoisBean = DomainRegCheckUtil.check(d);

        String checkResult = "-1";

        // 不合法的域名
        if (QueryDomainWhoisStatusEnum.ILLEGAL_DOMAIN.getKey().equals(whoisBean.getQueryStatus())) {
            checkResult = "-2";
        }

        // 未注册
        if (DomainWhoisStatusEnum.NOT_REG.getKey().equals(whoisBean.getDomainStatus())) {
            checkResult = "0";
        } else if (DomainWhoisStatusEnum.REG.getKey().equals(whoisBean.getDomainStatus())) {
            checkResult = "1";
        }
        try {
            actionUtil.getResponse().getWriter().print(checkResult);
        } catch (IOException e) {
            logger.error("Async Reg Check Error.", e);
        }
    }

    public String check() {
        if (StringUtil.isBlank(d)) {
            return SUCCESS;
        }
        String[] domainArray = d.split("\r\n");
        List<WhoisBean> whoisBeanList = new ArrayList<WhoisBean>();
        for (String domain : domainArray) {
            if (!StringUtil.isBlank(domain)) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    logger.error("Check Domain Sleep Error.", e);
                }
                WhoisBean whoisBean = DomainRegCheckUtil.check(domain.trim());
                whoisBeanList.add(whoisBean);
            }
        }
        actionUtil.setAttributeToRequest("whoisBeanList", whoisBeanList);
        return SUCCESS;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

}
