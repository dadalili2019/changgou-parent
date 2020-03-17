package com.changgou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author caoqian
 * @ClassName EurekaApplication  微服务注册中心
 * @Date 2020/3/16 21:37
 * @Version 1.0
 */
@SpringBootApplication
@EnableEurekaServer //开启Eureka服务
public class EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class,args);
    }
}
