package com.msdn.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author hresh
 * @date 2020/4/28 16:27
 * @description
 */

/**
 * chain 的中文含义是链式的，为一个布尔值， 如果为 true 生成的 set 方法返回 this，
 * 为 false 生成的 set 方法是 void 类型。  默认为 false，除非当 fluent 为 true时，chain 默认则为 true 。
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Dept implements Serializable {
    private long deptId;
    private String dpName;
    private String dbSource;

    public Dept(String dpName) {
        this.dpName = dpName;
    }
}
