package com.bjike.goddess.materialbuy.config;

import com.bjike.goddess.common.consumer.config.HIInfo;
import com.bjike.goddess.common.consumer.config.Interceptor;
import com.bjike.goddess.common.consumer.interceptor.limit.SmoothBurstyInterceptor;
import com.bjike.goddess.common.consumer.interceptor.login.LoginIntercept;
import com.bjike.goddess.common.consumer.interceptor.login.StorageIntercept;
import com.bjike.goddess.storage.api.StorageUserAPI;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: [chenjunhao]
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
        SmoothBurstyInterceptor smoothInterceptor = new SmoothBurstyInterceptor(100, SmoothBurstyInterceptor.LimitType.DROP);
        HIInfo smoothInfo = new HIInfo(smoothInterceptor, "/**");

        /**
         * 登录拦截器
         */
        HIInfo loginInfo = new HIInfo(new LoginIntercept(userAPI), "/**");
        HIInfo storage = new HIInfo(new StorageIntercept(storageUserAPI, "materialbuy", "123456", "materialbuy"), "/**");


        return Arrays.asList(smoothInfo,storage,loginInfo);
    }
}
