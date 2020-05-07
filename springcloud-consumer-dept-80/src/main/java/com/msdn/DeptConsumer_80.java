package com.msdn;

import com.myrule.RuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author hresh
 * @date 2020/4/28 21:34
 * @description
 */
@SpringBootApplication
@EnableEurekaClient
//在微服务启动时去加载我们自定义的负载均衡策略
@RibbonClient(name = "SPRINGCLOUD-PROVIDER-DEPT",configuration = RuleConfig.class)
//@EnableCircuitBreaker
public class DeptConsumer_80 {

    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer_80.class,args);
    }
}
