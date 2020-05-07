//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.myrule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MyRandomRule extends AbstractLoadBalancerRule {

    private int count = 0;  //每个服务执行次数
    private int providerNum = 0;    //当前哪个服务被执行

    public MyRandomRule(){

    }

    @SuppressWarnings({"RCN_REDUNDANT_NULLCHECK_OF_NULL_VALUE"})
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        } else {
            Server server = null;

            while(server == null) {
                if (Thread.interrupted()) {
                    return null;
                }

                List<Server> upList = lb.getReachableServers(); //获得活着的服务
                List<Server> allList = lb.getAllServers(); //获得全部的服务
                int serverCount = allList.size();
                if (serverCount == 0) {
                    return null;
                }

                //核心部分
//                int index = this.chooseRandomInt(serverCount);
//                server = (Server)upList.get(index);
                //我们定义属于自己的执行策略，目前我们有3个provider，那么决定每个provider执行3次，然后接着执行下一个provider
                if(count<3){
                    server = (Server)upList.get(providerNum);
                    count++;
                }else{
                    count = 0;
                    providerNum++;
                    if(providerNum>=serverCount){
                        providerNum = 0;
                    }
                    server = (Server)upList.get(providerNum);
                }


                if (server == null) {
                    Thread.yield();
                } else {
                    if (server.isAlive()) {
                        return server;
                    }

                    server = null;
                    Thread.yield();
                }
            }

            return server;
        }
    }

    protected int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }

    public Server choose(Object key) {
        return this.choose(this.getLoadBalancer(), key);
    }

    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }
}
