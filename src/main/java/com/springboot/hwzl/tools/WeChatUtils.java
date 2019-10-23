package com.springboot.hwzl.tools;

import com.springboot.hwzl.configs.configEntitys.Weixin;
import org.springframework.beans.factory.annotation.Value;

public class WeChatUtils {
    public static String getAccessToken(){
        String url=Weixin.accessTokenUrl.replace("APPID", Weixin.appId);
        return  "";
    }
}
