package com.bjike.goddess.common.consumer.interceptor.auth;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.api.rbac.PermissionAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

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
                if (uri.endsWith(last_suffix)) {
                    pass = true;
                    break;
                }
            }
            if (pass) {
                return super.preHandle(request, response, handler);
            }
        }

        List<String> permissions = permissionAPI.currentPermissions();
        for (String per : permissions) {
            /**
             * url 完全相匹配
             */
            if (per.equals(uri)) {
                pass = true;
                break;
            }
            /**
             * 权限资源没有子资源且前缀相匹配
             */
            String per_url = StringUtils.substringBeforeLast(per,"/*");
            if(StringUtils.isNotBlank(per_url) && uri.indexOf(per_url)!=-1){
                pass = true;
                break;
            }
        }

        if (pass) {
            return super.preHandle(request, response, handler);
        }
        //  System.out.println(permissionBOS);
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
