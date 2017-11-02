package com.bjike.goddess.common.consumer.interceptor.login;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.common.api.constant.RpcCommon;
import com.bjike.goddess.common.consumer.http.ResponseContext;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.storage.api.StorageUserAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    private StorageUserAPI storageUserAPI;
    /**
     * 自动登录账户
     */
    private String account;
    /**
     * 自动登录密码
     */
    private String password;
    /**
     * 自动登录模块
     */
    private String moduleName;
    /**
     * 自动登录
     */
    private boolean autoLogin = true;

    /**
     * 自动登录
     *
     * @param storageUserAPI
     * @param account
     * @param password
     * @param moduleName
     */
    public StorageIntercept(StorageUserAPI storageUserAPI, String account, String password, String moduleName) {
        this.storageUserAPI = storageUserAPI;
        this.moduleName = moduleName;
        this.account = account;
        this.password = password;
    }

    /**
     * 手动登录
     *
     * @param storageUserAPI
     * @param autoLogin
     */
    public StorageIntercept(StorageUserAPI storageUserAPI, boolean autoLogin) {
        this.storageUserAPI = storageUserAPI;
        this.autoLogin = autoLogin;

    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getServletPath().equals("/v1/login")){
            return true;
        }
        if (autoLogin) {

            String userToken = request.getHeader(RpcCommon.USER_TOKEN);
            String token = storageUserAPI.getStorageToken(account, password, moduleName, userToken);
            request.setAttribute(RpcCommon.STORAGE_TOKEN, token);
            RpcContext.getContext().setAttachment(RpcCommon.STORAGE_TOKEN, token);
        } else {
            if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
                return validateLogin(request, response);
            }
            Method method = ((HandlerMethod) handler).getMethod();
            Class<?> clazz = method.getDeclaringClass();
            //该类或者方法上是否有登录安全认证注解
            if (clazz.isAnnotationPresent(StorageAuth.class) || method.isAnnotationPresent(StorageAuth.class)) {
                return validateLogin(request, response);
            }
        }

        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        //调用完UserFilter才会调用此方法
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    private boolean validateLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Object obj = request.getParameter(RpcCommon.STORAGE_TOKEN);

        String token = null;
        if (null != obj) {
            token = obj.toString();
        } else {
            obj = request.getAttribute(RpcCommon.STORAGE_TOKEN);
            token = (obj != null ? obj.toString() : null);
        }
        try {
            if (StringUtils.isNotBlank(token) && null != storageUserAPI.getCurrentUser(token)) {
                RpcContext.getContext().setAttachment(RpcCommon.STORAGE_TOKEN, token);
                return true;
            } else {
                handlerNotHasLogin(response, "存储用户未登录！");
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
        ActResult actResult = new ActResult();
        response.setContentType("text/html;charset=utf-8");
        actResult.setCode(403);
        actResult.setMsg(msg);
        response.setStatus(200);
        ResponseContext.writeData(actResult);
    }

}
