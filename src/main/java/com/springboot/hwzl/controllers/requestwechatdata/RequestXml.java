package com.springboot.hwzl.controllers.requestwechatdata;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestXml {
    private static final long serialVersionUID = 2L;
    private  String ToUserName;
    private  String FromUserName;
    private  String MsgType;
    private  String Content;
    private  String Event;
    private  String EventKey;

}
