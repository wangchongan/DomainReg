/**
 * Project: lp4pl.biz.logistics
 * 
 * File Created at 2012-2-7
 * $Id$
 * 
 * Copyright 1999-2100 Alibaba.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Alibaba Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Alibaba.com.
 */
package com.jinmibao.common.util;

import java.util.Date;

/**
 * 时间片段
 * 
 * @author kailong.zhangkl
 */
public class TimeUnit {
    /**
     * 时间片段起始时间
     */
    private Date gmtStart;
    /**
     * 时间片段结束时间
     */
    private Date gmtEnd;

    public TimeUnit(Date gmtStart, Date gmtEnd) {
        this.gmtStart = gmtStart;
        this.gmtEnd = gmtEnd;
    }

    public Date getGmtStart() {
        return gmtStart;
    }

    public void setGmtStart(Date gmtStart) {
        this.gmtStart = gmtStart;
    }

    public Date getGmtEnd() {
        return gmtEnd;
    }

    public void setGmtEnd(Date gmtEnd) {
        this.gmtEnd = gmtEnd;
    }

}
