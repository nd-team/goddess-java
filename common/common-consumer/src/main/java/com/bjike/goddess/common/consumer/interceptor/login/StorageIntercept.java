package com.bjike.goddess.common.consumer.interceptor.login;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.storage.api.StorageUserAPI;
import org.apache.commons.lang3.StringUtils;
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
 *
 * @Author: [liguiqin]
 * @Date: [2017-01-14 14:34]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class StorageIntercept extends HandlerInterceptorAdapter {
    public StorageIntercept(StorageUserAPI storageUserAPI) {
        this.storageUserAPI = storageUserAPI;
    }

    private StorageUserAPI storageUserAPI;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            validatePath(request, response);
            return validateLogin(request, response);
        }
        Method method = ((HandlerMethod) handler).getMethod();
        Class<?> clazz = method.getDeclaringClass();
        //该类或者方法上是否有登录安全认证注解
        if (clazz.isAnnotationPresent(StorageAuth.class) || method.isAnnotationPresent(StorageAuth.class)) {
            validatePath(request, response);
            return validateLogin(request, response);
        }

        return true;
    }

    private void validatePath(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (StringUtils.isBlank(request.getParameter("path"))) {
            PrintWriter out = response.getWriter();
            out.flush();
            response.setContentType("text/html; charset=UTF-8"); //转码
            response.setStatus(200);
            ActResult result = new ActResult();
            result.setMsg("path 不能为空!");
            result.setCode(1);
            out.println(JSON.toJSONString(result));
        }
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        //调用完UserFilter才会调用此方法
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


    private boolean validateLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Object obj = request.getHeader("storageToken");
        String token = null;
        if (null != obj) {
            token = obj.toString();
        }
        try {
            if (StringUtils.isNotBlank(token) && null != storageUserAPI.getCurrentUser(token)) {
                return true;
            } else {
                handlerNotHasLogin(response, "用户未登录！");
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }


    }

    /**
     * 未登录处理
     *
     * @param response
     * @throws IOException
     */
    private void handlerNotHasLogin(HttpServletResponse response, String msg) throws IOException {
        PrintWriter out = response.getWriter();
        out.flush();
        response.setContentType("text/html; charset=UTF-8"); //转码
        response.setStatus(200);
        ActResult result = new ActResult();
        result.setMsg(msg);
        result.setCode(403);
        out.println(JSON.toJSONString(result));
    }
}
