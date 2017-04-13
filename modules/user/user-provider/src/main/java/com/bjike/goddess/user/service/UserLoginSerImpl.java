package com.bjike.goddess.user.service;


import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.utils.PasswordHash;
import com.bjike.goddess.common.user.session.auth_code.AuthCode;
import com.bjike.goddess.common.user.session.auth_code.AuthCodeSession;
import com.bjike.goddess.common.user.session.constant.UserCommon;
import com.bjike.goddess.common.user.session.valid_err.PwdErrSession;
import com.bjike.goddess.common.user.session.valid_right.LoginUser;
import com.bjike.goddess.common.user.session.valid_right.UserSession;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.token.TokenUtil;
import com.bjike.goddess.redis.client.RedisClient;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.entity.User;
import com.bjike.goddess.user.enums.LoginType;
import com.bjike.goddess.user.to.UserLoginLogTO;
import com.bjike.goddess.user.to.UserLoginTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    @Transactional
    @Override
    public String login(UserLoginTO loginTO) throws SerException {
        String token = null;
        String account = loginTO.getAccount();
        loginTO.setIp("192.168.0.1");
        UserBO userBO = userSer.findByAccountNumber(account); //通过用户名/手机号/或者邮箱查找用户
        if (null != userBO) {
            User user = BeanTransform.copyProperties(userBO, User.class, true);
            boolean authCode = validateAuthCode(account, loginTO.getAuthCode());
            if (authCode) { //验证码正确
                token = validatePassword(loginTO, user);  //验证密码
                if (StringUtils.isNotBlank(token)) { //登录成功处理业务
                    PwdErrSession.remove(account);//删除密码验证错误次数统计
                    AuthCodeSession.remove(account);//清除验证码
                    //保存登录用户到session
                    saveToSession(user, token);
                    //记录登录日志
                    saveLoginLog(loginTO, user);
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

    private void saveLoginLog(UserLoginTO loginTO, User user) throws SerException {
        UserLoginLogTO userLoginLogTO = new UserLoginLogTO();
        userLoginLogTO.setLoginIp(loginTO.getIp());
        userLoginLogTO.setLoginType(loginTO.getLoginType());
        userLoginLogTO.setLoginAddress("not has address");
        userLoginLogTO.setLoginType(LoginType.PC);
        userLoginLogTO.setUser(user);
        userLoginLogTO.setLoginTime(DateUtil.dateToString(LocalDateTime.now()));
        userLoginLogSer.saveLoginLog(userLoginLogTO);

    }

    /**
     * 保存登录用户到Session
     *
     * @param user
     * @param token
     * @throws SerException
     */
    private void saveToSession(User user, String token) throws SerException {
        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(user, loginUser);
        UserSession.put(token, loginUser);
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
                token = redis.getMap(UserCommon.USERID_TOKEN, persistUser.getId());
                if (StringUtils.isNotBlank(token)) { //已登录过
                    if (null == UserSession.get(token)) { //重新设置登录用户到session
                        UserSession.put(token, BeanTransform.copyProperties(persistUser, LoginUser.class));
                    }
                    return token;
                } else {
                    token = createToken(persistUser, loginTO);
                }
            } else { //密码错误
                PwdErrSession.put(account);
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
        saveToSession(persistUser, token);
        PwdErrSession.remove(loginTO.getAccount());//删除密码验证错误次数统计
        return token;
    }

    /**
     * 验证 验证码
     *
     * @param account
     * @param authCode
     * @return
     */
    private boolean validateAuthCode(String account, String authCode) throws SerException {
        AuthCode code = AuthCodeSession.get(account);
        boolean pass = false;
        if (null == code) {
            pass = true;
        } else {
            if (code.getCode().equals(authCode)) {
                pass = true;
            }
        }
        return pass;
    }

    @Override
    public Boolean loginOut(String token) throws SerException {
        if (StringUtils.isNotBlank(token)) {
            LoginUser loginUser = UserSession.get(token);
            UserSession.remove(token);
            redis.removeMap(UserCommon.LOGIN_USER, token);
            if (null != loginUser) {
                redis.removeMap(UserCommon.USERID_TOKEN, loginUser.getId());
            }
            return true;
        }
        throw new SerException("userToken can not null!");
    }


}
