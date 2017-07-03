package com.bjike.goddess.contractquotemanager.config;

import com.bjike.goddess.common.consumer.config.HIInfo;
import com.bjike.goddess.common.consumer.config.Interceptor;
import com.bjike.goddess.common.consumer.interceptor.auth.AuthIntercept;
import com.bjike.goddess.common.consumer.interceptor.login.LoginIntercept;
import com.bjike.goddess.common.consumer.interceptor.login.StorageIntercept;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.api.StorageUserAPI;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.rbac.PermissionAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: [sunfengtao]
 * @Date: [2017-04-19 14:23]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Component
public class CustomIntercept implements Interceptor {

    @Autowired
    private UserAPI userAPI;

    @Autowired
    private StorageUserAPI storageUserAPI;

    @Override
    public List<HIInfo> customerInterceptors() {

        /**
         * 添加限流器
         */
//        SmoothBurstyInterceptor smoothInterceptor = new SmoothBurstyInterceptor(100, SmoothBurstyInterceptor.LimitType.DROP);
//        HIInfo smoothInfo = new HIInfo(smoothInterceptor, "/**");

        /**
         * 登录拦截器
         */
        HIInfo loginInfo = new HIInfo(new LoginIntercept(userAPI), "/**");
        HIInfo storage = new HIInfo(new StorageIntercept(storageUserAPI,"contractquotemanager","123456","contractquotemanager"), "/**");

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
        return Arrays.asList(storage, loginInfo);

    }
}
