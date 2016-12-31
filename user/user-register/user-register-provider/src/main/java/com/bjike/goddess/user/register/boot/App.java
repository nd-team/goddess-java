package com.bjike.goddess.user.register.boot;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
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
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryBean",basePackages = {"org.ndshop.user.common.dao"})
@EnableTransactionManagement(proxyTargetClass = true)
@EnableCaching
@PropertySource({"classpath:config.properties"})
@ImportResource({"classpath:dubbo-demo-consumer.xml"})
@ComponentScan(basePackages = {"user_register_code", "com.bjike.goddess.user.common", "com.bjike.goddess.user.register"},
        excludeFilters = {@ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                value = {Configuration.class})})
public class App {


}
