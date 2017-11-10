package com.lhl.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

/**
 * http client 工具类.
 */
public final class HttpClientUtils {
    private static HttpClientUtils httpClientUtils = null;

    private HttpClientUtils() {

    }

    /**
     * 初始化方法.
     *
     * @return 初始化方法
     */
    public static HttpClientUtils init() {
        if (null == httpClientUtils) {
            httpClientUtils = new HttpClientUtils();
        }
        return httpClientUtils;
    }

    /**
     * get.
     *
     * @param url url
     * @return get内容
     */
    public final String get(final String url) {
        return this.get(url, null);
    }

    /**
     * get 方法.
     *
     * @param url    url
     * @param params 参数对象
     */
    public final String get(final String url, final Map<String, String> params) {
        String result = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(this.handleParams(url, params));
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            int code = response.getStatusLine().getStatusCode();
            if (200 == code) {
                HttpEntity entity = response.getEntity();
                if (null != entity) {
                    result = EntityUtils.toString(entity);
                }
            }
        } catch (IOException e) {
            result = null;
            e.printStackTrace();
        } finally {
            try {
                httpGet.releaseConnection();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * put方法.
     *
     * @param url     url
     * @param jsonObj 对象
     * @return response
     */
    public final String put(String url, String jsonObj) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(url);
        if (null != jsonObj) {
            StringEntity stringEntity = new StringEntity(jsonObj, "UTF-8");
            httpPut.setEntity(stringEntity);
        }
        return this.print(httpClient, httpPut);
    }

    /**
     * post 方法.
     *
     * @param url     url
     * @param jsonObj 对象
     * @return 打印内容
     */
    public final String post(String url, String jsonObj) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        if (null != jsonObj) {
            StringEntity stringEntity = new StringEntity(jsonObj, "UTF-8");
            httpPost.setEntity(stringEntity);
        }
        return this.print(httpClient, httpPost);
    }

    /**
     * put 方法.
     *
     * @param url url
     * @return response
     */
    public final String put(String url) {
        return this.put(url, null);
    }

    /**
     * 删除.
     *
     * @param url url
     * @return response
     */
    public final String delete(final String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete(url);
        return this.print(httpClient, httpDelete);
    }

    /**
     * head.
     *
     * @param url url
     * @return 打印内容
     */
    public final String head(final String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpHead httpHead = new HttpHead(url);
        return this.print(httpClient, httpHead);
    }

    /**
     * 打印.
     *
     * @param httpClient      httpClient
     * @param httpRequestBase 请求
     * @return 打印内容
     */
    private String print(CloseableHttpClient httpClient, HttpRequestBase httpRequestBase) {
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
        return result;
    }

    /**
     * 拼接url中的参数.
     *
     * @param url    url
     * @param params 参数
     * @return 拼接后的参数
     */
    private String handleParams(final String url, final Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(url).append("?");
        if (null != params) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                stringBuilder.append(key).append("=").append(value).append("&");
            }
        }
        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }
}
