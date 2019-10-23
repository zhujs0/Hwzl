package com.springboot.hwzl.global;

import com.springboot.hwzl.global.globalentity.RetCode;
import lombok.Data;

@Data
public class BusinessException extends  RuntimeException {
    private static final long serialVersionUID = -1L;
    public int code;
    public String msg;
    public RetCode retCode;
    public BusinessException(RetCode retCode, String message)
    {
        this.code=retCode.getCode();
        this.msg=message;
        this.retCode=retCode;
    }
}
