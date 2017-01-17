package com.bjike.goddess.common.consumer.auth;

import java.lang.annotation.*;

/**
 * @Author: [liguiqin]
 * @Date: [2017-01-15 09:59]
 * @Description: [登录安全认证]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginAuth {
}
