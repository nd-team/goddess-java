package com.bjike.goddess.common.consumer.interceptor.auth;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 资源权限拦截
 *
 * @Author: [liguiqin]
 * @Date: [2017-04-15 09:34]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class HystrixIntercept extends HandlerInterceptorAdapter {
    private static  final String[] AUTH_URL =new String[]{"/user/*","/group*"};
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        wrapWithHystrixCommnad(handler);
        return super.preHandle(request, response, handler);
    }

    private HystrixCommand<Object> wrapWithHystrixCommnad(Object handler) {

        return new HystrixCommand<Object>(setter()) {
            @Override
            protected Object run() throws Exception {
                try {
                    return handler;
                } catch (Throwable throwable) {
                    if(throwable instanceof ActException){
                        throw new HystrixBadRequestException(throwable.getMessage(),throwable);
                    }else{
                        throw (Exception) throwable;
                    }
                }
            }

            @Override
            protected Object getFallback() {
                return new ActResult("服务不可用");
            }
        };
    }

    private HystrixCommand.Setter setter() {
        return HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("user"));
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
