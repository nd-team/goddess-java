package com.bjike.goddess.festival;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.context.annotation.*;

import javax.servlet.MultipartConfigElement;
import java.io.IOException;

/**
 * 扫描com.bjike.goddess.common.consumer 加入过滤器引入userToken
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.bjike.goddess.festival.action", "com.bjike.goddess.festival.config","com.bjike.goddess.common.consumer"})
@ImportResource("classpath:app.xml")
@PropertySource(value = {"classpath:permission.properties"},encoding="utf-8")
@EnableAutoConfiguration(exclude = {ValidationAutoConfiguration.class})
public class Application {



    public static void main(String[] args) throws IOException {

        SpringApplication.run(Application.class, args);
        System.in.read();
    }




}
