package com.bjike.goddess.user.login.service;


import com.bjike.goddess.user.login.dto.UserLoginDto;
import com.dounine.corgi.security.PasswordHash;
import com.dounine.corgi.spring.rpc.Reference;
import com.dounine.corgi.spring.rpc.Service;
import org.apache.commons.lang3.StringUtils;
import com.bjike.goddess.dbs.jpa.exception.SerException;
import com.bjike.goddess.user.common.entity.User;
import com.bjike.goddess.user.common.enums.LoginStatus;
import com.bjike.goddess.user.common.service.IUserSer;
import com.bjike.goddess.user.common.session.authcode.AuthCode;
import com.bjike.goddess.user.common.session.authcode.AuthCodeSession;
import com.bjike.goddess.user.common.session.validcorrect.TokenUtils;
import com.bjike.goddess.user.common.session.validcorrect.UserSession;
import com.bjike.goddess.user.common.session.validfail.ValidErrSession;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-24 09:37]
 * @Description: [用户登陆业务实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Service
public class UserLoginSerImpl implements IUserLoginSer {

    @Reference
    private IUserSer userSer;


    @Override
    public Boolean verify(String token) throws SerException {
        if (TokenUtils.verify(token)) {//token 可能来自不同ip，不同客户端
            User user = UserSession.get(token);
            if (null != user && user.getLoginStatus().equals(LoginStatus.LOGIN)) {
                return true;
            }
            return false;
        }
        throw new SerException("token无效");
    }

    @Override
    public String login(UserLoginDto dto) throws SerException {

        String token = null;
        String account = dto.getAccount();
        dto.setIp("192.168.0.1");
        User user = userSer.findByAccountNumber(account); //通过用户名/手机号/或者邮箱查找用户
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
    private String validatePassword(UserLoginDto dto, User persistUser) throws SerException {
        String token = null;
        String account = dto.getAccount();
        try {
            if (PasswordHash.validatePassword(dto.getPassword(), persistUser.getPassword())) {
                UserSession.removeByUsername(persistUser.getUsername());
                token = TokenUtils.create(dto.getIp(), persistUser.getUsername());
                persistUser.setLoginStatus(LoginStatus.LOGIN);
                UserSession.put(token, persistUser);
                ValidErrSession.remove(account);//删除密码验证错误次数统计
            } else { //密码错误
                ValidErrSession.putValidErr(account);
            }

        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
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
        User user = UserSession.get(token);
        if (null != user) {
            user.setLoginStatus(LoginStatus.LOGINOUT);
        }
        return true;
    }


}
