package com.msdn.controller;

import com.msdn.pojo.Dept;
import com.msdn.service.DeptService;
import com.msdn.service.DeptServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @author hresh
 * @date 2020/4/28 17:05
 * @description
 */
@RestController
public class DeptController {

    @Autowired
    DeptServiceImpl deptService;

    @GetMapping("/dept/get/{id}")
    public Dept getDept(@PathVariable("id") long id) {
        Dept dept = deptService.queryDept(id);
        return dept;
    }

    @GetMapping("/dept/list")
    public List<Dept> queryAll() throws ExecutionException, InterruptedException {
        List<Dept> list = deptService.queryAll();
        return list;
    }

    @PostMapping("/dept/add")
    public boolean addDept(@RequestBody Dept dept){
        System.out.println(dept);
        return deptService.addDept(dept);
    }

    @RequestMapping("/dept/getList/{ids}")
    public List<Dept> getDepts2(@PathVariable("ids") String sid) throws ExecutionException, InterruptedException {
        List<Long> ids = Arrays.stream(sid.split(",")).map(Long::parseLong).collect(Collectors.toList());

        List<Dept> list = deptService.getDepts(ids);

        return list;
    }
}
