package com.msdn.service;

import com.msdn.pojo.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hresh
 * @date 2020/5/6 13:34
 * @description
 */
//降级
@Component
public class DeptServiceFallbackFactory implements FallbackFactory {
    @Override
    public DeptFeignService create(Throwable throwable) {
        return new DeptFeignService() {
            @Override
            public boolean addDept(Dept dept) {
                return false;
            }

            @Override
            public Dept queryDept(long id) {
                return new Dept().setDeptId(id).
                        setDpName("id="+id+"=>没有对应的信息，null").
                        setDbSource("服务降级处理");
            }

            @Override
            public List<Dept> queryAll() {
                return null;
            }
        };
    }
}
