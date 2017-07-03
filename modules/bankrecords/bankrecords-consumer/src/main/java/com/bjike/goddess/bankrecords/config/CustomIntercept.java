package com.bjike.goddess.bankrecords.config;

import com.bjike.goddess.common.consumer.config.HIInfo;
import com.bjike.goddess.common.consumer.config.Interceptor;
import com.bjike.goddess.common.consumer.interceptor.login.StorageIntercept;
import com.bjike.goddess.storage.api.StorageUserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: [Jason]
 * @Date: [2017-04-19 14:23]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CustomIntercept implements Interceptor {

    @Autowired
    private StorageUserAPI storageUserAPI;


    @Override
    public List<HIInfo> customerInterceptors() {
        String account ="bankrecords";
        String password="123456";
        String name="bankrecords";
        HIInfo storageInfo = new HIInfo(new StorageIntercept(storageUserAPI,account,password,name), "/**");

        /**
         * 暂时不加权限
         */
        return Arrays.asList(storageInfo);
    }
}
