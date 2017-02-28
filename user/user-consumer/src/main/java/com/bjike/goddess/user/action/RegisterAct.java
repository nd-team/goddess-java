package com.bjike.goddess.user.action;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.user.dto.ext.UserRegisterDTO;
import com.bjike.goddess.user.service.UserRegisterAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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
    private UserRegisterAPI registerSer;

    /**
     *
     * @param dto 注册用户传输对象
     * @param result
     * @return
     * @throws ActException
     */
    @PostMapping("register")
    public ActResult register(@Valid UserRegisterDTO dto, BindingResult result) throws ActException {
        try {
            registerSer.verifyCodeAndReg(dto);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
        return new ActResult("注册成功！");
    }


    /**
     * 验证手机号码并发生验证码到手机
     *
     * @param phone 手机号码
     * @return
     * @throws ActException
     */
    @GetMapping("verifyPhone/{phone}")
    public ActResult sendCodeToPhone(@PathVariable String phone) throws ActException {
        try {
            registerSer.verifyAndSendCode(phone);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
        return ActResult.initialize(Boolean.TRUE);
    }

}
