package com.bjike.goddess.user.common.session.validcorrect;

import com.bjike.goddess.dbs.common.exception.SerException;
import com.bjike.goddess.user.common.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: 用户登陆session管理类]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public final class UserSession {

    private static final Logger CONSOLE = LoggerFactory.getLogger(UserSession.class);
    private static final Map<String, User> USER_SESSIONS = new ConcurrentHashMap<>(0);
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
     * @param token 令牌值
     * @param user  登录用户信息
     * @return 是否已经登录
     */
    public static void put(String token, User user) {
        if (StringUtils.isNotBlank(token)) {
            user.setAccessTime(LocalDateTime.now());
            USER_SESSIONS.put(token, user);
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
        }
        throw TOKEN_NOT_NULL;
    }

    /**
     * 根据令牌删除用户会话信息
     *
     * @param token 登录令牌
     * @return 是否删除成功
     */
    public static User get(String token) {
        if (StringUtils.isNotBlank(token)) {
           return USER_SESSIONS.get(token);
        }
        throw TOKEN_NOT_NULL;
    }


    public static void removeByUsername(String username) throws SerException {
        if (StringUtils.isNotBlank(username)) {
            for (Map.Entry<String, User> entry : USER_SESSIONS.entrySet()) {
                if (username.equals(entry.getValue().getUsername())) {
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
    public static Map<String, User> sessions() {
        if (null != USER_SESSIONS && USER_SESSIONS.size() > 0) {
            return USER_SESSIONS;
        }
        return null;
    }

    public static boolean verify(String token) {
        return USER_SESSIONS.get(token) != null;
    }

    public static void main(String[] args)throws Exception {
        USER_SESSIONS.put("1",new User());
        USER_SESSIONS.put("2",new User());
        USER_SESSIONS.put("3",new User());
        USER_SESSIONS.put("4",new User());
        Thread.sleep(1000*60*2);
        System.out.println("first end ....!"+USER_SESSIONS.size());
        USER_SESSIONS.put("5",new User());
        USER_SESSIONS.put("6",new User());
        USER_SESSIONS.put("7",new User());
        USER_SESSIONS.put("8",new User());
        Thread.sleep(1000*60*2);
        System.out.println("second end ....!"+USER_SESSIONS.size());
        String token = TokenUtils.create("119.129.208.1", "admin");
        System.out.println(TokenUtils.verify(TokenUtils.create("119.129.208.1", "admin")));
    }


}
