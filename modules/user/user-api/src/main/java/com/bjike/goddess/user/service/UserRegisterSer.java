package com.bjike.goddess.user.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.to.UserRegisterTO;

/**
 * 用户注册业务接口
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface UserRegisterSer {


    /**
     * 验证并注册
     *
     * @param registerTO
     * @throws SerException
     */
    void verifyCodeAndReg(UserRegisterTO registerTO) throws SerException;

    /**
     * 注册验证手机并发生验证码
     *
     * @param phone
     */
    void verifyAndSendCode(String phone) throws SerException;
}
