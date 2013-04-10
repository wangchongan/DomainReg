package com.jinmibao.biz.dataobject.user;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据对象
 * 
 * @since 2012-05-02
 * @author wangchongan
 */
public class RegDO implements Serializable {

    private static final long serialVersionUID = 133597358214712424L;

    /**
     * column jmb_reg.ID
     */
    private Long              id;

    /**
     * column jmb_reg.gmt_create
     */
    private Date              gmtCreate;

    /**
     * column jmb_reg.reg_ip
     */
    private String            regIp;

    /**
     * column jmb_reg.email
     */
    private String            email;

    /**
     * column jmb_reg.mobile
     */
    private String            mobile;

    /**
     * column jmb_reg.gmt_invalid
     */
    private Date              gmtInvalid;

    private Date              gmtSend;

    /**
     * column jmb_reg.valid_code
     */
    private String            validCode;

    public RegDO(){
        super();
    }

    /**
     * getter for Column jmb_reg.ID
     */
    public Long getId() {
        return id;
    }

    /**
     * setter for Column jmb_reg.ID
     * 
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * getter for Column jmb_reg.gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * setter for Column jmb_reg.gmt_create
     * 
     * @param gmtCreate
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * getter for Column jmb_reg.reg_ip
     */
    public String getRegIp() {
        return regIp;
    }

    /**
     * setter for Column jmb_reg.reg_ip
     * 
     * @param regIp
     */
    public void setRegIp(String regIp) {
        this.regIp = regIp;
    }

    /**
     * getter for Column jmb_reg.email
     */
    public String getEmail() {
        return email;
    }

    /**
     * setter for Column jmb_reg.email
     * 
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * getter for Column jmb_reg.mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * setter for Column jmb_reg.mobile
     * 
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * getter for Column jmb_reg.gmt_invalid
     */
    public Date getGmtInvalid() {
        return gmtInvalid;
    }

    /**
     * setter for Column jmb_reg.gmt_invalid
     * 
     * @param gmtInvalid
     */
    public void setGmtInvalid(Date gmtInvalid) {
        this.gmtInvalid = gmtInvalid;
    }

    /**
     * getter for Column jmb_reg.valid_code
     */
    public String getValidCode() {
        return validCode;
    }

    /**
     * setter for Column jmb_reg.valid_code
     * 
     * @param validCode
     */
    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }

    public Date getGmtSend() {
        return gmtSend;
    }

    public void setGmtSend(Date gmtSend) {
        this.gmtSend = gmtSend;
    }
}
