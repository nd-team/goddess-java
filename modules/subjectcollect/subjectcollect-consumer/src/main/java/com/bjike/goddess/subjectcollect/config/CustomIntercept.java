package com.bjike.goddess.subjectcollect.config;

import com.bjike.goddess.common.consumer.config.HIInfo;
import com.bjike.goddess.common.consumer.config.Interceptor;
import com.bjike.goddess.common.consumer.interceptor.login.LoginIntercept;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ike on 17-5-10.
 */
@Component
public class CustomIntercept implements Interceptor {
    @Autowired
    private UserAPI userAPI;
    @Override
    public List<HIInfo> customerInterceptors() {
        HIInfo loginInfo = new HIInfo(new LoginIntercept(userAPI), "/**");
        return Arrays.asList(loginInfo);
    }
}
