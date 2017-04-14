package com.bjike.goddess.message;

import com.bjike.goddess.message.kafka.KafkaConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;

import java.io.IOException;

/**
 * 扫描com.bjike.goddess.common.consumer 加入过滤器引入userToken
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.bjike.goddess.message.action", "com.bjike.goddess.common.consumer"},
        excludeFilters = {@ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                value = {Configuration.class})})
@ImportResource("classpath:app.xml")
@PropertySource({"classpath:kafka.properties"})

public class Application {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args);
        new KafkaConsumer().consumer();
        System.in.read();
    }

}
