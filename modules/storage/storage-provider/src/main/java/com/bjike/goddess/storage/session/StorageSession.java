package com.bjike.goddess.storage.session;

import com.bjike.goddess.common.api.exception.SerException;
import com.google.common.cache.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Author: [liguiqin]
 * @Date: [2017-04-14 17:24]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class StorageSession {
    private static final RuntimeException TOKEN_NOT_NULL = new RuntimeException("token令牌不能为空");
    private static Logger logger = LoggerFactory.getLogger(StorageSession.class);

    private static final LoadingCache<String, LoginUser> STORAGE_SESSION = CacheBuilder.newBuilder()
            .expireAfterWrite(30, TimeUnit.DAYS)
            .maximumSize(1000)
            .removalListener(new RemovalListener<String, LoginUser>() {
                @Override
                public void onRemoval(RemovalNotification<String, LoginUser> notification) {
                    logger.info("remove:" + notification.getKey());

                }
            })
            .build(new CacheLoader<String, LoginUser>() {
                @Override
                public LoginUser load(String key) throws Exception {
                    return null;
                }
            });


    public static void put(String token, LoginUser loginUser) throws SerException {
        if (StringUtils.isNotBlank(token)) {
            STORAGE_SESSION.put(token, loginUser);
        } else {
            throw TOKEN_NOT_NULL;
        }
    }

    /**
     * 根据令牌删除验证码会话信息
     *
     * @param account 用户名,手机,昵称
     */
    public static void remove(String account) {
        if (StringUtils.isNotBlank(account)) {
            STORAGE_SESSION.invalidate(account);
        } else {

            throw TOKEN_NOT_NULL;
        }
    }


    public static LoginUser get(String account) {
        try {
            if (StringUtils.isNotBlank(account)) {
                return STORAGE_SESSION.get(account);
            }
        } catch (Exception e) {
            return null;
        }

        throw TOKEN_NOT_NULL;
    }

}
