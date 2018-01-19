package com.bjike.goddess.event;

import com.bjike.goddess.event.action.event.WebSocketAction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;

import javax.servlet.MultipartConfigElement;
import java.io.IOException;

/**
 * @Author: [sunfengtao]
 * @Date: [2017-03-29 10:28]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.bjike.goddess.event.action","com.bjike.goddess.event.config", "com.bjike.goddess.common.consumer"},
        excludeFilters = {@ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                value = {})})
@PropertySource(value = {"classpath:permission.properties"},encoding="utf-8")
@ImportResource({"classpath:app.xml"})
@EnableAutoConfiguration(exclude = {ValidationAutoConfiguration.class})
public class Application {
    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        WebSocketAction.setApplicationContext(applicationContext);
        System.in.read();
    }

}
