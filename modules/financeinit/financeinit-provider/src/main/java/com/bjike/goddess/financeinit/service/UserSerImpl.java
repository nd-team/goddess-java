package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-03-01 11:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 */

@Service
public class UserSerImpl implements UserSer{
    @Autowired
    UserAPI userAPI;

    @Override
    public String getSystemId() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        String systemId = userAPI.currentUser(userToken).getSystemNO();
        RpcTransmit.transmitUserToken(userToken);
        return systemId;
    }
}
