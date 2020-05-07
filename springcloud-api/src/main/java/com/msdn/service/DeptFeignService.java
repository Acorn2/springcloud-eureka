package com.msdn.service;

import com.msdn.pojo.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author hresh
 * @date 2020/5/1 19:59
 * @description
 */
@Service
@FeignClient(value = "SPRINGCLOUD-PROVIDER-DEPT",fallbackFactory = DeptServiceFallbackFactory.class)
public interface DeptFeignService {

    @PostMapping("/dept/add")
    boolean addDept(@RequestBody Dept dept);

    @GetMapping("/dept/get/{id}")
    Dept queryDept(@PathVariable("id") long id);

    @GetMapping("/dept/list")
    List<Dept> queryAll();
}
