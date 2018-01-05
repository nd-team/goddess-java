package com.bjike.goddess.common.consumer.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by huanghuanlai on 2017/1/14.
 */
public final class RequestContext {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestContext.class);

    private RequestContext(){}

    public static HttpServletRequest get(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();//springmvc 自带
    }

}
