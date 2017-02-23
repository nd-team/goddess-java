package com.bjike.goddess.user.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.session.authcode.AuthCode;
import com.bjike.goddess.user.session.authcode.AuthCodeSession;
import com.bjike.goddess.user.session.validfail.ValidErr;
import com.bjike.goddess.user.session.validfail.ValidErrSession;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 用户验证码业务实现
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-26 09:36]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("userAuthCodeSer")
public class UserAuthCodeSer implements UserAuthCodeAPI {

    @Override
    public Boolean showAuthCode(String account) throws SerException {

        ValidErr code = ValidErrSession.get(account);
        if (null != code && code.getCount() >= 5) { //验证次数大于5次需要验证码
            return true;
        }
        return false;
    }


    /**
     * 保存验证码到session
     *
     * @param account
     * @param code
     */
    public void handleAuthCode(String account, String code) {
        AuthCode authCode = AuthCodeSession.get(account);
        if (null == authCode) {
            AuthCode auth = new AuthCode();
            auth.setCode(code);
            AuthCodeSession.put(account, auth);
        } else {
            authCode.setCode(code);
            authCode.setCreateTime(LocalDateTime.now());
        }
    }

}
