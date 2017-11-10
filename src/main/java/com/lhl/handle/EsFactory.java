package com.lhl.handle;

import com.alibaba.fastjson.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * es工厂类.
 */
public final class EsFactory {
    /**
     * 线程个数.
     */
    private static final int THREADS_NUM = 10;
    /**
     * es 工厂类.
     */
    private static EsFactory esFactory = new EsFactory();

    /**
     * 私有化构造函数.
     */
    private EsFactory() {

    }

    /**
     * 初始化.
     *
     * @return es 工厂类
     */
    public static EsFactory getInit() {
        return esFactory;
    }

    /**
     * 创建连接.
     *
     * @param object 参数对象
     * @return 返回对象
     */
    public JSONObject search(Object object) {
        JSONObject jsonObject = new JSONObject();
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS_NUM);
        Future<JSONObject> future = executorService.submit(new EsThread(object));
        try {
            jsonObject = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
