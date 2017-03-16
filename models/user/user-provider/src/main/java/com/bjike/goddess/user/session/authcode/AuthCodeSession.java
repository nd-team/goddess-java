package com.bjike.goddess.user.session.authcode;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 验证码管理会话
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-28 09:28]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AuthCodeSession {

    private static final Logger CONSOLE = LoggerFactory.getLogger(AuthCodeSession.class);
    private static final Map<String, AuthCode> AUTH_CODE_SESSIONS = new ConcurrentHashMap<>(0);
    private static final RuntimeException ACCOUNT_NOT_NULL = new RuntimeException("账户名不能为空");

    static {
        CONSOLE.info("AuthCodeSession start");
        new AuthCodeQuartz(AUTH_CODE_SESSIONS);
    }


    /**
     * 通过用户查找验证码
     *
     * @param account 账号名
     */
    public static AuthCode get(String account) {
        if (StringUtils.isNotBlank(account)) {
            return AUTH_CODE_SESSIONS.get(account);
        } else {
            throw ACCOUNT_NOT_NULL;
        }
    }

    /**
     * 添加验证码
     *
     * @param account 账号名
     */
    public static void put(String account, AuthCode authCode) {
        if (StringUtils.isNotBlank(account)) {
            if (AUTH_CODE_SESSIONS.containsKey(account)) {
                AUTH_CODE_SESSIONS.get(account).setCode(authCode.getCode());
            } else {
                AUTH_CODE_SESSIONS.put(account, authCode);
            }
        } else {
            throw ACCOUNT_NOT_NULL;
        }
    }


    /**
     * 获取会话总数
     *
     * @return 总数
     */
    public static long count() {
        return AUTH_CODE_SESSIONS.size();
    }

    /**
     * 根据用户名删除验证会话信息
     *
     * @param account 用户名
     */
    public static void remove(String account) {
        if (StringUtils.isNotBlank(account)) {
            AUTH_CODE_SESSIONS.remove(account);
            return;
        }
        throw ACCOUNT_NOT_NULL;
    }


}
