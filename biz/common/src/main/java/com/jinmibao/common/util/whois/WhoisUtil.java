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
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jinmibao.common.enums.DomainWhoisStatusEnum;
import com.jinmibao.common.enums.QueryDomainWhoisStatusEnum;
import com.jinmibao.common.util.DomainUtil;

public class WhoisUtil {

    private final static Logger       logger         = LoggerFactory.getLogger(WhoisUtil.class);

    @SuppressWarnings("unchecked")
    public static Map<String, String> whoisServerMap = new HashMap() {

                                                         private static final long serialVersionUID = -7198628728221352925L;

                                                         {
                                                             put("biz", "whois.neulevel.biz");
                                                             put("com", "whois.internic.net");
                                                             put("us", "whois.nic.us");
                                                             put("coop", "whois.nic.coop");
                                                             put("info", "whois.nic.info");
                                                             put("name", "whois.nic.name");
                                                             put("net", "whois.internic.net");
                                                             put("gov", "whois.nic.gov");
                                                             put("edu", "whois.internic.net");
                                                             put("mil", "rs.internic.net");
                                                             put("int", "whois.iana.org");
                                                             put("ac", "whois.nic.ac");
                                                             put("ae", "whois.uaenic.ae");
                                                             put("at", "whois.ripe.net");
                                                             put("au", "whois.aunic.net");
                                                             put("be", "whois.dns.be");
                                                             put("bg", "whois.ripe.net");
                                                             put("br", "whois.registro.br");
                                                             put("bz", "whois.belizenic.bz");
                                                             put("ca", "whois.cira.ca");
                                                             put("cc", "whois.nic.cc");
                                                             put("ch", "whois.nic.ch");
                                                             put("cl", "whois.nic.cl");
                                                             put("cn", "whois.cnnic.net.cn");
                                                             put("cz", "whois.nic.cz");
                                                             put("de", "whois.nic.de");
                                                             put("fr", "whois.nic.fr");
                                                             put("hu", "whois.nic.hu");
                                                             put("ie", "whois.domainregistry.ie");
                                                             put("il", "whois.isoc.org.il");
                                                             put("in", "whois.ncst.ernet.in");
                                                             put("ir", "whois.nic.ir");
                                                             put("mc", "whois.ripe.net");
                                                             put("to", "whois.tonic.to");
                                                             put("tv", "whois.tv");
                                                             put("ru", "whois.ripn.net");
                                                             put("org", "whois.pir.org");
                                                             put("aero", "whois.information.aero");
                                                             put("nl", "whois.domain-registry.nl");
                                                         }
                                                     };

    public final static int           port           = 43;

    @SuppressWarnings("deprecation")
    public static WhoisBean queryWhois(String domain) {

        WhoisBean whoisBean = new WhoisBean();

        // 如果不是合法域名
        if (!DomainUtil.isDomain(domain)) {
            whoisBean.setQueryStatus(QueryDomainWhoisStatusEnum.ILLEGAL_DOMAIN.getKey());
            return whoisBean;
        }
        domain = domain.trim();
        whoisBean.setDomain(domain);
        whoisBean.setDomainArray(DomainUtil.getDomainSplit(domain));

        String whoisServer = whoisServerMap.get(DomainUtil.getDomainSplit(domain)[1]);

        // 不支持的域名后缀
        if (whoisServer == null) {
            whoisBean.setQueryStatus(QueryDomainWhoisStatusEnum.UNSUPPORT_DOMAIN.getKey());
            return whoisBean;
        }

        whoisBean.setQueryStatus(QueryDomainWhoisStatusEnum.QUERY_SUCCESS.getKey());

        // 查询域名被注册情况
        WhoisBean regWhoisBean = DomainRegCheckUtil.check(domain);
        if (QueryDomainWhoisStatusEnum.QUERY_SUCCESS.getKey().equals(regWhoisBean.getQueryStatus())) {
            whoisBean.setDomainStatus(regWhoisBean.getDomainStatus());
            // 如果未被注册，则无需再查询WHOIS
            if (DomainWhoisStatusEnum.NOT_REG.getKey().equals(regWhoisBean.getDomainStatus())) {
                return whoisBean;
            }
        }

        Socket theSocket = new Socket();
        DataInputStream theWhoisStream = null;
        PrintStream ps = null;

        try {
            // 最大4秒超时
            theSocket.connect(new InetSocketAddress(whoisServer, port), 4000);
            // 在TCP服务端口43（十进制）连接SRI-NIC服务主机
            // theSocket = new Socket(whoisServer, port, true);
            ps = new PrintStream(theSocket.getOutputStream());
            // 发送用户提供的一个或多个命令
            ps.print(domain + " ");
            // 以回车和换行（<CRLF>）结尾
            ps.print("\r\n");

            // 接受相应命令的返回信息
            theWhoisStream = new DataInputStream(theSocket.getInputStream());
            String sb = new String();
            String s;
            while ((s = theWhoisStream.readLine()) != null) {
                s = new String(s.getBytes("iso8859-1"), "UTF-8");
                sb = sb + (s + "<br/>");
            }
            whoisBean.setWhoisMessage(sb);
            // 查询成功
            whoisBean.setQueryStatus(QueryDomainWhoisStatusEnum.QUERY_SUCCESS.getKey());
            return whoisBean;

        } catch (IOException e) {
            whoisBean.setQueryStatus(QueryDomainWhoisStatusEnum.QUERY_ERROR.getKey());
            logger.error("Whois Query Error.", e);
        } finally {
            if (theWhoisStream != null) {
                try {
                    theWhoisStream.close();
                } catch (IOException e) {
                    logger.error("IOException", e);
                }
            }
            if (ps != null) {
                ps.close();
            }
            if (theSocket != null) {
                try {
                    theSocket.close();
                } catch (IOException e) {
                    logger.error("IOException", e);
                }
            }
        }

        return whoisBean;
    }
}
