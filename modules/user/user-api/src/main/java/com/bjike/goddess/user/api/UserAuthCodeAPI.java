package com.bjike.goddess.user.api;

import com.bjike.goddess.common.api.exception.SerException;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-11 16:57]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface UserAuthCodeAPI {


    /**
     * 是否需要验证码
     *
     * @param account 账号
     * @return
     * @throws SerException
     */
    Boolean showAuthCode(String account) throws SerException;

    /**
     * 保存验证码到session
     *
     * @param account
     * @param code
     */
    void handleAuthCode(String account, String code) throws SerException;

}
