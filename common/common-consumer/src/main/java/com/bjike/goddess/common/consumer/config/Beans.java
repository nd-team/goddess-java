package com.bjike.goddess.common.consumer.config;

import com.bjike.goddess.common.consumer.interceptor.idem.IdempotencyInterceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by lake on 17-4-19.
 */
@Configuration
public class Beans {

//    @Bean
    public IdempotencyInterceptor idempotencyInterceptor(){
        return new IdempotencyInterceptor();
    }

}
