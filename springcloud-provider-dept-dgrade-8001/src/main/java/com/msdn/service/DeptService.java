package com.msdn.service;

import com.msdn.pojo.Dept;

import java.util.List;
import java.util.Map;

/**
 * @author hresh
 * @date 2020/4/28 16:54
 * @description
 */
public interface DeptService {

    boolean addDept(Dept dept);
    Dept queryDept(long id);
    List<Dept> queryAll();
    List<Dept> getDepts(List<Long> ids);
}
