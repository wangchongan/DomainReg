package com.jinmibao.biz.query.common;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据对象
 * @since 2012-04-18
 * @author wangchongan
 */
public class SysConfigQuery implements Serializable {

    private static final long serialVersionUID = 133476400909284749L;

    /**
     * column jmb_sys_config.id
     */
    private Long id;

    /**
     * column jmb_sys_config.gmt_create
     */
    private Date gmtCreate;

    /**
     * column jmb_sys_config.gmt_modified
     */
    private Date gmtModified;

    /**
     * column jmb_sys_config.name
     */
    private String name;

    /**
     * column jmb_sys_config.value
     */
    private String value;

    /**
     * column jmb_sys_config.group_name
     */
    private String groupName;

    /**
     * column jmb_sys_config.remark
     */
    private String remark;

    public SysConfigQuery() {
        super();
    }

    public SysConfigQuery(Long id, Date gmtCreate, Date gmtModified, String name, String value, String groupName, String remark) {
        this.id = id;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.name = name;
        this.value = value;
        this.groupName = groupName;
        this.remark = remark;
    }

    /**
     * getter for Column jmb_sys_config.id
     */
    public Long getId() {
        return id;
    }

    /**
     * setter for Column jmb_sys_config.id
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * getter for Column jmb_sys_config.gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * setter for Column jmb_sys_config.gmt_create
     * @param gmtCreate
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * getter for Column jmb_sys_config.gmt_modified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * setter for Column jmb_sys_config.gmt_modified
     * @param gmtModified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * getter for Column jmb_sys_config.name
     */
    public String getName() {
        return name;
    }

    /**
     * setter for Column jmb_sys_config.name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter for Column jmb_sys_config.value
     */
    public String getValue() {
        return value;
    }

    /**
     * setter for Column jmb_sys_config.value
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * getter for Column jmb_sys_config.group_name
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * setter for Column jmb_sys_config.group_name
     * @param groupName
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * getter for Column jmb_sys_config.remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * setter for Column jmb_sys_config.remark
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

}