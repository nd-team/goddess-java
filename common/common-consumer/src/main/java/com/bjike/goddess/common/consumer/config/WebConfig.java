package com.bjike.goddess.common.consumer.config;

import com.bjike.goddess.common.consumer.interceptor.ErrorRequestInterceptor;
import com.bjike.goddess.common.consumer.interceptor.SecurityIntercept;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private Interceptor interceptor;
    @Autowired
    private UserAPI userAPI;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new SecurityIntercept(userAPI)).addPathPatterns("/**");
        registry.addInterceptor(new ErrorRequestInterceptor()).addPathPatterns("/**");
        if(interceptor.customerInterceptors()!=null){
            for(HIInfo h : interceptor.customerInterceptors()){
                registry.addInterceptor(h.getHandlerInterceptor()).addPathPatterns(h.getPath());
            }
        }
        super.addInterceptors(registry);
    }

}
