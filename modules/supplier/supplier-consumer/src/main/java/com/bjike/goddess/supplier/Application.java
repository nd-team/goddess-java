package com.bjike.goddess.supplier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.*;

import javax.servlet.MultipartConfigElement;
import java.io.IOException;

/**
 * 扫描com.bjike.goddess.common.consumer 加入过滤器引入userToken
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.bjike.goddess.supplier.action", "com.bjike.goddess.supplier.config", "com.bjike.goddess.common.consumer"},
        excludeFilters = {@ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                value = {Configuration.class})})
@ImportResource("classpath:app.xml")
@PropertySource(value = {"classpath:permission.properties"}, encoding = "utf-8")
@EnableAutoConfiguration(exclude = {ValidationAutoConfiguration.class})
public class Application {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("50MB");
        factory.setMaxRequestSize("50MB");
        return factory.createMultipartConfig();
    }

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args);
        System.in.read();
    }

}
