package com.bjike.goddess.user.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.service.UserRegisterSer;
import com.bjike.goddess.user.to.UserRegisterTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-11 16:40]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("userRegisterApiImpl")
public class UserRegisterApiImpl implements UserRegisterAPI {
    @Autowired
    private UserRegisterSer userRegisterSer;

    @Override
    public Boolean existUsername(String username) throws SerException {
        return userRegisterSer.existUsername(username);
    }

    @Override
    public void verifyCodeAndReg(UserRegisterTO registerTO) throws SerException {
        userRegisterSer.verifyCodeAndReg(registerTO);
    }

    @Override
    public void verifyAndSendCode(String phone) throws SerException {
        userRegisterSer.verifyAndSendCode(phone);
    }
}
