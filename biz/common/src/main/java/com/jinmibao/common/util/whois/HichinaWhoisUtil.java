/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.common.util.whois;

/**
 * 类Whois.java的实现描述：TODO 类实现描述
 * 
 * @author chongan.wangca 2012-4-22 下午10:40:05
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jinmibao.common.enums.DomainWhoisStatusEnum;
import com.jinmibao.common.enums.QueryDomainWhoisStatusEnum;
import com.jinmibao.common.util.CharsetUtil;
import com.jinmibao.common.util.DomainUtil;
import com.jinmibao.common.util.StringUtil;

public class HichinaWhoisUtil {

    private final static Logger logger = LoggerFactory.getLogger(HichinaWhoisUtil.class);

    public static WhoisBean queryWhois(String domain) throws Exception {

        WhoisBean whoisBean = new WhoisBean();

        // 如果不是合法域名
        if (!DomainUtil.isDomain(domain)) {
            whoisBean.setQueryStatus(QueryDomainWhoisStatusEnum.ILLEGAL_DOMAIN.getKey());
            return whoisBean;
        }
        domain = domain.trim();
        whoisBean.setDomain(domain);
        whoisBean.setDomainArray(DomainUtil.getDomainSplit(domain));

        whoisBean.setQueryStatus(QueryDomainWhoisStatusEnum.QUERY_SUCCESS.getKey());

        // WHOIS信息查询URL
        String hichinaWhoisUrl = "http://whois.hichina.com/whois/api_whois?host=" + domain + "&oper=0";

        URL url = new URL(hichinaWhoisUrl);
        URLConnection URLconnection = url.openConnection();
        HttpURLConnection httpConnection = (HttpURLConnection) URLconnection;

        System.setProperty("sun.net.client.defaultConnectTimeout", "8000");
        System.setProperty("sun.net.client.defaultReadTimeout", "8000");

        int responseCode = httpConnection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
        } else {
            whoisBean.setQueryStatus(QueryDomainWhoisStatusEnum.QUERY_ERROR.getKey());
            return whoisBean;
        }

        // InputStreamReader reder = new InputStreamReader(conn.getInputStream(), "utf-8");

        // InputStream urlStream = httpConnection.getInputStream();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(),
                                                                                 "GB2312"));

        String sCurrentLine = "";
        StringBuffer responseJSON = new StringBuffer();
        while ((sCurrentLine = bufferedReader.readLine()) != null) {
            responseJSON.append(sCurrentLine);
        }
        String responseJSONStr = responseJSON.toString();
        responseJSONStr = CharsetUtil.changeCharset(responseJSONStr, "UTF-8");

        JSONObject jsonObject = JSONObject.fromObject(responseJSONStr);

        String space = null;
        if (jsonObject.containsKey("space")) {
            space = jsonObject.getString("space");
        }
        String regDate = null;
        if (jsonObject.containsKey("reg_date")) {
            regDate = jsonObject.getString("reg_date");
        }
        String error = null;
        if (jsonObject.containsKey("error")) {
            error = jsonObject.getString("error");
        }

        // 是否返回查询失败
        if (!StringUtil.isBlank(error)) {
            whoisBean.setQueryStatus(QueryDomainWhoisStatusEnum.QUERY_ERROR.getKey());
            return whoisBean;
        }

        // 未注册
        if ("space".equals(space)) {
            whoisBean.setDomainStatus(DomainWhoisStatusEnum.NOT_REG.getKey());
            return whoisBean;
        } else if (!StringUtil.isBlank(regDate)) {
            // 已被注册
            whoisBean.setDomainStatus(DomainWhoisStatusEnum.REG.getKey());
            // 所有信息
            whoisBean.setWhoisMessage(jsonObject.containsKey("total_infor") ? jsonObject.getString("total_infor") : "");
            whoisBean.setCreationDateStr(jsonObject.containsKey("reg_date") ? jsonObject.getString("reg_date") : "");
            whoisBean.setExpirationDateStr(jsonObject.containsKey("exp_date") ? jsonObject.getString("exp_date") : "");
            whoisBean.setWhoisStatus(jsonObject.containsKey("status") ? jsonObject.getString("status") : "");
            whoisBean.setRegistrar(jsonObject.containsKey("registrar") ? jsonObject.getString("registrar") : "");
            whoisBean.setNameServer(jsonObject.containsKey("name_server") ? jsonObject.getString("name_server") : "");
        } else {
            // 禁止查询
            whoisBean.setDomainStatus(DomainWhoisStatusEnum.FORBID.getKey());
        }

        return whoisBean;
    }

}
