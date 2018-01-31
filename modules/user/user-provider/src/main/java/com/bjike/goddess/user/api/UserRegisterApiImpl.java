package com.bjike.goddess.user.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.service.UserRegisterSer;
import com.bjike.goddess.user.to.AppUserRegisterTO;
import com.bjike.goddess.user.to.SmsCodeParameterTO;
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
    public void verifyCodeAndReg(UserRegisterTO registerTO) throws SerException {
        userRegisterSer.verifyCodeAndReg(registerTO);
    }

    @Override
    public void verifyAndSendCode(String phone) throws SerException {
        userRegisterSer.verifyAndSendCode(phone);
    }


    @Override
    public String sendSmsVerifyCode(SmsCodeParameterTO smsCodeParameterTO) throws SerException {
        return userRegisterSer.sendSmsVerifyCode( smsCodeParameterTO );
    }

    @Override
    public String sendSmsVerifyCodes(SmsCodeParameterTO smsCodeParameterTO) throws SerException {
        return userRegisterSer.sendSmsVerifyCodes( smsCodeParameterTO );
    }

    @Override
    public Boolean verifyCode(String phone,String code ) throws SerException {
        return userRegisterSer.verifyCode( phone ,code );
    }

    @Override
    public void checkPassword(String password, String repassword) throws SerException {
        userRegisterSer.checkPassword(password,repassword);
    }

    @Override
    public void checkIntegral(String code) throws SerException {
        userRegisterSer.checkIntegral(code);
    }

    @Override
    public void registerUser(AppUserRegisterTO appUserRegisterTO) throws SerException {
        userRegisterSer.registerUser(appUserRegisterTO);
    }

    @Override
    public String autogenerationNum(String startNumber) throws SerException {
        return userRegisterSer.autogenerationNum(startNumber);
    }

    @Override
    public void inviteReg(String inviteUrl, AppUserRegisterTO appUserRegisterTO) throws SerException {
        userRegisterSer.inviteReg(inviteUrl,appUserRegisterTO);
    }
}
