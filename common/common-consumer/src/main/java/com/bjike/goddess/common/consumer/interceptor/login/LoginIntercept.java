package com.bjike.goddess.common.consumer.interceptor.login;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.consumer.auth.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.user.api.UserAPI;
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
 * RpcContext.getContext().getAttachment(key)获取隐形参数，
 * 假如在调用之前又进行了远程调用（如：userApi.findUser()）,则隐藏参数失效
 *
 * @Author: [liguiqin]
 * @Date: [2017-01-14 14:34]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class LoginIntercept extends HandlerInterceptorAdapter {
    public LoginIntercept(UserAPI userAPI) {
        this.userAPI = userAPI;
    }

    private UserAPI userAPI;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object obj = request.getParameter("userToken");
        String token = null;
        if (null != obj) {
            token = obj.toString();
        }
        Boolean pass = false;
        if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            pass = validateLogin(token, response);
        }
        if (!pass) {
            Method method = ((HandlerMethod) handler).getMethod();
            Class<?> clazz = method.getDeclaringClass();
            //该类或者方法上是否有登录安全认证注解
            if (clazz.isAnnotationPresent(LoginAuth.class) || method.isAnnotationPresent(LoginAuth.class)) {
                pass = validateLogin(token, response);
            }

        }
        handlerUserToken(token);
        return pass;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        //调用完UserFilter才会调用此方法
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


    private boolean validateLogin(String token, HttpServletResponse response) throws IOException {
        try {
            if (StringUtils.isNotBlank(token) && null != userAPI.currentUser()) {
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
     * 处理用户token
     *
     * @return
     */
    private void handlerUserToken(String token) {
        if (StringUtils.isNotBlank(token)) {
            RpcContext.getContext().setAttachment("userToken", token);

        }
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
}
