package com.bjike.goddess.common.consumer.interceptor;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.consumer.auth.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * 登录安全拦截(仅检测是否有携带token,用token获取用户的时候再进行token有无效判定)
 * RpcContext.getContext().getAttachment(key)获取隐形参数，
 * 假如在调用之前又进行了远程调用（如：userApi.findUser()）,则隐藏参数失效
 *
 * @Author: [liguiqin]
 * @Date: [2017-01-14 14:34]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Component
public class SecurityIntercept extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityIntercept.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = handlerToken(request);
        if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            return validateLogin(token, response);
        }

        final Method method = ((HandlerMethod) handler).getMethod();
        final Class<?> clazz = method.getDeclaringClass();
        //该类或者方法上是否有登录安全认证注解
        if (clazz.isAnnotationPresent(LoginAuth.class) || method.isAnnotationPresent(LoginAuth.class)) {
            return validateLogin(token, response);
        }
        return true;
    }

    private boolean validateLogin(String token, HttpServletResponse response) throws IOException {
        if (StringUtils.isNotBlank(token)) {
            return true;
        } else {
            return false;
        }

    }


    /**
     * 处理用户token
     *
     * @param request
     * @return
     */
    private String handlerToken(HttpServletRequest request) {
        Object token = request.getParameter("userToken");
        if (null != token) {
            RpcContext.getContext().setAttachment("userToken", String.valueOf(token));
            LOGGER.info("token:" + token);
            return String.valueOf(token);
        }
        return null;
    }

    /**
     * 未登录处理
     *
     * @param response
     * @throws IOException
     */
    private void handlerNotHasLogin(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("text/html; charset=UTF-8"); //转码
        PrintWriter out = response.getWriter();
        out.flush();
        response.setStatus(200);
        ActResult result = new ActResult();
        result.setMsg(msg);
        result.setCode(403);
        out.println(JSON.toJSONString(result));
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        //调用完UserFilter才会调用此方法
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
