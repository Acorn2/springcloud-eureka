package com.msdn.mapper;

import com.msdn.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hresh
 * @date 2020/4/28 16:37
 * @description
 */
@Mapper
@Repository
public interface DeptMapper {

    boolean addDept(Dept dept);
    Dept queryDept(long id);
    List<Dept> queryAll();
    List<Dept> getDepts(List<Long> ids);
}
