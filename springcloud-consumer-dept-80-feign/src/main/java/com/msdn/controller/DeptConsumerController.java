package com.msdn.controller;

import com.msdn.pojo.Dept;
import com.msdn.service.DeptFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hresh
 * @date 2020/4/28 21:27
 * @description
 */
@RestController
public class DeptConsumerController {

    @Autowired
    DeptFeignService service;

    @RequestMapping("/consumer/dept/get/{id}")
    public Dept getDept(@PathVariable("id") long id) {
        return service.queryDept(id);
    }

    @RequestMapping("/consumer/dept/list")
    public List<Dept> queryAll() {
        return service.queryAll();
    }

    @RequestMapping(name = "/consumer/dept/add")
    public boolean addDept(Dept dept) {
        return service.addDept(dept);
    }

}
