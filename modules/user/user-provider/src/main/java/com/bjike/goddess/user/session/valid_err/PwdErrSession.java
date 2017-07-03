package com.bjike.goddess.user.session.valid_err;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.session.auth_code.AuthCodeSession;
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
public final class PwdErrSession {

    private PwdErrSession() {
    }

    private static final RuntimeException ACCOUNT_NOT_NULL = new RuntimeException("account令牌不能为空");
    private static Logger logger = LoggerFactory.getLogger(AuthCodeSession.class);

    private static final LoadingCache<String, Integer> PWD_ERR_SESSION = CacheBuilder.newBuilder()
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .maximumSize(1000)
            .removalListener(new RemovalListener<String, Integer>() {
                @Override
                public void onRemoval(RemovalNotification<String, Integer> notification) {
                    logger.info("remove:" + notification.getKey());
                }
            })
            .build(new CacheLoader<String, Integer>() {
                @Override
                public Integer load(String key) throws Exception {
                    return null;
                }
            });


    /**
     * 新增密码错误会话信息
     *
     * @param account 用户名,手机,昵称
     */
    public static void put(String account) throws SerException {
        if (StringUtils.isNotBlank(account)) {
            Integer count = get(account);
            if (null != count) {
                PWD_ERR_SESSION.put(account, count + 1);
            } else {
                PWD_ERR_SESSION.put(account, 1 + 1);
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
        if (StringUtils.isNotBlank(account)) {
            PWD_ERR_SESSION.invalidate(account);
        } else {

            throw ACCOUNT_NOT_NULL;
        }
    }


    public static Integer get(String account) {
        try {
            if (StringUtils.isNotBlank(account)) {
                return PWD_ERR_SESSION.get(account);
            }
        } catch (Exception e) {
            return 0;
        }

        throw ACCOUNT_NOT_NULL;
    }

}
