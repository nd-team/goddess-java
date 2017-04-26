package com.bjike.goddess.employeecontract.config;

import com.bjike.goddess.common.consumer.config.HIInfo;
import com.bjike.goddess.common.consumer.config.Interceptor;
import com.bjike.goddess.common.consumer.interceptor.login.StorageIntercept;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: [dengjunren]
 * @Date: [2017-04-19 14:23]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Component
public class CustomIntercept implements Interceptor {


    @Override
    public List<HIInfo> customerInterceptors() {
        return Arrays.asList(new HIInfo(new StorageIntercept(), "/**"));
    }
}
