package com.bjike.goddess.reportmanagement.config;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.bjike.goddess.common.consumer.config.HIInfo;
import com.bjike.goddess.common.consumer.config.Interceptor;
import com.bjike.goddess.common.consumer.interceptor.limit.SmoothBurstyInterceptor;
import com.bjike.goddess.common.consumer.interceptor.login.LoginIntercept;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 拦截器添加
 *
 * @Author: [liguiqin]
 * @Date: [2017-04-15 09:39]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Component
@Activate(group = {Constants.PROVIDER})
public class CustomIntercept implements Interceptor, Filter {
    @Autowired
    private UserAPI userAPI;
//    @Autowired
//    private PermissionAPI permissionAPI;

    @Override
    public List<HIInfo> customerInterceptors() {
        /**
         * 添加限流器
         */
        SmoothBurstyInterceptor smoothInterceptor = new SmoothBurstyInterceptor(100, SmoothBurstyInterceptor.LimitType.DROP);
        HIInfo smoothInfo = new HIInfo(smoothInterceptor, "/**");

        /**
         * 登录拦截器
         */
        HIInfo loginInfo = new HIInfo(new LoginIntercept(userAPI), "/**");

        /**
         * 权限拦截器
         */
//        String[] excludes = new String[]{
//                "*/login",
//                "*/register",
//                "/user/version/verifyPhone/*",
//                "/user/version/register/*",
//                "public/version/key"
//        };
        String[] excludes = new String[]{
                "*/login",
                "*/register"
        };
//        HIInfo authInfo = new HIInfo(new AuthIntercept(permissionAPI, excludes), "/**");

        /**
         * 顺序
         */
        return Arrays.asList(smoothInfo, loginInfo);
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        RpcContext.getContext().getAttachments().remove(Constants.ASYNC_KEY);
        return invoker.invoke(invocation);
    }
}
