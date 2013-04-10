/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.web.user;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jinmibao.biz.service.user.RegService;
import com.jinmibao.biz.service.user.UserService;
import com.jinmibao.biz.web.util.ActionUtil;
import com.jinmibao.common.entity.ActionResult;
import com.jinmibao.common.util.StringUtil;
import com.jinmibao.web.entity.user.UserDTO;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 用户注册ACTION处理类
 * 
 * @author chongan.wangca 2012-4-19 上午1:34:14
 */
public class UserRegAction extends ActionSupport {

    private final static Logger logger           = LoggerFactory.getLogger(UserRegAction.class);

    @Resource
    private ActionUtil          actionUtil;

    private UserDTO             userDTO;

    private String              email;

    @Resource
    private RegService          regService;

    @Resource
    private UserService         userService;

    /**
     * 
     */
    private static final long   serialVersionUID = -5153839172612629181L;

    /**
     * 用户提交邮箱进行申请注册链接
     */
    public void regEmailSubmit() {
        ActionResult actionResult = new ActionResult();
        actionResult = userService.regEmailSubmit(email, StringUtil.getIpAddr(actionUtil.getRequest()));
        try {
            actionUtil.responseJSON(actionResult);
            return;
        } catch (Exception e) {
            logger.error("Response JSON Error.", e);
        }
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
