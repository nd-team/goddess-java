package com.bjike.goddess.financeinit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.financeinit.service.UserSer;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户业务
 * @Author: [caiwenxian]
 * @Date: [2018-03-01 09:50]
 * @Description: [ ]
 * @Version: [1.0.0]
 */
@Service("userServiceApiImpl")
public class UserServiceApiImpl implements UserServiceAPI {

    @Autowired
    UserSer userSer;

    @Override
    public String getSystemId() throws SerException {
        return userSer.getSystemId();
    }
}
