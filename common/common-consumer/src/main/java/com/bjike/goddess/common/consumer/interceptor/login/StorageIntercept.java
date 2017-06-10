package com.bjike.goddess.common.consumer.interceptor.login;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.api.constant.RpcCommon;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.storage.api.StorageUserAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
    public StorageIntercept(StorageUserAPI storageUserAPI, String account, String password, String moduleName) {
        this.storageUserAPI = storageUserAPI;
        this.moduleName = moduleName;
        this.account = account;
        this.password = password;
    }

    private StorageUserAPI storageUserAPI;
    private String account;
    private String password;
    private String moduleName;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userToken = request.getHeader(RpcCommon.USER_TOKEN);
        String token = storageUserAPI.getStorageToken(account, password, moduleName,userToken);
        RpcContext.getContext().setAttachment(RpcCommon.STORAGE_TOKEN, token);
        return true;
    }




    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        //调用完UserFilter才会调用此方法
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
