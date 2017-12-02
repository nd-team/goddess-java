package com.bjike.goddess.user.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.to.AppUserRegisterTO;
import com.bjike.goddess.user.to.SmsCodeParameterTO;
import com.bjike.goddess.user.to.UserRegisterTO;

/**
 * 注册业务接口
 *
 * @Author: [liguiqin]
 * @Date: [2017-03-11 16:38]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface UserRegisterAPI {

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


    /**
     * 获取发送短信验证码
     * tanghaixiang
     * @return
     * @throws SerException
     */
    default String sendSmsVerifyCode(SmsCodeParameterTO smsCodeParameterTO ) throws SerException {
        return null;
    }

    /**
     * 校验手机短信验证码
     * tanghaixiang
     * @param phone 手机号
     * @param code 验证码
     * @return
     * @throws SerException
     */
    default Boolean verifyCode(String phone ,String code) throws SerException {
        return null;
    }
    /**
     * 验证密码
     * lijuntao
     *
     * @param password   密码
     * @param repassword 确认密码
     * @return
     * @throws SerException
     */
    default void checkPassword(String password, String repassword) throws SerException {
        return;
    }

    /**
     * 移动端用户注册
     * lijuntao
     *
     * @param appUserRegisterTO 注册to
     * @return
     * @throws SerException
     */
    default void registerUser(AppUserRegisterTO appUserRegisterTO) throws SerException {
        return;
    }

    /**
     * 移动端填写前五位字母自动生成用户编号
     * lijuntao
     *
     * @param startNumber 前五为字母
     * @return
     * @throws SerException
     */
    default String autogenerationNum(String startNumber) throws SerException {
        return null;
    }
    /**
     * 移动端邀请员工注册
     * lijuntao
     *
     * @param inviteUrl 邀请链接
     * @throws SerException
     */
    default void inviteReg(String inviteUrl,AppUserRegisterTO appUserRegisterTO) throws SerException {
        return;
    }
}
