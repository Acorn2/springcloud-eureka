package com.msdn.service;

import com.google.common.base.Joiner;
import com.msdn.pojo.Dept;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @author hresh
 * @date 2020/5/5 10:11
 * @description
 */
@Service
public class DeptConsumerService {

    @Autowired
    RestTemplate restTemplate;

    private static final String REST_URL_PREFIX = "http://SPRINGCLOUD-PROVIDER-DEPT";

    //利用 hystrix 合并请求
    @HystrixCollapser(batchMethod = "batchDept", scope =
            com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL,
            collapserProperties = {
                    //请求时间间隔在 2s 之内的请求会被合并为一个请求,默认为 10ms
                    @HystrixProperty(name =
                            "timerDelayInMilliseconds", value = "2000"),
                    //设置触发批处理执行之前，在批处理中允许的最大请求数。
                    @HystrixProperty(name = "maxRequestsInBatch",
                            value = "200"),
            })
    public Future<Dept> getDept2(Long id){
        return null;
    }

    @HystrixCommand
    public List<Dept> batchDept(List<Long> ids){
        System.out.println("batchDept---------"+ids+"Thread.currentThread().getName():" + Thread.currentThread().getName());
        List<Dept> list = new ArrayList<>();
        String sid = Joiner.on(",").join(ids);
        Dept[] depts = restTemplate.getForObject(REST_URL_PREFIX+"/dept/getList/"+sid, Dept[].class);
        list = Arrays.asList(depts);
        return list;
    }
}
