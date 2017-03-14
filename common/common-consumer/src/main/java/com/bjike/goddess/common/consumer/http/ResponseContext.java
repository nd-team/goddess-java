package com.bjike.goddess.common.consumer.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by huanghuanlai on 2017/1/14.
 */
public final class ResponseContext {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseContext.class);

    private ResponseContext(){}

    public static HttpServletResponse get(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();//springmvc 自带
    }

    public static void writeData(String data){
        HttpServletResponse response = get();
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().print(data);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

}
