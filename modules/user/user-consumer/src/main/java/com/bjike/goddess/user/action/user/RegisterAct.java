package com.bjike.goddess.user.action.user;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.user.api.UserRegisterAPI;
import com.bjike.goddess.user.to.AppUserRegisterTO;
import com.bjike.goddess.user.to.SmsCodeParameterTO;
import com.bjike.goddess.user.to.SmsCodeTO;
import com.bjike.goddess.user.to.UserRegisterTO;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户注册
 *
 * @Author: [liguiqin]
 * @Date: [2017-01-14 09:46]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@DefaultProperties
@RestController
public class RegisterAct {
    @Autowired
    private UserRegisterAPI userRegisterAPI;
    @Autowired
    private Environment environment;

    /**
     * 发送短信验证码
     *
     * @param phone 手机号
     * @des 发送短信验证码
     * @version v1
     */
    @GetMapping("v2/sendSmsCode/{phone}")
    public Result sendSmsCode(@PathVariable String phone ) throws ActException {
        try {
            SmsCodeParameterTO smsCodeParameterTO = new SmsCodeParameterTO();
            smsCodeParameterTO.setProduct(environment.getProperty("sms.product"));
            smsCodeParameterTO.setDomain(environment.getProperty("sms.domain"));
            smsCodeParameterTO.setAccessKeyId(environment.getProperty("sms.accessKeyId"));
            smsCodeParameterTO.setAccessKeySecret(environment.getProperty("sms.accessKeySecret"));
            smsCodeParameterTO.setSignName(environment.getProperty("sms.signName"));
            smsCodeParameterTO.setTemplateCode(environment.getProperty("sms.templateCode"));
            smsCodeParameterTO.setRandomNum(environment.getProperty("sms.randomNum"));
            smsCodeParameterTO.setPhoneNumber( phone );
            userRegisterAPI.sendSmsVerifyCode(smsCodeParameterTO);
            return new ActResult("send success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 注册
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
     * 发送验证码
     *
     * @param phone 手机号码
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/verify-code/{phone}")
    public Result sendCodeToPhone(@PathVariable String phone) throws ActException {
        try {
            userRegisterAPI.verifyAndSendCode(phone);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
        return ActResult.initialize(Boolean.TRUE);
    }




    /**
     * 校验验证码是否正确
     *
     * @param smsCodeTO 手机号码和验证码
     * @throws ActException
     * @version v1
     */
    @PostMapping("v2/verifyCode")
    public Result verifyCode( @Validated SmsCodeTO smsCodeTO,BindingResult bindingResult ) throws ActException {
        try {
           Boolean result=  userRegisterAPI.verifyCode(smsCodeTO.getPhoneNumber() ,smsCodeTO.getCode() );
            return ActResult.initialize(result);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 移动端验证密码
     *
     * @param password 密码
     * @param repassword 确认密码
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/checkPassword")
    public Result checkPassword(@RequestParam String password, @RequestParam String repassword ) throws ActException {
        try {
            userRegisterAPI.checkPassword(password ,repassword );
            return new ActResult("密码校验通过");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 移动端用户注册
     *
     * @param appUserRegisterTO 注册to
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/registerUser")
    public Result registerUser(@Validated(ADD.class) AppUserRegisterTO appUserRegisterTO, BindingResult bindingResult ) throws ActException {
        try {
            userRegisterAPI.registerUser(appUserRegisterTO );
            return new ActResult("注册成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 移动端填写前五位字母自动生成用户编号
     *
     * @param startNumber 开始字母(五位)
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/autogenerationNum")
    public Result autogenerationNum(@RequestParam String startNumber) throws ActException {
        try {
            String num = userRegisterAPI.autogenerationNum(startNumber );
            return ActResult.initialize(num);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
