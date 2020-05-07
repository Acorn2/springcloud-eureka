package com.msdn.config;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRegistration;

/**
 * @author hresh
 * @date 2020/5/6 14:53
 * @description
 */
@Configuration
public class ServletConfig {

//    @Bean
//    public ServletRegistrationBean getServletRegistrationBean(){
//        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new HystrixMetricsStreamServlet());
//        servletRegistrationBean.addUrlMappings("/actuator/hystrix.stream");
//        return servletRegistrationBean;
//    }
}
