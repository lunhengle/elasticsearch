package com.lhl.util;

import com.alibaba.fastjson.JSONObject;
import com.lhl.bean.Clue;
import com.lhl.service.EsSearch;
import com.lhl.service.EsSearchImpl;
import org.junit.Test;

/**
 * 测试注解解析类.
 */
public class TestEsSearch {
    /**
     * 测试注解解析类.
     */
    @Test
    public void testProcess() {
        Clue clue = new Clue();
        clue.setCompanyName("公司");
        clue.setProvince("广东省");
        clue.setFrom("0");
        clue.setSize("100");
        clue.setRegistTimeStart("2016-07-06");
        clue.setRegistTimeEnd("2016-07-15");
        clue.setStatus(2);
        clue.setCity("深圳市");
        String[] categorys = {"电子商务", "互联网"};
        clue.setCategorys(categorys);
        clue.setCategory("电子商务");
        clue.setLegalPerson("马振豪");
        String[] legalPersons = {"马振豪", "张三"};
        clue.setLegalPersons(legalPersons);
        EsSearch esSearch = new EsSearchImpl();
        JSONObject jsonObject = esSearch.search(clue);
        System.out.println(jsonObject);
    }
}
