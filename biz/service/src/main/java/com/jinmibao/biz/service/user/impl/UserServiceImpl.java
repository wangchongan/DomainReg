/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.biz.service.user.impl;

import java.util.Date;

import javax.annotation.Resource;

import com.jinmibao.biz.dataobject.user.RegDO;
import com.jinmibao.biz.dataobject.user.UserDO;
import com.jinmibao.biz.query.user.RegQuery;
import com.jinmibao.biz.query.user.UserQuery;
import com.jinmibao.biz.service.config.UserConfig;
import com.jinmibao.biz.service.user.RegService;
import com.jinmibao.biz.service.user.UserService;
import com.jinmibao.common.base.service.impl.GenericServiceImpl;
import com.jinmibao.common.entity.ActionResult;
import com.jinmibao.common.util.DateUtil;
import com.jinmibao.common.util.RegexUtil;
import com.jinmibao.common.util.StringUtil;

/**
 * 类DomainDeletedServiceImpl.java的实现描述：TODO 类实现描述
 * 
 * @author chongan.wangca 2012-4-19 上午12:04:18
 */
public class UserServiceImpl extends GenericServiceImpl<UserDO, UserQuery> implements UserService {

    @Resource
    private RegService regService;

    /*
     * (non-Javadoc)
     * @see com.jinmibao.biz.service.user.UserService#regEmailSubmit(java.lang.String)
     */
    @Override
    public ActionResult regEmailSubmit(String email, String ip) {

        ActionResult actionResult = new ActionResult();

        if (email == null) {
            actionResult.hasError(true);
            actionResult.setMessage("请输入注册邮箱。");
            return actionResult;
        }

        email = email.trim();

        if (!RegexUtil.isEmail(email)) {
            actionResult.hasError(true);
            actionResult.setMessage("邮箱格式不合法，请重新输入。");
            return actionResult;
        }

        // 校验该邮箱是否被注册过
        UserQuery userQuery = new UserQuery();
        userQuery.setEmail(email);
        UserDO userDO = find(userQuery);
        if (userDO != null) {
            actionResult.hasError(true);
            actionResult.setMessage("该邮箱已被注册过，请换一个注册。");
            return actionResult;
        }

        RegQuery regQuery = new RegQuery();
        regQuery.setEmail(email);
        RegDO regDO = regService.find(regQuery);
        if (regDO == null) {
            regDO = new RegDO();
            String code = StringUtil.getRandStr(10);
            // 这里只是试验打出来语句，实际上该快是使用异步任务去处理发送邮件的，发送完邮件后gmt_send会置上值
            // 发送邮件的任务是会搜索gmt_send为空的进行发送邮件
            boolean sendResult = sendRegEmail(email, code);
            actionResult.setFlag("SUCCESS");
            String newCode = StringUtil.getRandStr(10);
            regDO.setRegIp(ip);
            regDO.setValidCode(newCode);
            regDO.setGmtInvalid(DateUtil.increaseMin(new Date(), UserConfig.REG_CODE_VALID));
            regDO.setEmail(email);
            // 入库
            regService.add(regDO);
            return actionResult;

        } else { // 如果该邮箱已经存在
            // 1、判断是否是未失效邮箱，如果是，则提示已经发送过激活邮件，可以再发送。
            // 2、如果是已经失效的邮箱，则更新激活码以及激活邮件发送时间、IP等字段。
            Date gmtInvalid = regDO.getGmtInvalid();
            // 如果当前时间已经晚于有效时间，则说明链接已经失效
            if (new Date().after(gmtInvalid)) { // 已失效
                String newCode = StringUtil.getRandStr(10);
                boolean sendResult = sendRegEmail(email, newCode);
                actionResult.setFlag("SUCCESS");
                regDO.setRegIp(ip);
                regDO.setValidCode(newCode);
                regDO.setGmtInvalid(DateUtil.increaseMin(new Date(), UserConfig.REG_CODE_VALID));
                // 入库
                regService.update(regDO);
                return actionResult;
            } else { // 还未失效
                actionResult.hasError(true);
                actionResult.setMessage("该邮箱已接收过注册邀请邮件，请到邮箱查收，如未发现，请注意查看是否在垃圾收件箱中。");
                return actionResult;
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see com.jinmibao.biz.service.user.UserService#sendRegEmail(java.lang.String)
     */
    @Override
    public boolean sendRegEmail(String email, String code) {
        email = StringUtil.urlEncode(email);
        System.out.println("http://localhost:8181/jinmibao/user/for_reg.html?email=" + email + "&code=" + code);
        return true;
    }

}
