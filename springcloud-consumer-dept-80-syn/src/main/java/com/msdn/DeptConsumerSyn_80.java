package com.msdn;

import com.msdn.pojo.Dept;
import com.msdn.service.DeptConsumerService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.concurrent.Future;

/**
 * @author hresh
 * @date 2020/4/28 21:34
 * @description
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class DeptConsumerSyn_80 {

    public static void main(String[] args) {
        SpringApplication.run(DeptConsumerSyn_80.class,args);
    }

}
