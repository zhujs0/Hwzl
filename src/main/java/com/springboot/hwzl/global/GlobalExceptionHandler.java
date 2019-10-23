package com.springboot.hwzl.global;


import com.springboot.hwzl.global.globalentity.Response;
import com.springboot.hwzl.global.globalentity.RetCode;
import org.apache.ibatis.binding.BindingException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.net.BindException;
import java.net.ConnectException;
import java.sql.SQLException;

@RestControllerAdvice    //等价于 @ResponseBody @ControllerAdvice组合
public class GlobalExceptionHandler {

    /**
     * 自定义异常的捕获
     * 自定义抛出异常。统一的在这里捕获返回JSON格式
     * @param exception
     * @param request
     * @param <T>
     * @return
     */
    @ExceptionHandler(value = {BusinessException.class})
    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)//http返回的Status
    public <T> Response<T> exceptionHandler(BusinessException exception, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();//抛出异常的请求地址
        return new Response<>(exception.retCode,exception.msg);
    }

    /**
     * 请求参数类型错误异常的捕获
     * @param e
     * @return
     */
    @ExceptionHandler(value={BindException.class})
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    public Response badRequest(BindException e){
        return new Response(RetCode.BAD_REQUEST,e.getMessage());
    }

    /**
     * 404错误异常的捕获
     * @param e
     * @return
     */
    @ExceptionHandler(value={NoHandlerFoundException.class})
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    public Response badRequestNotFound(BindException e){
        return new Response (RetCode.NOT_FOUND,e.getMessage());
    }

    /**
     * mybatis未绑定异常
     * @param e
     * @return
     */
    @ExceptionHandler(BindingException.class)
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    public Response mybatis(Exception e){
        return new Response(RetCode.BOUND_STATEMENT_NOT_FOUNT,e.getMessage());
    }

    /**
     * 数据库操作出现异常
     * @param e
     * @return
     */
    @ExceptionHandler(value={SQLException.class, DataAccessException.class})
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    public Response sqlError(Exception e){
        return new Response(RetCode.DATABASE_ERROR,e.getMessage());
    }

    /**
     * 网络连接失败！
     * @param e
     * @return
     */
    @ExceptionHandler(value={ConnectException.class})
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    public Response connect(Exception e){
        return new Response(RetCode.CONNECTION_ERROR,e.getMessage());
    }


    /**
     * 异常类
     * @param e
     * @return
     */
    @ExceptionHandler(value={Exception.class})
    @ResponseStatus(value=HttpStatus.SERVICE_UNAVAILABLE)
    public Response serverError(Exception e){
        return new Response(RetCode.INTERNAL_SERVER_ERROR,e.toString());
    }





}
