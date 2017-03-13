package com.bjike.goddess.user.action.user;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.user.api.UserLoginAPI;
import com.bjike.goddess.user.enums.LoginType;
import com.bjike.goddess.user.service.UserLoginSer;
import com.bjike.goddess.user.to.UserLoginTO;
import com.bjike.goddess.user.utils.CheckMobile;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户登录
 *
 * @Author: [liguiqin]
 * @Date: [2017-01-14 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("user")
public class LoginAct {

    @Autowired
    private UserLoginAPI userLoginAPI;

    /**
     * 登录
     *
     * @param loginTO 登录用户传输数据对象
     * @param request
     * @version v1
     */
    @PostMapping("v1/login")
    public Result login(UserLoginTO loginTO, HttpServletRequest request) throws ActException {
        try {
            String userAgent = request.getHeader("USER-AGENT").toLowerCase();
            LoginType type = LoginType.PC;
            if (CheckMobile.check(userAgent)) { //判断是否为移动端访问
                type = LoginType.MOBILE;
            }
            loginTO.setLoginType(type);
            String token = userLoginAPI.login(loginTO);
            return ActResult.initialize(token);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}