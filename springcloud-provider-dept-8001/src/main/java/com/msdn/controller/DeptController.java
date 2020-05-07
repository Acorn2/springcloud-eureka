package com.msdn.controller;

import com.msdn.pojo.Dept;
import com.msdn.service.DeptService;
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
    public Dept getDept(@PathVariable("id") long id) {
        Dept dept = deptService.queryDept(id);
        return dept;
    }

    @GetMapping("/dept/list")
    public List<Dept> queryAll(){
        List<Dept> list = deptService.queryAll();
        return list;
    }

    @PostMapping("/dept/add")
    public boolean addDept(@RequestBody Dept dept){
        System.out.println(dept);
        return deptService.addDept(dept);
    }

    //获取一些配置的信息，得到具体的微服务
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/dept/discovery")
    public Object getService(){
        List<String> services = discoveryClient.getServices();
        System.out.println("discovery=>services:"+services);

        List<ServiceInstance> instances = discoveryClient.getInstances("SPRINGCLOUD-PROVIDER-DEPT");
        for (ServiceInstance instance : instances) {
            System.out.println(
                    instance.getHost()+"\t"+
                            instance.getPort()+"\t"+
                            instance.getUri()+"\t"+
                            instance.getServiceId()
            );
        }
        return discoveryClient;
    }

}
