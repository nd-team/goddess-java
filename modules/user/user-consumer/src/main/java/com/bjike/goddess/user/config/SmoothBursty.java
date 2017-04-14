package com.bjike.goddess.user.config;

import com.bjike.goddess.common.consumer.config.HIInfo;
import com.bjike.goddess.common.consumer.config.Interceptor;
import com.bjike.goddess.common.consumer.interceptor.limit.SmoothBurstyInterceptor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 添加限流器
 *
 * @Author: [liguiqin]
 * @Date: [2017-04-14 09:54]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Component
public class SmoothBursty implements Interceptor {
    @Override
    public List<HIInfo> customerInterceptors() {
        SmoothBurstyInterceptor interceptor = new SmoothBurstyInterceptor(1, SmoothBurstyInterceptor.LimitType.DROP);
        return Arrays.asList(new HIInfo(interceptor, "/**"));
    }
}
