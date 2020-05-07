package com.msdn.service;

import com.msdn.mapper.DeptMapper;
import com.msdn.pojo.Dept;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author hresh
 * @date 2020/4/28 16:54
 * @description
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    DeptMapper deptMapper;

    @Override
    public boolean addDept(Dept dept) {
        return deptMapper.addDept(dept);
    }

    @HystrixCommand(fallbackMethod = "getDeptHystrix")
    @Override
    public Dept queryDept(long id) {
        Dept dept = deptMapper.queryDept(id);
        if (dept == null){
            throw new RuntimeException("id="+id+"=>没有对应的信息，null");
        }
        return dept;
    }

    public Dept getDeptHystrix(long id){
        return new Dept().setDeptId(id).
                setDpName("id="+id+"=>没有对应的信息，null").
                setDbSource("no database in MySQL");
    }

    @Override
    public List<Dept> queryAll() {
        return deptMapper.queryAll();
    }
}
