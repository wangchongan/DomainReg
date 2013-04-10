/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.web.entity.user;

/**
 * 类UserDTO.java的实现描述：TODO 类实现描述
 * 
 * @author chongan.wangca 2012-5-3 上午1:51:38
 */
public class UserDTO {

    // 用户ID
    private Long   id;

    // 用户昵称
    private String userNick;

    // 用户邮箱
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
