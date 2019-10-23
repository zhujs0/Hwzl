package com.springboot.hwzl.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TracingAspect {
    @Before("@annotation(hfiTrace)")
    public void before(JoinPoint point, HfiTrace hfiTrace){
        System.out.println("method " + point.getSignature().getName() + " is called on " + point.getThis() + " with " +
                "args" +
                " " + point.getArgs());
        System.out.println("before invoke: "+ hfiTrace.name());
    }

}
