package com.bjike.goddess.user.session.valid_right;


import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.redis.client.RedisClient;
import com.bjike.goddess.user.session.auth_code.AuthCodeSession;
import com.bjike.goddess.user.session.constant.UserCommon;
import com.google.common.cache.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

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
    private static final RuntimeException TOKEN_NOT_NULL = new RuntimeException("token令牌不能为空");
    private static Logger logger = LoggerFactory.getLogger(AuthCodeSession.class);
    public static RedisClient redisClient;

    private UserSession() {
    }

    private static final LoadingCache<String, LoginUser> USER_SESSION = CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.DAYS)
            .maximumSize(1000)
            .removalListener(new RemovalListener<String, LoginUser>() {
                @Override
                public void onRemoval(RemovalNotification<String, LoginUser> notification) {
                    removeByRedis(notification.getKey());
                    logger.info("remove:" + notification.getKey());

                }
            })
            .build(new CacheLoader<String, LoginUser>() {
                @Override
                public LoginUser load(String key) throws Exception {
                    return null;
                }
            });

    /**
     * 移除redis
     *
     * @param token
     */
    private static void removeByRedis(String token) {
        try {
            redisClient.removeMap(UserCommon.LOGIN_USER, token);
        } catch (SerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 新增用户会话信息
     *
     * @param token     令牌值
     * @param loginUser 登录用户信息
     * @return 是否已经登录
     */
    public static void put(String token, LoginUser loginUser) {
        if (StringUtils.isNotBlank(token)) {
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
        if (StringUtils.isNotBlank(token)) {
            USER_SESSION.invalidate(token);
        } else {

            throw TOKEN_NOT_NULL;
        }
    }


    public static LoginUser get(String token) {
        try {
            if (StringUtils.isNotBlank(token)) {
                return USER_SESSION.get(token);
            }
            throw TOKEN_NOT_NULL;
        } catch (Exception e) {
            return null;
        }

    }


    /**
     * 检测用户会话是否存在
     *
     * @param token 令牌
     * @return 是否存在
     */
    public static boolean exist(String token) throws SerException {

        try {
            if (StringUtils.isNotBlank(token)) {
                return USER_SESSION.get(token) != null;
            }
            throw TOKEN_NOT_NULL;
        } catch (Exception e) {
            return false;
        }

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
    public static LoadingCache<String, LoginUser> sessions() {
        if (null != USER_SESSION && USER_SESSION.size() > 0) {
            return USER_SESSION;
        }
        return null;
    }

    public static boolean verify(String token) throws SerException {
        try {
            return USER_SESSION.get(token) != null;
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
    }

}
