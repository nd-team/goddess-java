package com.bjike.goddess.common.consumer.interceptor.login;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.api.exception.SerException;
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
    public StorageIntercept(StorageUserAPI storageUserAPI) {
        this.storageUserAPI = storageUserAPI;
    }

    private StorageUserAPI storageUserAPI;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (StringUtils.isBlank(request.getParameter("path"))) {
            throw new SerException("path 不能为空!");
        }
        return validateLogin(request, response);

    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        //调用完UserFilter才会调用此方法
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


    private boolean validateLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Object obj = request.getParameter("storageToken");
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
        response.setContentType("text/html; charset=UTF-8"); //转码
        out.flush();
        response.setStatus(200);
        ActResult result = new ActResult();
        result.setMsg(msg);
        result.setCode(403);
        out.println(JSON.toJSONString(result));
    }
}
