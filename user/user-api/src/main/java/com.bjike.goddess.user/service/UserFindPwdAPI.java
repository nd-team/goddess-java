package com.bjike.goddess.user.service;


import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.entity.User;
import com.bjike.goddess.user.sto.UserSimpleSTO;

/**
 * 用户找回密码接口
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface UserFindPwdAPI {


    /**
     * 是否存在该用户 并验证验证码是否正确
     *
     * @param account  用户
     * @param authCode 验证码
     * @return
     * @throws SerException
     */
    UserSimpleSTO verifyAccount(String account, String authCode) throws SerException;


    /**
     * 发送验证码到手机
     *
     * @param nickname 昵称
     * @return
     * @throws SerException
     */
    Boolean sendCodeByNickname(String nickname) throws SerException;

    /**
     * 验证手机验证码
     *
     * @param nickname  昵称
     * @param phoneCode 手机验证码
     * @return
     */
    Boolean verifyPhoneCode(String nickname, String phoneCode) throws SerException;


}
