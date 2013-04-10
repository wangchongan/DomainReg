package com.jinmibao.biz.query.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据对象
 * 
 * @since 2012-04-20
 * @author wangchongan
 */
public class PinyinQuery implements Serializable {

    private static final long serialVersionUID = 133492678224067474L;

    /**
     * column jmb_pinyin.id
     */
    private Long              id;

    /**
     * column jmb_pinyin.gmt_create
     */
    private Date              gmtCreate;

    /**
     * column jmb_pinyin.gmt_modified
     */
    private Date              gmtModified;

    /**
     * column jmb_pinyin.pinyin
     */
    private String            pinyin;

    /**
     * column jmb_pinyin.chinese
     */
    private String            chinese;

    /**
     * column jmb_pinyin.start_with
     */
    private String            startWith;

    /**
     * column jmb_pinyin.all_length
     */
    private Long              allLength;

    /**
     * column jmb_pinyin.is_yuanyin
     */
    private String            isYuanyin;

    /**
     * column jmb_pinyin.num
     */
    private Long              num;

    /**
     * column jmb_pinyin.first
     */
    private Long              first;

    /**
     * column jmb_pinyin.first_pinyin
     */
    private String            firstPinyin;

    /**
     * column jmb_pinyin.second
     */
    private Long              second;

    /**
     * column jmb_pinyin.second_pinyin
     */
    private String            secondPinyin;

    /**
     * column jmb_pinyin.third
     */
    private Long              third;

    /**
     * column jmb_pinyin.third_pinyin
     */
    private String            thirdPinyin;

    /**
     * column jmb_pinyin.fourth
     */
    private Long              fourth;

    /**
     * column jmb_pinyin.fourth_pinyin
     */
    private String            fourthPinyin;

    /**
     * column jmb_pinyin.hot
     */
    private Long              hot;

    public PinyinQuery(){
        super();
    }

    public PinyinQuery(Long id, Date gmtCreate, Date gmtModified, String pinyin, String chinese, String startWith,
                       Long allLength, String isYuanyin, Long num, Long first, String firstPinyin, Long second,
                       String secondPinyin, Long third, String thirdPinyin, Long fourth, String fourthPinyin, Long hot){
        this.id = id;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.pinyin = pinyin;
        this.chinese = chinese;
        this.startWith = startWith;
        this.allLength = allLength;
        this.isYuanyin = isYuanyin;
        this.num = num;
        this.first = first;
        this.firstPinyin = firstPinyin;
        this.second = second;
        this.secondPinyin = secondPinyin;
        this.third = third;
        this.thirdPinyin = thirdPinyin;
        this.fourth = fourth;
        this.fourthPinyin = fourthPinyin;
        this.hot = hot;
    }

    /**
     * getter for Column jmb_pinyin.id
     */
    public Long getId() {
        return id;
    }

    /**
     * setter for Column jmb_pinyin.id
     * 
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * getter for Column jmb_pinyin.gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * setter for Column jmb_pinyin.gmt_create
     * 
     * @param gmtCreate
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * getter for Column jmb_pinyin.gmt_modified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * setter for Column jmb_pinyin.gmt_modified
     * 
     * @param gmtModified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * getter for Column jmb_pinyin.pinyin
     */
    public String getPinyin() {
        return pinyin;
    }

    /**
     * setter for Column jmb_pinyin.pinyin
     * 
     * @param pinyin
     */
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    /**
     * getter for Column jmb_pinyin.chinese
     */
    public String getChinese() {
        return chinese;
    }

    /**
     * setter for Column jmb_pinyin.chinese
     * 
     * @param chinese
     */
    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    /**
     * getter for Column jmb_pinyin.start_with
     */
    public String getStartWith() {
        return startWith;
    }

    /**
     * setter for Column jmb_pinyin.start_with
     * 
     * @param startWith
     */
    public void setStartWith(String startWith) {
        this.startWith = startWith;
    }

    /**
     * getter for Column jmb_pinyin.all_length
     */
    public Long getAllLength() {
        return allLength;
    }

    /**
     * setter for Column jmb_pinyin.all_length
     * 
     * @param allLength
     */
    public void setAllLength(Long allLength) {
        this.allLength = allLength;
    }

    /**
     * getter for Column jmb_pinyin.is_yuanyin
     */
    public String getIsYuanyin() {
        return isYuanyin;
    }

    /**
     * setter for Column jmb_pinyin.is_yuanyin
     * 
     * @param isYuanyin
     */
    public void setIsYuanyin(String isYuanyin) {
        this.isYuanyin = isYuanyin;
    }

    /**
     * getter for Column jmb_pinyin.num
     */
    public Long getNum() {
        return num;
    }

    /**
     * setter for Column jmb_pinyin.num
     * 
     * @param num
     */
    public void setNum(Long num) {
        this.num = num;
    }

    /**
     * getter for Column jmb_pinyin.first
     */
    public Long getFirst() {
        return first;
    }

    /**
     * setter for Column jmb_pinyin.first
     * 
     * @param first
     */
    public void setFirst(Long first) {
        this.first = first;
    }

    /**
     * getter for Column jmb_pinyin.first_pinyin
     */
    public String getFirstPinyin() {
        return firstPinyin;
    }

    /**
     * setter for Column jmb_pinyin.first_pinyin
     * 
     * @param firstPinyin
     */
    public void setFirstPinyin(String firstPinyin) {
        this.firstPinyin = firstPinyin;
    }

    /**
     * getter for Column jmb_pinyin.second
     */
    public Long getSecond() {
        return second;
    }

    /**
     * setter for Column jmb_pinyin.second
     * 
     * @param second
     */
    public void setSecond(Long second) {
        this.second = second;
    }

    /**
     * getter for Column jmb_pinyin.second_pinyin
     */
    public String getSecondPinyin() {
        return secondPinyin;
    }

    /**
     * setter for Column jmb_pinyin.second_pinyin
     * 
     * @param secondPinyin
     */
    public void setSecondPinyin(String secondPinyin) {
        this.secondPinyin = secondPinyin;
    }

    /**
     * getter for Column jmb_pinyin.third
     */
    public Long getThird() {
        return third;
    }

    /**
     * setter for Column jmb_pinyin.third
     * 
     * @param third
     */
    public void setThird(Long third) {
        this.third = third;
    }

    /**
     * getter for Column jmb_pinyin.third_pinyin
     */
    public String getThirdPinyin() {
        return thirdPinyin;
    }

    /**
     * setter for Column jmb_pinyin.third_pinyin
     * 
     * @param thirdPinyin
     */
    public void setThirdPinyin(String thirdPinyin) {
        this.thirdPinyin = thirdPinyin;
    }

    /**
     * getter for Column jmb_pinyin.fourth
     */
    public Long getFourth() {
        return fourth;
    }

    /**
     * setter for Column jmb_pinyin.fourth
     * 
     * @param fourth
     */
    public void setFourth(Long fourth) {
        this.fourth = fourth;
    }

    /**
     * getter for Column jmb_pinyin.fourth_pinyin
     */
    public String getFourthPinyin() {
        return fourthPinyin;
    }

    /**
     * setter for Column jmb_pinyin.fourth_pinyin
     * 
     * @param fourthPinyin
     */
    public void setFourthPinyin(String fourthPinyin) {
        this.fourthPinyin = fourthPinyin;
    }

    /**
     * getter for Column jmb_pinyin.hot
     */
    public Long getHot() {
        return hot;
    }

    /**
     * setter for Column jmb_pinyin.hot
     * 
     * @param hot
     */
    public void setHot(Long hot) {
        this.hot = hot;
    }

}
