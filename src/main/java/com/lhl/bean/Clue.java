package com.lhl.bean;

import com.lhl.annotatioin.*;
import com.lhl.constants.Condition;
import com.lhl.constants.Match;
import com.lhl.constants.Sort;

/**
 * 线索.
 * 建议属性不要用 基本数据类型 , 基本数据类型有默认值 如果没有填写值 就会默认拼写到查询条件中
 */
@Index(index = "es_clue", type = "t_clue")
public class Clue {
    /**
     * id.
     */
    @com.lhl.annotatioin.Sort(name = "id", order = Sort.DESC, socre = 100)
    private Integer id;
    /**
     * 公司名称.
     */
    @Row(name = "company_name", match = Match.MATCH_PHRASE, condition = Condition.MUST)
    private String companyName;
    /**
     * 省份.
     */
    @Row(name = "province", match = Match.MATCH_PHRASE, condition = Condition.MUST)
    private String province;
    /**
     * 开始时间.
     */
    @com.lhl.annotatioin.Sort(name = "regist_time", order = Sort.ASC, socre = 100)
    @Row(name = "regist_time", match = Match.GTE, condition = Condition.MUST)
    private String registTimeStart;
    /**
     * 结束时间.
     */
    @Row(name = "regist_time", match = Match.LTE, condition = Condition.MUST)
    private String registTimeEnd;
    /**
     * 状态.
     */
    @Row(name = "status", match = Match.TERM, condition = Condition.MUST)
    private Integer status;
    /**
     * 城市.
     */
    @Row(name = "city", match = Match.MATCH_PHRASE, condition = Condition.MUST_NOT)
    private String city;
    /**
     * 行业.
     */
    @Row(name = "category", match = Match.MATCH_PHRASE, condition = Condition.SHOULD)
    private String category;
    /**
     * 行业.
     */
    @Row(name = "category", match = Match.MATCH_PHRASE, condition = Condition.SHOULD)
    private String categorys[];
    /**
     * 法人.
     */
    @Row(name = "legal_person", match = Match.MATCH_PHRASE, condition = Condition.MUST_NOT)
    private String legalPerson;
    /**
     * 法人.
     */
    @Row(name = "legal_person", match = Match.MATCH_PHRASE, condition = Condition.MUST_NOT)
    private String legalPersons[];
    /**
     * 开始.
     */
    @PageFrom
    private String from;
    /**
     * 每页大小.
     */
    @PageSize
    private String size;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getRegistTimeStart() {
        return registTimeStart;
    }

    public void setRegistTimeStart(String registTimeStart) {
        this.registTimeStart = registTimeStart;
    }

    public String getRegistTimeEnd() {
        return registTimeEnd;
    }

    public void setRegistTimeEnd(String registTimeEnd) {
        this.registTimeEnd = registTimeEnd;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String[] getCategorys() {
        return categorys;
    }

    public void setCategorys(String[] categorys) {
        this.categorys = categorys;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String[] getLegalPersons() {
        return legalPersons;
    }

    public void setLegalPersons(String[] legalPersons) {
        this.legalPersons = legalPersons;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
