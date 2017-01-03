package com.bjike.goddess.user.login.boot;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [扫描配置文件及自身包，依赖包]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Configuration

@EnableJpaRepositories(basePackages = {"com.bjike.goddess.user.common.dao"})
@EnableTransactionManagement(proxyTargetClass = true)
@EnableCaching
@PropertySource({"classpath:config.properties"})
@ComponentScan(basePackages = {"com.bjike.goddess.user.common", "com.bjike.goddess.user.login"},
        excludeFilters = {@ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                value = {Configuration.class})})
public class App {

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 多个拦截器组成一个拦截器链
//        // addPathPatterns 用于添加拦截规则
//        // excludePathPatterns 用户排除拦截
////        registry.addInterceptor(new WatchHandlerInterceptor()).addPathPatterns("/**");
////        registry.addInterceptor(new MyInterceptor2()).addPathPatterns("/**");
//        super.addInterceptors(registry);
//    }

}
