package com.lhl.service;

import com.alibaba.fastjson.JSONObject;
import com.lhl.handle.EsFactory;

/**
 * 查询实现类.
 */
public class EsSearchImpl implements EsSearch {
    /**
     * 查询.
     *
     * @param object 查询对象
     * @return 返回查询结果
     */
    @Override
    public JSONObject search(Object object) {
        return EsFactory.getInit().search(object);
    }
}
