/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.biz.service.user;

import com.jinmibao.biz.dataobject.user.UserDO;
import com.jinmibao.biz.query.user.UserQuery;
import com.jinmibao.common.base.service.GenericService;
import com.jinmibao.common.entity.ActionResult;

/**
 * 类DomainDeletedService.java的实现描述：TODO 类实现描述
 * 
 * @author chongan.wangca 2012-4-18 下午11:59:51
 */
public interface UserService extends GenericService<UserDO, UserQuery> {

    /**
     * 提交用户注册邮箱，负责发送注册链接到用户邮箱 如果出现错误则对应提示
     * 
     * @param email
     * @return
     */
    public ActionResult regEmailSubmit(String email, String ip);

    /**
     * 给用户发送注册链接到邮箱中
     * 
     * @param email
     * @return
     */
    public boolean sendRegEmail(String email, String code);

}
