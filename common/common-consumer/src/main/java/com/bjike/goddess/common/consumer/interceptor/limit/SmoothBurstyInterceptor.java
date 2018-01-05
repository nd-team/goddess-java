package com.bjike.goddess.common.consumer.interceptor.limit;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 限流器
 *
 * @Author: [liguiqin]
 * @Date: [2017-04-14 09:12]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

public class SmoothBurstyInterceptor extends HandlerInterceptorAdapter {

    public enum LimitType {
        DROP,//丢弃
        WAIT //等待
    }

    /**
     * 限流器
     */
    private RateLimiter limiter;
    /**
     * 限流方式
     */
    private LimitType limitType = LimitType.DROP;

    public SmoothBurstyInterceptor() {
        this.limiter = RateLimiter.create(10);
    }

    /**
     * @param tps       限流量 (每秒处理量)
     * @param limitType 限流类型:等待/丢弃(达到限流量)
     */
    public SmoothBurstyInterceptor(int tps, SmoothBurstyInterceptor.LimitType limitType) {
        this.limiter = RateLimiter.create(tps);
        this.limitType = limitType;
    }
    /**
     * @param permitsPerSecond  每秒新增的令牌数
     * @param limitType 限流类型:等待/丢弃(达到限流量)
     */
    public SmoothBurstyInterceptor(double permitsPerSecond, SmoothBurstyInterceptor.LimitType limitType) {
        this.limiter = RateLimiter.create(permitsPerSecond, 1000, TimeUnit.MILLISECONDS);
        this.limitType = limitType;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (limitType.equals(LimitType.DROP)) {
            if (limiter.tryAcquire()) {
                return super.preHandle(request, response, handler);
            }
        } else {
            limiter.acquire();
            return super.preHandle(request, response, handler);
        }
        throw new Exception("网络异常!");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    public RateLimiter getLimiter() {
        return limiter;
    }

    public void setLimiter(RateLimiter limiter) {
        this.limiter = limiter;
    }
}
