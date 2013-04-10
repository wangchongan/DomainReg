/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.common.entity;

/**
 * 类JsonResult.java的实现描述：TODO 类实现描述
 * 
 * @author chongan.wangca 2012-3-20 下午2:15:53
 */
public class JsonResult {

    private Integer errorFlag;

    private String  message;

    private Object  resultObj;

    /**
     * 
     */
    public JsonResult(){
    }

    /**
     * @param errorFlag
     * @param message
     */
    public JsonResult(Integer errorFlag, String message){
        this.errorFlag = errorFlag;
        this.message = message;
    }

    public Integer getErrorFlag() {
        return errorFlag;
    }

    public void setErrorFlag(Integer errorFlag) {
        this.errorFlag = errorFlag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResultObj() {
        return resultObj;
    }

    public void setResultObj(Object resultObj) {
        this.resultObj = resultObj;
    }

}
