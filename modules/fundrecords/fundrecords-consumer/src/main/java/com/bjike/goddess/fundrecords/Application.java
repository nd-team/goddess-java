package com.bjike.goddess.fundrecords;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
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
@ComponentScan(basePackages = {"com.bjike.goddess.fundrecords.action","com.bjike.goddess.fundrecords.config","com.bjike.goddess.common.consumer"},
        excludeFilters = {@ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                value = {Configuration.class})})
@ImportResource({"classpath:app.xml"})
@PropertySource(value = {"classpath:permission.properties"},encoding="utf-8")
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

        SpringApplication.run(Application.class,args);
        System.in.read();
    }

}
