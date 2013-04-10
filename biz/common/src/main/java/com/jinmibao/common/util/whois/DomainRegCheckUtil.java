/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.common.util.whois;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jinmibao.common.enums.DomainWhoisStatusEnum;
import com.jinmibao.common.enums.QueryDomainWhoisStatusEnum;
import com.jinmibao.common.util.DomainUtil;
import com.jinmibao.common.util.XMLUtil;

/**
 * 查询校验域名是否被注册
 * 
 * @author chongan.wangca 2012-4-23 上午1:38:23
 */
public class DomainRegCheckUtil {

    private final static Logger logger = LoggerFactory.getLogger(WhoisUtil.class);

    public static WhoisBean check(String domain) {

        WhoisBean whoisBean = new WhoisBean();
        whoisBean.setDomain(domain);
        whoisBean.setQueryStatus(QueryDomainWhoisStatusEnum.QUERY_SUCCESS.getKey());

        // 不合法的域名
        if (!DomainUtil.isDomain(domain)) {
            whoisBean.setQueryStatus(QueryDomainWhoisStatusEnum.ILLEGAL_DOMAIN.getKey());
            return whoisBean;
        }

        String checkUrl = "http://panda.www.net.cn/cgi-bin/check.cgi?area_domain=" + domain.trim();

        Document document = null;
        try {
            URL url = new URL(checkUrl);
            URLConnection URLconnection = url.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) URLconnection;
            // 设置超时
            System.setProperty("sun.net.client.defaultConnectTimeout", "8000");
            System.setProperty("sun.net.client.defaultReadTimeout", "8000");

            int responseCode = httpConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
            } else {
                logger.error("Check Error." + checkUrl);
            }

            InputStream urlStream = httpConnection.getInputStream();
            document = XMLUtil.getDocumentByInputStream(urlStream);
        } catch (Exception e) {
            logger.error("Check Error.", e);
            whoisBean.setQueryStatus(QueryDomainWhoisStatusEnum.QUERY_ERROR.getKey());
            return whoisBean;
        }
        String returncode = XMLUtil.getElementText("returncode", document.getRootElement());
        // 不等于200，代表查询失败
        if (!"200".equals(returncode)) {
            whoisBean.setQueryStatus(QueryDomainWhoisStatusEnum.QUERY_ERROR.getKey());
            return whoisBean;
        }
        String original = XMLUtil.getElementText("original", document.getRootElement());

        if (original.startsWith("210")) {
            whoisBean.setDomainStatus(DomainWhoisStatusEnum.NOT_REG.getKey());
        } else if (original.startsWith("211")) {
            whoisBean.setDomainStatus(DomainWhoisStatusEnum.REG.getKey());
        } else if (original.startsWith("212")) {
            whoisBean.setQueryStatus(QueryDomainWhoisStatusEnum.QUERY_ERROR.getKey());
        }

        return whoisBean;
    }
}
