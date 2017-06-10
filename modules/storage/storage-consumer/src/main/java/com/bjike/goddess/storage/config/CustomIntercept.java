package com.bjike.goddess.storage.config;

import com.bjike.goddess.common.consumer.config.HIInfo;
import com.bjike.goddess.common.consumer.config.Interceptor;
import com.bjike.goddess.common.consumer.interceptor.login.StorageIntercept;
import com.bjike.goddess.storage.api.StorageUserAPI;
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
    private StorageUserAPI storageUserAPI;

    @Override
    public List<HIInfo> customerInterceptors() {
        String username = "ike";
        String password = "123456";
        String moduleName = "storage";
        HIInfo storageInfo = new HIInfo(new StorageIntercept(storageUserAPI, username, password, moduleName), "/**");

        /**
         * 暂时不加权限
         */
        return Arrays.asList(storageInfo);
    }
}
