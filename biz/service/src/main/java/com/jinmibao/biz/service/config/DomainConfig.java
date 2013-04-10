/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.biz.service.config;

/**
 * 类DomainConfig.java的实现描述：TODO 类实现描述
 * 
 * @author chongan.wangca 2012-4-28 上午12:40:09
 */
public class DomainConfig {

    // 高级查询全排列类型时候最多一次查询条数
    public final static Long ADVANCE_CHECK_MAX_COUNT_ALL         = 2000L;

    // 高级查询按位置查询时候最多一次查询条数
    public final static Long ADVANCE_CHECK_MAX_COUNT_PLACE       = 200L;

    // 高级查询全排列时候按总共单词数-最大
    public final static Long ADVANCE_CHECK_MAX_ALL_WORDS_COUNT   = 10L;

    // 高级查询全排列时候按总共单词数-最小
    public final static Long ADVANCE_CHECK_MIN_ALL_WORDS_COUNT   = 2L;

    // 组合查询，按位置查询时，总共单词个数最多数量
    public final static Long ADVANCE_CHECK_PLACE_WORD_MAX_COUNT  = 25L;

    // 组合查询，按位置查询时，最大的位置数量
    public final static Long ADVANCE_CHECK_PLACE_COUNT_MAX_COUNT = 5L;

    // 组合查询，按位置查询时，最小的位置数量
    public final static Long ADVANCE_CHECK_PLACE_COUNT_MIN_COUNT = 2L;

}
