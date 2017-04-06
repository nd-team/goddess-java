package com.bjike.goddess.user.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.redis.client.RedisClient;
import com.bjike.goddess.user.constant.UserCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * 用户验证码业务实现
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-26 09:36]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class UserAuthCodeSerImpl implements UserAuthCodeSer {

    @Autowired
    private RedisClient redisClient;
    @Autowired
    private Environment env;

    @Override
    public Boolean showAuthCode(String account) throws SerException {

        String code = redisClient.getMap(UserCommon.VALID_ERR, account);
        if (null != code && Integer.parseInt(code) >= 5) { //验证次数大于5次需要验证码
            return true;
        }
        return false;
    }


    /**
     * 保存验证码到session
     *
     * @param account
     * @param code
     */
    public void handleAuthCode(String account, String code) throws SerException {
        int seconds = Integer.parseInt(env.getProperty("authcode.timeout"));
        redisClient.appendToMap(UserCommon.AUTH_CODE, account, code, seconds);
    }

}
