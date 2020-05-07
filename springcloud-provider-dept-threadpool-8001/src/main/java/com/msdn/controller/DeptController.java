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
}
