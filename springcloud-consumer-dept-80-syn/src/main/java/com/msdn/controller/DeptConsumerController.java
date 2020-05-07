package com.msdn.controller;

import com.msdn.pojo.Dept;
import com.msdn.service.DeptConsumerService;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @author hresh
 * @date 2020/4/28 21:27
 * @description
 */
@RestController
public class DeptConsumerController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DeptConsumerService service;

//    private static final String REST_URL_PREFIX = "http://localhost:8001";
    //通过服务名进行调用
    private static final String REST_URL_PREFIX = "http://SPRINGCLOUD-PROVIDER-DEPT";

    @RequestMapping("/consumer/dept/get/{id}")
    public Dept getDept(@PathVariable("id") long id) throws ExecutionException, InterruptedException {
        Future<Dept> dept1 = service.getDept2(id);

        return dept1.get();
    }

    @RequestMapping("/consumer/dept/getList2/{ids}")
    public List<Dept> getDepts2(@PathVariable("ids") String sid) throws ExecutionException, InterruptedException {
        List<Long> ids = Arrays.stream(sid.split(",")).map(Long::parseLong).collect(Collectors.toList());


        List<Dept> list = ids.stream().map(id -> {
            return restTemplate.getForObject(REST_URL_PREFIX+"/dept/get/"+id, Dept.class);
        }).collect(Collectors.toList());

        return list;
    }

    @RequestMapping("/consumer/dept/getList/{ids}")
    public List<Dept> getDepts(@PathVariable("ids") String sid) throws ExecutionException, InterruptedException {
//        Dept dept = restTemplate.getForObject(REST_URL_PREFIX+"/dept/get/"+id, Dept.class);
        List<Long> ids = Arrays.stream(sid.split(",")).map(Long::parseLong).collect(Collectors.toList());

        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        List<Future<Dept>> depts = ids.stream().map(
                id -> {
                    Future<Dept> dept = service.getDept2(id);
                    return dept;
                }
        ).collect(Collectors.toList());

        List<Dept> list = depts.stream().map(deptFuture -> {
            try {
                return deptFuture.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
        context.close();
        return list;
    }
//
//    @RequestMapping("/consumer/dept/list")
//    public List<Dept> queryAll() throws ExecutionException, InterruptedException {
//        List list = restTemplate.getForObject(REST_URL_PREFIX+"/dept/list",List.class);
//        System.out.println("springcloud-consumer-dept-80-syn=》DeptConsumerController---->"+list.get(0).toString());
//        return list;
//    }

    @RequestMapping("/consumer/dept/list2")
    public void queryAll2() throws ExecutionException, InterruptedException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        Future<Dept> dept1 = service.getDept2(1l);
        Future<Dept> dept2 = service.getDept2(1l);
        Future<Dept> dept3 = service.getDept2(1l);

        Thread.sleep(3000);
        Future<Dept> dept4 = service.getDept2(4l);

        System.out.println(dept1.get().toString());
        System.out.println(dept2.get().toString());
        System.out.println(dept3.get().toString());
        System.out.println(dept4.get().toString());

        context.close();
    }

//    @RequestMapping(name = "/consumer/dept/add")
//    public boolean addDept(Dept dept){
//        return restTemplate.postForObject(REST_URL_PREFIX+"/dept/add",dept,Boolean.class);
//    }
}
