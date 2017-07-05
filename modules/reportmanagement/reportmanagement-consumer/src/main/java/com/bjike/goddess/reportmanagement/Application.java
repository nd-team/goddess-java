package com.bjike.goddess.reportmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;


@SpringBootApplication
@ComponentScan(basePackages = {"com.bjike.goddess.reportmanagement.action","com.bjike.goddess.reportmanagement.config", "com.bjike.goddess.common.consumer"})
//@PropertySource(value = {"classpath:permission.properties"},encoding="utf-8")
@ImportResource("classpath:app.xml")
@EnableAutoConfiguration(exclude = {ValidationAutoConfiguration.class})
public class Application {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args);
        System.in.read();
    }

}
