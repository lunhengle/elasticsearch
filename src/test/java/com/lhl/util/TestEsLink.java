package com.lhl.util;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestEsLink {

    /**
     * 测试get方法.
     */
    @Test
    public void testGet() {
        //查看集群节点.
        String get_node_url = "http://127.0.0.1:9200/_cat/nodes?v";
        //查看索引节点
        String get_indices_url = "http://127.0.0.1:9200/_cat/indices?v";

        Map<String, String> params = new HashMap<>();
        String result = HttpClientUtils.init().get(get_indices_url, params);
        System.out.println(result);
        result = HttpClientUtils.init().get(get_node_url, params);
        System.out.println(result);
    }

    /**
     * 添加索引.
     */
    @Test
    public void testHead() {
        String put_index_node = "http://127.0.0.1:9200/customer?pretty";
        String result = HttpClientUtils.init().head(put_index_node);
        System.out.println(result);
    }

    /**
     * 添加节点.
     */
    @Test
    public void testPutNode() {
        String put_index_node = "http://127.0.0.1:9200/customer?pretty";
        String result = HttpClientUtils.init().put(put_index_node);
        System.out.println(result);
    }

    /**
     * 删除节点.
     */
    @Test
    public void testDeleteNode() {
        String put_index_node = "http://127.0.0.1:9200/customer?pretty";
        String result = HttpClientUtils.init().delete(put_index_node);
        System.out.println(result);
    }

    /**
     * 根据id 删除节点.
     */
    @Test
    public void testDeleteId() {
        String delete_index_id = "http://127.0.0.1:9200/customer/external/2/?pretty";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "李四");
        jsonObject.put("age", 30);
        String result = HttpClientUtils.init().get(delete_index_id);
        System.out.println("get: " + result);
        result = HttpClientUtils.init().put(delete_index_id, jsonObject.toString());
        System.out.println("put: " + result);
        result = HttpClientUtils.init().get(delete_index_id);
        System.out.println("get1: " + result);
        result = HttpClientUtils.init().delete(delete_index_id);
        System.out.println("delete:" + result);
    }

    /**
     * 测试put 方法.
     * 添加索引字段
     */
    @Test
    public void testPutContent() {
        String put_index_content = "http://127.0.0.1:9200/customer/external/2?pretty";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "网三");
        jsonObject.put("age", 4);
        String result = HttpClientUtils.init().put(put_index_content, jsonObject.toString());
        System.out.println(result);
    }

    /**
     * 测试得到的方法.
     * 查看索引字段
     */
    @Test
    public void testGetContent() {
        String get_index_content = "http://127.0.0.1:9200/customer/external/2?pretty";
        String result = HttpClientUtils.init().get(get_index_content);
        System.out.println(result);
    }

    /**
     * 测试post.
     * 更新索引字段
     */
    @Test
    public void testPostContent() {
        String post_index_content = "http://127.0.0.1:9200/customer/external/1/_update/?pretty";
        JSONObject json = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "张三");
        jsonObject.put("age", 1);
        json.put("doc", jsonObject);
        String result = HttpClientUtils.init().post(post_index_content, json.toString());
        System.out.println(result);
    }

    /**
     * 测试带有计算字段.
     */
    @Test
    public void testPostContent1() {
        String post_index_content1 = "http://127.0.0.1:9200/customer/external/1/_update?pretty";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("script", "ctx._source.age += 5");
        String result = HttpClientUtils.init().post(post_index_content1, jsonObject.toString());
        System.out.println(result);
    }

    /**
     * 测试批量处理.
     */
    @Test
    public void testPostBulk() {
        String post_index_content = "http://127.0.0.1:9200/cusomer/external/_bulk?pretty";
        JSONObject json1 = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("_id", 3);
        json1.put("index", jsonObject1);
        String result = HttpClientUtils.init().put(post_index_content, json1.toString());
        System.out.println(result);
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("name", "John Doe");
        HttpClientUtils.init().put(post_index_content, jsonObject2.toString());
        System.out.println(result);
    }

    /**
     * 查询字段.
     */
    @Test
    public void testSearch() {
        String get_index_content = "http://127.0.0.1:9200/customer/external/_search?q=*&pretty";
        String result = HttpClientUtils.init().get(get_index_content);
        System.out.println(result);
    }
}
