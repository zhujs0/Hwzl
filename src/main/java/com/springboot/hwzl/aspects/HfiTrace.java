package com.springboot.hwzl.aspects;


import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HfiTrace {
    String name() default "默认注解信息";
}
