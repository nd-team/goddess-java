package com.bjike.goddess.projectmeasure.config;

import com.bjike.goddess.common.consumer.config.HIInfo;
import com.bjike.goddess.common.consumer.config.Interceptor;
import com.bjike.goddess.common.consumer.interceptor.login.LoginIntercept;
import com.bjike.goddess.common.consumer.interceptor.login.StorageIntercept;
import com.bjike.goddess.storage.api.StorageUserAPI;
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

//    @Autowired
//    private StorageUserAPI storageUserAPI;

    @Override
    public List<HIInfo> customerInterceptors() {
        HIInfo loginInfo = new HIInfo(new LoginIntercept(userAPI), "/**");

        /**
         * 自动登录
         */
//        String username = "projectmeasure";
//        String password = "123456";
//        String moduleName = "projectmeasure";
//        /**
//         * 直接访问须手动登录
//         */
//        HIInfo storageInfo = new HIInfo(new StorageIntercept(storageUserAPI, false), "/**");
        return Arrays.asList(loginInfo );
    }

}
