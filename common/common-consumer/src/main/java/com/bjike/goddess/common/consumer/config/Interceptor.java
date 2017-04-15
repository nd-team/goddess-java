package com.bjike.goddess.common.consumer.config;

import java.util.List;

/**
 * 自定义拦截器注入实现改接口
 *
 * @Author: [liguiqin]
 * @Date: [2017-04-14 10:01]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface Interceptor {

    /**
     * 用户注入拦截器
     *
     * @return
     */
    List<HIInfo> customerInterceptors();
}
