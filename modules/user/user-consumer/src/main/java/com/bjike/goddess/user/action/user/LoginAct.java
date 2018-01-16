package com.bjike.goddess.user.action.user;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.token.IpUtil;
import com.bjike.goddess.user.api.UserLoginAPI;
import com.bjike.goddess.user.enums.LoginType;
import com.bjike.goddess.user.to.UserLoginTO;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户登录
 *
 * @Author: [liguiqin]
 * @Date: [2017-01-14 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@DefaultProperties
@RestController
public class LoginAct {

    @Autowired
    private UserLoginAPI userLoginAPI;

    /**
     * 登录
     *
     * @param loginTO 登录用户传输数据对象
     * @param request httpRequest
     * @version v1
     */
    @PostMapping("v1/login")
    public Result login(@Validated UserLoginTO loginTO, BindingResult result, HttpServletRequest request) throws ActException {
        try {
//            String userAgent = request.getHeader("USER-AGENT").toLowerCase();
            LoginType type = LoginType.PC;
//            if (CheckMobile.check(userAgent)) { //判断是否为移动端访问
//                type = LoginType.MOBILE;
//            }
            loginTO.setLoginType(type);
            loginTO.setIp(IpUtil.getIp(request));
            String token = userLoginAPI.login(loginTO);
            return ActResult.initialize(token);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 退出登录
     *
     * @param token 登录用户userToken
     * @version v1
     */

    @GetMapping("v1/sign-out/{token}")
    public Result signOut( @PathVariable String token) throws ActException {
        try {
            Boolean result = userLoginAPI.signOut(token);
            return ActResult.initialize(result);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 移动端登录
     *
     * @param loginTO 登录用户传输数据对象
     * @param request httpRequest
     * @version v1
     */
    @PostMapping("v1/phone/login")
    public Result phoneLogin(@Validated UserLoginTO loginTO, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            LoginType type = LoginType.MOBILE;
            loginTO.setLoginType(type);
            loginTO.setIp(IpUtil.getIp(request));
            String token = userLoginAPI.logins(loginTO);
            return ActResult.initialize(token);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}