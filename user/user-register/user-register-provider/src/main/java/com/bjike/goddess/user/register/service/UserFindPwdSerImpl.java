package com.bjike.goddess.user.register.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bjike.goddess.dbs.jpa.exception.SerException;
import com.bjike.goddess.user.common.entity.User;
import com.bjike.goddess.user.common.service.IUserSer;
import com.bjike.goddess.user.common.session.authcode.AuthCode;
import com.bjike.goddess.user.common.session.authcode.AuthCodeSession;
import com.bjike.goddess.user.common.session.phonecode.PhoneCode;
import com.bjike.goddess.user.common.session.phonecode.PhoneCodeSession;
import com.bjike.goddess.user.common.utils.authCode.AuthCodeGenerate;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-30 15:47]
 * @Description: 用户找回密码业务实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public class UserFindPwdSerImpl implements IUserFindPwdSer {
    @Reference
    IUserSer userSer;

    @Override
    public BufferedImage generatePwdAuthCode(String sid) throws SerException {
        Map<String, BufferedImage> imageMap = AuthCodeGenerate.build();
        BufferedImage image = null;
        String code = null;
        for (Map.Entry<String, BufferedImage> entry : imageMap.entrySet()) {
            image = entry.getValue();
            code = entry.getKey();
        }
        handleAuthCode(sid, code);
        return image;
    }

    /**
     * 保存找回密码验证码到session
     *
     * @param sid
     * @param code
     */
    private void handleAuthCode(String sid, String code) {
        AuthCode authCode = new AuthCode();
        authCode.setCode(code);
        AuthCodeSession.put(sid, authCode);
    }

    @Override
    public User verifyAccount(String account, String authCode, String sid) throws SerException {
        User user = userSer.findByAccountNumber(account);
        if (user == userSer.findByAccountNumber(account)) {
            throw new SerException("用户不存在");
        }
        AuthCode auth = AuthCodeSession.get(sid);
        if (null == auth && !auth.getCode().equalsIgnoreCase(authCode)) {
            throw new SerException("验证码不正确");
        }
        User simpleUser = new User();
        simpleUser.setNickname(user.getNickname());
        String phone = user.getPhone();
        phone = phone.substring(0, 3) + "*****" + phone.substring(8, phone.length());
        simpleUser.setPhone(phone);
        return simpleUser;
    }

    @Override
    public Boolean sendCodeToPhone(String nickname) throws SerException {
        User user = userSer.findByNickname(nickname);
        if (null != user) {
            PhoneCode phoneCode = new PhoneCode();
            phoneCode.setCode("123456");
            PhoneCodeSession.put(user.getPhone(), phoneCode);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean
    verifyPhoneCode(String nickname, String phoneCode) throws SerException {
        User user = userSer.findByNickname(nickname);
        if (null != user) {
            PhoneCode code = PhoneCodeSession.get(user.getPhone());
            if (null == code && !phoneCode.equalsIgnoreCase(code.getCode())) {
                throw new SerException("手机校验码错误");
            }
            return true;
        } else {
            throw new SerException("手机校验码错误");
        }
    }
}
