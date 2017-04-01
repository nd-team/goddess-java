package com.bjike.goddess.common.consumer.interceptor;

import com.alibaba.dubbo.rpc.RpcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 存储登录安全拦截(仅检测是否有携带storageToken,用storageToken获取用户的时候再进行storageToken有无效判定)
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
public class StorageIntercept extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(StorageIntercept.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        handlerToken(request);
        return true;
    }


    /**
     * 处理用户token
     *
     * @param request
     * @return
     */
    private String handlerToken(HttpServletRequest request) {
        Object storageToken = request.getParameter("storageToken");
        if (null != storageToken) {
            RpcContext.getContext().setAttachment("storageToken", String.valueOf(storageToken));
            LOGGER.info("storageToken:" + storageToken);
            return String.valueOf(storageToken);
        }
        return null;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }

}
