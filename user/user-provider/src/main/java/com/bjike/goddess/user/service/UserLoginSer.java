package com.bjike.goddess.user.service;


import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.utils.PasswordHash;
import com.bjike.goddess.user.dto.ext.UserLoginDTO;
import com.bjike.goddess.user.entity.User;
import com.bjike.goddess.user.session.authcode.AuthCode;
import com.bjike.goddess.user.session.authcode.AuthCodeSession;
import com.bjike.goddess.user.session.validcorrect.Subject;
import com.bjike.goddess.user.session.validcorrect.UserSession;
import com.bjike.goddess.user.session.validfail.ValidErrSession;
import com.bjike.goddess.user.utils.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-24 09:37]
 * @Description: [用户登陆业务实现]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "userSerCache")
@Service("userLoginSer")
public class UserLoginSer implements UserLoginAPI {

    @Autowired
    private UserAPI userAPI;


    @Override
    public Boolean verify(String token) throws SerException {
        if (TokenUtil.verify(token)) {//token 可能来自不同ip，不同客户端
            User user = UserSession.getUser(token);
                return true;

        }
        return false;
    }

    @Override
    public String login(UserLoginDTO dto) throws SerException {

        String token = null;
        String account = dto.getAccount();
        dto.setIp("192.168.0.1");
        User user = userAPI.findByAccountNumber(account); //通过用户名/手机号/或者邮箱查找用户
        if (null != user) {
            boolean authCode = validateAuthCode(account, dto.getAuthCode());
            if (authCode) { //验证码正确
                token = validatePassword(dto, user);  //验证密码
                if (StringUtils.isNotBlank(token)) { //登录成功处理业务
                    ValidErrSession.remove(account); //清除密码输错会话
                    AuthCodeSession.remove(account);//清除验证码
                    //记录登录日志
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
     * @param dto
     */
    private String validatePassword(UserLoginDTO dto, User persistUser) throws SerException {
        String token = null;
        String account = dto.getAccount();
        try {
            if (PasswordHash.validatePassword(dto.getPassword(), persistUser.getPassword())) {
                Map.Entry<String, Subject> entity = UserSession.getByUserId(persistUser.getId());
                if (null != entity) { //已登录过
                    token = entity.getKey();
                    Subject subject = entity.getValue();
                    if(subject.getLoginType().equals(dto.getLoginType())){
                        return token;
                    }else {
                        token = createToken( persistUser,  dto, account);
                        return  token;
                    }

                } else {
                    token = createToken( persistUser,  dto, account);
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

    private String  createToken(User persistUser, UserLoginDTO dto,String account){
        String token = TokenUtil.create("192.168.0.148", persistUser.getUsername());
        Subject subject = new Subject();
        subject.setUser(persistUser);
        subject.setLoginType(dto.getLoginType());
        subject.setIp(dto.getIp());
        subject.setRemember(dto.isRememberMe());
        UserSession.put(token, subject);
        ValidErrSession.remove(account);//删除密码验证错误次数统计
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
