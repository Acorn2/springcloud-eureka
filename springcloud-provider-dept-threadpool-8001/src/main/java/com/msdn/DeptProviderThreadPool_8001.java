package com.msdn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author hresh
 * @date 2020/4/28 17:12
 * @description
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class DeptProviderThreadPool_8001 {

    public static void main(String[] args) {
        SpringApplication.run(DeptProviderThreadPool_8001.class,args);
    }
}
