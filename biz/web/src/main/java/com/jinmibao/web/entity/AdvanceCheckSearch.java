/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.web.entity;

/**
 * 类AdvanceCheckSearch.java的实现描述：TODO 类实现描述
 * 
 * @author chongan.wangca 2012-4-26 上午12:21:26
 */
public class AdvanceCheckSearch {

    // 0-按位置组合模式，1-全组合模式
    private Long   checkType   = 0L;

    private Long   lengthStart = 2L;

    private Long   lengthEnd   = 10L;

    private Long   countStart  = 2L;

    private Long   countEnd    = 3L;

    // 按位置排列的单词，使用|拼接的
    private String wordStr;

    // 按位置排列的单词的中文含义，使用|拼接
    private String wordChineseStr;

    // 位置个数
    private Long   placeCount;

    private String allWords;

    private String allWordsChinese;

    private String domainType;

    public Long getCheckType() {
        return checkType;
    }

    public void setCheckType(Long checkType) {
        this.checkType = checkType;
    }

    public Long getLengthStart() {
        return lengthStart;
    }

    public void setLengthStart(Long lengthStart) {
        this.lengthStart = lengthStart;
    }

    public Long getLengthEnd() {
        return lengthEnd;
    }

    public void setLengthEnd(Long lengthEnd) {
        this.lengthEnd = lengthEnd;
    }

    public String getWordStr() {
        return wordStr;
    }

    public void setWordStr(String wordStr) {
        this.wordStr = wordStr;
    }

    public String getAllWords() {
        return allWords;
    }

    public void setAllWords(String allWords) {
        this.allWords = allWords;
    }

    public String getDomainType() {
        return domainType;
    }

    public void setDomainType(String domainType) {
        this.domainType = domainType;
    }

    public Long getCountStart() {
        return countStart;
    }

    public void setCountStart(Long countStart) {
        this.countStart = countStart;
    }

    public Long getCountEnd() {
        return countEnd;
    }

    public void setCountEnd(Long countEnd) {
        this.countEnd = countEnd;
    }

    public String getAllWordsChinese() {
        return allWordsChinese;
    }

    public void setAllWordsChinese(String allWordsChinese) {
        this.allWordsChinese = allWordsChinese;
    }

    public Long getPlaceCount() {
        return placeCount;
    }

    public void setPlaceCount(Long placeCount) {
        this.placeCount = placeCount;
    }

    public String getWordChineseStr() {
        return wordChineseStr;
    }

    public void setWordChineseStr(String wordChineseStr) {
        this.wordChineseStr = wordChineseStr;
    }

}
