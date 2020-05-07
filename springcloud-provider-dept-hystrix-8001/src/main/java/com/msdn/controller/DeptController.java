package com.msdn.controller;

import com.msdn.pojo.Dept;
import com.msdn.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hresh
 * @date 2020/4/28 17:05
 * @description
 */
@RestController
public class DeptController {

    @Autowired
    DeptService deptService;

    @GetMapping("/dept/get/{id}")
//    @HystrixCommand(fallbackMethod = "getDeptHystrix")
//    @HystrixCommand(fallbackMethod = "getDeptHystrix",
//            commandProperties = {
//                    //默认 20 个;10s 内请求数大于 20 个时就启动熔断器，当请求符合熔断条件时将触发 getFallback()。
//                    @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD,
//                            value = "10"),
//                    //请求错误率大于 50%时就熔断，然后 for 循环发起请求，当请求符合熔断条件时将触发 getFallback()。
//                    @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE,
//                            value = "50"),
//                    //默认 5 秒;熔断多少秒后去尝试请求
//                    @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS,
//                            value = "5000"),
//            })
    @HystrixCommand(groupKey = "ego-product-provider",
            commandKey = "getDept",
            threadPoolKey = "ego-product-provider", //给线程名添加前缀
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),//线程池大小
                    @HystrixProperty(name = "maxQueueSize", value = "100"),//最大队列长度
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),//线程存活时间
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15")//拒绝请求
            },
            fallbackMethod = "getDeptHystrix")
    public Dept getDept(@PathVariable("id") long id){
        Dept dept = deptService.queryDept(id);
//        if (dept == null){
//            throw new RuntimeException("id="+id+"=>没有对应的信息，null");
//        }
        return dept;
    }

    public Dept getDeptHystrix(@PathVariable("id") long id){
        return new Dept().setDeptId(id).
                setDpName("id="+id+"=>没有对应的信息，null").
                setDbSource("no database in MySQL");
    }
}
