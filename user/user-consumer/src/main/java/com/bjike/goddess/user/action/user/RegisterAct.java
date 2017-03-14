package com.bjike.goddess.user.action.user;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.user.api.UserRegisterAPI;
import com.bjike.goddess.user.to.UserRegisterTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户注册
 *
 * @Author: [liguiqin]
 * @Date: [2017-01-14 09:46]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("user")
public class RegisterAct {
    @Autowired
    private UserRegisterAPI userRegisterAPI;

    /**
     * 注册用户
     *
     * @param registerTO 注册用户信息
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/register")
    public Result register(@Validated UserRegisterTO registerTO, BindingResult result) throws ActException {
        try {
            userRegisterAPI.verifyCodeAndReg(registerTO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
        return new ActResult("注册成功！");
    }


    /**
     * 发送手机验证码
     *
     * @param phone 手机号码
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/verifyPhone/{phone}")
    public Result sendCodeToPhone(@PathVariable String phone) throws ActException {
        try {
            userRegisterAPI.verifyAndSendCode(phone);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
        return ActResult.initialize(Boolean.TRUE);
    }

}
