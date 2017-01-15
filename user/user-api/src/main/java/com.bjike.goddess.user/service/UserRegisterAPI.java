package com.bjike.goddess.user.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.dto.ext.UserRegisterDTO;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: 用户注册业务接口]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface UserRegisterAPI {

    /**
     * 用户名是否已经注册
     *
     * @param username
     * @throws SerException
     */
    Boolean existUsername(String username) throws SerException;



    /**
     * 验证并注册
     *
     * @param dto
     * @throws SerException
     */
    void verifyCodeAndReg(UserRegisterDTO dto) throws SerException;

    /**
     * 注册验证手机并发生验证码
     * @param phone
     */
     void verifyAndSendCode(String phone)throws SerException;
}
