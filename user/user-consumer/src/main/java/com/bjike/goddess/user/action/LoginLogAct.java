package com.bjike.goddess.user.action;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.consumer.auth.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.user.service.UserLoginLogAPI;
import com.bjike.goddess.user.sto.UserLoginLogSTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-01-14 15:47]
 * @Description: [用户操作]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("user")
public class LoginLogAct {

    @Autowired
    private UserLoginLogAPI userLoginLogAPI;


    @LoginAuth
    @GetMapping("logs")
    public ActResult logs() throws ActException {
        try {
            List<UserLoginLogSTO> loginLogs = userLoginLogAPI.findByCurrentUser();
            return ActResult.initialize(loginLogs);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}