package com.springboot.hwzl.configs.configEntitys;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "weixin")
@Data
public class Weixin {
    public static String accessTokenUrl;

    public void setAccessTokenUrl(String accessTokenUrl) {
        this.accessTokenUrl = accessTokenUrl;
    }

    public static String token;

    public void setToken(String token) {
        this.token = token;
    }
    public static  String appId;
    public void  setAppId(String appId){
        this.appId=appId;
    }

    public String getAppId(){
        return this.appId;
    }
}

