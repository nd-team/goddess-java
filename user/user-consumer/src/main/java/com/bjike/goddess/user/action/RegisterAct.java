package com.bjike.goddess.user.action;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.user.dto.ext.UserRegisterDTO;
import com.bjike.goddess.user.service.UserRegisterAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: [liguiqin]
 * @Date: [2017-01-14 09:46]
 * @Description: [用户注册]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("user")
public class RegisterAct {
    @Autowired
    private UserRegisterAPI registerSer;

    @GetMapping("register")
    public ActResult register(UserRegisterDTO dto) throws ActException {
        try {
            registerSer.verifyCodeAndReg(dto);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
        return new ActResult();
    }


    /**
     * 验证手机号码并发生验证码到手机
     * @param phone
     * @return
     * @throws ActException
     */
    @GetMapping("verifyPhone")
    public ActResult sendCodeToPhone(String phone) throws ActException {
        try {
            registerSer.verifyAndSendCode(phone);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
        return  ActResult.initialize(Boolean.TRUE);
    }

}
