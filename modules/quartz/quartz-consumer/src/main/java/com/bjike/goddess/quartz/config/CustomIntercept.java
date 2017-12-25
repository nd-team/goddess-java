package com.bjike.goddess.quartz.config;

import com.bjike.goddess.common.consumer.config.HIInfo;
import com.bjike.goddess.common.consumer.config.Interceptor;
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
public class CustomIntercept implements Interceptor {
    @Autowired
    private UserAPI userAPI;

    @Override
    public List<HIInfo> customerInterceptors() {

        /**
         * 登录拦截器
         */
        HIInfo loginInfo = new HIInfo(new LoginIntercept(userAPI), "/**");
        return Arrays.asList(loginInfo);
    }
}
