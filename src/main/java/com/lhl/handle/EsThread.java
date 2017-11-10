package com.lhl.handle;

import com.alibaba.fastjson.JSONObject;

import java.util.concurrent.Callable;

/**
 * es 线程.
 */
public final class EsThread implements Callable<JSONObject> {
    /**
     * 数据对象.
     */
    private Object object;

    /**
     * 构造函数.
     *
     * @param object 数据对象
     */
    EsThread(Object object) {
        this.object = object;
    }

    /**
     * 返回es json 数据.
     *
     * @return es json
     */
    @Override
    public JSONObject call() {
        EsLink esLink = EsLink.getInit();
        EsAnalysis esAnalysis = EsAnalysis.getInit();

        JSONObject jsonObject = esAnalysis.analysis(object);
        String path = (String) jsonObject.get("path");
        JSONObject entry = (JSONObject) jsonObject.get("entry");
        return esLink.post(path, entry);
    }
}
