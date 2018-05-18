package com.bjike.goddess.user.session.auth_code;

import com.bjike.goddess.common.api.exception.SerException;
import com.google.common.cache.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Author: [liguiqin]
 * @Date: [2017-04-12 09:46]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public final class AuthCodeSession {
    private static Logger logger = LoggerFactory.getLogger(AuthCodeSession.class);

    private AuthCodeSession() {
    }

    private static final RuntimeException ACCOUNT_NOT_NULL = new RuntimeException("account令牌不能为空");

    private static final LoadingCache<String, String> AUTH_CODE_SESSION = CacheBuilder.newBuilder()
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .maximumSize(1000)
            .removalListener(new RemovalListener<String, String>() {
                @Override
                public void onRemoval(RemovalNotification<String, String> notification) {
                    logger.info("remove:" + notification.getCause().name());
                }
            })
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    return null;
                }
            });

    /**
     * 新增验证码
     *
     * @param account 用户名,手机,昵称
     */
    public static void put(String account, String code) throws SerException {
        if (StringUtils.isNotBlank(account)) {
            AUTH_CODE_SESSION.put(account, code);
        } else {
            throw ACCOUNT_NOT_NULL;
        }
    }

    /**
     * 根据令牌删除验证码会话信息
     *
     * @param account 用户名,手机,昵称
     */
    public static void remove(String account) {
        if (StringUtils.isNotBlank(account)) {
            AUTH_CODE_SESSION.invalidate(account);
        } else {

            throw ACCOUNT_NOT_NULL;
        }
    }


    public static String get(String account){
        try {
            if (StringUtils.isNotBlank(account)) {
                return AUTH_CODE_SESSION.get(account);
            }
        } catch (Exception e) {
            return null;
//            e.printStackTrace();
        }

        throw ACCOUNT_NOT_NULL;
    }


}
