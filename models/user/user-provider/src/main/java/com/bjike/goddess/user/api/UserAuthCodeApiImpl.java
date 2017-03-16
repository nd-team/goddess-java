package com.bjike.goddess.user.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.service.UserAuthCodeSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-11 16:59]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("userAuthCodeApiImpl")
public class UserAuthCodeApiImpl implements UserAuthCodeAPI {
    @Autowired
    private UserAuthCodeSer userAuthCodeSer;
    @Override
    public Boolean showAuthCode(String account) throws SerException {
        return userAuthCodeSer.showAuthCode(account);
    }

    @Override
    public void handleAuthCode(String account, String code) throws SerException {
        userAuthCodeSer.handleAuthCode(account,code);
    }
}
