package com.springboot.hwzl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan("com.springboot.hwzl.dao")//扫描dao
@SpringBootApplication(scanBasePackages="com.springboot.hwzl" )
@EnableCaching//启动使用缓存
@EnableTransactionManagement //启动事务管理
@ComponentScan("com.springboot.hwzl.*")
@ComponentScan("com.springboot.hwzl.configs")
class HwzlApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(HwzlApplication.class, args);
    }

    //重写configure方法，否则在部署到tomcat时，接口将访问不到
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HwzlApplication.class);
    }

}
