package com.bjike.goddess.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;

import java.io.IOException;

/**
 * 扫描com.bjike.goddess.common.consumer 加入过滤器引入userToken
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.bjike.goddess.user.action", "com.bjike.goddess.user.config","com.bjike.goddess.common.consumer"},
        excludeFilters = {@ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                value = {Configuration.class})})
@ImportResource("classpath:app.xml")
public class Application {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args);
        System.in.read();
    }

}
