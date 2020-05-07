package com.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hresh
 * @date 2020/5/1 19:20
 * @description
 */
@Configuration
public class RuleConfig {

    @Bean
    public IRule getRule(){
        //仅当测试自定义负载均衡策略时使用，算法存在小问题
//        return new MyRandomRule();
        return new RoundRobinRule();
    }
}
