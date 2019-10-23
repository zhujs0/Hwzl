package com.springboot.hwzl.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 切面拦截,暂无启用
 */
public class MethodInterceptorTest implements org.aopalliance.intercept.MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("method "+methodInvocation.getMethod()+" is called on "+
                methodInvocation.getThis()+" with args "+methodInvocation.getArguments());
        Object ret=methodInvocation.proceed();
        System.out.println("method "+methodInvocation.getMethod()+" returns "+ret);
        return ret;

    }
}
