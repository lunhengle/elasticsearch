package com.lhl.handle;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * http client 工具类.
 */
final class EsLink {
    /**
     * 初始化.
     */
    private static EsLink esLink = new EsLink();

    /**
     * 私有化构造函数.
     */
    private EsLink() {

    }

    /**
     * 返回实例.
     *
     * @return 实例
     */
    static EsLink getInit() {
        return esLink;
    }

    /**
     * url.
     */
    private final static String URL = "http://59.110.9.94:9200";


    /**
     * post 方法.
     *
     * @param path path
     * @param json 对象
     * @return 打印内容
     */
    final JSONObject post(final String path, final JSONObject json) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String uri = this.handlePath(path);
        System.out.println(uri);
        HttpPost httpPost = new HttpPost(uri);
        System.out.println(json);
        StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");
        httpPost.setEntity(stringEntity);
        return this.print(httpClient, httpPost);
    }


    /**
     * 打印.
     *
     * @param httpClient      httpClient
     * @param httpRequestBase 请求
     * @return 打印内容
     */
    private JSONObject print(CloseableHttpClient httpClient, HttpRequestBase httpRequestBase) {
        String result = "";
        try {
            HttpResponse response = httpClient.execute(httpRequestBase);
            int code = response.getStatusLine().getStatusCode();
            if (200 == code) {
                HttpEntity httpEntity = response.getEntity();
                if (null != httpEntity) {
                    result = EntityUtils.toString(httpEntity);
                }
            }
        } catch (IOException e) {
            result = null;
            e.printStackTrace();
        } finally {
            try {
                httpRequestBase.releaseConnection();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.handleJson(result);
    }

    /**
     * 处理json.
     *
     * @param result http 返回结果
     * @return 处理过之后的json
     */
    private JSONObject handleJson(String result) {
        JSONObject json = JSONObject.parseObject(result);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total", 0);
        jsonObject.put("list", new JSONArray());
        if (null != json && !json.isEmpty() && !json.getJSONObject("hits").isEmpty() && json.getJSONObject("hits").getIntValue("total") != 0) {
            jsonObject.put("total", json.getJSONObject("hits").getIntValue("total"));
            JSONArray list = new JSONArray();
            JSONArray jr = json.getJSONObject("hits").getJSONArray("hits");
            for (int i = 0; i < jr.size(); i++) {
                list.add(jr.getJSONObject(i).getJSONObject("_source"));
            }
            jsonObject.put("list", list);
        }
        return jsonObject;
    }

    /**
     * 处理请求路径.
     *
     * @param path 路径
     * @return 返回拼接好的路径
     */
    private String handlePath(String path) {
        String uri = "";
        if (null != path && null != URL) {
            if (path.substring(0, 1).contains("/") && URL.substring(URL.length() - 1, URL.length()).contains("/")) {
                uri = URL.substring(0, URL.length() - 1) + path;
            } else if (!path.substring(0, 1).contains("/") && !URL.substring(URL.length() - 1, URL.length()).contains("/")) {
                uri = URL + "/" + path;
            } else {
                uri = URL + path;
            }
        }
        return uri;
    }
}
