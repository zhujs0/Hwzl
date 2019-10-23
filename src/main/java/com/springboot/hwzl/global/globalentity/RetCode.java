package com.springboot.hwzl.global.globalentity;

public enum RetCode {
    // 成功
    SUCCESS(200),

    //错误请求
    BAD_REQUEST(400),

    // 未认证（签名错误）
    UNAUTHORIZED(401),

    // 接口不存在
    NOT_FOUND(404),

    // 服务器内部错误
    INTERNAL_SERVER_ERROR(500),

    //警告
    WARNING(10000),

    //连接失败
    CONNECTION_ERROR(10001),

    //mybatis未绑定异常，mapper异常
    BOUND_STATEMENT_NOT_FOUNT(10002),

    //数据库操作异常
    DATABASE_ERROR(10003);



    private int code;

    RetCode(int code) {
        this.code = code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
