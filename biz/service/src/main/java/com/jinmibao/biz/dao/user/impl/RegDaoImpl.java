/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.biz.dao.user.impl;

import com.jinmibao.biz.dao.user.RegDao;
import com.jinmibao.biz.dataobject.user.RegDO;
import com.jinmibao.biz.query.user.RegQuery;
import com.jinmibao.common.base.dataobject.common.base.dao.impl.GenericDaoImpl;

/**
 * 类SysConfigDaoImpl.java的实现描述：TODO 类实现描述
 * 
 * @author chongan.wangca 2012-4-19 上午12:07:34
 */
public class RegDaoImpl extends GenericDaoImpl<RegDO, RegQuery> implements RegDao {

    public static final String NS = "reg";

    /*
     * @see com.taobaiji.base.dao.impl.GenericDaoImpl#getNameSpace()
     */
    @Override
    public String getNameSpace() {
        return NS;
    }

}
