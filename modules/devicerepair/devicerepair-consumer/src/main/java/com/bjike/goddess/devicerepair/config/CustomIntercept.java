package com.bjike.goddess.devicerepair.config;

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
 * @Author: [sunfengtao]
 * @Date: [2017-04-19 14:23]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Component
public class CustomIntercept implements Interceptor {

    @Autowired
    private StorageUserAPI storageUserAPI;

    @Autowired
    private UserAPI userAPI;

    @Override
    public List<HIInfo> customerInterceptors() {
        String account="devicerepair";
        String password="123456";
        String name="devicerepair";
        return Arrays.asList(new HIInfo(new StorageIntercept(storageUserAPI,account,password,name), "/**"),
                new HIInfo(new LoginIntercept(userAPI), "/**"));
    }
}
