package com.bjike.goddess.user.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.redis.client.RedisClient;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.bo.UserSimpleBO;
import com.bjike.goddess.user.constant.UserCommon;
import com.bjike.goddess.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * 用户找回密码业务实现
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-30 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class UserFindPwdSerImpl implements UserFindPwdSer {
    @Autowired
    private UserSer userSer;
    @Autowired
    private RedisClient redis;
    @Autowired
    private Environment env;

    @Override
    public UserSimpleBO verifyAccount(String account, String authCode) throws SerException {
        UserBO userBO = userSer.findByAccountNumber(account);
        if (null == userBO) {
            throw new SerException("用户不存在");
        }
        String code =null;
        code =  redis.getMap(UserCommon.AUTH_CODE,account);
        if (null == code && !code.equalsIgnoreCase(authCode)) {
            throw new SerException("验证码不正确");
        }
        UserSimpleBO simpleBO = new UserSimpleBO();
        simpleBO.setNickname(userBO.getNickname());
        String phone = userBO.getPhone();
        phone = phone.substring(0, 3) + "*****" + phone.substring(8, phone.length());
        simpleBO.setPhone(phone);
        return simpleBO;
    }

    @Override
    public Boolean sendCodeByNickname(String nickname) throws SerException {
        UserBO bo = userSer.findByNickname(nickname);
        if (null != bo) {
            String code = "123456";
            redis.appendToMap(UserCommon.PHONE_CODE,nickname,code,Integer.parseInt(env.getProperty("phonecode.timeout")));

            return true;
        } else {
            throw new SerException();
        }
    }

    @Override
    public Boolean verifyPhoneCode(String nickname, String phoneCode) throws SerException {
        User user = null;
        UserBO bo = userSer.findByNickname(nickname);
        if (null != bo) {
            String code = redis.getMap(UserCommon.PHONE_CODE,nickname);
            if (null != code && !phoneCode.equalsIgnoreCase(code)) {
                throw new SerException("手机校验码错误");
            }
            return true;
        } else {
            throw new SerException("手机校验码错误");
        }
    }
}
