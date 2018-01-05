package com.bjike.goddess.staffing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;


@SpringBootApplication
@ComponentScan(basePackages = {"com.bjike.goddess.staffing.action", "com.bjike.goddess.staffing.config", "com.bjike.goddess.common.consumer"})
@ImportResource("classpath:app.xml")
@PropertySource(value = {"classpath:permission.properties"}, encoding = "utf-8")
@EnableAutoConfiguration(exclude = {ValidationAutoConfiguration.class})
public class Application {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args);
        System.in.read();
    }

}
