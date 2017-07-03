package com.bjike.goddess.firmreward.config;

import com.bjike.goddess.common.consumer.config.HIInfo;
import com.bjike.goddess.common.consumer.config.Interceptor;
import com.bjike.goddess.common.consumer.interceptor.login.LoginIntercept;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 拦截器
 *
 * @Author: [sunfengtao]
 * @Date: [2017-06-02 16:04]
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
        HIInfo loginInfo = new HIInfo(new LoginIntercept(userAPI), "/**");
        return Arrays.asList(loginInfo );
    }
}
