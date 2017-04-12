package com.bjike.goddess.common.user.session.valid_right;


import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户登陆session管理类
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public final class UserSession {

    private static final Map<String, LoginUser> USER_SESSION = new ConcurrentHashMap<>(0);
    private static final RuntimeException TOKEN_NOT_NULL = new RuntimeException("token令牌不能为空");

    private UserSession() {
    }

    static {
        new SessionQuartz(USER_SESSION);
        System.out.println("sessionQuartz start");
    }

    /**
     * 新增用户会话信息
     *
     * @param token       令牌值
     * @param loginUser 登录用户信息
     * @return 是否已经登录
     */
    public static void put(String token, LoginUser loginUser) {
        if (null != token && !"".equals(token.trim())) {
            loginUser.setAccessTime(LocalDateTime.now());
            USER_SESSION.put(token, loginUser);
        } else {
            throw TOKEN_NOT_NULL;
        }
    }

    /**
     * 根据令牌删除用户会话信息
     *
     * @param token 登录令牌
     * @return 是否删除成功
     */
    public static void remove(String token) {
        if (null != token && !"".equals(token.trim())) {
            USER_SESSION.remove(token);
        } else {

            throw TOKEN_NOT_NULL;
        }
    }


    public static LoginUser get(String token) {
        if (null != token && !"".equals(token.trim())) {
            return USER_SESSION.get(token);
        }
        throw TOKEN_NOT_NULL;
    }


    /**
     * 检测用户会话是否存在
     *
     * @param token 令牌
     * @return 是否存在
     */
    public static boolean exist(String token) {
        if (null != token && !"".equals(token.trim())) {
            return USER_SESSION.get(token) != null;
        }
        throw TOKEN_NOT_NULL;
    }

    /**
     * 获取用户会话总数
     *
     * @return 总数
     */
    public static long count() {
        return USER_SESSION.size();
    }

    /**
     * 获取全部用户会话信息
     *
     * @return 会话信息集合
     */
    public static Map<String, LoginUser> sessions() {
        if (null != USER_SESSION && USER_SESSION.size() > 0) {
            return USER_SESSION;
        }
        return null;
    }

    public static boolean verify(String token) {
        return USER_SESSION.get(token) != null;
    }

}
