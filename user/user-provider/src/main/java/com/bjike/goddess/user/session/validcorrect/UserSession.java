package com.bjike.goddess.user.session.validcorrect;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger CONSOLE = LoggerFactory.getLogger(UserSession.class);
    private static final Map<String, Subject> USER_SESSIONS = new ConcurrentHashMap<>(0);
    private static final RuntimeException TOKEN_NOT_NULL = new RuntimeException("token令牌不能为空");

    private UserSession() {
    }

    static {
        new SessionQuartz(USER_SESSIONS);
        CONSOLE.info("sessionQuartz start");

    }

    /**
     * 新增用户会话信息
     *
     * @param token   令牌值
     * @param subject 登录用户信息
     * @return 是否已经登录
     */
    public static void put(String token, Subject subject) {
        if (StringUtils.isNotBlank(token)) {
            subject.setAccessTime(LocalDateTime.now());
            USER_SESSIONS.put(token, subject);
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
        if (StringUtils.isNotBlank(token)) {
            USER_SESSIONS.remove(token);
        } else {

            throw TOKEN_NOT_NULL;
        }
    }


    public static User getUser(String token) {
        if (StringUtils.isNotBlank(token)) {
            Subject subject = USER_SESSIONS.get(token);
            if (null != subject) {
                subject.setAccessTime(LocalDateTime.now());
                return subject.getUser();
            }
            return null;
        }
        throw TOKEN_NOT_NULL;
    }

    public static Map.Entry<String, Subject> getByUserId(String user_id) {
        if (StringUtils.isNotBlank(user_id)) {
            for (Map.Entry<String, Subject> entry : USER_SESSIONS.entrySet()) {
                if (user_id.equals(entry.getValue().getUser().getId())) {
                    return entry;
                }
            }
        } else {
            throw TOKEN_NOT_NULL;
        }
        return null;
    }


    public static Subject get(String token) {
        if (StringUtils.isNotBlank(token)) {
            Subject subject = USER_SESSIONS.get(token);
            if (null != subject) {
                subject.setAccessTime(LocalDateTime.now());
            }
            return subject;
        }
        throw TOKEN_NOT_NULL;
    }

    public static void removeByUsername(String username) throws SerException {
        if (StringUtils.isNotBlank(username)) {
            for (Map.Entry<String, Subject> entry : USER_SESSIONS.entrySet()) {
                if (username.equals(entry.getValue().getUser().getUsername())) {
                    USER_SESSIONS.remove(entry.getKey());
                    break;
                }
            }
        } else {
            throw new SerException("username用户名不能为空");
        }
    }


    /**
     * 检测用户会话是否存在
     *
     * @param token 令牌
     * @return 是否存在
     */
    public static boolean exist(String token) {
        if (StringUtils.isNotBlank(token)) {
            return USER_SESSIONS.get(token) != null;
        }
        throw TOKEN_NOT_NULL;
    }

    /**
     * 获取用户会话总数
     *
     * @return 总数
     */
    public static long count() {
        return USER_SESSIONS.size();
    }

    /**
     * 获取全部用户会话信息
     *
     * @return 会话信息集合
     */
    public static Map<String, Subject> sessions() {
        if (null != USER_SESSIONS && USER_SESSIONS.size() > 0) {
            return USER_SESSIONS;
        }
        return null;
    }

    public static boolean verify(String token) {
        return USER_SESSIONS.get(token) != null;
    }

}
