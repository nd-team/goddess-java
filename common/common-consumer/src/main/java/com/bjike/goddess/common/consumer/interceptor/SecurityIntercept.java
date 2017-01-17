package com.bjike.goddess.common.consumer.interceptor;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.consumer.auth.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @Author: [liguiqin]
 * @Date: [2017-01-14 14:34]
 * @Description: [登录安全拦截]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Component
public class SecurityIntercept extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            // isLogin
            handlerLogin(response);
            return false;
        }
        final Method method = ((HandlerMethod) handler).getMethod();
        final Class<?> clazz = method.getDeclaringClass();
        //判断该类或者方法上是否有登录安全认证注解
        if (clazz.isAnnotationPresent(LoginAuth.class) || method.isAnnotationPresent(LoginAuth.class)) {
            // isLogin
            handlerLogin(response);
            return false;
        }
        Object token = request.getAttribute("userToken");
        System.out.println(token);
        return true;
    }

    private void handlerLogin(HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8"); //转码
        PrintWriter out = response.getWriter();
        out.flush();
        response.setStatus(200);
        ActResult result = new ActResult();
        result.setMsg("请先登录，再进行操作！");
        result.setCode(403);
        out.println(JSON.toJSONString(result));
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        Object token = request.getAttribute("userToken");
        System.out.println(token);
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        Object token = httpServletRequest.getAttribute("userToken");
        System.out.println(token);
//        RpcContext.getContext().setAttachment("userToken", token);

    }
}
