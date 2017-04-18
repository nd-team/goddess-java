package com.bjike.goddess.user.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.bo.UserSimpleBO;
import com.bjike.goddess.user.service.UserFindPwdSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: [liguiqin]
 * @Date: [2017-04-17 15:38]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("userFindPwdApiImpl")
public class UserFindPwdApiImpl implements UserFindPwdAPI {
    @Autowired
    private UserFindPwdSer userFindPwdSer;

    @Override
    public UserSimpleBO verifyAccount(String account, String authCode) throws SerException {
        return userFindPwdSer.verifyAccount(account, authCode);
    }

    @Override
    public Boolean sendCodeByNickname(String nickname) throws SerException {
        return userFindPwdSer.sendCodeByNickname(nickname);
    }

    @Override
    public Boolean verifyPhoneCode(String nickname, String phoneCode) throws SerException {
        return userFindPwdSer.verifyPhoneCode(nickname, phoneCode);
    }
}
