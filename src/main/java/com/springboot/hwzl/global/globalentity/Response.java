package com.springboot.hwzl.global.globalentity;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.io.Serializable;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> implements Serializable{
    private static final long serialVersionUID = 1L;
    public int code;
    public String message;
    public T data;

    public  Response(RetCode retCode){
        this.code = retCode.getCode();
    }

    public Response(int code,String msg){
        this.code = code;
        this.message = msg;
    }


    public  Response(RetCode retCode,String msg){
        this.code = retCode.getCode();
        this.message = msg;
    }

    public  Response(RetCode retCode,T data){
        this.code = retCode.getCode();
        this.data = data;
    }

    public  Response(RetCode retCode,String msg,T data){
        this.code = retCode.getCode();
        this.message = msg;
        this.data = data;
    }

    public Response(int code,String msg,T data){
        this.code = code;
        this.message = msg;
        this.data = data;
    }

}
