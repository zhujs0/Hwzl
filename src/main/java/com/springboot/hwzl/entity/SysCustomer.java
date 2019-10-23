package com.springboot.hwzl.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SysCustomer {
    private int CustomerId;
    private String OpenId;
    private String CustomerName;
    private String HeadImagePath;
    private String WechatNo;
    private String QRCodePath;
    private String Tel;
    private LocalDateTime CreatedDate;
    private LocalDateTime LevelValidityDate;
    private String PersonalProfile;
    private boolean Subscribe;
    private String PersonalDetail;
}
