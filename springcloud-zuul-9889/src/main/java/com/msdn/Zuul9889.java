package com.msdn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author hresh
 * @date 2020/5/7 14:04
 * @description
 */
@SpringBootApplication
@EnableZuulProxy
public class Zuul9889 {

    public static void main(String[] args) {
        SpringApplication.run(Zuul9889.class);
    }
}
