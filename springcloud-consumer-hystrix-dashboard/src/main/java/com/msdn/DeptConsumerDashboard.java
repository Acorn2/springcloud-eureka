package com.msdn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author hresh
 * @date 2020/5/6 14:25
 * @description
 */
@SpringBootApplication
@EnableHystrixDashboard
@EnableHystrix
public class DeptConsumerDashboard {

    public static void main(String[] args) {
        SpringApplication.run(DeptConsumerDashboard.class);
    }
}
