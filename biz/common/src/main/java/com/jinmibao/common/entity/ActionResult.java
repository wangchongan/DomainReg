/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.common.entity;

/**
 * 类ActionResult.java的实现描述：TODO 类实现描述
 * 
 * @author chongan.wangca 2012-3-16 下午4:48:45
 */
public class ActionResult {

    // 错误标志 0-无错误 1-有错误
    @SuppressWarnings("unused")
    private Integer errorFlag = 0;

    // 特殊标记位，可以用于自定义一些内容用于前端判断展现逻辑
    private String  flag;

    // 错误提示信息
    private String  message;

    public Integer getErrorFlag() {
        return this.errorFlag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void hasError(boolean hasError) {
        if (hasError) {
            this.errorFlag = 1;
        } else {
            this.errorFlag = 0;
        }
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
