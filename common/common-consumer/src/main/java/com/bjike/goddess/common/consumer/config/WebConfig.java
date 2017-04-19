package com.bjike.goddess.common.consumer.config;

import com.bjike.goddess.common.consumer.interceptor.ErrorRequestInterceptor;
import com.bjike.goddess.common.consumer.interceptor.idem.IdempotencyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: [liguiqin]
 * @Date: [2017-01-15 10:10]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Component
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired(required = false)
    private Interceptor interceptor;
    @Autowired(required = false)
    private IdempotencyInterceptor idempotencyFilter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        registry.addInterceptor(new ErrorRequestInterceptor()).addPathPatterns("/**");
        if(null!=idempotencyFilter){
            registry.addInterceptor(idempotencyFilter).excludePathPatterns(idempotencyFilter.getExcludePathPatterns()).addPathPatterns(idempotencyFilter.getPathPatterns());//幂等请求
        }

        if (null != interceptor && interceptor.customerInterceptors() != null) {
            for (HIInfo h : interceptor.customerInterceptors()) {
                System.out.println(h);
                registry.addInterceptor(h.getHandlerInterceptor()).addPathPatterns(h.getPath());
            }
        }
        super.addInterceptors(registry);
    }

}
