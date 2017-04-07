package com.bjike.goddess.user.service;


import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.utils.PasswordHash;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.token.TokenUtil;
import com.bjike.goddess.redis.client.RedisClient;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.constant.UserCommon;
import com.bjike.goddess.user.dto.UserDTO;
import com.bjike.goddess.user.entity.User;
import com.bjike.goddess.user.entity.UserLoginLog;
import com.bjike.goddess.user.to.UserLoginTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 用户登陆业务实现
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-24 09:37]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "userSerCache")
@Service
public class UserLoginSerImpl implements UserLoginSer {

    @Autowired
    private UserSer userSer;
    @Autowired
    private UserLoginLogSer userLoginLogSer;
    @Autowired
    private RedisClient redis;
    @Autowired
    private Environment env;


    @Override
    public String login(UserLoginTO loginTO) throws SerException {
        String token = null;
        String account = loginTO.getAccount();
        loginTO.setIp("192.168.0.1");
        UserBO userBO = userSer.findByAccountNumber(account); //通过用户名/手机号/或者邮箱查找用户
        if (null != userBO) {
            User user = BeanTransform.copyProperties(userBO, User.class,true);
            boolean authCode = validateAuthCode(account, loginTO.getAuthCode());
            if (authCode) { //验证码正确
                token = validatePassword(loginTO, user);  //验证密码
                if (StringUtils.isNotBlank(token)) { //登录成功处理业务
                    redis.removeMap(UserCommon.VALID_ERR,loginTO.getAccount());//删除密码验证错误次数统计
                    redis.removeMap(UserCommon.AUTH_CODE,account);//清除验证码
                    //保存登录用户到redis
                    saveToRedis(user, token);
                    //记录登录日志
                    UserDTO dto= new UserDTO();
                    dto.getConditions().add(Restrict.eq("id",user.getId()));
                    user = userSer.findOne(dto);
                    UserLoginLog loginLog = new UserLoginLog();
                    loginLog.setUser(user);
                    loginLog.setLoginIp(loginTO.getIp());
                    loginLog.setLoginTime(LocalDateTime.now());
                    loginLog.setLoginType(loginTO.getLoginType());
                    loginLog.setLoginAddress("not has address");
                  //  userLoginLogSer.save(loginLog);
                } else {
                    throw new SerException("账号或者密码错误");
                }
            } else {
                throw new SerException("验证码错误");
            }
        } else {
            throw new SerException("账号或者密码错误");
        }
        return token;
    }

    /**
     * 保存登录用户到redis
     *
     * @param user
     * @param token
     * @throws SerException
     */
    private void saveToRedis(User user, String token) throws SerException {
        UserBO userBO = BeanTransform.copyProperties(user, UserBO.class);
        redis.save(user.getId(), token);
        redis.appendToMap(UserCommon.LOGIN_USER, token, JSON.toJSONString(userBO), Integer.parseInt(env.getProperty("session.timeout")));
    }


    /**
     * 验证登陆密码
     *
     * @param loginTO
     */
    private String validatePassword(UserLoginTO loginTO, User persistUser) throws SerException {
        String token = null;
        String account = loginTO.getAccount();
        try {
            if (PasswordHash.validatePassword(loginTO.getPassword(), persistUser.getPassword())) {
                token = redis.get(persistUser.getId());
                if (StringUtils.isNotBlank(token)) { //已登录过
                    String userBo_str = redis.getMap(UserCommon.LOGIN_USER, token);
                    if (StringUtils.isNotBlank(userBo_str)) {
                        return token;
                    } else {
                        token = createToken(persistUser, loginTO);

                    }
                } else {
                    token = createToken(persistUser, loginTO);
                }
            } else { //密码错误
                String errCount = redis.getMap(UserCommon.VALID_ERR, account);
                if (StringUtils.isNotBlank(errCount)) {
                    int count = Integer.parseInt(errCount) + 1;
                    redis.appendToMap(UserCommon.VALID_ERR, account, String.valueOf(count), Integer.parseInt(env.getProperty("validerr.timeout")));
                } else {
                    redis.appendToMap(UserCommon.VALID_ERR, account, "1", Integer.parseInt(env.getProperty("validerr.timeout")));
                }
                return null;
            }

        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
        return token;

    }

    /**
     * 创建 token 令牌
     *
     * @param persistUser
     * @param loginTO
     * @return
     */
    private String createToken(User persistUser, UserLoginTO loginTO) throws SerException {
        String token = TokenUtil.create("192.168.0.148", persistUser.getUsername());
        saveToRedis(persistUser, token);
        redis.removeMap(UserCommon.VALID_ERR,loginTO.getAccount());//删除密码验证错误次数统计
        return token;
    }

    /**
     * 验证 验证码
     *
     * @param account
     * @param authCode
     * @return
     */
    private boolean validateAuthCode(String account, String authCode)throws SerException {
        String code = redis.getMap(UserCommon.AUTH_CODE,account);
        boolean pass = false;
        if (null == code) {
            pass = true;
        } else {
            if (code.equals(authCode)) {
                pass = true;
            }
        }
        return pass;
    }

    @Override
    public Boolean loginOut(String token) throws SerException {
        String userBo_str = redis.getMap(UserCommon.LOGIN_USER, token);
        if (StringUtils.isNotBlank(userBo_str)) {
            UserBO bo = JSON.parseObject(userBo_str, UserBO.class);
            redis.removeMap(UserCommon.LOGIN_USER, token);
            redis.remove(bo.getId());
        }
        return true;
    }


}
