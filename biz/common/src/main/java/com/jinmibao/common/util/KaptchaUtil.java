package com.jinmibao.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 验证码校验工具
 */
public class KaptchaUtil extends ActionSupport {

    /**
     * 
     */
    private static final long   serialVersionUID = 3124789334657516979L;
    private final static Logger logger           = LoggerFactory.getLogger(KaptchaUtil.class);

    public boolean checkKaptcha(String kaptcha) {

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        String kap = (String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        /*
         * 防止验证出错的情况，如果取到session中验证码为空，则使用一个在页面端 无法获得的字符串伪装验证码，以免验证出错，让客户端重新获取新的验证码
         */
        if (kap == null) {
            kap = "#";
        }

        // 移除验证码对象
        session.removeAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);

        // 如果验证码不正确,不区分大小写，统一以小写匹配
        if (kaptcha != null && kap.toLowerCase().equals(kaptcha.toLowerCase())) {
            return true;
        } else {
            return false;
        }
    }
}
