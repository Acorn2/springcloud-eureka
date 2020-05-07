package com.msdn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author hresh
 * @date 2020/4/28 17:12
 * @description
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class DeptProvider_8002 {

    public static void main(String[] args) {
        SpringApplication.run(DeptProvider_8002.class,args);
    }
}
