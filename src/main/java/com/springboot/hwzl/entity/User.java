package com.springboot.hwzl.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

//@ApiModel
@Data
public class User
{
    //@ApiModelProperty("用户ID")
    //@JsonProperty(value = "UserId")
    private Integer userId;
    //@ApiModelProperty("用户名称")
    //@JsonProperty(value = "UserName")
    private String userName;
    //@ApiModelProperty("用户账号")
    //@JsonProperty(value = "Account")
    private String account;
    //@ApiModelProperty("用户密码")
    //@JsonProperty(value = "Password")
    private String password;

}
