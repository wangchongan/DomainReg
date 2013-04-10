package com.jinmibao.biz.dataobject.user;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据对象
 * @since 2012-05-02
 * @author wangchongan
 */
public class UserDO implements Serializable {

    private static final long serialVersionUID = 133597358536259496L;

    /**
     * column jmb_user.id
     */
    private Long id;

    /**
     * column jmb_user.gmt_create
     */
    private Date gmtCreate;

    /**
     * column jmb_user.gmt_modified
     */
    private Date gmtModified;

    /**
     * column jmb_user.creator
     */
    private Long creator;

    /**
     * column jmb_user.modifier
     */
    private Long modifier;

    /**
     * column jmb_user.user_nick
     */
    private String userNick;

    /**
     * column jmb_user.user_psw
     */
    private String userPsw;

    /**
     * column jmb_user.email
     */
    private String email;

    /**
     * column jmb_user.reg_ip
     */
    private String regIp;

    /**
     * column jmb_user.mobile
     */
    private String mobile;

    /**
     * column jmb_user.has_buy_app
     */
    private String hasBuyApp;

    /**
     * column jmb_user.gmt_last_login
     */
    private Date gmtLastLogin;

    /**
     * column jmb_user.last_login_ip
     */
    private String lastLoginIp;

    /**
     * column jmb_user.gmt_current_login
     */
    private Date gmtCurrentLogin;

    /**
     * column jmb_user.current_login_ip
     */
    private String currentLoginIp;

    /**
     * column jmb_user.login_count
     */
    private Long loginCount;

    /**
     * column jmb_user.website_url
     */
    private String websiteUrl;

    /**
     * column jmb_user.status  NORMAL - 正常账户
            LOCKED - 被锁定
     */
    private String status;

    public UserDO() {
        super();
    }

    public UserDO(Long id, Date gmtCreate, Date gmtModified, Long creator, Long modifier, String userNick, String userPsw, String email, String regIp, String mobile, String hasBuyApp, Date gmtLastLogin, String lastLoginIp, Date gmtCurrentLogin, String currentLoginIp, Long loginCount, String websiteUrl, String status) {
        this.id = id;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.creator = creator;
        this.modifier = modifier;
        this.userNick = userNick;
        this.userPsw = userPsw;
        this.email = email;
        this.regIp = regIp;
        this.mobile = mobile;
        this.hasBuyApp = hasBuyApp;
        this.gmtLastLogin = gmtLastLogin;
        this.lastLoginIp = lastLoginIp;
        this.gmtCurrentLogin = gmtCurrentLogin;
        this.currentLoginIp = currentLoginIp;
        this.loginCount = loginCount;
        this.websiteUrl = websiteUrl;
        this.status = status;
    }

    /**
     * getter for Column jmb_user.id
     */
    public Long getId() {
        return id;
    }

    /**
     * setter for Column jmb_user.id
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * getter for Column jmb_user.gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * setter for Column jmb_user.gmt_create
     * @param gmtCreate
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * getter for Column jmb_user.gmt_modified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * setter for Column jmb_user.gmt_modified
     * @param gmtModified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * getter for Column jmb_user.creator
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * setter for Column jmb_user.creator
     * @param creator
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }

    /**
     * getter for Column jmb_user.modifier
     */
    public Long getModifier() {
        return modifier;
    }

    /**
     * setter for Column jmb_user.modifier
     * @param modifier
     */
    public void setModifier(Long modifier) {
        this.modifier = modifier;
    }

    /**
     * getter for Column jmb_user.user_nick
     */
    public String getUserNick() {
        return userNick;
    }

    /**
     * setter for Column jmb_user.user_nick
     * @param userNick
     */
    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    /**
     * getter for Column jmb_user.user_psw
     */
    public String getUserPsw() {
        return userPsw;
    }

    /**
     * setter for Column jmb_user.user_psw
     * @param userPsw
     */
    public void setUserPsw(String userPsw) {
        this.userPsw = userPsw;
    }

    /**
     * getter for Column jmb_user.email
     */
    public String getEmail() {
        return email;
    }

    /**
     * setter for Column jmb_user.email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * getter for Column jmb_user.reg_ip
     */
    public String getRegIp() {
        return regIp;
    }

    /**
     * setter for Column jmb_user.reg_ip
     * @param regIp
     */
    public void setRegIp(String regIp) {
        this.regIp = regIp;
    }

    /**
     * getter for Column jmb_user.mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * setter for Column jmb_user.mobile
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * getter for Column jmb_user.has_buy_app
     */
    public String getHasBuyApp() {
        return hasBuyApp;
    }

    /**
     * setter for Column jmb_user.has_buy_app
     * @param hasBuyApp
     */
    public void setHasBuyApp(String hasBuyApp) {
        this.hasBuyApp = hasBuyApp;
    }

    /**
     * getter for Column jmb_user.gmt_last_login
     */
    public Date getGmtLastLogin() {
        return gmtLastLogin;
    }

    /**
     * setter for Column jmb_user.gmt_last_login
     * @param gmtLastLogin
     */
    public void setGmtLastLogin(Date gmtLastLogin) {
        this.gmtLastLogin = gmtLastLogin;
    }

    /**
     * getter for Column jmb_user.last_login_ip
     */
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    /**
     * setter for Column jmb_user.last_login_ip
     * @param lastLoginIp
     */
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    /**
     * getter for Column jmb_user.gmt_current_login
     */
    public Date getGmtCurrentLogin() {
        return gmtCurrentLogin;
    }

    /**
     * setter for Column jmb_user.gmt_current_login
     * @param gmtCurrentLogin
     */
    public void setGmtCurrentLogin(Date gmtCurrentLogin) {
        this.gmtCurrentLogin = gmtCurrentLogin;
    }

    /**
     * getter for Column jmb_user.current_login_ip
     */
    public String getCurrentLoginIp() {
        return currentLoginIp;
    }

    /**
     * setter for Column jmb_user.current_login_ip
     * @param currentLoginIp
     */
    public void setCurrentLoginIp(String currentLoginIp) {
        this.currentLoginIp = currentLoginIp;
    }

    /**
     * getter for Column jmb_user.login_count
     */
    public Long getLoginCount() {
        return loginCount;
    }

    /**
     * setter for Column jmb_user.login_count
     * @param loginCount
     */
    public void setLoginCount(Long loginCount) {
        this.loginCount = loginCount;
    }

    /**
     * getter for Column jmb_user.website_url
     */
    public String getWebsiteUrl() {
        return websiteUrl;
    }

    /**
     * setter for Column jmb_user.website_url
     * @param websiteUrl
     */
    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    /**
     * getter for Column jmb_user.status
     */
    public String getStatus() {
        return status;
    }

    /**
     * setter for Column jmb_user.status
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

}