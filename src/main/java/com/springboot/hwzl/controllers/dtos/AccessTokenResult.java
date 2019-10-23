package com.springboot.hwzl.controllers.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccessTokenResult {
    public  String code;
    public  String access_token;
    public LocalDateTime requestDate;
    public  int expires_in;
}
