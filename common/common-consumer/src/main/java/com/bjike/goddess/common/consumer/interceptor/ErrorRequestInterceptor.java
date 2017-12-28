package com.bjike.goddess.common.consumer.interceptor;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.consumer.restful.ActResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by huanghuanlai on 2017/2/27.
 */
@Component
public class ErrorRequestInterceptor implements HandlerInterceptor {

    private static Logger CONSOLE = LoggerFactory.getLogger(ErrorRequestInterceptor.class);

    @Autowired
    private Environment environment;

    @PostConstruct
    public void init(){
        if(StringUtils.isBlank(environment.getProperty("spring.application.name"))){
            CONSOLE.error(ModuleInfo.MODULE_NAME);
        }else{
            ModuleInfo.MODULE_NAME = environment.getProperty("spring.application.name");
        }

    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if(200!=httpServletResponse.getStatus()){
            ActResult result = new ActResult();
            httpServletResponse.setHeader("Content-Type","application/json;charset=UTF-8");
            if(httpServletResponse.getStatus()==404){
                result.setMsg("访问的请求不存在.");
            }else if(httpServletResponse.getStatus()==400){
                result.setMsg("请检查请求参数.");
            }else if(httpServletResponse.getStatus()==500){
                result.setMsg(((Exception)o).getMessage());
            }else if(httpServletResponse.getStatus()==405){
                result.setMsg("请求方式不支持,请检查.");
            }
            result.setCode(httpServletResponse.getStatus());
            result.setData(ModuleInfo.MODULE_NAME.toString());
            httpServletResponse.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
