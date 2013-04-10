/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.task.service.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jinmibao.biz.common.enums.PendingDeletedDomainDealStatusEnum;
import com.jinmibao.biz.dataobject.domain.DomainDeletedDO;
import com.jinmibao.biz.query.domain.DomainDeletedQuery;
import com.jinmibao.biz.service.domain.DomainDeletedService;
import com.jinmibao.common.constants.CommonConstant;
import com.jinmibao.common.util.DomainUtil;
import com.jinmibao.task.service.SyncPendingDeletedDomainService;

/**
 * 同步删除即将过期域名
 * 
 * @author chongan.wangca 2012-4-19 上午12:17:38
 */
public class SyncPendingDeletedDomainServiceImpl implements SyncPendingDeletedDomainService {

    private final static Logger  logger   = LoggerFactory.getLogger(SyncPendingDeletedDomainServiceImpl.class);

    @Resource
    private DomainDeletedService domainDeletedService;

    // http://list.kingnic.com/2012-04-23.txt
    // 同步过期域名的URL
    public final static String   SYNC_URL = "http://list.kingnic.com/";

    public final static int      MOD_LOG  = 1000;

    /*
     * (non-Javadoc)
     * @see com.jinmibao.task.service.SyncPendingDeletedDomainService#syncMain(java.util.List)
     */
    @Override
    public void syncMain(List<Date> syncDateList) {
        if (syncDateList == null || syncDateList.size() < 1) {
            return;
        }
        for (Date date : syncDateList) {
            try {
                syncFromWeb(date);
            } catch (Exception e) {
                logger.error("Sync Error, Update Sync Result To DB. SyncDate=" + date, e);
            }
        }
    }

    /**
     * 通过其他网站抓取过期域名
     * 
     * @param syncDate
     * @return
     * @throws Exception
     */
    public boolean syncFromWeb(Date syncDate) throws Exception {
        // 同步的地址
        String syncUrl = getSyncUrl(syncDate);
        URL url = new URL(syncUrl);
        URLConnection URLconnection = url.openConnection();
        HttpURLConnection httpConnection = (HttpURLConnection) URLconnection;
        int responseCode = httpConnection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            logger.info("Url=" + syncUrl + " can visit, visit success.");
        } else {
            logger.info("Url=" + syncUrl + " can not visit, visit fail.");
            return false;
        }

        InputStream urlStream = httpConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlStream));
        String sCurrentLine = "";
        int count = 0;
        while ((sCurrentLine = bufferedReader.readLine()) != null) {
            count++;
            if (count % MOD_LOG == 0) {
                logger.info("Current deal count = " + count);
            }
            try {
                dealSingleDomain(sCurrentLine.trim(), syncDate);
            } catch (Exception e) {
                logger.error("Deal single domain error. domain=" + sCurrentLine, e);
            }
        }
        return true;
    }

    /**
     * 获取同步的URL
     * 
     * @param syncDate
     * @return
     */
    public String getSyncUrl(Date syncDate) {

        // String yearVal = DateUtil.getYear(syncDate);
        // String monthVal = DateUtil.getMonth(syncDate);
        // String dateVal = DateUtil.getDate(syncDate);
        // String syncFileName = yearVal + "-" + monthVal + "-" + dateVal + ".txt";
        // // 同步的地址
        // String syncUrl = SYNC_URL + syncFileName;
        // return syncUrl;
        return "http://domain.webmasterhome.cn/todayDel_domain.asp";
        // return "http://localhost:8181/jinmibao/domainfiles/" + DateUtil.date2String(syncDate,
        // DateUtil.DAY_DATE_FORMAT)
        // + ".txt";
    }

    public boolean dealSingleDomain(String domain, Date syncDate) {
        String[] domainArray = DomainUtil.getDomainSplit(domain);
        if (domainArray == null || "".equals(domain.trim())) {
            return false;
        }

        DomainDeletedDO domainDeletedDO = new DomainDeletedDO();
        domainDeletedDO.setDomainName(domainArray[0]);
        domainDeletedDO.setDomainType(domainArray[1]);
        domainDeletedDO.setDealStatus(PendingDeletedDomainDealStatusEnum.PENDING.getKey());
        domainDeletedDO.setGmtPlanDeleted(syncDate);
        domainDeletedDO.setIsDeleted(CommonConstant.NO);

        try {
            insertIfNotExists(domainDeletedDO);
        } catch (Exception e) {
            logger.error("Insert if not exists error", e);
            return false;
        }

        return true;
    }

    /**
     * 将过期域名入库
     * 
     * @param domainDeletedDO
     * @return
     */
    public void insertIfNotExists(DomainDeletedDO domainDeletedDO) {
        DomainDeletedQuery domainDeletedQuery = new DomainDeletedQuery();
        domainDeletedQuery.setDomainName(domainDeletedDO.getDomainName());
        domainDeletedQuery.setDomainType(domainDeletedDO.getDomainType());
        DomainDeletedDO ddd = domainDeletedService.find(domainDeletedQuery);
        if (ddd == null) {
            domainDeletedService.add(domainDeletedDO);
        }
    }

}
