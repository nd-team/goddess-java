package com.bjike.goddess.oilcardprepared;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;


@SpringBootApplication
@ComponentScan(basePackages = {"com.bjike.goddess.oilcardprepared.action","com.bjike.goddess.oilcardprepared.config","com.bjike.goddess.common.consumer"})
@PropertySource(value = {"classpath:permission.properties"},encoding="utf-8")
@ImportResource("classpath:app.xml")
@EnableAutoConfiguration(exclude = {ValidationAutoConfiguration.class})
public class Application {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args);
        System.in.read();
    }

}
