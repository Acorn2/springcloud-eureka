package com.msdn.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author hresh
 * @date 2020/4/28 21:27
 * @description
 */
@Configuration
public class MyConfig {

    //配置负载均衡实现RestTemplate
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
