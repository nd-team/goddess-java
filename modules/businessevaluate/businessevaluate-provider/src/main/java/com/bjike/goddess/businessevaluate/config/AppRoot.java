package com.bjike.goddess.businessevaluate.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = {"com.bjike.goddess.businessevaluate.dao"})//jpa 所在包
@EnableTransactionManagement(proxyTargetClass = true)
@EnableCaching
@PropertySource({"classpath:config.properties"})
@ImportResource({"classpath:application.xml"})
@ComponentScan(basePackages = {"com.bjike.goddess.businessevaluate"},
        excludeFilters = {@ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                value = {Configuration.class})})
public class AppRoot{

}
