package com.msdn.controller;

import com.msdn.pojo.Dept;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author hresh
 * @date 2020/4/28 21:27
 * @description
 */
@RestController
public class DeptConsumerController {

    @Autowired
    RestTemplate restTemplate;

//    private static final String REST_URL_PREFIX = "http://localhost:8001";
    //通过服务名进行调用
    private static final String REST_URL_PREFIX = "http://SPRINGCLOUD-PROVIDER-DEPT";

//    @HystrixCommand(fallbackMethod = "getDeptHystrix")
    @RequestMapping("/consumer/dept/get/{id}")
    public Dept getDept(@PathVariable("id") long id) {
        Dept dept = restTemplate.getForObject(REST_URL_PREFIX+"/dept/get/"+id, Dept.class);
        return dept;
    }

//    public Dept getDeptHystrix(long id){
//        return new Dept().setDeptId(id).
//                setDpName("id="+id+"=>没有对应的信息，null").
//                setDbSource("no database in MySQL");
//    }

    @RequestMapping("/consumer/dept/list")
    public List<Dept> queryAll(){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/list",List.class);
    }

    @RequestMapping(name = "/consumer/dept/add")
    public boolean addDept(Dept dept){
        return restTemplate.postForObject(REST_URL_PREFIX+"/dept/add",dept,Boolean.class);
    }
}
