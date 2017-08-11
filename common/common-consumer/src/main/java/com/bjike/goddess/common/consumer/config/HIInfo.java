package com.bjike.goddess.common.consumer.config;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @Author: [liguiqin]
 * @Date: [2017-04-14 10:15]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class HIInfo {

    public HIInfo(HandlerInterceptor handlerInterceptor, String path) {
        this.path = path;
        this.handlerInterceptor = handlerInterceptor;
    }

    /**
     * 拦截链接
     */
    private String path;
    /**
     * 拦截器
     */
    private HandlerInterceptor handlerInterceptor;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HandlerInterceptor getHandlerInterceptor() {
        return handlerInterceptor;
    }

    public void setHandlerInterceptor(HandlerInterceptor handlerInterceptor) {
        this.handlerInterceptor = handlerInterceptor;
    }
}
