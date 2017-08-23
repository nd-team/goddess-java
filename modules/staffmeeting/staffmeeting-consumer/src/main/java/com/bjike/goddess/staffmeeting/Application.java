package com.bjike.goddess.staffmeeting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.context.annotation.*;

import java.io.IOException;

/**
 * @Author: [sunfengtao]
 * @Date: [2017-03-29 10:28]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.bjike.goddess.staffmeeting.action", "com.bjike.goddess.common.consumer",
        "com.bjike.goddess.staffmeeting.config"},
        excludeFilters = {@ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                value = {Configuration.class})})
@PropertySource(value = {"classpath:permission.properties"},encoding="utf-8")
@ImportResource({"classpath:app.xml"})
@EnableAutoConfiguration(exclude = {ValidationAutoConfiguration.class})
public class Application {


    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("800MB");
        factory.setMaxRequestSize("800MB");
        return factory.createMultipartConfig();
    }

    public static void main(String[] args) throws IOException {

        SpringApplication.run(Application.class, args);
        System.in.read();
    }

}
