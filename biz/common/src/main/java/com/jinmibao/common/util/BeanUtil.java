/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.common.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 * 类BeanUtil.java的实现描述：TODO 类实现描述
 * 
 * @author chongan.wangca 2012-3-30 下午8:23:10
 */
public class BeanUtil {

    private final static Logger logger = LoggerFactory.getLogger(BeanUtil.class);

    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }

    /**
     * 复制属性。 当目标对象里面的属性的值为空时候才会从源对象中复制过去，如果目标对象中的字段原先已经 有值，则不会覆盖
     * 
     * @param source
     * @param target
     */
    public static void copyPropertiesIfNull(Object source, Object target) {
        if (source == null || target == null) {
            throw new RuntimeException("Source Or Target Can't Be Null");
        }
        Method[] methods = target.getClass().getDeclaredMethods();
        List<String> notNullPropertyList = new ArrayList<String>();
        for (Method method : methods) {
            if (!method.getName().startsWith("get")) {
                continue;
            }
            try {
                Object methodResult = method.invoke(target);
                if (methodResult != null) {
                    if (method.getReturnType().getName().equals("java.lang.String")
                        && StringUtil.isBlank((String) methodResult)) {
                        continue;
                    }
                    notNullPropertyList.add(BeanUtils.findPropertyForMethod(method).getName());
                }
            } catch (Exception e) {
                logger.error("", e);
            }
        }
        BeanUtils.copyProperties(source, target, ListUtil.list2Array(notNullPropertyList));
    }
}
