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
import com.jinmibao.common.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 域名删除监视宝
 * 
 * @author chongan.wangca 2012-4-19 上午1:34:14
 */
public class DelMonitorAction extends ActionSupport {

    private final static Logger logger           = LoggerFactory.getLogger(DelMonitorAction.class);

    @Resource
    private ActionUtil          actionUtil;

    /**
     * 
     */
    private static final long   serialVersionUID = -5153839172612629181L;

    // 域名删除监视页面
    public String delMonitor() {
        actionUtil.addRequestAttribute("monitorNO", StringUtil.getRandStr(5).toUpperCase());
        return SUCCESS;
    }

}
