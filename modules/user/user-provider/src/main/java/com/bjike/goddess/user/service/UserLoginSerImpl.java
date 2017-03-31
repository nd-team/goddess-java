package com.bjike.goddess.user.service;


import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.utils.PasswordHash;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.token.TokenUtil;
import com.bjike.goddess.user.api.UserLoginLogAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.entity.User;
import com.bjike.goddess.user.entity.UserLoginLog;
import com.bjike.goddess.user.session.authcode.AuthCode;
import com.bjike.goddess.user.session.authcode.AuthCodeSession;
import com.bjike.goddess.user.session.validcorrect.Subject;
import com.bjike.goddess.user.session.validcorrect.UserSession;
import com.bjike.goddess.user.session.validfail.ValidErrSession;
import com.bjike.goddess.user.to.UserLoginLogTO;
import com.bjike.goddess.user.to.UserLoginTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

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


    @Override
    public String login(UserLoginTO loginTO) throws SerException {
        String token = null;
        String account = loginTO.getAccount();
        loginTO.setIp("192.168.0.1");
        UserBO userBO = userSer.findByAccountNumber(account); //通过用户名/手机号/或者邮箱查找用户
        if (null != userBO) {
            User user = BeanTransform.copyProperties(userBO, User.class);
            boolean authCode = validateAuthCode(account, loginTO.getAuthCode());
            if (authCode) { //验证码正确
                token = validatePassword(loginTO, user);  //验证密码
                if (StringUtils.isNotBlank(token)) { //登录成功处理业务
                    ValidErrSession.remove(account); //清除密码输错会话
                    AuthCodeSession.remove(account);//清除验证码
                    //存入session
                    Subject subject = new Subject();
                    subject.setAccessTime(LocalDateTime.now());
                    subject.setIp(loginTO.getIp());
                    subject.setLoginType(loginTO.getLoginType());
                    subject.setUser(user);
                    UserSession.put(token, subject);
                    //记录登录日志
                    UserLoginLog loginLog = new UserLoginLog();
                    loginLog.setUser(user);
                    loginLog.setLoginIp(loginTO.getIp());
                    loginLog.setLoginTime(LocalDateTime.now());
                    loginLog.setLoginType(loginTO.getLoginType());
                    loginLog.setLoginAddress("not has address");
                    userLoginLogSer.save(loginLog);
                } else {
                    throw new SerException("账号或者密码错误");
                }
            } else {
                throw new SerException("验证码错误");
            }
        }
        return token;
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
                Map.Entry<String, Subject> entity = UserSession.getByUserId(persistUser.getId());
                if (null != entity) { //已登录过
                    token = entity.getKey();
                    Subject subject = entity.getValue();
                    if (subject.getLoginType().equals(loginTO.getLoginType())) {
                        return token;
                    } else {
                        token = createToken(persistUser, loginTO);
                        return token;
                    }

                } else {
                    token = createToken(persistUser, loginTO);
                    return token;
                }
            } else { //密码错误
                ValidErrSession.putValidErr(account);
                return null;
            }

        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }

    }

    /**
     * 创建 token 令牌
     *
     * @param persistUser
     * @param loginTO
     * @return
     */
    private String createToken(User persistUser, UserLoginTO loginTO) {
        String token = TokenUtil.create("192.168.0.148", persistUser.getUsername());
        Subject subject = new Subject();
        subject.setUser(persistUser);
        subject.setLoginType(loginTO.getLoginType());
        subject.setIp(loginTO.getIp());
        subject.setRemember(loginTO.isRememberMe());
        UserSession.put(token, subject);
        ValidErrSession.remove(loginTO.getAccount());//删除密码验证错误次数统计
        return token;
    }

    /**
     * 验证 验证码
     *
     * @param account
     * @param authCode
     * @return
     */
    private boolean validateAuthCode(String account, String authCode) {
        AuthCode auth = AuthCodeSession.get(account);
        boolean pass = false;
        if (null == auth) {
            pass = true;
        } else {
            if (auth.getCode().equals(authCode)) {
                pass = true;
            }
        }
        return pass;
    }

    @Override
    public Boolean loginOut(String token) throws SerException {
        User user = UserSession.getUser(token);
        if (null != user) {
//            user.setLoginStatus(LoginStatus.LOGINOUT);
        }
        return true;
    }


}
