package com.bjike.goddess.common.consumer.interceptor.login;

import java.lang.annotation.*;

/**
 * 存储登录安全认证
 *
 * @Author: [liguiqin]
 * @Date: [2017-05-09 15:41]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StorageAuth {
}
