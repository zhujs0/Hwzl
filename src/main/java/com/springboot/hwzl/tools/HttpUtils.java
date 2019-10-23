package com.springboot.hwzl.tools;

import com.springboot.hwzl.configs.configEntitys.Weixin;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import java.net.URI;
import java.util.Map;

/**
 * Http工具类
 */
public class HttpUtils {

    /**
     * 可设置请求头的HttpGet方法
     * @param url
     * @param headers
     * @return
     * @throws Exception
     */
    public  static  String doGet (String url, Map<String,String> headers) throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        // 设置配置请求参数
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
                .setConnectionRequestTimeout(35000)// 请求超时时间
                .setSocketTimeout(60000)// 数据读取超时时间
                .build();
        // 为httpGet实例设置配置
        httpGet.setConfig(requestConfig);
        if (headers!=null&&headers.size()>0){
            for(Map.Entry<String, String> entry : headers.entrySet()){
                httpGet.addHeader(entry.getKey(),entry.getValue());
            }
        }
        // 执行get请求得到返回对象
        CloseableHttpResponse  response = httpClient.execute(httpGet);
//        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
//
//        }
        // 通过返回对象获取返回数据
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);
        response.close();
        httpGet.abort();
        httpClient.close();
        return  result;
    }

    public  static  String doPost(String url,String postData, Map<String,String> headers)
    throws Exception{
        //URI uri = new URI(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        if (headers!=null&&headers.size()>0){
            for(Map.Entry<String, String> entry : headers.entrySet()){
                httpPost.addHeader(entry.getKey(),entry.getValue());
            }
        }
        if (!StringUtils.isEmpty(postData)) {
            StringEntity body = new StringEntity(postData, "utf-8");
            httpPost.setEntity(body);
        }
        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);
        response.close();
        httpPost.abort();
        httpClient.close();
        return  result;
    }
}
