/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.common.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 邮件数据对象
 * 
 * @author chongan.wangca
 */
public class Mail implements Serializable {

    private static final long serialVersionUID   = 7012261845618351104L;

    /**
     * 编码格式
     */
    private String            charset            = "GBK";

    /**
     * 收件人、抄送、密送、回复地址
     */
    private String[]          toAddress;
    private String[]          ccAddress;
    private String[]          bccAddress;
    private String[]          replyToAddress;
    // 附件的绝对路径
    private String[]          attachmentsPath;

    // 是否需要在发送邮件完毕后删除附件
    private boolean           needDelAttachments = false;

    /**
     * 发送时间
     */
    private Date              gmtSent;

    /**
     * 主题
     */
    private String            subject;

    /**
     * 内容
     */
    private String            content;

    /**
     * 内容类型，例如：text/html、text/plain
     */
    private String            contentType;

    public String[] getBccAddress() {
        return bccAddress;
    }

    public void setBccAddress(String[] bccAddress) {
        this.bccAddress = bccAddress;
    }

    public String[] getCcAddress() {
        return ccAddress;
    }

    public void setCcAddress(String[] ccAddress) {
        this.ccAddress = ccAddress;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Date getGmtSent() {
        return gmtSent;
    }

    public void setGmtSent(Date gmtSent) {
        this.gmtSent = gmtSent;
    }

    public String[] getReplyToAddress() {
        return replyToAddress;
    }

    public void setReplyToAddress(String[] replyToAddress) {
        this.replyToAddress = replyToAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String[] getToAddress() {
        return toAddress;
    }

    public void setToAddress(String[] toAddress) {
        this.toAddress = toAddress;
    }

    public String[] getAttachmentsPath() {
        return attachmentsPath;
    }

    public void setAttachmentsPath(String[] attachmentsPath) {
        this.attachmentsPath = attachmentsPath;
    }

    public boolean isNeedDelAttachments() {
        return needDelAttachments;
    }

    public void setNeedDelAttachments(boolean needDelAttachments) {
        this.needDelAttachments = needDelAttachments;
    }

}
