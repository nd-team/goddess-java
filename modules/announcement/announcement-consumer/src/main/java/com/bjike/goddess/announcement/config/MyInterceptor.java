package com.bjike.goddess.announcement.config;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.announcement.api.AnnouncementAPI;
import com.bjike.goddess.common.api.constant.RpcCommon;
import com.bjike.goddess.common.api.exception.ActException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截除了读必读公告的所有请求
 *
 * @Author: [chenjunhao]
 * @Date: [2017-07-08 10:59]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class MyInterceptor implements HandlerInterceptor {
    private AnnouncementAPI announcementAPI;

    public MyInterceptor(AnnouncementAPI announcementAPI) {
        this.announcementAPI = announcementAPI;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object obj = request.getHeader(RpcCommon.USER_TOKEN);
        String token = null;
        if (null != obj) {
            token = obj.toString();
        }
        handlerUserToken(token);
        if (announcementAPI.checkRequired()) {
            String path = request.getServletPath();
            if (path.startsWith("/announcement/v1/read") || path.startsWith("/announcement/v1/requiredReads") || path.startsWith("/announcement/v1/requiredCount")|| path.startsWith("/announcement/v1/checkByClass")) {   //todo:获取阅读必读公告路径
                return true;  //放行
            } else {
                throw new ActException("您有必读公告未读，请先阅读必读公告，否则不能操作");
            }
        }
        return true;    //放行
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * 处理用户token
     *
     * @return
     */
    private void handlerUserToken(String token) {
        if (StringUtils.isNotBlank(token)) {
            RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, token);

        }
    }
}
