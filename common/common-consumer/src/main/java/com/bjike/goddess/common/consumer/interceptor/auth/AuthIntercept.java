package com.bjike.goddess.common.consumer.interceptor.auth;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.api.rbac.PermissionAPI;
import com.bjike.goddess.user.bo.rbac.PermissionBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 资源权限拦截
 *
 * @Author: [liguiqin]
 * @Date: [2017-04-15 09:34]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AuthIntercept extends HandlerInterceptorAdapter {

    private String[] excludes;

    private PermissionAPI permissionAPI;

    public AuthIntercept(PermissionAPI permissionAPI, String... excludes) {
        this.permissionAPI = permissionAPI;
        this.excludes = excludes;
    }

    public AuthIntercept(PermissionAPI permissionAPI) {
        this.permissionAPI = permissionAPI;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        boolean pass = false;
        if (null != excludes) {
            for (String exclude : excludes) { //过滤请求资源
                String last_suffix = StringUtils.substringAfterLast(exclude, "/");
                String pass_url = StringUtils.substringBeforeLast(uri, "/") + "/*";
                if (uri.endsWith(last_suffix) || exclude.equals(pass_url) || uri.equals(exclude)) {
                    pass = true;
                    break;
                }
            }
            if (pass) {
                return super.preHandle(request, response, handler);
            }
        }
        List<PermissionBO> permissions = permissionAPI.currentPermissions();
        for (PermissionBO per : permissions) {
            /**
             * url 完全相匹配
             */
            String resource = per.getResource();
            if (resource.equals(uri)) {
                return super.preHandle(request, response, handler);
            }
            /**
             * 权限资源没有子资源且前缀相匹配
             */
            String per_url = StringUtils.substringBeforeLast(resource, "/*");
            if (StringUtils.isNotBlank(per_url) && uri.indexOf(per_url) != -1) {
                pass = true;
                if (per.getHasChild()) { //如存在子资源,通配符*无效
                    pass = false;
                    for (PermissionBO p : permissions) {
                        if (p.getResource().equals(uri)) { //子资源匹配
                            pass = true;
                            break;
                        }
                    }
                    if (pass) {
                        break;
                    }
                }

            }
        }

        if (pass) {
            return super.preHandle(request, response, handler);
        }
        throw new SerException("该权限资源受限:" + uri);

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

}
