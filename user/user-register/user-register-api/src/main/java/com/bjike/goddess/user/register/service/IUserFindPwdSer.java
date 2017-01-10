package com.bjike.goddess.user.register.service;


import com.bjike.goddess.dbs.common.exception.SerException;
import com.bjike.goddess.user.common.entity.User;

import java.awt.image.BufferedImage;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: 用户找回密码接口]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IUserFindPwdSer {

    /**
     * 点击找回密码生成验证码
     *
     * @param sid 前端生成唯一识别用户id
     * @return 验证码流
     */
    BufferedImage generatePwdAuthCode(String sid) throws SerException;


    /**
     * 是否存在该用户 并验证验证码是否正确
     *
     * @param account  用户
     * @param authCode 验证码
     * @param sid      验证码唯一标识
     * @return
     * @throws SerException
     */
    User verifyAccount(String account, String authCode, String sid) throws SerException;


    /**
     * 发送验证码到手机
     *
     * @param nickname 昵称
     * @return
     * @throws SerException
     */
    Boolean sendCodeToPhone(String nickname) throws SerException;

    /**
     * 验证手机验证码
     *
     * @param nickname  昵称
     * @param phoneCode 手机验证码
     * @return
     */
    Boolean verifyPhoneCode(String nickname, String phoneCode) throws SerException;


}
