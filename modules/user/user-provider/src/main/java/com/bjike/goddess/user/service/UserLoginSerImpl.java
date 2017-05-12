package com.bjike.goddess.user.service;


import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.utils.PasswordHash;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.token.TokenUtil;
import com.bjike.goddess.redis.client.RedisClient;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.bo.rbac.PermissionBO;
import com.bjike.goddess.user.entity.User;
import com.bjike.goddess.user.service.rbac.PermissionSer;
import com.bjike.goddess.user.session.auth_code.AuthCodeSession;
import com.bjike.goddess.user.session.constant.UserCommon;
import com.bjike.goddess.user.session.valid_err.PwdErrSession;
import com.bjike.goddess.user.session.valid_right.LoginUser;
import com.bjike.goddess.user.session.valid_right.UserSession;
import com.bjike.goddess.user.to.UserLoginLogTO;
import com.bjike.goddess.user.to.UserLoginTO;
import com.bjike.goddess.user.utils.RSACoder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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
    private PermissionSer permissionSer;


    @Transactional
    @Override
    public String login(UserLoginTO loginTO) throws SerException {
        String token = null;
        String account = loginTO.getAccount();
        UserBO userBO = userSer.findByAccountNumber(account); //通过用户名/手机号/或者邮箱查找用户
        if (null != userBO) {
            User user = BeanTransform.copyProperties(userBO, User.class, true);
            boolean authCode = validateAuthCode(account, loginTO.getAuthCode());
            if (authCode) { //验证码正确
                token = validatePassword(loginTO, user);  //验证密码
                if (StringUtils.isNotBlank(token)) { //登录成功处理业务
                    PwdErrSession.remove(account);//删除密码验证错误次数统计
                    AuthCodeSession.remove(account);//清除验证码
                    saveLoginLog(loginTO, user);  //记录登录日志
                    LoginUser loginUser = saveToSessionAndRedis(user, token); //保存登录用户到session和redis
                    setPermission(loginUser, token);
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

    @Override
    public Boolean signOut(String token) throws SerException {
        if (StringUtils.isNotBlank(token)) {
            UserSession.remove(token);
            redis.removeMap(UserCommon.LOGIN_USER, token);
            return true;
        }
        throw new SerException("userToken can not null!");
    }

    private void saveLoginLog(UserLoginTO loginTO, User user) throws SerException {
        UserLoginLogTO userLoginLogTO = new UserLoginLogTO();
        userLoginLogTO.setLoginIp(loginTO.getIp());
        userLoginLogTO.setLoginType(loginTO.getLoginType());
        userLoginLogTO.setLoginAddress("not has address");
        userLoginLogTO.setLoginType(loginTO.getLoginType());
        userLoginLogTO.setUser(user);
        userLoginLogTO.setLoginTime(DateUtil.dateToString(LocalDateTime.now()));
        userLoginLogSer.saveLoginLog(userLoginLogTO);

    }

    /**
     * 保存登录用户到Session redis
     *
     * @param user
     * @param token
     * @throws SerException
     */
    private LoginUser saveToSessionAndRedis(User user, String token) throws SerException {
        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(user, loginUser);
        UserSession.put(token, loginUser);
        redis.appendToMap(UserCommon.LOGIN_USER, token, JSON.toJSONString(loginUser), UserCommon.LOGIN_TIMEOUT);
        return loginUser;
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
            //该密码经过公钥加密
            byte[] decodedData = RSACoder.decryptByPrivateKey(loginTO.getPassword().trim(),
                    userSer.privateKey());
            String password = new String(decodedData); //得到明文密码
             password = loginTO.getPassword(); //不使用加密公钥
            if (PasswordHash.validatePassword(password, persistUser.getPassword())) {
                token = createToken(persistUser, loginTO);
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
        String token = TokenUtil.create(loginTO.getIp(), persistUser.getUsername());
        saveToSessionAndRedis(persistUser, token);
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
        String code = AuthCodeSession.get(account);
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

    private void setPermission(LoginUser loginUser, String token) throws SerException {
        List<PermissionBO> permissions = permissionSer.findByUserId(loginUser.getId());
        if (null != permissions && permissions.size() > 0) {
            loginUser.setPermissions(permissions);
        }
        UserSession.put(token, loginUser);
    }

}
