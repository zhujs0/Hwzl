package com.springboot.hwzl.interceptor;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;

/**
 * 切面拦截,暂无启用
 */
//@Configuration
public class InterceptorConfig {
    public static final String traceExecution = "execution(* com.hfi.aop..*.*(..))";//该包下的所有方法


    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor2() {
        MethodInterceptorTest interceptor = new MethodInterceptorTest();
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(traceExecution);

        // 配置增强类advisor
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(interceptor);
        return advisor;
    }

}
