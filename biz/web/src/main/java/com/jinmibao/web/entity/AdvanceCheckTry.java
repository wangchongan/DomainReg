/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.web.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jinmibao.biz.service.config.DomainConfig;
import com.jinmibao.common.util.ListUtil;
import com.jinmibao.common.util.PermutationUtil;

/**
 * 组合查询试算结果对象
 * 
 * @author chongan.wangca 2012-4-26 上午2:29:12
 */
public class AdvanceCheckTry {

    // 0-位置全组合 1-全排列组合
    private Long                tryType;

    // 所有计算过的域名
    private List<DomainEntity>  tryDomainList;

    // 所有要排列组合的单词
    private List<String>        allWordsList;

    // 所有要排列组合的单词中文意思
    private List<String>        allWordsChineseList;

    // 五位单词
    private List<String>        word1;
    private List<String>        word2;
    private List<String>        word3;
    private List<String>        word4;
    private List<String>        word5;

    // 位置数目
    private Long                placeCount;

    private Long                lengthStart            = 1L;

    private Long                lengthEnd              = 15L;

    private Long                countStart             = 1L;

    private Long                countEnd               = 8L;

    // 域名后缀
    private List<String>        domainTypeList;

    // 已经存在的
    private Map<String, String> alreadyExistsDomainMap = new HashMap<String, String>();

    /**
     * 获取所有计算过的域名，含后缀
     */
    public List<DomainEntity> calcTryDomainPerm() {
        if (tryType.longValue() == 0) { // 位置类型
            this.tryDomainList = getAllTryDomainPlaceType();
            return tryDomainList;
        } else if (tryType.longValue() == 1) { // 所有全排列
            this.tryDomainList = getAllTryDomainAllType();
            return tryDomainList;
        }
        this.tryDomainList = null;
        return null;
    }

    /**
     * 当位置类型时候，获取所有排列后域名
     * 
     * @return
     */
    private List<DomainEntity> getAllTryDomainPlaceType() {
        return null;
    }

    /**
     * 当所有全排列类型时候，获取所有排列后域名
     * 
     * @return
     */
    private List<DomainEntity> getAllTryDomainAllType() {
        int allWordsCount = allWordsList.size();

        if (allWordsCount < DomainConfig.ADVANCE_CHECK_MIN_ALL_WORDS_COUNT) {
            throw new RuntimeException("亲！组合的单词不能小于" + DomainConfig.ADVANCE_CHECK_MIN_ALL_WORDS_COUNT + "个哦。");
        }

        if (allWordsCount > DomainConfig.ADVANCE_CHECK_MAX_ALL_WORDS_COUNT) {
            throw new RuntimeException("亲！组合的单词不能大于" + DomainConfig.ADVANCE_CHECK_MAX_ALL_WORDS_COUNT + "个哦。");
        }

        String orginal = "";

        for (int i = 0; i < allWordsCount; i++) {
            orginal = orginal + i;
        }

        PermutationUtil permutationUtil = new PermutationUtil();
        permutationUtil.dealPerm(orginal);

        List<String> permList = permutationUtil.getPermList();

        List<DomainEntity> newPermList = new ArrayList<DomainEntity>();
        for (String p : permList) {
            PermCalcResult permCalcResult = replacePerm(p);
            if (alreadyExistsDomainMap.containsKey(permCalcResult.getDomain())) {
                continue;
            } else {
                alreadyExistsDomainMap.put(permCalcResult.getDomain(), null);
            }

            for (String domainType : domainTypeList) {
                // 要在需要的长度范围内才计算在内
                if (permCalcResult.getWordLength() >= countStart && permCalcResult.getWordLength() <= countEnd
                    && permCalcResult.getDomainLength() >= lengthStart && permCalcResult.getDomainLength() <= lengthEnd) {
                    DomainEntity de = new DomainEntity();
                    de.setDomainName(permCalcResult.getDomain());
                    de.setDomainType(domainType);
                    de.setDomainChinese(permCalcResult.getDomainChinese());
                    de.setWordLength(permCalcResult.getWordLength());
                    de.setDomainLength(permCalcResult.getDomainLength());
                    newPermList.add(de);
                }
            }
        }

        // Let GC work!
        alreadyExistsDomainMap = null;

        return newPermList;
    }

    /**
     * 根据全排列，替换为域名 比如把 012 替换为 taobaiji,并且返回具体信息
     * 
     * @param perm
     * @return
     */
    private PermCalcResult replacePerm(String perm) {
        PermCalcResult permCalcResult = new PermCalcResult();
        List<String> joinList = new ArrayList<String>();
        List<String> joinChineseList = new ArrayList<String>();
        for (int i = 0; i < perm.length(); i++) {
            String word = allWordsList.get(Integer.valueOf(String.valueOf(perm.charAt(i))));
            joinList.add(word);
            joinChineseList.add(allWordsChineseList.get(Integer.valueOf(String.valueOf(perm.charAt(i)))));
            permCalcResult.getEveryWordList().add(word);
        }
        // 单词个数
        permCalcResult.setWordLength(perm.length());
        // 域名
        permCalcResult.setDomain(ListUtil.joinList2Str(joinList, ""));
        // 中文
        permCalcResult.setDomainChinese(ListUtil.joinList2Str(joinChineseList, ""));
        // 总长度
        permCalcResult.setDomainLength(permCalcResult.getDomain().length());
        return permCalcResult;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public Long getTryType() {
        return tryType;
    }

    public void setTryType(Long tryType) {
        this.tryType = tryType;
    }

    public List<DomainEntity> getTryDomainList() {
        return tryDomainList;
    }

    public void setTryDomainList(List<DomainEntity> tryDomainList) {
        this.tryDomainList = tryDomainList;
    }

    public List<String> getAllWordsList() {
        return allWordsList;
    }

    public void setAllWordsList(List<String> allWordsList) {
        this.allWordsList = allWordsList;
    }

    public List<String> getWord1() {
        return word1;
    }

    public void setWord1(List<String> word1) {
        this.word1 = word1;
    }

    public List<String> getWord2() {
        return word2;
    }

    public void setWord2(List<String> word2) {
        this.word2 = word2;
    }

    public List<String> getWord3() {
        return word3;
    }

    public void setWord3(List<String> word3) {
        this.word3 = word3;
    }

    public List<String> getWord4() {
        return word4;
    }

    public void setWord4(List<String> word4) {
        this.word4 = word4;
    }

    public List<String> getWord5() {
        return word5;
    }

    public void setWord5(List<String> word5) {
        this.word5 = word5;
    }

    public Long getPlaceCount() {
        return placeCount;
    }

    public void setPlaceCount(Long placeCount) {
        this.placeCount = placeCount;
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

    public List<String> getDomainTypeList() {
        return domainTypeList;
    }

    public void setDomainTypeList(List<String> domainTypeList) {
        this.domainTypeList = domainTypeList;
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

    public List<String> getAllWordsChineseList() {
        return allWordsChineseList;
    }

    public void setAllWordsChineseList(List<String> allWordsChineseList) {
        this.allWordsChineseList = allWordsChineseList;
    }

    public Map<String, String> getAlreadyExistsDomainMap() {
        return alreadyExistsDomainMap;
    }

    public void setAlreadyExistsDomainMap(Map<String, String> alreadyExistsDomainMap) {
        this.alreadyExistsDomainMap = alreadyExistsDomainMap;
    }

}
