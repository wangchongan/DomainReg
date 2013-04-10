/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.web.common.util;

import javax.annotation.Resource;

import com.jinmibao.biz.web.util.ActionUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 类SyncPendingDeletedDomainAction.java的实现描述：TODO 类实现描述
 * 
 * @author chongan.wangca 2012-4-19 上午1:34:14
 */
public class RedirectAction extends ActionSupport {

    @Resource
    private ActionUtil        actionUtil;

    private String            url;

    /**
     * 
     */
    private static final long serialVersionUID = -5153839172612629181L;

    public String redirect() {
        actionUtil.setAttributeToRequest("url", url);
        return SUCCESS;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
