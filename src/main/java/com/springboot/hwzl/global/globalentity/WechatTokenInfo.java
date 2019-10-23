package com.springboot.hwzl.global.globalentity;

import cn.hutool.json.JSONUtil;
import com.springboot.hwzl.configs.configEntitys.Weixin;
import com.springboot.hwzl.controllers.dtos.AccessTokenResult;
import com.springboot.hwzl.global.BusinessException;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class WechatTokenInfo {
    private  static String token;
    private  static LocalDateTime requestTime;
    private  static int EffectiveTime;

    public  static String getToken() throws Exception {
        //当获取token时间不等于null（即已经存在时）并且EffectiveTime>0时，比较有效时间与当前时间的大小
        if (requestTime != null) {
            LocalDateTime EffectiveDate = requestTime.plusSeconds(EffectiveTime);//token有效时间
            LocalDateTime nowDate = LocalDateTime.now();
            if (EffectiveDate.compareTo(nowDate) <= 0) {
                //有效時間<=當前時間，token已過期，需要重新獲取token
                init();
            }
        } else {
            //初始化tokenInfo
            init();
        }
        return token;
    }

    private  static void init() throws Exception{
        AccessTokenResult accessTokenResult= HttpGetToken();
        if(accessTokenResult.code.equals(200))
        {
            token=accessTokenResult.access_token;
            requestTime=accessTokenResult.requestDate;
            EffectiveTime=accessTokenResult.expires_in;
        }
        else{
            throw new BusinessException(RetCode.UNAUTHORIZED,"请求access_token失败");
        }
    }

    private static AccessTokenResult HttpGetToken() throws IOException {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String accessTokenUrl = Weixin.accessTokenUrl;
        httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(accessTokenUrl);
        // 设置配置请求参数
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
                .setConnectionRequestTimeout(35000)// 请求超时时间
                .setSocketTimeout(60000)// 数据读取超时时间
                .build();
        // 为httpGet实例设置配置
        httpGet.setConfig(requestConfig);
        // 执行get请求得到返回对象
        response = httpClient.execute(httpGet);
        // 通过返回对象获取返回数据
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);
        AccessTokenResult accessTokenResult= JSONUtil.toBean(result,AccessTokenResult.class);
        return accessTokenResult;
    }





}
