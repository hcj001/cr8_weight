package com.ohoyee.weight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 * 打jar包方式；本地idea启动方式
 */@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
public class SmartFactoryCr8WeightApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartFactoryCr8WeightApplication.class, args);
    }

}

/**
 * 打war包方式
 */
//@SpringBootApplication
//@EnableScheduling
//@EnableTransactionManagement
//public class SmartFactoryCr8WeightApplication extends SpringBootServletInitializer {
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(SmartFactoryCr8WeightApplication.class);
//    }
//
//    public static void main(String[] args) throws Exception {
//        SpringApplication.run(SmartFactoryCr8WeightApplication.class, args);
//    }
//
//}

