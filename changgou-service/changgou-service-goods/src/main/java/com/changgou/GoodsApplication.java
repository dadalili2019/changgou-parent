package com.changgou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author caoqian
 * @ClassName GoodsApplication
 * @Date 2020/3/17 4:33
 * @Version 1.0
 */
@SpringBootApplication
@EnableEurekaClient //开启Eureka客户端
@MapperScan(basePackages = {"com.changgou.dao"})
public class GoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class, args);
    }
}
