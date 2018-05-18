package com.bjike.goddess.recruit;

import com.bjike.goddess.recruit.action.recruit.MyWebSocket;
import com.bjike.goddess.recruit.api.WorkOGAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.servlet.MultipartConfigElement;
import java.io.IOException;

/**
 * 扫描com.bjike.goddess.common.consumer 加入过滤器引入userToken
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-15 09:52]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.bjike.goddess.recruit.action","com.bjike.goddess.common.consumer","com.bjike.goddess.recruit.config"},
        excludeFilters = {@ComponentScan.Filter(
                type = FilterType.ANNOTATION)})
@PropertySource(value = {"classpath:permission.properties"},encoding="utf-8")
@ImportResource({"classpath:app.xml"})
@EnableAutoConfiguration(exclude = {ValidationAutoConfiguration.class})
@EnableScheduling
public class Application{

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("800MB");
        factory.setMaxRequestSize("800MB");
        return factory.createMultipartConfig();
    }

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class,args);
        System.in.read();
        MyWebSocket.setContext(applicationContext);
    }

}
