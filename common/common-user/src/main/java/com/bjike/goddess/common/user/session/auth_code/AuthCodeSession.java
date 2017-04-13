package com.bjike.goddess.common.user.session.auth_code;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: [liguiqin]
 * @Date: [2017-04-12 09:46]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AuthCodeSession {

    private static final Map<String, AuthCode> AUTH_CODE_SESSION = new ConcurrentHashMap<>(0);
    private static final RuntimeException ACCOUNT_NOT_NULL = new RuntimeException("account令牌不能为空");

    private AuthCodeSession() {
    }

    static {
        new AuthCodeQuartz(AUTH_CODE_SESSION);
        System.out.println("auth_code_session start");
    }

    /**
     * 新增密码错误会话信息
     *
     * @param account 用户名,手机,昵称
     */
    public static void put(String account, String code) {
        if (null != account && !"".equals(account.trim())) {
            AuthCode authCode = get(account);
            if (null != authCode) {
                authCode.setAccessTime(LocalDateTime.now());
                authCode.setCode(code);
            } else {
                authCode = new AuthCode();
                authCode.setAccessTime(LocalDateTime.now());
                authCode.setCode(code);
            }
        } else {
            throw ACCOUNT_NOT_NULL;
        }
    }

    /**
     * 根据令牌删除错误会话信息
     *
     * @param account 用户名,手机,昵称
     */
    public static void remove(String account) {
        if (null != account && !"".equals(account.trim())) {
            AUTH_CODE_SESSION.remove(account);
        } else {

            throw ACCOUNT_NOT_NULL;
        }
    }


    public static AuthCode get(String account) {
        if (null != account && !"".equals(account.trim())) {
            return AUTH_CODE_SESSION.get(account);
        }
        throw ACCOUNT_NOT_NULL;
    }


}
