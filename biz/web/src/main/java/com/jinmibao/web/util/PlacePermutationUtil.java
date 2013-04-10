/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.web.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.jinmibao.web.entity.DomainEntity;

/**
 * 按位置的全排列组合的计算器
 * 
 * @author chongan.wangca 2012-5-1 上午1:22:21
 */
public class PlacePermutationUtil {

    private Map<String, String>      alreadyExistsDomainMap = new ConcurrentHashMap<String, String>();

    // 位数
    private Integer                  placeCount;

    // 单词
    private List<List<String>>       placeWordList;

    // 单词对应中文含义
    private List<List<String>>       placeWordChineseList;

    // 域名后缀
    private List<String>             domainTypeList;

    // 单词和中文意思组合得到对象
    private List<List<DomainEntity>> domainEntityList       = new ArrayList<List<DomainEntity>>();

    public List<DomainEntity> getResult() {

        // 初始化permCalcResultList
        this.initPermCalcResultList();

        List<DomainEntity> calcResultList = calcIterator(domainEntityList.get(0), domainEntityList.get(1));
        int i = 2;
        while (i < placeCount) {
            calcResultList = calcIterator(calcResultList, domainEntityList.get(i));
            i++;
        }

        List<DomainEntity> finalDomainList = new ArrayList<DomainEntity>();

        for (DomainEntity domainEntity : calcResultList) {
            for (String domainType : domainTypeList) {
                DomainEntity newDomainEntity = new DomainEntity();
                newDomainEntity.setDomainName(domainEntity.getDomainName());
                newDomainEntity.setDomainType(domainType);
                newDomainEntity.setDomainChinese(domainEntity.getDomainChinese());
                newDomainEntity.setWordLength(placeCount);
                newDomainEntity.setDomainLength(domainEntity.getDomainName().length());
                finalDomainList.add(newDomainEntity);
            }
        }

        return finalDomainList;
    }

    /**
     * 初始化permCalcResultList
     */
    private void initPermCalcResultList() {

        for (int i = 0; i < placeWordList.size(); i++) {

            List<String> placeWords = placeWordList.get(i);
            List<String> placeWordsChinese = null;
            if (placeWordChineseList != null && placeWordChineseList.size() > 0) {
                placeWordsChinese = placeWordChineseList.get(i);
            }

            List<DomainEntity> domainEntitys = new ArrayList<DomainEntity>();
            for (int j = 0; j < placeWords.size(); j++) {
                DomainEntity domainEntity = new DomainEntity();
                domainEntity.setDomainName(placeWords.get(j));
                if (placeWordsChinese != null) {
                    domainEntity.setDomainChinese(placeWordsChinese.get(j));
                } else {
                    // 如果不存在则使用空串代替
                    domainEntity.setDomainChinese("");
                }
                domainEntitys.add(domainEntity);
            }
            this.domainEntityList.add(domainEntitys);
        }
    }

    /**
     * 两两计算
     * 
     * @param firstList
     * @param endList
     * @return
     */
    private List<DomainEntity> calcIterator(List<DomainEntity> firstList, List<DomainEntity> endList) {
        List<DomainEntity> newPermCalcResultList = new ArrayList<DomainEntity>();
        for (DomainEntity firstPermCalcResult : firstList) {
            for (DomainEntity endPermCalcResult : endList) {
                DomainEntity permCalcResult = new DomainEntity();
                String domain = firstPermCalcResult.getDomainName() + endPermCalcResult.getDomainName();
                if (alreadyExistsDomainMap.containsKey(domain)) {
                    continue;
                } else {
                    alreadyExistsDomainMap.put(domain, domain);
                }
                permCalcResult.setDomainName(domain);
                permCalcResult.setDomainChinese(firstPermCalcResult.getDomainChinese()
                                                + endPermCalcResult.getDomainChinese());
                newPermCalcResultList.add(permCalcResult);
            }
        }
        return newPermCalcResultList;
    }

    public void setPlaceCount(Integer placeCount) {
        this.placeCount = placeCount;
    }

    public void setPlaceWordList(List<List<String>> placeWordList) {
        this.placeWordList = placeWordList;
    }

    public void setPlaceWordChineseList(List<List<String>> placeWordChineseList) {
        this.placeWordChineseList = placeWordChineseList;
    }

    public List<String> getDomainTypeList() {
        return domainTypeList;
    }

    public void setDomainTypeList(List<String> domainTypeList) {
        this.domainTypeList = domainTypeList;
    }

    public static void main(String[] args) {
        PlacePermutationUtil placePermutationUtil = new PlacePermutationUtil();
        placePermutationUtil.setPlaceCount(3);

        List<List<String>> wordList = new ArrayList<List<String>>();
        List<String> word1 = new ArrayList<String>();
        word1.add("tao");
        word1.add("le");
        wordList.add(word1);
        List<String> word2 = new ArrayList<String>();
        word2.add("bao");
        word2.add("hao");
        word2.add("fu");
        wordList.add(word2);
        List<String> word3 = new ArrayList<String>();
        word3.add("mei");
        word3.add("you");
        word3.add("ji");
        wordList.add(word3);

        List<List<String>> wordChineseList = new ArrayList<List<String>>();
        List<String> word1Chinese = new ArrayList<String>();
        word1Chinese.add("淘");
        word1Chinese.add("乐");
        wordChineseList.add(word1Chinese);
        List<String> word2Chinese = new ArrayList<String>();
        word2Chinese.add("宝");
        word2Chinese.add("好");
        word2Chinese.add("付");
        wordChineseList.add(word2Chinese);
        List<String> word3Chinese = new ArrayList<String>();
        word3Chinese.add("美");
        word3Chinese.add("优");
        word3Chinese.add("技");
        wordChineseList.add(word3Chinese);

        placePermutationUtil.setPlaceWordList(wordList);
        placePermutationUtil.setPlaceWordChineseList(wordChineseList);

        List<DomainEntity> permCalcResults = placePermutationUtil.getResult();
        for (DomainEntity permCalcResult : permCalcResults) {
            System.out.println(permCalcResult.getDomainName() + "===" + permCalcResult.getDomainChinese());
        }
    }

}
