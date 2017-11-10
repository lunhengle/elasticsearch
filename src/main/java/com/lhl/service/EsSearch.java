package com.lhl.service;

import com.alibaba.fastjson.JSONObject;

/**
 * es 查询.
 */
public interface EsSearch {
    /**
     * 查询.
     *
     * @param object 查询对象
     * @return 返回查询结果
     */
    JSONObject search(Object object);
}
