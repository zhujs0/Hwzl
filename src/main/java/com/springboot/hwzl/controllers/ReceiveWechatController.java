package com.springboot.hwzl.controllers;

import cn.hutool.core.util.XmlUtil;
import cn.hutool.json.JSONUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.springboot.hwzl.controllers.requestwechatdata.RequestXml;
import com.springboot.hwzl.controllers.requestwechatdata.WeChatEventType;
import com.springboot.hwzl.controllers.requestwechatdata.WeChatMsgType;
import com.springboot.hwzl.customannotation.PassToken;
import com.springboot.hwzl.customannotation.UserLoginToken;
import com.springboot.hwzl.entity.User;
import com.springboot.hwzl.global.BusinessException;
import com.springboot.hwzl.global.globalentity.Response;
import com.springboot.hwzl.global.globalentity.RetCode;
import com.springboot.hwzl.services.userservice.userservice.IUserService;
import com.springboot.hwzl.tools.HttpUtils;
import com.springboot.hwzl.tools.XmlUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.springboot.hwzl.configs.configEntitys.Weixin;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.*;

//@Api(tags ="微信公众号调用接口", value="微信公众号调用接口",description = "微信公众号调用接口")
@RestController
public class ReceiveWechatController {

    @Autowired
    private HttpServletRequest request;
    //@HfiTrace
    @ApiOperation(value="测试")
    @RequestMapping(value = "test",method = RequestMethod.POST)
    @PassToken
    public Response test(@RequestBody User user){
//        LocalDateTime now=LocalDateTime.now();
//        now.plusSeconds(60);
//        LocalDateTime localDateTime = LocalDateTime.now();
//        ZoneId zone = ZoneId.systemDefault();
//        Instant instant = localDateTime.atZone(zone).toInstant();
//        Date date = Date.from(instant);

        String Url="http://zl.wmxmt.cn/WechatInfo/GetAccessToken";
        String postdata=JSONUtil.toJsonStr(user);
        try{
            Map<String,String> map=new HashMap<>();
            map.put("Content-Type","application/json");
          String result = HttpUtils.doGet(Url,null);

          return new Response(RetCode.SUCCESS,"",result);
        }catch (Exception ex){
            throw new BusinessException(RetCode.BAD_REQUEST,ex.toString());
        }

//        Date date=new Date();
//        Calendar c= Calendar.getInstance();
//        c.setTime(date);
//        c.add(Calendar.MINUTE,1);
//        date=c.getTime();
//
//        String token= JWT.create()
//                .withAudience(user.getUserId().toString())
//                .withExpiresAt(date)
//                .sign(Algorithm.HMAC256(user.getPassword()));
//        return new Response(RetCode.SUCCESS,"登录成功",token);
    }


    @PassToken
    //@PostMapping("ReceiveMsg")
    public void receiveMsg()throws Exception{
        InputStream inputStream = request.getInputStream();
        RequestXml requestXml= XmlUtils.toBean(inputStream,RequestXml.class);
        switch (requestXml.getMsgType()){
            case WeChatMsgType.TextType:
                //接收Url文本推送
                break;
            case WeChatMsgType.EventType:
                //接收事件推送
                switch (requestXml.getEvent()){
                    case WeChatEventType.Subscribe:
                        //关注事件推送
                        break;
                    case WeChatEventType.Unsubscribe:
                        //取注事件推送
                        break;
                    case WeChatEventType.Click:
                        //点击事件推送
                        break;
                    case WeChatEventType.SCAN:
                        //扫描二维码事件推送
                        break;
                }
                break;
        }
    }

    @PassToken
    @GetMapping("ReceiveMsg")
    public String checkSignature(String signature, String timestamp,String nonce, String echostr)throws Exception{
        List<String> listStrs = Arrays.asList(Weixin.token, timestamp, nonce);
        Collections.sort(listStrs);
        String encryptionStr = String.join("", listStrs);
        encryptionStr = DigestUtils.sha1Hex(encryptionStr);
        if (encryptionStr.equals(signature)) {
            return echostr;
        } else {
            return "";
        }
    }





}
