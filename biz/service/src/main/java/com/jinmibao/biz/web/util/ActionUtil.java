/**
 * Project: tbj.biz.web File Created at 2012-3-15 $Id$ Copyright 1999-2100 Alibaba.com Corporation Limited. All rights
 * reserved. This software is the confidential and proprietary information of Alibaba Company.
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.biz.web.util;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jinmibao.common.base.page.PageControler;
import com.jinmibao.common.base.page.Pager;
import com.jinmibao.common.entity.ActionResult;
import com.jinmibao.common.util.BeanUtil;

/**
 * TODO Comment of ActionUtil
 * 
 * @author chongan.wangca
 */
public class ActionUtil {

    private final static Logger logger           = LoggerFactory.getLogger(ActionUtil.class);

    /**
     * 
     */
    private static final long   serialVersionUID = 7430269426119721546L;

    /**
     * 输出JSON
     * 
     * @param jsonObject
     * @throws Exception
     */
    public void responseJSON(JSONObject jsonObject) throws Exception {
        getResponse().getWriter().print(jsonObject.toString());
    }

    /**
     * 输出JSON
     * 
     * @param jsonObject
     * @throws Exception
     */
    public void responseJSON(Object object) throws Exception {
        JSONObject jsonObject = JSONObject.fromObject(object);
        getResponse().getWriter().print(jsonObject.toString());
    }

    public HttpServletRequest getRequest() {
        return ServletActionContext.getRequest();
    }

    public HttpServletResponse getResponse() {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        return response;

    }

    public HttpSession getSession() {
        return ServletActionContext.getRequest().getSession();
    }

    public PageContext getPageContext() {
        return ServletActionContext.getPageContext();
    }

    /**
     * 获取参数值
     * 
     * @param name
     * @return
     */
    public String getRequestParamString(String name) {
        if (name == null) {
            return null;
        }
        return ServletActionContext.getRequest().getParameter(name);
    }

    /**
     * 获取参数值
     * 
     * @param name
     * @return
     */
    public Long getRequestParamLong(String name) {
        if (name == null) {
            return null;
        }
        return Long.valueOf(ServletActionContext.getRequest().getParameter(name));
    }

    /**
     * @param name
     * @param value
     */
    public void setAttributeToRequest(String name, Object value) {
        ServletActionContext.getRequest().setAttribute(name, value);
    }

    /**
     * 公用的页面提示语Request 属性名为：message
     * 
     * @param name
     * @param value
     */
    public void addMessageToRequest(String message) {
        ServletActionContext.getRequest().setAttribute("message", message);
    }

    /**
     * 标志Request 属性名为：flag 一般用于存放区分不同结果的字母或数字
     * 
     * @param name
     * @param value
     */
    public void addFlagToRequest(String flag) {
        ServletActionContext.getRequest().setAttribute("flag", flag);
    }

    /**
     * 标志Request 属性名为：flag 一般用于存放区分不同结果的字母或数字
     * 
     * @param name
     * @param value
     */
    public void addFlagToRequest(int flag) {
        ServletActionContext.getRequest().setAttribute("flag", flag);
    }

    /**
     * @param name
     * @param value
     */
    public void setAttributeToSession(String name, Object value) {
        ServletActionContext.getRequest().getSession().setAttribute(name, value);
    }

    /**
     * 把属性放入request
     * 
     * @param map
     */
    public void addRequestAttribute(Map<String, Object> map) {
        if (map == null) {
            return;
        }
        Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, Object> entry = it.next();
            setAttributeToRequest(entry.getKey(), entry.getValue());
        }
    }

    /**
     * 把属性放入request
     * 
     * @param map
     */
    public void addRequestAttribute(String name, Object value) {
        setAttributeToRequest(name, value);
    }

    /**
     * 把属性放入session
     * 
     * @param map
     */
    public void addSessionAttribute(String name, Object value) {
        setAttributeToSession(name, value);
    }

    public void addErrorToRequest(String errorStr) {
        ActionResult ar = new ActionResult();
        ar.hasError(true);
        ar.setMessage(errorStr);
        addRequestAttribute("actionResult", ar);
    }

    /**
     * 获取站点首页URL
     * 
     * @return
     */
    public String getWebSiteIndexUrl() {
        String serverName = getRequest().getServerName();
        int serverPort = getRequest().getServerPort();
        String contextPath = getRequest().getContextPath();
        String serverPortStr = serverPort == 80 ? "" : ":" + serverPort;
        return "http://" + serverName + serverPortStr + contextPath;
    }

    @SuppressWarnings({ "unused", "unchecked" })
    public String getRequestURL(HttpServletRequest request, String method) {

        if (request == null) {
            return null;
        }

        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String serverPortStr = serverPort == 80 ? "" : ":" + serverPort;
        String indexHead = "http://" + serverName + serverPortStr;

        String url = "";
        url = request.getContextPath();
        url = url + request.getServletPath();

        // 如果是POST方式，则不需要添加后面的参数
        if ("post".equals(method)) {
            return indexHead + url;
        }

        Enumeration<String> names = request.getParameterNames();
        int i = 0;
        String requestPageUrl = "";
        if (names != null) {
            while (names.hasMoreElements()) {
                String name = (String) names.nextElement();
                if (name.equals("requestPageUrl")) {
                    requestPageUrl = request.getParameter(name);
                    continue;
                }
                if (i == 0) {
                    url = url + "?";
                } else {
                    url = url + "&";
                }
                i++;
                String value = request.getParameter(name);
                if (value == null) {
                    value = "";
                }
                url = url + name + "=" + value;
            }
        }
        try {
            java.net.URLEncoder.encode(url, "UTF-8");
        } catch (Exception e) {
            logger.error("", e);
        }
        return indexHead + url; // 将会返回http://localhost:8080/projectName/myPhoto.do?id=888 这样的串
    }

    public ServletContext getApplication() {
        return ServletActionContext.getServletContext();
    }

    public void addApplicationAttribute(String name, Object value) {
        getApplication().setAttribute(name, value);
    }

    /**
     * 把分页的结果放入request
     * 
     * @param pageControler
     */
    @SuppressWarnings("rawtypes")
    public void addPageToRequest(PageControler pageControler) {
        Pager pager = new Pager();
        BeanUtil.copyProperties(pageControler, pager);
        addRequestAttribute("pager", pager);
    }

}
