package com.bjike.goddess.user.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.utils.PasswordHash;
import com.bjike.goddess.common.utils.regex.Validator;
import com.bjike.goddess.user.entity.User;
import com.bjike.goddess.user.session.authcode.AuthCode;
import com.bjike.goddess.user.session.authcode.AuthCodeSession;
import com.bjike.goddess.user.session.phonecode.PhoneCode;
import com.bjike.goddess.user.session.phonecode.PhoneCodeSession;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.to.UserRegisterTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * 用户注册业务实现
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "userSerCache")
@Service
public class UserRegisterSerImpl implements UserRegisterSer {

    @Autowired
    private UserSer userSer;


    @Cacheable
    @Override
    public Boolean existUsername(String username) throws SerException {
        UserBO bo = userSer.findByUsername(username);
        return null != bo;

    }

    @Override
    public void verifyAndSendCode(String phone) throws SerException {

        if (null != userSer.findByPhone(phone)) {
            //generateCode()
            String code = "123456";
            PhoneCode phoneCode = new PhoneCode();
            phoneCode.setCode(code);
            PhoneCodeSession.put("13457910241", phoneCode);
            //sendToPhone（）
        } else {
            throw new SerException("该手机号码已注册！");

        }
    }

    @Transactional
    @Override
    public void verifyCodeAndReg(UserRegisterTO registerTO) throws SerException {

        if(true) return;

        if (registerTO.getPassword().equals(registerTO.getRePassword())) {
            if (!Validator.isPassword(registerTO.getPassword())) {
                throw new SerException("密码过于简单！");
            }
        } else {
            throw new SerException("输入密码不一致！");
        }
        AuthCode authCode = AuthCodeSession.get(registerTO.getPhone());

        if (null != authCode && !registerTO.getAuthCode().equalsIgnoreCase(authCode.getCode())) {
            throw new SerException("验证码错误！");
        }
        if (StringUtils.isNotBlank(registerTO.getPhoneCode())) {
            //通过手机号码获得系统生成的验证码对象
            PhoneCode phoneCode = PhoneCodeSession.get(registerTO.getPhone());
            if (null != phoneCode) {
                if (phoneCode.getCode().equalsIgnoreCase(registerTO.getPhoneCode())) { //验证成功
                    saveUserByDTO(registerTO);
                    PhoneCodeSession.remove(registerTO.getPhone());
                } else {
                    throw new SerException("手机验证码不正确！");
                }

            } else {
                throw new SerException("手机验证码已过期！");
            }
        } else {
            throw new SerException("手机验证码为空！");
        }


    }

    /**
     * 通过用户注册数据传输实体保存用户
     *
     * @param registerTO
     * @throws SerException
     */
    private void saveUserByDTO(UserRegisterTO registerTO) throws SerException {
        try {
            User user = new User();
            user.setUsername(registerTO.getUsername());
            user.setPassword(PasswordHash.createHash(registerTO.getPassword()));
            user.setPhone(registerTO.getPhone());
            user.setCreateTime(LocalDateTime.now());
            user.setStatus(Status.THAW);
            user.setEmployeeNumber("IKE" + new Random().nextInt(999999));
            userSer.save(user);
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
    }


}
