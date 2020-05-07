package com.msdn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author hresh
 * @date 2020/4/29 10:25
 * @description
 */
@EnableEurekaServer //启动服务发现，接受注册
@SpringBootApplication
public class EurekaServer_7003 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServer_7003.class, args);
    }
}
