package com.jinmibao.biz.query.domain;

import java.io.Serializable;
import java.util.Date;

import com.jinmibao.common.util.DateUtil;

/**
 * 数据对象
 * 
 * @since 2012-04-18
 * @author wangchongan
 */
public class DomainDeletedQuery implements Serializable {

    private static final long serialVersionUID = 133476400508335062L;

    private String            keywords;

    /**
     * column jmb_domain_deleted.id
     */
    private Long              id;

    /**
     * column jmb_domain_deleted.gmt_create
     */
    private Date              gmtCreate;

    /**
     * column jmb_domain_deleted.gmt_modified
     */
    private Date              gmtModified;

    /**
     * column jmb_domain_deleted.is_deleted
     */
    private String            isDeleted;

    /**
     * column jmb_domain_deleted.domain_name
     */
    private String            domainName;

    /**
     * column jmb_domain_deleted.domain_type
     */
    private String            domainType;

    /**
     * column jmb_domain_deleted.gmt_plan_deleted
     */
    private Date              gmtPlanDeleted;

    /**
     * column jmb_domain_deleted.is_all_num
     */
    private String            isAllNum;

    /**
     * column jmb_domain_deleted.is_all_letter
     */
    private String            isAllLetter;

    /**
     * column jmb_domain_deleted.is_all_pinyin
     */
    private String            isAllPinyin;

    /**
     * column jmb_domain_deleted.is_all_pinyin_one
     */
    private String            isAllPinyinOne;

    /**
     * column jmb_domain_deleted.is_all_pinyin_two
     */
    private String            isAllPinyinTwo;

    /**
     * column jmb_domain_deleted.is_all_pinyin_three
     */
    private String            isAllPinyinThree;

    /**
     * column jmb_domain_deleted.is_has_pinyin
     */
    private String            isHasPinyin;

    /**
     * column jmb_domain_deleted.is_has_pinyin_one
     */
    private String            isHasPinyinOne;

    /**
     * column jmb_domain_deleted.is_has_pinyin_two
     */
    private String            isHasPinyinTwo;

    /**
     * column jmb_domain_deleted.is_has_pinyin_three
     */
    private String            isHasPinyinThree;

    /**
     * column jmb_domain_deleted.all_length
     */
    private Long              allLength;

    /**
     * column jmb_domain_deleted.is_has_line
     */
    private String            isHasLine;

    /**
     * column jmb_domain_deleted.line_count
     */
    private Long              lineCount;

    /**
     * column jmb_domain_deleted.is_num_start
     */
    private String            isNumStart;

    /**
     * column jmb_domain_deleted.is_letter_start
     */
    private String            isLetterStart;

    /**
     * column jmb_domain_deleted.is_num_start_end
     */
    private String            isNumStartEnd;

    /**
     * column jmb_domain_deleted.is_letter_start_end
     */
    private String            isLetterStartEnd;

    /**
     * column jmb_domain_deleted.is_has_year
     */
    private String            isHasYear;

    /**
     * column jmb_domain_deleted.year_id
     */
    private Long              yearId;

    /**
     * column jmb_domain_deleted.is_has_cn_prov
     */
    private String            isHasCnProv;

    /**
     * column jmb_domain_deleted.prov_id
     */
    private Long              provId;

    /**
     * column jmb_domain_deleted.is_has_cn_city
     */
    private String            isHasCnCity;

    /**
     * column jmb_domain_deleted.city_id
     */
    private Long              cityId;

    /**
     * column jmb_domain_deleted.is_has_country
     */
    private String            isHasCountry;

    /**
     * column jmb_domain_deleted.country_id
     */
    private Long              countryId;

    /**
     * column jmb_domain_deleted.is_has_company
     */
    private String            isHasCompany;

    /**
     * column jmb_domain_deleted.company_id
     */
    private Long              companyId;

    /**
     * column jmb_domain_deleted.is_has_Celebrity
     */
    private String            isHasCelebrity;

    /**
     * column jmb_domain_deleted.Celebrity_id
     */
    private Long              celebrityId;

    /**
     * column jmb_domain_deleted.is_chinese
     */
    private String            isChinese;

    /**
     * column jmb_domain_deleted.chinese_type
     */
    private String            chineseType;

    /**
     * column jmb_domain_deleted.is_has_post
     */
    private String            isHasPost;

    /**
     * column jmb_domain_deleted.post_id
     */
    private Long              postId;

    /**
     * column jmb_domain_deleted.deal_status
     */
    private String            dealStatus;

    // ~~~~~~~~~~~~~~~~~~~~~~~搜索~~~~~~~~~~~~~~~~~~~~~~~~

    private String            keywordPlace;

    private String[]          domainTypeArray;

    private Long              lengthStart;

    private Long              lengthEnd;

    private Date              deletedDateStart;

    private Date              deletedDateEnd;

    private String            deletedDateStartStr;

    private String            deletedDateEndStr;

    private String            rankByType;

    // 排除的关键字，目前只支持一个
    private String            excludeKeywords;

    // 是否是统计计数，如果是的话，则在COUNT时候不加排序
    private Long              isCount;

    // ~~~~~~~~~~~~~~~~~~~搜索~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public DomainDeletedQuery(){
        super();
    }

    /**
     * getter for Column jmb_domain_deleted.id
     */
    public Long getId() {
        return id;
    }

    /**
     * setter for Column jmb_domain_deleted.id
     * 
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * getter for Column jmb_domain_deleted.gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * setter for Column jmb_domain_deleted.gmt_create
     * 
     * @param gmtCreate
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * getter for Column jmb_domain_deleted.gmt_modified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * setter for Column jmb_domain_deleted.gmt_modified
     * 
     * @param gmtModified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * getter for Column jmb_domain_deleted.is_deleted
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * setter for Column jmb_domain_deleted.is_deleted
     * 
     * @param isDeleted
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * getter for Column jmb_domain_deleted.domain_name
     */
    public String getDomainName() {
        return domainName;
    }

    /**
     * setter for Column jmb_domain_deleted.domain_name
     * 
     * @param domainName
     */
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    /**
     * getter for Column jmb_domain_deleted.domain_type
     */
    public String getDomainType() {
        return domainType;
    }

    /**
     * setter for Column jmb_domain_deleted.domain_type
     * 
     * @param domainType
     */
    public void setDomainType(String domainType) {
        this.domainType = domainType;
    }

    /**
     * getter for Column jmb_domain_deleted.gmt_plan_deleted
     */
    public Date getGmtPlanDeleted() {
        return gmtPlanDeleted;
    }

    /**
     * setter for Column jmb_domain_deleted.gmt_plan_deleted
     * 
     * @param gmtPlanDeleted
     */
    public void setGmtPlanDeleted(Date gmtPlanDeleted) {
        this.gmtPlanDeleted = gmtPlanDeleted;
    }

    /**
     * getter for Column jmb_domain_deleted.is_all_num
     */
    public String getIsAllNum() {
        return isAllNum;
    }

    /**
     * setter for Column jmb_domain_deleted.is_all_num
     * 
     * @param isAllNum
     */
    public void setIsAllNum(String isAllNum) {
        this.isAllNum = isAllNum;
    }

    /**
     * getter for Column jmb_domain_deleted.is_all_letter
     */
    public String getIsAllLetter() {
        return isAllLetter;
    }

    /**
     * setter for Column jmb_domain_deleted.is_all_letter
     * 
     * @param isAllLetter
     */
    public void setIsAllLetter(String isAllLetter) {
        this.isAllLetter = isAllLetter;
    }

    /**
     * getter for Column jmb_domain_deleted.is_all_pinyin
     */
    public String getIsAllPinyin() {
        return isAllPinyin;
    }

    /**
     * setter for Column jmb_domain_deleted.is_all_pinyin
     * 
     * @param isAllPinyin
     */
    public void setIsAllPinyin(String isAllPinyin) {
        this.isAllPinyin = isAllPinyin;
    }

    /**
     * getter for Column jmb_domain_deleted.is_all_pinyin_one
     */
    public String getIsAllPinyinOne() {
        return isAllPinyinOne;
    }

    /**
     * setter for Column jmb_domain_deleted.is_all_pinyin_one
     * 
     * @param isAllPinyinOne
     */
    public void setIsAllPinyinOne(String isAllPinyinOne) {
        this.isAllPinyinOne = isAllPinyinOne;
    }

    /**
     * getter for Column jmb_domain_deleted.is_all_pinyin_two
     */
    public String getIsAllPinyinTwo() {
        return isAllPinyinTwo;
    }

    /**
     * setter for Column jmb_domain_deleted.is_all_pinyin_two
     * 
     * @param isAllPinyinTwo
     */
    public void setIsAllPinyinTwo(String isAllPinyinTwo) {
        this.isAllPinyinTwo = isAllPinyinTwo;
    }

    /**
     * getter for Column jmb_domain_deleted.is_all_pinyin_three
     */
    public String getIsAllPinyinThree() {
        return isAllPinyinThree;
    }

    /**
     * setter for Column jmb_domain_deleted.is_all_pinyin_three
     * 
     * @param isAllPinyinThree
     */
    public void setIsAllPinyinThree(String isAllPinyinThree) {
        this.isAllPinyinThree = isAllPinyinThree;
    }

    /**
     * getter for Column jmb_domain_deleted.is_has_pinyin
     */
    public String getIsHasPinyin() {
        return isHasPinyin;
    }

    /**
     * setter for Column jmb_domain_deleted.is_has_pinyin
     * 
     * @param isHasPinyin
     */
    public void setIsHasPinyin(String isHasPinyin) {
        this.isHasPinyin = isHasPinyin;
    }

    /**
     * getter for Column jmb_domain_deleted.is_has_pinyin_one
     */
    public String getIsHasPinyinOne() {
        return isHasPinyinOne;
    }

    /**
     * setter for Column jmb_domain_deleted.is_has_pinyin_one
     * 
     * @param isHasPinyinOne
     */
    public void setIsHasPinyinOne(String isHasPinyinOne) {
        this.isHasPinyinOne = isHasPinyinOne;
    }

    /**
     * getter for Column jmb_domain_deleted.is_has_pinyin_two
     */
    public String getIsHasPinyinTwo() {
        return isHasPinyinTwo;
    }

    /**
     * setter for Column jmb_domain_deleted.is_has_pinyin_two
     * 
     * @param isHasPinyinTwo
     */
    public void setIsHasPinyinTwo(String isHasPinyinTwo) {
        this.isHasPinyinTwo = isHasPinyinTwo;
    }

    /**
     * getter for Column jmb_domain_deleted.is_has_pinyin_three
     */
    public String getIsHasPinyinThree() {
        return isHasPinyinThree;
    }

    /**
     * setter for Column jmb_domain_deleted.is_has_pinyin_three
     * 
     * @param isHasPinyinThree
     */
    public void setIsHasPinyinThree(String isHasPinyinThree) {
        this.isHasPinyinThree = isHasPinyinThree;
    }

    /**
     * getter for Column jmb_domain_deleted.all_length
     */
    public Long getAllLength() {
        return allLength;
    }

    /**
     * setter for Column jmb_domain_deleted.all_length
     * 
     * @param allLength
     */
    public void setAllLength(Long allLength) {
        this.allLength = allLength;
    }

    /**
     * getter for Column jmb_domain_deleted.is_has_line
     */
    public String getIsHasLine() {
        return isHasLine;
    }

    /**
     * setter for Column jmb_domain_deleted.is_has_line
     * 
     * @param isHasLine
     */
    public void setIsHasLine(String isHasLine) {
        this.isHasLine = isHasLine;
    }

    /**
     * getter for Column jmb_domain_deleted.line_count
     */
    public Long getLineCount() {
        return lineCount;
    }

    /**
     * setter for Column jmb_domain_deleted.line_count
     * 
     * @param lineCount
     */
    public void setLineCount(Long lineCount) {
        this.lineCount = lineCount;
    }

    /**
     * getter for Column jmb_domain_deleted.is_num_start
     */
    public String getIsNumStart() {
        return isNumStart;
    }

    /**
     * setter for Column jmb_domain_deleted.is_num_start
     * 
     * @param isNumStart
     */
    public void setIsNumStart(String isNumStart) {
        this.isNumStart = isNumStart;
    }

    /**
     * getter for Column jmb_domain_deleted.is_letter_start
     */
    public String getIsLetterStart() {
        return isLetterStart;
    }

    /**
     * setter for Column jmb_domain_deleted.is_letter_start
     * 
     * @param isLetterStart
     */
    public void setIsLetterStart(String isLetterStart) {
        this.isLetterStart = isLetterStart;
    }

    /**
     * getter for Column jmb_domain_deleted.is_num_start_end
     */
    public String getIsNumStartEnd() {
        return isNumStartEnd;
    }

    /**
     * setter for Column jmb_domain_deleted.is_num_start_end
     * 
     * @param isNumStartEnd
     */
    public void setIsNumStartEnd(String isNumStartEnd) {
        this.isNumStartEnd = isNumStartEnd;
    }

    /**
     * getter for Column jmb_domain_deleted.is_letter_start_end
     */
    public String getIsLetterStartEnd() {
        return isLetterStartEnd;
    }

    /**
     * setter for Column jmb_domain_deleted.is_letter_start_end
     * 
     * @param isLetterStartEnd
     */
    public void setIsLetterStartEnd(String isLetterStartEnd) {
        this.isLetterStartEnd = isLetterStartEnd;
    }

    /**
     * getter for Column jmb_domain_deleted.is_has_year
     */
    public String getIsHasYear() {
        return isHasYear;
    }

    /**
     * setter for Column jmb_domain_deleted.is_has_year
     * 
     * @param isHasYear
     */
    public void setIsHasYear(String isHasYear) {
        this.isHasYear = isHasYear;
    }

    /**
     * getter for Column jmb_domain_deleted.year_id
     */
    public Long getYearId() {
        return yearId;
    }

    /**
     * setter for Column jmb_domain_deleted.year_id
     * 
     * @param yearId
     */
    public void setYearId(Long yearId) {
        this.yearId = yearId;
    }

    /**
     * getter for Column jmb_domain_deleted.is_has_cn_prov
     */
    public String getIsHasCnProv() {
        return isHasCnProv;
    }

    /**
     * setter for Column jmb_domain_deleted.is_has_cn_prov
     * 
     * @param isHasCnProv
     */
    public void setIsHasCnProv(String isHasCnProv) {
        this.isHasCnProv = isHasCnProv;
    }

    /**
     * getter for Column jmb_domain_deleted.prov_id
     */
    public Long getProvId() {
        return provId;
    }

    /**
     * setter for Column jmb_domain_deleted.prov_id
     * 
     * @param provId
     */
    public void setProvId(Long provId) {
        this.provId = provId;
    }

    /**
     * getter for Column jmb_domain_deleted.is_has_cn_city
     */
    public String getIsHasCnCity() {
        return isHasCnCity;
    }

    /**
     * setter for Column jmb_domain_deleted.is_has_cn_city
     * 
     * @param isHasCnCity
     */
    public void setIsHasCnCity(String isHasCnCity) {
        this.isHasCnCity = isHasCnCity;
    }

    /**
     * getter for Column jmb_domain_deleted.city_id
     */
    public Long getCityId() {
        return cityId;
    }

    /**
     * setter for Column jmb_domain_deleted.city_id
     * 
     * @param cityId
     */
    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    /**
     * getter for Column jmb_domain_deleted.is_has_country
     */
    public String getIsHasCountry() {
        return isHasCountry;
    }

    /**
     * setter for Column jmb_domain_deleted.is_has_country
     * 
     * @param isHasCountry
     */
    public void setIsHasCountry(String isHasCountry) {
        this.isHasCountry = isHasCountry;
    }

    /**
     * getter for Column jmb_domain_deleted.country_id
     */
    public Long getCountryId() {
        return countryId;
    }

    /**
     * setter for Column jmb_domain_deleted.country_id
     * 
     * @param countryId
     */
    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    /**
     * getter for Column jmb_domain_deleted.is_has_company
     */
    public String getIsHasCompany() {
        return isHasCompany;
    }

    /**
     * setter for Column jmb_domain_deleted.is_has_company
     * 
     * @param isHasCompany
     */
    public void setIsHasCompany(String isHasCompany) {
        this.isHasCompany = isHasCompany;
    }

    /**
     * getter for Column jmb_domain_deleted.company_id
     */
    public Long getCompanyId() {
        return companyId;
    }

    /**
     * setter for Column jmb_domain_deleted.company_id
     * 
     * @param companyId
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /**
     * getter for Column jmb_domain_deleted.is_has_Celebrity
     */
    public String getIsHasCelebrity() {
        return isHasCelebrity;
    }

    /**
     * setter for Column jmb_domain_deleted.is_has_Celebrity
     * 
     * @param isHasCelebrity
     */
    public void setIsHasCelebrity(String isHasCelebrity) {
        this.isHasCelebrity = isHasCelebrity;
    }

    /**
     * getter for Column jmb_domain_deleted.Celebrity_id
     */
    public Long getCelebrityId() {
        return celebrityId;
    }

    /**
     * setter for Column jmb_domain_deleted.Celebrity_id
     * 
     * @param celebrityId
     */
    public void setCelebrityId(Long celebrityId) {
        this.celebrityId = celebrityId;
    }

    /**
     * getter for Column jmb_domain_deleted.is_chinese
     */
    public String getIsChinese() {
        return isChinese;
    }

    /**
     * setter for Column jmb_domain_deleted.is_chinese
     * 
     * @param isChinese
     */
    public void setIsChinese(String isChinese) {
        this.isChinese = isChinese;
    }

    /**
     * getter for Column jmb_domain_deleted.chinese_type
     */
    public String getChineseType() {
        return chineseType;
    }

    /**
     * setter for Column jmb_domain_deleted.chinese_type
     * 
     * @param chineseType
     */
    public void setChineseType(String chineseType) {
        this.chineseType = chineseType;
    }

    /**
     * getter for Column jmb_domain_deleted.is_has_post
     */
    public String getIsHasPost() {
        return isHasPost;
    }

    /**
     * setter for Column jmb_domain_deleted.is_has_post
     * 
     * @param isHasPost
     */
    public void setIsHasPost(String isHasPost) {
        this.isHasPost = isHasPost;
    }

    /**
     * getter for Column jmb_domain_deleted.post_id
     */
    public Long getPostId() {
        return postId;
    }

    /**
     * setter for Column jmb_domain_deleted.post_id
     * 
     * @param postId
     */
    public void setPostId(Long postId) {
        this.postId = postId;
    }

    /**
     * getter for Column jmb_domain_deleted.deal_status
     */
    public String getDealStatus() {
        return dealStatus;
    }

    /**
     * setter for Column jmb_domain_deleted.deal_status
     * 
     * @param dealStatus
     */
    public void setDealStatus(String dealStatus) {
        this.dealStatus = dealStatus;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getKeywordPlace() {
        return keywordPlace;
    }

    public void setKeywordPlace(String keywordPlace) {
        this.keywordPlace = keywordPlace;
    }

    public String[] getDomainTypeArray() {
        return domainTypeArray;
    }

    public void setDomainTypeArray(String[] domainTypeArray) {
        this.domainTypeArray = domainTypeArray;
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

    public Date getDeletedDateStart() {
        return deletedDateStart;
    }

    public void setDeletedDateStart(Date deletedDateStart) {
        this.deletedDateStart = deletedDateStart;
    }

    public Date getDeletedDateEnd() {
        return deletedDateEnd;
    }

    public void setDeletedDateEnd(Date deletedDateEnd) {
        this.deletedDateEnd = deletedDateEnd;
    }

    public String getRankByType() {
        return rankByType;
    }

    public void setRankByType(String rankByType) {
        this.rankByType = rankByType;
    }

    public Long getIsCount() {
        return isCount;
    }

    public void setIsCount(Long isCount) {
        this.isCount = isCount;
    }

    public String getDeletedDateStartStr() {
        return DateUtil.date2String(this.deletedDateStart, DateUtil.DAY_DATE_FORMAT);
    }

    public void setDeletedDateStartStr(String deletedDateStartStr) {
        this.deletedDateStartStr = deletedDateStartStr;
    }

    public String getDeletedDateEndStr() {
        return DateUtil.date2String(this.deletedDateEnd, DateUtil.DAY_DATE_FORMAT);
    }

    public void setDeletedDateEndStr(String deletedDateEndStr) {
        this.deletedDateEndStr = deletedDateEndStr;
    }

    public String getExcludeKeywords() {
        return excludeKeywords;
    }

    public void setExcludeKeywords(String excludeKeywords) {
        this.excludeKeywords = excludeKeywords;
    }

}
