package com.msdn.service;

import com.msdn.mapper.DeptMapper;
import com.msdn.pojo.Dept;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @HystrixCommand(groupKey = "ego-dept-provider",
            commandKey = "queryDept",
            threadPoolKey = "ego-dept-provider", //给线程名添加前缀
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),//线程池大小
                    @HystrixProperty(name = "maxQueueSize", value = "100"),//最大队列长度
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),//线程存活时间
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15")//拒绝请求
            },
            fallbackMethod = "getDeptHystrix")
    @Override
    public Dept queryDept(long id) {
        return deptMapper.queryDept(id);
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
