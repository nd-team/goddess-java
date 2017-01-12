package com.bjike.goddess.user.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.dto.UserRegisterDTO;

import java.awt.image.BufferedImage;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: 用户注册业务接口]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface IUserRegisterSer {

    /**
     * 点击注册页面生成验证码
     *
     * @param sid 前端生成唯一识别用户id
     * @return 验证码流
     */
    BufferedImage generateRegAuthCode(String sid)throws SerException;

    /**
     * 用户名是否已经注册
     *
     * @param username
     * @throws SerException
     */
    Boolean existUsername(String username) throws SerException;

    /**
     * 手机号是否已注册
     *
     * @param phone
     * @return
     * @throws SerException
     */
    Boolean existPhone(String phone) throws SerException;

    /**
     * 发送验证码到手机
     */
    void sendCodeToPhone(UserRegisterDTO dto) throws SerException;

    /**
     * 验证并注册
     *
     * @param dto
     * @throws SerException
     */
    void verifyCodeAndReg(UserRegisterDTO dto) throws SerException;


}
